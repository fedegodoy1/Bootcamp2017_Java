package pattern.transformer;

import domain.Atmosphere;
import domain.Day;
import domain.Location;
import domain.Temperature;
import domain.Wind;
import domain.yahoo.AtmosphereYahoo;
import domain.yahoo.DayYahoo;
import domain.yahoo.LocationYahoo;
import domain.yahoo.TemperatureYahoo;
import domain.yahoo.WindYahoo;
import pattern.builder.AtmosphereBuilder;
import pattern.builder.DayBuilder;
import pattern.builder.LocationBuilder;
import pattern.builder.TemperatureBuilder;
import pattern.builder.WindBuilder;

/**
 *
 * @author federico
 */
public class WeatherTransformer {
    public static Atmosphere transformAtmosphereYahooToAtmosphere(AtmosphereYahoo aYahoo){
        Atmosphere a = new AtmosphereBuilder().withHumidity(aYahoo.getHumidity()).withPressure(aYahoo.getPressure()).withVisibility(aYahoo.getVisibility()).build();
        return a;
    }
    
    public static Location transformLocationYahooToLocation(LocationYahoo lYahoo){
        Location l = new LocationBuilder().withCity(lYahoo.getCity()).withCountry(lYahoo.getCountry()).withRegion(lYahoo.getRegion()).build();
        return l;
    }
    
    public static Wind transformWindYahooToWind(WindYahoo wYahoo){
        Wind w = new WindBuilder().withDirection(wYahoo.getDirection()).withSpeed(wYahoo.getSpeed()).build();
        return w;
    }
    
    public static Temperature transformTemperatureYahooToTemperature(TemperatureYahoo tYahoo){
        Temperature t = new TemperatureBuilder().withCTemp(tYahoo.getCurrentTemp()).withHTemp(tYahoo.getHighTemp()).withLTemp(tYahoo.getLowTemp()).build();
        return t;
    }
    
    public static Day transformDayYahooToDay(DayYahoo dYahoo){
        
        Atmosphere a = transformAtmosphereYahooToAtmosphere(dYahoo.getAtmosphere());
        Location l = transformLocationYahooToLocation(dYahoo.getLocation());
        Wind w = transformWindYahooToWind(dYahoo.getWind());
        Temperature t = transformTemperatureYahooToTemperature(dYahoo.getTemperature());
        Day d = new DayBuilder().withName(dYahoo.getName()).withDate(dYahoo.getDate()).withDescription(dYahoo.getDescription()).withAtmosphere(a).witLocation(l).withTemperature(t).withWind(w).build();
        return d;
    }   
}
