package service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Day;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pattern.transformer.WeatherTransformer;

/**
 *
 * @author federico
 */
@Component
public class AdapterYahooResponseToDay implements YahooObject {

    @Autowired
    ClientYahooWeather proxy;
    @Autowired
    WeatherTransformer weatherTransformer;
    

    public void setProxy(ClientYahooWeather p) {
        this.proxy = p;
    }

    @Override
    public Day requestForecastCurrentDay(String location, String country) {
        Day d = new Day();
        ObjectMapper map = new ObjectMapper();
        try {
            String response = proxy.getForecast(location, country);
            //APLICAR ADAPTER KILOMETERS Y CELSIUS
            if (response.equals("There are not response for location:" + location + " and country: " + country)) {
                d.setName("Location and country incorrects");
                return d;
            } 
            else {
                JsonNode j = map.readTree(response);
                d = weatherTransformer.transformJsonToDay(j);//transformer
                
            }
            //d = map.readValue(response, Day.class);
        } catch (IOException ex) {
            Logger.getLogger(AdapterYahooResponseToDay.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }

    @Override
    public ArrayList<Day> requestForecastAllExtended(String location, String country) {
        ArrayList<Day> list = new ArrayList();
        Day d = new Day();
        ObjectMapper map = new ObjectMapper();
        try {
            String response = proxy.getForecast(location, country);
            if (response.equals("There are not response for location:" + location + " and country: " + country)) {
                d.setName("Location and country incorrects");
                
                
                list.add(d);
                return list;
            } else {
                JsonNode j = map.readTree(response);
                for (int i = 0; i < 10; i++) {
                    if (i == 0) {
                        d = weatherTransformer.transformJsonToDay(j);
                        list.add(d);
                    } 
                    else {
                        d = weatherTransformer.transformJsonToDayExtended(j,i);
                        list.add(d);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(AdapterYahooResponseToDay.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

}

