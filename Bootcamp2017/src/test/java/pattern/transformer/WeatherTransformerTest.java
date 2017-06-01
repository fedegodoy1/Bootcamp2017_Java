/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pattern.transformer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author federico
 */
public class WeatherTransformerTest {
    
    public WeatherTransformerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of transformAtmosphereYahooToAtmosphere method, of class WeatherTransformer.
     */
    @Test
    public void testTransformJsonToAtmosphere() {
        ObjectMapper map = new ObjectMapper();
        JsonNode j = null;
        try {
            j = map.readTree("{\"query\":{\"count\":1,\"created\":\"2017-05-31T15:54:23Z\",\"lang\":\"es-ES\",\"results\":{\"channel\":{\"units\":{\"distance\":\"mi\",\"pressure\":\"in\",\"speed\":\"mph\",\"temperature\":\"F\"},\"title\":\"Yahoo! Weather - Nome, AK, US\",\"link\":\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-2460286/\",\"description\":\"Yahoo! Weather for Nome, AK, US\",\"language\":\"en-us\",\"lastBuildDate\":\"Wed, 31 May 2017 07:54 AM AKDT\",\"ttl\":\"60\",\"location\":{\"city\":\"Nome\",\"country\":\"United States\",\"region\":\" AK\"},\"wind\":{\"chill\":\"30\",\"direction\":\"45\",\"speed\":\"14\"},\"atmosphere\":{\"humidity\":\"85\",\"pressure\":\"1014.0\",\"rising\":\"0\",\"visibility\":\"16.1\"},\"astronomy\":{\"sunrise\":\"1:8 am\",\"sunset\":\"4:52 am\"},\"image\":{\"title\":\"Yahoo! Weather\",\"width\":\"142\",\"height\":\"18\",\"link\":\"http://weather.yahoo.com\",\"url\":\"http://l.yimg.com/a/i/brand/purplelogo//uh/us/news-wea.gif\"},\"item\":{\"title\":\"Conditions for Nome, AK, US at 07:00 AM AKDT\",\"lat\":\"64.499474\",\"long\":\"-165.405792\",\"link\":\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-2460286/\",\"pubDate\":\"Wed, 31 May 2017 07:00 AM AKDT\",\"condition\":{\"code\":\"33\",\"date\":\"Wed, 31 May 2017 07:00 AM AKDT\",\"temp\":\"37\",\"text\":\"Mostly Clear\"},\"forecast\":[{\"code\":\"34\",\"date\":\"31 May 2017\",\"day\":\"Wed\",\"high\":\"47\",\"low\":\"36\",\"text\":\"Mostly Sunny\"},{\"code\":\"34\",\"date\":\"01 Jun 2017\",\"day\":\"Thu\",\"high\":\"53\",\"low\":\"37\",\"text\":\"Mostly Sunny\"},{\"code\":\"32\",\"date\":\"02 Jun 2017\",\"day\":\"Fri\",\"high\":\"54\",\"low\":\"39\",\"text\":\"Sunny\"},{\"code\":\"30\",\"date\":\"03 Jun 2017\",\"day\":\"Sat\",\"high\":\"63\",\"low\":\"43\",\"text\":\"Partly Cloudy\"},{\"code\":\"30\",\"date\":\"04 Jun 2017\",\"day\":\"Sun\",\"high\":\"59\",\"low\":\"48\",\"text\":\"Partly Cloudy\"},{\"code\":\"30\",\"date\":\"05 Jun 2017\",\"day\":\"Mon\",\"high\":\"59\",\"low\":\"51\",\"text\":\"Partly Cloudy\"},{\"code\":\"34\",\"date\":\"06 Jun 2017\",\"day\":\"Tue\",\"high\":\"57\",\"low\":\"48\",\"text\":\"Mostly Sunny\"},{\"code\":\"32\",\"date\":\"07 Jun 2017\",\"day\":\"Wed\",\"high\":\"64\",\"low\":\"46\",\"text\":\"Sunny\"},{\"code\":\"34\",\"date\":\"08 Jun 2017\",\"day\":\"Thu\",\"high\":\"65\",\"low\":\"52\",\"text\":\"Mostly Sunny\"},{\"code\":\"32\",\"date\":\"09 Jun 2017\",\"day\":\"Fri\",\"high\":\"56\",\"low\":\"50\",\"text\":\"Sunny\"}],\"description\":\"<![CDATA[<img src=\\\"http://l.yimg.com/a/i/us/we/52/33.gif\\\"/>\\n<BR />\\n<b>Current Conditions:</b>\\n<BR />Mostly Clear\\n<BR />\\n<BR />\\n<b>Forecast:</b>\\n<BR /> Wed - Mostly Sunny. High: 47Low: 36\\n<BR /> Thu - Mostly Sunny. High: 53Low: 37\\n<BR /> Fri - Sunny. High: 54Low: 39\\n<BR /> Sat - Partly Cloudy. High: 63Low: 43\\n<BR /> Sun - Partly Cloudy. High: 59Low: 48\\n<BR />\\n<BR />\\n<a href=\\\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-2460286/\\\">Full Forecast at Yahoo! Weather</a>\\n<BR />\\n<BR />\\n(provided by <a href=\\\"http://www.weather.com\\\" >The Weather Channel</a>)\\n<BR />\\n]]>\",\"guid\":{\"isPermaLink\":\"false\"}}}}}}");
                    } catch (IOException ex) {
            Logger.getLogger(WeatherTransformerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Atmosphere a = WeatherTransformer.transformJsonToAtmosphere(j);
        assertEquals(85, a.getHumidity(),1);
    }

    /**
     * Test of transformLocationYahooToLocation method, of class WeatherTransformer.
     */
    @Test
    public void testTransformJsonToLocation() {
        ObjectMapper map = new ObjectMapper();
        JsonNode j = null;
        try {
            j = map.readTree("{\"query\":{\"count\":1,\"created\":\"2017-05-31T15:54:23Z\",\"lang\":\"es-ES\",\"results\":{\"channel\":{\"units\":{\"distance\":\"mi\",\"pressure\":\"in\",\"speed\":\"mph\",\"temperature\":\"F\"},\"title\":\"Yahoo! Weather - Nome, AK, US\",\"link\":\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-2460286/\",\"description\":\"Yahoo! Weather for Nome, AK, US\",\"language\":\"en-us\",\"lastBuildDate\":\"Wed, 31 May 2017 07:54 AM AKDT\",\"ttl\":\"60\",\"location\":{\"city\":\"Nome\",\"country\":\"United States\",\"region\":\" AK\"},\"wind\":{\"chill\":\"30\",\"direction\":\"45\",\"speed\":\"14\"},\"atmosphere\":{\"humidity\":\"85\",\"pressure\":\"1014.0\",\"rising\":\"0\",\"visibility\":\"16.1\"},\"astronomy\":{\"sunrise\":\"1:8 am\",\"sunset\":\"4:52 am\"},\"image\":{\"title\":\"Yahoo! Weather\",\"width\":\"142\",\"height\":\"18\",\"link\":\"http://weather.yahoo.com\",\"url\":\"http://l.yimg.com/a/i/brand/purplelogo//uh/us/news-wea.gif\"},\"item\":{\"title\":\"Conditions for Nome, AK, US at 07:00 AM AKDT\",\"lat\":\"64.499474\",\"long\":\"-165.405792\",\"link\":\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-2460286/\",\"pubDate\":\"Wed, 31 May 2017 07:00 AM AKDT\",\"condition\":{\"code\":\"33\",\"date\":\"Wed, 31 May 2017 07:00 AM AKDT\",\"temp\":\"37\",\"text\":\"Mostly Clear\"},\"forecast\":[{\"code\":\"34\",\"date\":\"31 May 2017\",\"day\":\"Wed\",\"high\":\"47\",\"low\":\"36\",\"text\":\"Mostly Sunny\"},{\"code\":\"34\",\"date\":\"01 Jun 2017\",\"day\":\"Thu\",\"high\":\"53\",\"low\":\"37\",\"text\":\"Mostly Sunny\"},{\"code\":\"32\",\"date\":\"02 Jun 2017\",\"day\":\"Fri\",\"high\":\"54\",\"low\":\"39\",\"text\":\"Sunny\"},{\"code\":\"30\",\"date\":\"03 Jun 2017\",\"day\":\"Sat\",\"high\":\"63\",\"low\":\"43\",\"text\":\"Partly Cloudy\"},{\"code\":\"30\",\"date\":\"04 Jun 2017\",\"day\":\"Sun\",\"high\":\"59\",\"low\":\"48\",\"text\":\"Partly Cloudy\"},{\"code\":\"30\",\"date\":\"05 Jun 2017\",\"day\":\"Mon\",\"high\":\"59\",\"low\":\"51\",\"text\":\"Partly Cloudy\"},{\"code\":\"34\",\"date\":\"06 Jun 2017\",\"day\":\"Tue\",\"high\":\"57\",\"low\":\"48\",\"text\":\"Mostly Sunny\"},{\"code\":\"32\",\"date\":\"07 Jun 2017\",\"day\":\"Wed\",\"high\":\"64\",\"low\":\"46\",\"text\":\"Sunny\"},{\"code\":\"34\",\"date\":\"08 Jun 2017\",\"day\":\"Thu\",\"high\":\"65\",\"low\":\"52\",\"text\":\"Mostly Sunny\"},{\"code\":\"32\",\"date\":\"09 Jun 2017\",\"day\":\"Fri\",\"high\":\"56\",\"low\":\"50\",\"text\":\"Sunny\"}],\"description\":\"<![CDATA[<img src=\\\"http://l.yimg.com/a/i/us/we/52/33.gif\\\"/>\\n<BR />\\n<b>Current Conditions:</b>\\n<BR />Mostly Clear\\n<BR />\\n<BR />\\n<b>Forecast:</b>\\n<BR /> Wed - Mostly Sunny. High: 47Low: 36\\n<BR /> Thu - Mostly Sunny. High: 53Low: 37\\n<BR /> Fri - Sunny. High: 54Low: 39\\n<BR /> Sat - Partly Cloudy. High: 63Low: 43\\n<BR /> Sun - Partly Cloudy. High: 59Low: 48\\n<BR />\\n<BR />\\n<a href=\\\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-2460286/\\\">Full Forecast at Yahoo! Weather</a>\\n<BR />\\n<BR />\\n(provided by <a href=\\\"http://www.weather.com\\\" >The Weather Channel</a>)\\n<BR />\\n]]>\",\"guid\":{\"isPermaLink\":\"false\"}}}}}}");
                    } 
        catch (IOException ex) {
            Logger.getLogger(WeatherTransformerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Location l = WeatherTransformer.transformJsonToLocation(j);
        assertEquals("Nome", l.getCity());
    }

    /**
     * Test of transformWindYahooToWind method, of class WeatherTransformer.
     */
    @Test
    public void testTransformJsonToWind() {
        ObjectMapper map = new ObjectMapper();
        JsonNode j = null;
        try {
            j = map.readTree("{\"query\":{\"count\":1,\"created\":\"2017-05-31T15:54:23Z\",\"lang\":\"es-ES\",\"results\":{\"channel\":{\"units\":{\"distance\":\"mi\",\"pressure\":\"in\",\"speed\":\"mph\",\"temperature\":\"F\"},\"title\":\"Yahoo! Weather - Nome, AK, US\",\"link\":\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-2460286/\",\"description\":\"Yahoo! Weather for Nome, AK, US\",\"language\":\"en-us\",\"lastBuildDate\":\"Wed, 31 May 2017 07:54 AM AKDT\",\"ttl\":\"60\",\"location\":{\"city\":\"Nome\",\"country\":\"United States\",\"region\":\" AK\"},\"wind\":{\"chill\":\"30\",\"direction\":\"45\",\"speed\":\"14\"},\"atmosphere\":{\"humidity\":\"85\",\"pressure\":\"1014.0\",\"rising\":\"0\",\"visibility\":\"16.1\"},\"astronomy\":{\"sunrise\":\"1:8 am\",\"sunset\":\"4:52 am\"},\"image\":{\"title\":\"Yahoo! Weather\",\"width\":\"142\",\"height\":\"18\",\"link\":\"http://weather.yahoo.com\",\"url\":\"http://l.yimg.com/a/i/brand/purplelogo//uh/us/news-wea.gif\"},\"item\":{\"title\":\"Conditions for Nome, AK, US at 07:00 AM AKDT\",\"lat\":\"64.499474\",\"long\":\"-165.405792\",\"link\":\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-2460286/\",\"pubDate\":\"Wed, 31 May 2017 07:00 AM AKDT\",\"condition\":{\"code\":\"33\",\"date\":\"Wed, 31 May 2017 07:00 AM AKDT\",\"temp\":\"37\",\"text\":\"Mostly Clear\"},\"forecast\":[{\"code\":\"34\",\"date\":\"31 May 2017\",\"day\":\"Wed\",\"high\":\"47\",\"low\":\"36\",\"text\":\"Mostly Sunny\"},{\"code\":\"34\",\"date\":\"01 Jun 2017\",\"day\":\"Thu\",\"high\":\"53\",\"low\":\"37\",\"text\":\"Mostly Sunny\"},{\"code\":\"32\",\"date\":\"02 Jun 2017\",\"day\":\"Fri\",\"high\":\"54\",\"low\":\"39\",\"text\":\"Sunny\"},{\"code\":\"30\",\"date\":\"03 Jun 2017\",\"day\":\"Sat\",\"high\":\"63\",\"low\":\"43\",\"text\":\"Partly Cloudy\"},{\"code\":\"30\",\"date\":\"04 Jun 2017\",\"day\":\"Sun\",\"high\":\"59\",\"low\":\"48\",\"text\":\"Partly Cloudy\"},{\"code\":\"30\",\"date\":\"05 Jun 2017\",\"day\":\"Mon\",\"high\":\"59\",\"low\":\"51\",\"text\":\"Partly Cloudy\"},{\"code\":\"34\",\"date\":\"06 Jun 2017\",\"day\":\"Tue\",\"high\":\"57\",\"low\":\"48\",\"text\":\"Mostly Sunny\"},{\"code\":\"32\",\"date\":\"07 Jun 2017\",\"day\":\"Wed\",\"high\":\"64\",\"low\":\"46\",\"text\":\"Sunny\"},{\"code\":\"34\",\"date\":\"08 Jun 2017\",\"day\":\"Thu\",\"high\":\"65\",\"low\":\"52\",\"text\":\"Mostly Sunny\"},{\"code\":\"32\",\"date\":\"09 Jun 2017\",\"day\":\"Fri\",\"high\":\"56\",\"low\":\"50\",\"text\":\"Sunny\"}],\"description\":\"<![CDATA[<img src=\\\"http://l.yimg.com/a/i/us/we/52/33.gif\\\"/>\\n<BR />\\n<b>Current Conditions:</b>\\n<BR />Mostly Clear\\n<BR />\\n<BR />\\n<b>Forecast:</b>\\n<BR /> Wed - Mostly Sunny. High: 47Low: 36\\n<BR /> Thu - Mostly Sunny. High: 53Low: 37\\n<BR /> Fri - Sunny. High: 54Low: 39\\n<BR /> Sat - Partly Cloudy. High: 63Low: 43\\n<BR /> Sun - Partly Cloudy. High: 59Low: 48\\n<BR />\\n<BR />\\n<a href=\\\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-2460286/\\\">Full Forecast at Yahoo! Weather</a>\\n<BR />\\n<BR />\\n(provided by <a href=\\\"http://www.weather.com\\\" >The Weather Channel</a>)\\n<BR />\\n]]>\",\"guid\":{\"isPermaLink\":\"false\"}}}}}}");
                    } 
        catch (IOException ex) {
            Logger.getLogger(WeatherTransformerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Wind w = WeatherTransformer.transformJsonToWind(j);
        assertEquals(14, w.getSpeed(),1);
    }

    /**
     * Test of transformTemperatureYahooToTemperature method, of class WeatherTransformer.
     */
    @Test
    public void testTransformJsonToTemperature() {
        ObjectMapper map = new ObjectMapper();
        JsonNode j = null;
        try {
            j = map.readTree("{\"query\":{\"count\":1,\"created\":\"2017-05-31T15:54:23Z\",\"lang\":\"es-ES\",\"results\":{\"channel\":{\"units\":{\"distance\":\"mi\",\"pressure\":\"in\",\"speed\":\"mph\",\"temperature\":\"F\"},\"title\":\"Yahoo! Weather - Nome, AK, US\",\"link\":\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-2460286/\",\"description\":\"Yahoo! Weather for Nome, AK, US\",\"language\":\"en-us\",\"lastBuildDate\":\"Wed, 31 May 2017 07:54 AM AKDT\",\"ttl\":\"60\",\"location\":{\"city\":\"Nome\",\"country\":\"United States\",\"region\":\" AK\"},\"wind\":{\"chill\":\"30\",\"direction\":\"45\",\"speed\":\"14\"},\"atmosphere\":{\"humidity\":\"85\",\"pressure\":\"1014.0\",\"rising\":\"0\",\"visibility\":\"16.1\"},\"astronomy\":{\"sunrise\":\"1:8 am\",\"sunset\":\"4:52 am\"},\"image\":{\"title\":\"Yahoo! Weather\",\"width\":\"142\",\"height\":\"18\",\"link\":\"http://weather.yahoo.com\",\"url\":\"http://l.yimg.com/a/i/brand/purplelogo//uh/us/news-wea.gif\"},\"item\":{\"title\":\"Conditions for Nome, AK, US at 07:00 AM AKDT\",\"lat\":\"64.499474\",\"long\":\"-165.405792\",\"link\":\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-2460286/\",\"pubDate\":\"Wed, 31 May 2017 07:00 AM AKDT\",\"condition\":{\"code\":\"33\",\"date\":\"Wed, 31 May 2017 07:00 AM AKDT\",\"temp\":\"37\",\"text\":\"Mostly Clear\"},\"forecast\":[{\"code\":\"34\",\"date\":\"31 May 2017\",\"day\":\"Wed\",\"high\":\"47\",\"low\":\"36\",\"text\":\"Mostly Sunny\"},{\"code\":\"34\",\"date\":\"01 Jun 2017\",\"day\":\"Thu\",\"high\":\"53\",\"low\":\"37\",\"text\":\"Mostly Sunny\"},{\"code\":\"32\",\"date\":\"02 Jun 2017\",\"day\":\"Fri\",\"high\":\"54\",\"low\":\"39\",\"text\":\"Sunny\"},{\"code\":\"30\",\"date\":\"03 Jun 2017\",\"day\":\"Sat\",\"high\":\"63\",\"low\":\"43\",\"text\":\"Partly Cloudy\"},{\"code\":\"30\",\"date\":\"04 Jun 2017\",\"day\":\"Sun\",\"high\":\"59\",\"low\":\"48\",\"text\":\"Partly Cloudy\"},{\"code\":\"30\",\"date\":\"05 Jun 2017\",\"day\":\"Mon\",\"high\":\"59\",\"low\":\"51\",\"text\":\"Partly Cloudy\"},{\"code\":\"34\",\"date\":\"06 Jun 2017\",\"day\":\"Tue\",\"high\":\"57\",\"low\":\"48\",\"text\":\"Mostly Sunny\"},{\"code\":\"32\",\"date\":\"07 Jun 2017\",\"day\":\"Wed\",\"high\":\"64\",\"low\":\"46\",\"text\":\"Sunny\"},{\"code\":\"34\",\"date\":\"08 Jun 2017\",\"day\":\"Thu\",\"high\":\"65\",\"low\":\"52\",\"text\":\"Mostly Sunny\"},{\"code\":\"32\",\"date\":\"09 Jun 2017\",\"day\":\"Fri\",\"high\":\"56\",\"low\":\"50\",\"text\":\"Sunny\"}],\"description\":\"<![CDATA[<img src=\\\"http://l.yimg.com/a/i/us/we/52/33.gif\\\"/>\\n<BR />\\n<b>Current Conditions:</b>\\n<BR />Mostly Clear\\n<BR />\\n<BR />\\n<b>Forecast:</b>\\n<BR /> Wed - Mostly Sunny. High: 47Low: 36\\n<BR /> Thu - Mostly Sunny. High: 53Low: 37\\n<BR /> Fri - Sunny. High: 54Low: 39\\n<BR /> Sat - Partly Cloudy. High: 63Low: 43\\n<BR /> Sun - Partly Cloudy. High: 59Low: 48\\n<BR />\\n<BR />\\n<a href=\\\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-2460286/\\\">Full Forecast at Yahoo! Weather</a>\\n<BR />\\n<BR />\\n(provided by <a href=\\\"http://www.weather.com\\\" >The Weather Channel</a>)\\n<BR />\\n]]>\",\"guid\":{\"isPermaLink\":\"false\"}}}}}}");
                    } 
        catch (IOException ex) {
            Logger.getLogger(WeatherTransformerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Temperature t = WeatherTransformer.transformJsonToTemperature(j);
        assertEquals(37, t.getCurrentTemperature(),1);
    }

    /**
     * Test of transformDayYahooToDay method, of class WeatherTransformer.
     */
    @Test
    public void testTransformJsonToDay() {
        ObjectMapper map = new ObjectMapper();
        JsonNode j = null;
        try {
            j = map.readTree("{\"query\":{\"count\":1,\"created\":\"2017-05-31T15:54:23Z\",\"lang\":\"es-ES\",\"results\":{\"channel\":{\"units\":{\"distance\":\"mi\",\"pressure\":\"in\",\"speed\":\"mph\",\"temperature\":\"F\"},\"title\":\"Yahoo! Weather - Nome, AK, US\",\"link\":\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-2460286/\",\"description\":\"Yahoo! Weather for Nome, AK, US\",\"language\":\"en-us\",\"lastBuildDate\":\"Wed, 31 May 2017 07:54 AM AKDT\",\"ttl\":\"60\",\"location\":{\"city\":\"Nome\",\"country\":\"United States\",\"region\":\" AK\"},\"wind\":{\"chill\":\"30\",\"direction\":\"45\",\"speed\":\"14\"},\"atmosphere\":{\"humidity\":\"85\",\"pressure\":\"1014.0\",\"rising\":\"0\",\"visibility\":\"16.1\"},\"astronomy\":{\"sunrise\":\"1:8 am\",\"sunset\":\"4:52 am\"},\"image\":{\"title\":\"Yahoo! Weather\",\"width\":\"142\",\"height\":\"18\",\"link\":\"http://weather.yahoo.com\",\"url\":\"http://l.yimg.com/a/i/brand/purplelogo//uh/us/news-wea.gif\"},\"item\":{\"title\":\"Conditions for Nome, AK, US at 07:00 AM AKDT\",\"lat\":\"64.499474\",\"long\":\"-165.405792\",\"link\":\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-2460286/\",\"pubDate\":\"Wed, 31 May 2017 07:00 AM AKDT\",\"condition\":{\"code\":\"33\",\"date\":\"Wed, 31 May 2017 07:00 AM AKDT\",\"temp\":\"37\",\"text\":\"Mostly Clear\"},\"forecast\":[{\"code\":\"34\",\"date\":\"31 May 2017\",\"day\":\"Wed\",\"high\":\"47\",\"low\":\"36\",\"text\":\"Mostly Sunny\"},{\"code\":\"34\",\"date\":\"01 Jun 2017\",\"day\":\"Thu\",\"high\":\"53\",\"low\":\"37\",\"text\":\"Mostly Sunny\"},{\"code\":\"32\",\"date\":\"02 Jun 2017\",\"day\":\"Fri\",\"high\":\"54\",\"low\":\"39\",\"text\":\"Sunny\"},{\"code\":\"30\",\"date\":\"03 Jun 2017\",\"day\":\"Sat\",\"high\":\"63\",\"low\":\"43\",\"text\":\"Partly Cloudy\"},{\"code\":\"30\",\"date\":\"04 Jun 2017\",\"day\":\"Sun\",\"high\":\"59\",\"low\":\"48\",\"text\":\"Partly Cloudy\"},{\"code\":\"30\",\"date\":\"05 Jun 2017\",\"day\":\"Mon\",\"high\":\"59\",\"low\":\"51\",\"text\":\"Partly Cloudy\"},{\"code\":\"34\",\"date\":\"06 Jun 2017\",\"day\":\"Tue\",\"high\":\"57\",\"low\":\"48\",\"text\":\"Mostly Sunny\"},{\"code\":\"32\",\"date\":\"07 Jun 2017\",\"day\":\"Wed\",\"high\":\"64\",\"low\":\"46\",\"text\":\"Sunny\"},{\"code\":\"34\",\"date\":\"08 Jun 2017\",\"day\":\"Thu\",\"high\":\"65\",\"low\":\"52\",\"text\":\"Mostly Sunny\"},{\"code\":\"32\",\"date\":\"09 Jun 2017\",\"day\":\"Fri\",\"high\":\"56\",\"low\":\"50\",\"text\":\"Sunny\"}],\"description\":\"<![CDATA[<img src=\\\"http://l.yimg.com/a/i/us/we/52/33.gif\\\"/>\\n<BR />\\n<b>Current Conditions:</b>\\n<BR />Mostly Clear\\n<BR />\\n<BR />\\n<b>Forecast:</b>\\n<BR /> Wed - Mostly Sunny. High: 47Low: 36\\n<BR /> Thu - Mostly Sunny. High: 53Low: 37\\n<BR /> Fri - Sunny. High: 54Low: 39\\n<BR /> Sat - Partly Cloudy. High: 63Low: 43\\n<BR /> Sun - Partly Cloudy. High: 59Low: 48\\n<BR />\\n<BR />\\n<a href=\\\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-2460286/\\\">Full Forecast at Yahoo! Weather</a>\\n<BR />\\n<BR />\\n(provided by <a href=\\\"http://www.weather.com\\\" >The Weather Channel</a>)\\n<BR />\\n]]>\",\"guid\":{\"isPermaLink\":\"false\"}}}}}}");
                    } 
        catch (IOException ex) {
            Logger.getLogger(WeatherTransformerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Day d = WeatherTransformer.transformJsonToDay(j);
        assertEquals("Wed", d.getName());
    }
    
}
