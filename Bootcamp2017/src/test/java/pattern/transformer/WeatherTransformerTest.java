/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    public void testTransformAtmosphereYahooToAtmosphere() {
        
        AtmosphereYahoo aYahoo = new AtmosphereYahoo(13,24,45);
        Atmosphere a = WeatherTransformer.transformAtmosphereYahooToAtmosphere(aYahoo);
        assertEquals(13, a.getHumidity());
    }

    /**
     * Test of transformLocationYahooToLocation method, of class WeatherTransformer.
     */
    @Test
    public void testTransformLocationYahooToLocation() {
        LocationYahoo lYahoo = new LocationYahoo("CBA","AR","CBA");
        Location l = WeatherTransformer.transformLocationYahooToLocation(lYahoo);
        assertEquals("CBA", l.getCity());
    }

    /**
     * Test of transformWindYahooToWind method, of class WeatherTransformer.
     */
    @Test
    public void testTransformWindYahooToWind() {
        WindYahoo wYahoo = new WindYahoo("N",12);
        Wind w = WeatherTransformer.transformWindYahooToWind(wYahoo);
        assertEquals("N", w.getDirection());
    }

    /**
     * Test of transformTemperatureYahooToTemperature method, of class WeatherTransformer.
     */
    @Test
    public void testTransformTemperatureYahooToTemperature() {
        TemperatureYahoo tYahoo = new TemperatureYahoo(12,23,4);
        Temperature t = WeatherTransformer.transformTemperatureYahooToTemperature(tYahoo);
        assertEquals(12, t.getCurrentTemperature());
    }

    /**
     * Test of transformDayYahooToDay method, of class WeatherTransformer.
     */
    @Test
    public void testTransformDayYahooToDay() {
        AtmosphereYahoo aYahoo = new AtmosphereYahoo(13,24,45);
        LocationYahoo lYahoo = new LocationYahoo("CBA","AR","CBA");
        WindYahoo wYahoo = new WindYahoo("N",12);
        TemperatureYahoo tYahoo = new TemperatureYahoo(12,23,4);
        DayYahoo dYahoo = new DayYahoo("Saturday","23/4","",aYahoo,lYahoo,tYahoo,wYahoo);
        Day d = WeatherTransformer.transformDayYahooToDay(dYahoo);
        assertEquals("Saturday", d.getName());
    }
    
}
