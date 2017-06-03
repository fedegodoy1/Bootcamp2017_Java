/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import pattern.builder.AtmosphereBuilder;
import pattern.builder.DayBuilder;
import pattern.builder.LocationBuilder;
import pattern.builder.TemperatureBuilder;
import pattern.builder.WindBuilder;
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
import domain.Atmosphere;
import domain.Day;
import domain.Location;
import domain.Temperature;
import domain.Wind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author federico
 */
@RestController()
@RequestMapping("WeatherApp")
public class WeatherController {
    @Autowired
    ServiceWeather service;//deberia ir interfaz
    
    public WeatherController(ServiceWeather service){
        this.service = service;
    }
    
    @RequestMapping(value = "/forecast/currentday/{country}/{location}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity forecastCurrentDay(@PathVariable("country") String country, @PathVariable("location") String location) {
        Day d = null;
        try {
            d= service.requestForecastCurrentDay(location,country);
            if(d == null){
                return new ResponseEntity<>("You are not connected - In data base there are not forecast for "+location,HttpStatus.NOT_FOUND);//.serverError().entity("You are not connected - In data base there are not forecast for "+location).build();
            }
            else{
                if(d.getName().equals("Location and country incorrects")){ 
                    return new ResponseEntity<>("There are not response for location: "+location+" and country:"+country,HttpStatus.NOT_FOUND);//Response.serverError().entity("There are not response for location: "+location+" and country:"+country).build();
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(WeatherController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ResponseEntity<Day>(d,HttpStatus.OK);
    }

    @RequestMapping(value = "/forecast/allextended/{country}/{location}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity allDays(@PathVariable("country") String country, @PathVariable("location") String location) {
        ArrayList<Day> days = new ArrayList<Day>();
        days = service.requestAllDays(country,location);
        if(days.isEmpty()){
            return new ResponseEntity<>("You are not connected - In data base there are not forecast for "+location,HttpStatus.NOT_FOUND);
        }
        else{
            if(days.get(0).getName().equals("Location and country incorrects")){
                return new ResponseEntity<>("There are not response for location: "+location+" and country:"+country,HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(days,HttpStatus.OK);
    }

    @RequestMapping(value = "/insertday", method = RequestMethod.POST)
    public ArrayList<Day> addDay(@RequestBody Day d) {
        ArrayList<Day> r = service.requestAddDay(d);
        return r;
    }

    @RequestMapping(value = "/updateday", method = RequestMethod.PUT)
    public ArrayList<Day> updateDay(@RequestBody Day d) {
        ArrayList<Day> r = service.requestUpdateDay(d);
        return r;
    }

}
