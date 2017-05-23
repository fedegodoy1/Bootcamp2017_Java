package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import builder.AtmosphereBuilder;
import builder.DayBuilder;
import builder.LocationBuilder;
import builder.TemperatureBuilder;
import builder.WindBuilder;
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
public class ForecastTest {
    
    public ForecastTest() {
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
     * Test of add method, of class Forecast.
     */
    @org.junit.Test
    public void testAdd() {
        System.out.println("add");
        int index = 0;
        
        Location l2 = new LocationBuilder().withCity("Córdoba").withCity("Argentina").withRegion("Latam").build();
        Atmosphere a2 = new AtmosphereBuilder().withHumidity(55).withPressure(3).withVisibility(13).build();
        Wind w2 = new WindBuilder().withDirection("N").withSpeed(26).build();
        Temperature t2 = new TemperatureBuilder().withCTemp(25).withHTemp(27).withLTemp(20).build();
        Day d2 = new DayBuilder().withName("Lunes").withDate("15/05").withDescription("Cloudy").witLocation(l2).withAtmosphere(a2).withTemperature(t2).withWind(w2).build();
        
        Forecast instance = new Forecast();
        Day expResult = d2;
        instance.add(index, d2);
        Day result = (Day)instance.get(index);
        assertEquals(expResult, result);
        
        if(!result.equals(expResult)){
            fail();
        }
    }

    /**
     * Test of addFirst method, of class Forecast.
     */
    @org.junit.Test
    public void testAddFirst() {
        System.out.println("addFirst");
        Location l = new LocationBuilder().withCity("Córdoba").withCity("Argentina").withRegion("Latam").build();
        Atmosphere a = new AtmosphereBuilder().withHumidity(32).withPressure(8).withVisibility(25).build();
        Wind w= new WindBuilder().withDirection("S").withSpeed(26).build();
        Temperature t = new TemperatureBuilder().withCTemp(25).withHTemp(27).withLTemp(20).build();
        Day d = new DayBuilder().withName("Lunes").withDate("15/05").withDescription("Cloudy").witLocation(l).withAtmosphere(a).withTemperature(t).withWind(w).build();
        
        Forecast instance = new Forecast();
        Day expResult = d;
        instance.addFirst(d);
        Day result = (Day)instance.get(0);
        assertEquals(expResult, result);
        
        if(!result.equals(expResult)){
            fail();
        }
    }

    /**
     * Test of search method, of class Forecast.
     */
    @org.junit.Test
    public void testSearch() throws NullPointerException{
        System.out.println("search");
        String dateDay = "viernes";
        Forecast instance = new Forecast();
        Day expResult = null;
        Day result = instance.search(dateDay);
        assertNull("El objeto es nulo",result);
    }

    /**
     * Test of get method, of class Forecast.
     */
    @org.junit.Test
    public void testGet() {
        System.out.println("get");
        Location l = new LocationBuilder().withCity("Córdoba").withCity("Argentina").withRegion("Latam").build();
        Atmosphere a = new AtmosphereBuilder().withHumidity(32).withPressure(8).withVisibility(25).build();
        Wind w= new WindBuilder().withDirection("S").withSpeed(26).build();
        Temperature t = new TemperatureBuilder().withCTemp(25).withHTemp(27).withLTemp(20).build();
        Day d = new DayBuilder().withName("Lunes").withDate("15/05").withDescription("Cloudy").witLocation(l).withAtmosphere(a).withTemperature(t).withWind(w).build();
        
        int index = 0;
        Forecast instance = new Forecast();
        instance.addFirst(d);
        Object expResult = null;
        Object result = instance.get(index);
        
        if(result.equals(expResult)){
            fail();
        }
    }

    /**
     * Test of remove method, of class Forecast.
     */
    @org.junit.Test
    public void testRemove() {
        System.out.println("remove");
        int index = 0;
        
        Forecast instance = new Forecast();
        
        Location l = new LocationBuilder().withCity("Córdoba").withCountry("Argentina").withRegion("Latam").build();
        Atmosphere a = new AtmosphereBuilder().withHumidity(23).withPressure(7).withVisibility(15).build();
        Wind w = new WindBuilder().withDirection("S").withSpeed(15).build();
        Temperature t = new TemperatureBuilder().withCTemp(11).withHTemp(13).withLTemp(5).build();
        Day d = new DayBuilder().withName("Domingo").withDate("14/05").withDescription("Sunny").witLocation(l).withTemperature(t).withAtmosphere(a).withWind(w).build();
        
        instance.addFirst(d);
        Day expResult = (Day) instance.get(index);
        Day result = instance.remove(index);
        
        
        if(!result.equals(expResult)){
            fail();
        }
    }

    /**
     * Test of set method, of class Forecast.
     */
    @org.junit.Test
    public void testSet() {
        System.out.println("set");
        int index = 0;
        
        Location l1 = new LocationBuilder().withCity("Córdoba").withCountry("Argentina").withRegion("Latam").build();
        Atmosphere a1 = new AtmosphereBuilder().withHumidity(23).withPressure(7).withVisibility(15).build();
        Wind w1 = new WindBuilder().withDirection("S").withSpeed(15).build();
        Temperature t1 = new TemperatureBuilder().withCTemp(11).withHTemp(13).withLTemp(5).build();
        Day day1 = new DayBuilder().withName("Domingo").withDate("14/05").withDescription("Sunny").witLocation(l1).withTemperature(t1).withAtmosphere(a1).withWind(w1).build();
        
        Location l2 = new LocationBuilder().withCity("Córdoba").withCity("Argentina").withRegion("Latam").build();
        Atmosphere a2 = new AtmosphereBuilder().withHumidity(55).withPressure(3).withVisibility(13).build();
        Wind w2 = new WindBuilder().withDirection("N").withSpeed(26).build();
        Temperature t2 = new TemperatureBuilder().withCTemp(25).withHTemp(27).withLTemp(20).build();
        Day sustituido = new DayBuilder().withName("Lunes").withDate("15/05").withDescription("Cloudy").witLocation(l2).withAtmosphere(a2).withTemperature(t2).withWind(w2).build();
        
        Forecast instance = new Forecast();
        
        instance.addFirst(day1);
        
        Day expResult = day1;
        Day result = instance.set(index, sustituido);
        assertEquals(expResult, result);
        if(!result.equals(expResult)){
            fail();
        }
    }
}
