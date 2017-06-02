/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pattern.proxy;

import org.easymock.EasyMock;
import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import service.ClientYahooWeather;

/**
 *
 * @author federico
 */
public class ProxyWeatherTest {
    
    public ProxyWeatherTest() {
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
     * Test of getForecast method, of class ProxyWeather.
     */
    @Test
    public void testGetForecast() {
        String location = "cordoba";
        String country = "argentina";
        String yql = "select woeid from geo.places(1) where text=\" " + location + ", " + country + "\"";
        String format = "json";
        ClientYahooWeather clientYahooWeatherMock = EasyMock.createMock(ClientYahooWeather.class);
        expect(clientYahooWeatherMock.getForecast(yql, format)).andReturn("{\"query\":{\"count\":0,\"created\":\"2017-06-02T04:12:10Z\",\"lang\":\"es-ES\",\"results\":null}}");
        replay(clientYahooWeatherMock);
        
        ClientYahooWeather proxy = new ProxyWeather(clientYahooWeatherMock);
        String response = proxy.getForecast(location, country);
        assertEquals("There are not response for location:" + location + " and country: " + country,response);
        verify(clientYahooWeatherMock);
    }
    
}
