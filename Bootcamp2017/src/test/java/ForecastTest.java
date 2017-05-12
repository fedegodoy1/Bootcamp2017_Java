/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Main.Forecast;
import Main.Location;
import Main.Atmosphere;
import Main.Temperature;
import Main.Day;
import Main.Wind;
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
        
        Location l2=new Location("Córdoba","Argentina","Latam");
        
        float humidity2 = 55;
        float presure2 = 3;
        float visibility2 = 13;
        Atmosphere a2 = new Atmosphere(humidity2,presure2,visibility2);
        
        String direction2 = "N";
        float speed2 = 26;
        Wind w2 = new Wind(direction2, speed2);
        
        float cTemp2 = 25;
        float hTemp2 = 27;
        float lTemp2 = 20;
        Temperature t2 = new Temperature(cTemp2,hTemp2,lTemp2);
        Day d= new Day("Jueves","11/05","Cloudy",l2,a2,w2,t2);
        
        Forecast instance = new Forecast();
        Day expResult = d;
        instance.add(index, d);
        Day result = (Day)instance.get(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if(!result.equals(expResult)){
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of addFirst method, of class Forecast.
     */
    @org.junit.Test
    public void testAddFirst() {
        System.out.println("addFirst");
        Location l2=new Location("Córdoba","Argentina","Latam");
        
        float humidity2 = 55;
        float presure2 = 3;
        float visibility2 = 13;
        Atmosphere a2 = new Atmosphere(humidity2,presure2,visibility2);
        
        String direction2 = "N";
        float speed2 = 26;
        Wind w2 = new Wind(direction2, speed2);
        
        float cTemp2 = 25;
        float hTemp2 = 27;
        float lTemp2 = 20;
        Temperature t2 = new Temperature(cTemp2,hTemp2,lTemp2);
        Day d= new Day("Jueves","11/05","Cloudy",l2,a2,w2,t2);
        
        Forecast instance = new Forecast();
        Day expResult = d;
        instance.addFirst(d);
        Day result = (Day)instance.get(0);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if(!result.equals(expResult)){
            fail("The test case is a prototype.");
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
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if(!result.equals(expResult)){
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of get method, of class Forecast.
     */
    @org.junit.Test
    public void testGet() {
        System.out.println("get");
        int index = -4;
        Forecast instance = new Forecast();
        Object expResult = null;
        Object result = instance.get(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if(!result.equals(expResult)){
            fail("The test case is a prototype.");
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
        
        Location l2=new Location("Córdoba","Argentina","Latam");
        
        float humidity2 = 55;
        float presure2 = 3;
        float visibility2 = 13;
        Atmosphere a2 = new Atmosphere(humidity2,presure2,visibility2);
        
        String direction2 = "N";
        float speed2 = 26;
        Wind w2 = new Wind(direction2, speed2);
        
        float cTemp2 = 25;
        float hTemp2 = 27;
        float lTemp2 = 20;
        Temperature t2 = new Temperature(cTemp2,hTemp2,lTemp2);
        Day d= new Day("Jueves","11/05","Cloudy",l2,a2,w2,t2);
        
        instance.add(index,d);
        
        Day result = instance.remove(index);
        Day expResult = (Day) instance.get(index);
        
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if(result.equals(expResult)){
            fail("The test case is a prototype.");
        }
    }

    /**
     * Test of set method, of class Forecast.
     */
    @org.junit.Test
    public void testSet() {
        System.out.println("set");
        int index = 0;
        Location l1=new Location("Córdoba","Argentina","Latam");
        
        float humidity3 = 10;
        float presure3 = 3;
        float visibility3 = 25;
        Atmosphere a3 = new Atmosphere(humidity3,presure3,visibility3);
        
        String direction3 = "N";
        float speed3 = 5;
        Wind w3 = new Wind(direction3, speed3);
        
        float cTemp3 = 15;
        float hTemp3 = 20;
        float lTemp3 = 10;
        Temperature t3 = new Temperature(cTemp3,hTemp3,lTemp3);
        Day day1 = new Day("Martes","02/05","Sunny",l1,a3,w3,t3);
        
        
        float humidity = 25;
        float presure = 4;
        float visibility = 10;
        Atmosphere a1 = new Atmosphere(humidity,presure,visibility);
        
        String direction = "E";
        float speed = 26;
        Wind w1 = new Wind(direction, speed);
        
        float cTemp = 10;
        float hTemp = 13;
        float lTemp = 9;
        Temperature t1 = new Temperature(cTemp,hTemp,lTemp);
        Day sustituido = new Day("Miercoles","10/05","Sunny",l1,a1,w1,t1);
        
        Forecast instance = new Forecast();
        
        instance.addFirst(day1);
        
        Day expResult = day1;
        Day result = instance.set(index, sustituido);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        if(!result.equals(expResult)){
            fail("The test case is a prototype.");
        }
    }
}
