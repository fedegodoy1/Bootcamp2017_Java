
package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Day;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author federico
 */
@Component
public class AdapterYahooResponseToString implements YahooObject{
    @Autowired
    ClientYahooWeather proxy;
    @Override
    public Day requestForecastCurrentDay(String location, String country){
        Day d = null;
        ObjectMapper map = new ObjectMapper();
        try {
            String yqlId = "select woeid from geo.places(1) where text=\" " + location + ", " + country + "\"";
            String response = proxy.getForecast(yqlId, "json");
            if(response == "Error"){
                return d=null;
            }
            d = map.readValue(response, Day.class);
        } catch (IOException ex) {
            Logger.getLogger(AdapterYahooResponseToString.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }
}
