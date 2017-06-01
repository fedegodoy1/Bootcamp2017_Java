package pattern.proxy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Day;
import domain.yahoo.AtmosphereYahoo;
import domain.yahoo.DayYahoo;
import domain.yahoo.LocationYahoo;
import domain.yahoo.TemperatureYahoo;
import domain.yahoo.WindYahoo;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pattern.adapter.AdapterMilesToKilometers;
import pattern.transformer.WeatherTransformer;
import service.ClientYahooWeather;
import service.ServiceWeather;

/**
 *
 * @author federico
 */
@Component
public class ProxyWeather implements ClientYahooWeather {

    @Resource(name = "clientYahooWeather")
    private ClientYahooWeather clientYahooWeather;
    @Autowired
    ServiceWeather service;

    //APLICAR ADAPTER KILOMETERS Y CELSIUS
    @Override
    public String getForecast(String location, String country) {
        ObjectMapper map = new ObjectMapper();
        String jsonInString = "";
        try {
            String yqlId = "select woeid from geo.places(1) where text=\" " + location + ", " + country + "\"";
            JsonNode j = map.readTree(clientYahooWeather.getForecast(yqlId, "json"));
            if (Validations.checkResponse(j) != true) {
                return "There are not response for location:" + location + " and country: " + country;
            } else {
                int id = j.get("query").get("results").get("place").get("woeid").asInt();
                String yqlForecast = "select * from weather.forecast where woeid = " + id;
                j = map.readTree(clientYahooWeather.getForecast(yqlForecast, "json"));
                jsonInString = j.toString();
            }
        } catch (IOException ex) {
            Logger.getLogger(ProxyWeather.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jsonInString;
    }
}
