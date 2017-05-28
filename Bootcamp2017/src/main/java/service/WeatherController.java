/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import builder.AtmosphereBuilder;
import builder.DayBuilder;
import builder.LocationBuilder;
import builder.TemperatureBuilder;
import builder.WindBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.AtmosphereDAO;
import dao.BaseWeatherDAO;
import dao.DayDAO;
import dao.LocationDAO;
import dao.TemperatureDAO;
import dao.WindDAO;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import main.Atmosphere;
import main.Day;
import main.Location;
import main.Temperature;
import main.Wind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author federico
 */
@RestController("MyApp")
public class WeatherController {

    @Autowired
    DayDAO dayDao;
    @Autowired
    AtmosphereDAO atDAO;
    @Autowired
    LocationDAO locDAO;
    @Autowired
    TemperatureDAO tempDAO;
    @Autowired
    WindDAO windDAO;
    
    @Resource(name="clientYahooWeather")
    ClientYahooWeather clientYahooWeather;
    
    @RequestMapping(value="/forecast/currentday/{country}/{location}", method=RequestMethod.GET)
    public ArrayList<Day> forecastCurrentDay(@PathVariable("country")String country, @PathVariable("location")String location){
        ArrayList<Day> days = new ArrayList();
        
        String yqlId="select woeid from geo.places(1) where text=\" "+location+", "+country+"\"";
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode j = mapper.readTree(clientYahooWeather.getForecastDayByLocation(yqlId,"json"));
            int id = j.get("query").get("results").get("place").get("woeid").asInt();
            String yqlForecast="select * from weather.forecast where woeid = "+id;
            j = mapper.readTree(clientYahooWeather.getForecastDayByLocation(yqlForecast,"json"));
            
            //creates an entity atmosphere humidity,pressure,visibility
            double humidity = j.get("query").get("results").get("channel").get("atmosphere").get("humidity").asDouble();
            double pressure = j.get("query").get("results").get("channel").get("atmosphere").get("pressure").asDouble();
            double visibility = j.get("query").get("results").get("channel").get("atmosphere").get("visibility").asDouble();
            Atmosphere a = new AtmosphereBuilder().withHumidity((float)humidity).withPressure((float)pressure).withVisibility((float)visibility).build();
            
            //creates an entity location, city, country, region
            String region = j.get("query").get("results").get("channel").get("location").get("region").asText();
            Location l = new LocationBuilder().withCity(location).withCountry(country).withRegion(region).build();
            
            //creates an entity temperature cTemp, lowTemp,highTemp
            double cTemp = j.get("query").get("results").get("channel").get("item").get("condition").get("temp").asDouble();
            double lowTemp = j.get("query").get("results").get("channel").get("item").get("forecast").get(1).get("high").asDouble();
            double highTemp = j.get("query").get("results").get("channel").get("item").get("forecast").get(1).get("low").asDouble();
            Temperature t = new TemperatureBuilder().withCTemp((float)cTemp).withHTemp((float)highTemp).withLTemp((float)lowTemp).build();
            
            //creates an entity wind direction(string), speed
            double spd = j.get("query").get("results").get("channel").get("wind").get("speed").asDouble();
            String direction = j.get("query").get("results").get("channel").get("wind").get("direction").asText();
            Wind w = new WindBuilder().withDirection(direction).withSpeed((float)spd).build();
            
            //creates an entity day, name, description, date
            String name = j.get("query").get("results").get("channel").get("item").get("pubDate").asText();
            String date = j.get("query").get("results").get("channel").get("item").get("condition").get("date").asText();
            String description = j.get("query").get("results").get("channel").get("item").get("condition").get("text").asText();
            Day d = new DayBuilder().withAtmosphere(a).withTemperature(t).withWind(w).witLocation(l).withDate(date).withName(name).withDescription(description).build();
            days.add(d);
            
        } catch (IOException ex) {
            Logger.getLogger(WeatherController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return days;
    }
    
    @RequestMapping(value = "/days/", method = RequestMethod.GET)
    public ArrayList<Day> allDays() {
        ArrayList<Day> days = new ArrayList<Day>();
        Day d = null;
        int cantDias = dayDao.count();
        for (int i = 0; i < cantDias; i++) {
            d = dayDao.select(i);
            days.add(d);
        }
        return days;
    }

    @RequestMapping(value = "/day/{idday}", method = RequestMethod.GET)
    public ArrayList<Day> day(@PathVariable("idday") String idday) {
        ArrayList<Day> name = new ArrayList<Day>();
        Day d = dayDao.select(Integer.parseInt(idday));
        name.add(d);
        return name;
    }
    
    @RequestMapping(value="/insertday", method = RequestMethod.POST)
    public ArrayList<Day> addDay(@RequestBody Day d){
        ArrayList<Day> r = new ArrayList<Day>();
        atDAO.insert(d.getAtmosphere());
        tempDAO.insert(d.getTemp());
        locDAO.insert(d.getLocation());
        windDAO.insert(d.getWind());
        dayDao.insert(d);
        r.add(d);
        return r;
    }
    
    @RequestMapping(value="/updateday", method = RequestMethod.PUT)
    public ArrayList<Day> updateDay(@RequestBody Day d){
        ArrayList<Day> r = new ArrayList<Day>();
        int vec[] = dayDao.idsComponentsDay(d.getDate()); //0:idLoc 1:idAt 2:idTemp 3:idWind
        locDAO.update(vec[0], d.getLocation());
        atDAO.update(vec[1], d.getAtmosphere());
        tempDAO.update(vec[2], d.getTemp());
        windDAO.update(vec[3], d.getWind());
        dayDao.update(dayDao.selectByDateId(d.getDate()), d);
        r.add(d);
        return r;
    }
    
}
