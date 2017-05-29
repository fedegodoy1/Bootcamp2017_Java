
package pattern.proxy;

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
import pattern.adapter.AdapterNameAndDateToName;
import pattern.transformer.WeatherTransformer;
import service.ClientYahooWeather;

/**
 *
 * @author federico
 */
public class ProxyWeather implements ClientYahooWeather{
    @Autowired
    private WeatherTransformer wt;
    @Autowired
    private AdapterNameAndDateToName a;
    @Autowired
    private Validations v;
    @Resource(name="clientYahooWeather")
    private ClientYahooWeather clientYahooWeather;
    
    @Override
    public String getForecast(String query, String format){
        boolean ok = true;
        Day d = null;
        if(ok){
            try {
                ObjectMapper map = new ObjectMapper();
                JsonNode j = map.readTree(clientYahooWeather.getForecast(query,format));
                int id = j.get("query").get("results").get("place").get("woeid").asInt();
                String yqlForecast="select * from weather.forecast where woeid = "+id;
                j = map.readTree(clientYahooWeather.getForecast(yqlForecast,format));
                
                AtmosphereYahoo ay = new AtmosphereYahoo(j);
                LocationYahoo ly = new LocationYahoo(j);
                TemperatureYahoo ty = new TemperatureYahoo(j);
                WindYahoo wy = new WindYahoo(j);
                DayYahoo dy = new DayYahoo(j,ay,ly,ty,wy);
                
                d = wt.transformDayYahooToDay(dy);
                
            } catch (IOException ex) {
                Logger.getLogger(ProxyWeather.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            
        }
        return d.toString();
    }
}
