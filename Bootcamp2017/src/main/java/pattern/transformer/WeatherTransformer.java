package pattern.transformer;

import com.fasterxml.jackson.databind.JsonNode;
import domain.Atmosphere;
import domain.Day;
import domain.Location;
import domain.Temperature;
import domain.Wind;
import org.springframework.stereotype.Component;
import pattern.builder.AtmosphereBuilder;
import pattern.builder.DayBuilder;
import pattern.builder.LocationBuilder;
import pattern.builder.TemperatureBuilder;
import pattern.builder.WindBuilder;

/**
 *
 * @author federico
 */
@Component
public class WeatherTransformer {
    public static Atmosphere transformJsonToAtmosphere(JsonNode j){
        Atmosphere a = new AtmosphereBuilder().
                withHumidity((float)j.get("query").get("results").get("channel").get("atmosphere").get("humidity").asDouble()).
                withPressure((float)j.get("query").get("results").get("channel").get("atmosphere").get("pressure").asDouble()).
                withVisibility((float)j.get("query").get("results").get("channel").get("atmosphere").get("visibility").asDouble()).
                build();
        return a;
    }
    
    public static Location transformJsonToLocation(JsonNode j){
        Location l = new LocationBuilder().
                withCity(j.get("query").get("results").get("channel").get("location").get("city").asText()).
                withCountry( j.get("query").get("results").get("channel").get("location").get("country").asText()).
                withRegion(j.get("query").get("results").get("channel").get("location").get("region").asText()).
                build();
        return l;
    }
    
    public static Wind transformJsonToWind(JsonNode j){
        Wind w = new WindBuilder().
                withDirection(j.get("query").get("results").get("channel").get("wind").get("direction").asText()).
                withSpeed((float)j.get("query").get("results").get("channel").get("wind").get("speed").asDouble()).
                build();
        return w;
    }
    
    public static Temperature transformJsonToTemperature(JsonNode j){
        Temperature t = new TemperatureBuilder().
                withCTemp((float)j.get("query").get("results").get("channel").get("item").get("condition").get("temp").asDouble()).
                withHTemp((float)j.get("query").get("results").get("channel").get("item").get("forecast").get(0).get("high").asDouble()).
                withLTemp((float)j.get("query").get("results").get("channel").get("item").get("forecast").get(0).get("low").asDouble()).
                build();
        return t;
    }
    
    public static Day transformJsonToDay(JsonNode j){
        
        Atmosphere a = transformJsonToAtmosphere(j);
        Location l = transformJsonToLocation(j);
        Wind w = transformJsonToWind(j);
        Temperature t = transformJsonToTemperature(j);
        Day d = new DayBuilder().
                withName(j.get("query").get("results").get("channel").get("item").get("forecast").get(0).get("day").asText()).
                withDate(j.get("query").get("results").get("channel").get("item").get("forecast").get(0).get("date").asText()).
                withDescription(j.get("query").get("results").get("channel").get("item").get("forecast").get(0).get("text").asText()).
                withAtmosphere(a).
                witLocation(l).
                withTemperature(t).
                withWind(w).
                build();
        return d;
    }   
    
    public static Temperature transformJsonToTemperatureOfDayExtended(JsonNode j, int i){
        Temperature t = new TemperatureBuilder().
                withCTemp(0).
                withHTemp((float)j.get("query").get("results").get("channel").get("item").get("forecast").get(i).get("high").asDouble()).
                withLTemp((float)j.get("query").get("results").get("channel").get("item").get("forecast").get(i).get("low").asDouble()).
                build();
        return t;
    }   
    
    public static Day transformJsonToDayExtended(JsonNode j,int i){
        Temperature t = transformJsonToTemperatureOfDayExtended(j,i);
        Atmosphere a = new Atmosphere();
        Location l = transformJsonToLocation(j);
        Wind w = new Wind();
        Day d = new DayBuilder().
                withName(j.get("query").get("results").get("channel").get("item").get("forecast").get(i).get("day").asText()).
                withDate(j.get("query").get("results").get("channel").get("item").get("forecast").get(i).get("date").asText()).
                withDescription(j.get("query").get("results").get("channel").get("item").get("forecast").get(i).get("text").asText()).
                withTemperature(t).
                witLocation(l).
                withWind(w).
                withAtmosphere(a).
                build();
        return d;
    }
}
