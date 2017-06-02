/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.Day;
import java.util.ArrayList;
import javax.ws.rs.core.Response;
import org.easymock.EasyMock;
import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author federico
 */
public class WeatherControllerTest {
    
    public WeatherControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    
    
    @After
    public void tearDown() {
    }

    /**
     * Test of forecastCurrentDay method, of class WeatherController.
     */
    
        
    @Before
    public void setUp() {
        
    }
    
    @Test
    public void testForecastCurrentDayForNullResponse() {
        
        String location = "cordoba";
        String country = "argentina";
        //Day dayMock = EasyMock.createMock(Day.class);
        ServiceWeather responseFromServiceMock = EasyMock.createMock(ServiceWeather.class);
        
        expect(responseFromServiceMock.requestForecastCurrentDay(location, country)).andReturn(null);
        replay(responseFromServiceMock);
        
        WeatherController weatherController= new WeatherController(responseFromServiceMock);
        ResponseEntity r = weatherController.forecastCurrentDay(country,location);
        String expected = "You are not connected - In data base there are not forecast for "+location;
        assertEquals(expected, r.getBody());
        
        verify(responseFromServiceMock);
    }
    
    @Test
    public void testForecastCurrentDayForDayResponse() {
        
        String location = "cordoba";
        String country = "argentina";
        Day dayMock = EasyMock.createMock(Day.class);
        ServiceWeather responseFromServiceMock = EasyMock.createMock(ServiceWeather.class);
        
        expect(dayMock.getName()).andReturn("Location and country incorrects");
        expect(responseFromServiceMock.requestForecastCurrentDay(location, country)).andReturn(dayMock);
        replay(responseFromServiceMock,dayMock);
        
        WeatherController weatherController= new WeatherController(responseFromServiceMock);
        ResponseEntity r = weatherController.forecastCurrentDay(country,location);
        String expected = "There are not response for location: "+location+" and country:"+country;
        assertEquals(expected, r.getBody());
        
        verify(responseFromServiceMock, dayMock);
    }

    /**
     * Test of allDays method, of class WeatherController.
     */
//    @Test
//    public void testAllDays() {
//        System.out.println("allDays");
//        WeatherController instance = new WeatherController();
//        ArrayList<Day> expResult = null;
//        ArrayList<Day> result = instance.allDays();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of day method, of class WeatherController.
     */
//    @Test
//    public void testDay() {
//        System.out.println("day");
//        String idday = "";
//        WeatherController instance = new WeatherController();
//        ArrayList<Day> expResult = null;
//        ArrayList<Day> result = instance.day(idday);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of addDay method, of class WeatherController.
     */
//    @Test
//    public void testAddDay() {
//        System.out.println("addDay");
//        Day d = null;
//        WeatherController instance = new WeatherController();
//        ArrayList<Day> expResult = null;
//        ArrayList<Day> result = instance.addDay(d);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of updateDay method, of class WeatherController.
     */
//    @Test
//    public void testUpdateDay() {
//        System.out.println("updateDay");
//        Day d = null;
//        WeatherController instance = new WeatherController();
//        ArrayList<Day> expResult = null;
//        ArrayList<Day> result = instance.updateDay(d);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
