
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
public class ProxyWeather implements ClientYahooWeather{
    
    @Resource(name="clientYahooWeather")
    private ClientYahooWeather clientYahooWeather;
    @Autowired
    ServiceWeather service;
    
    @Override
    public String getForecast(String query, String format){
        boolean ok = Validations.checkInet();
        ObjectMapper map = new ObjectMapper();
        Day d = null;
        String jsonInString="";
        if(ok){
            try {
                JsonNode j = map.readTree(clientYahooWeather.getForecast(query,format));
                int id = j.get("query").get("results").get("place").get("woeid").asInt();
                String yqlForecast="select * from weather.forecast where woeid = "+id;
                j = map.readTree(clientYahooWeather.getForecast(yqlForecast,format));

                d = WeatherTransformer.transformDayYahooToDay(j);
                service.insertDay(d);
                jsonInString = map.writeValueAsString(d);
            } catch (IOException ex) {
                Logger.getLogger(ProxyWeather.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            //Search for the last update in database
            d = service.searchLastUpdate();
            try {
                jsonInString = map.writeValueAsString(d);
            } catch (JsonProcessingException ex) {
                Logger.getLogger(ProxyWeather.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(d==null){
                return "Error";
            }
        }
        return jsonInString;
    }
}
