/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.fasterxml.jackson.databind.JsonNode;
import domain.Day;
import org.easymock.EasyMock;
import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pattern.transformer.WeatherTransformer;

/**
 *
 * @author federico
 */
public class AdapterYahooResponseToDayTest {

    public AdapterYahooResponseToDayTest() {
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
     * Test of requestForecastCurrentDay method, of class
     * AdapterYahooResponseToDay.
     */
    @Test
    public void testRequestForecastCurrentDayForIncorrectInput() {
        ClientYahooWeather proxyMock = EasyMock.createMock(ClientYahooWeather.class);
        String location = "cordoba";
        String country = "argentina";
        expect(proxyMock.getForecast(location, country)).andReturn("There are not response for location:" + location + " and country: " + country);
        replay(proxyMock);

        AdapterYahooResponseToDay adapter = new AdapterYahooResponseToDay();
        adapter.setProxy(proxyMock);
        Day dayResponse = adapter.requestForecastCurrentDay(location, country);
        assertEquals("Location and country incorrects", dayResponse.getName());

        verify(proxyMock);
    }
    
    @Test
    public void testRequestForecastCurrentDaySuccess() {
        ClientYahooWeather proxyMock = EasyMock.createMock(ClientYahooWeather.class);
//        JsonNode jsonMock = EasyMock.createMock(JsonNode.class);
        String location = "cordoba";
        String country = "argentina";
        
        expect(proxyMock.getForecast(anyString(), anyString())).andReturn("{\"query\":{\"count\":1,\"created\":\"2017-06-02T01:39:04Z\",\"lang\":\"es-ES\",\"results\":{\"channel\":{\"units\":{\"distance\":\"mi\",\"pressure\":\"in\",\"speed\":\"mph\",\"temperature\":\"F\"},\"title\":\"Yahoo! Weather - Cordoba, CBA, AR\",\"link\":\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-466861/\",\"description\":\"Yahoo! Weather for Cordoba, CBA, AR\",\"language\":\"en-us\",\"lastBuildDate\":\"Thu, 01 Jun 2017 10:39 PM ART\",\"ttl\":\"60\",\"location\":{\"city\":\"Cordoba\",\"country\":\"Argentina\",\"region\":\" CBA\"},\"wind\":{\"chill\":\"43\",\"direction\":\"23\",\"speed\":\"7\"},\"atmosphere\":{\"humidity\":\"92\",\"pressure\":\"969.0\",\"rising\":\"0\",\"visibility\":\"13.7\"},\"astronomy\":{\"sunrise\":\"8:7 am\",\"sunset\":\"6:21 pm\"},\"image\":{\"title\":\"Yahoo! Weather\",\"width\":\"142\",\"height\":\"18\",\"link\":\"http://weather.yahoo.com\",\"url\":\"http://l.yimg.com/a/i/brand/purplelogo//uh/us/news-wea.gif\"},\"item\":{\"title\":\"Conditions for Cordoba, CBA, AR at 10:00 PM ART\",\"lat\":\"-31.403959\",\"long\":\"-64.197929\",\"link\":\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-466861/\",\"pubDate\":\"Thu, 01 Jun 2017 10:00 PM ART\",\"condition\":{\"code\":\"31\",\"date\":\"Thu, 01 Jun 2017 10:00 PM ART\",\"temp\":\"45\",\"text\":\"Clear\"},\"forecast\":[{\"code\":\"32\",\"date\":\"01 Jun 2017\",\"day\":\"Thu\",\"high\":\"60\",\"low\":\"40\",\"text\":\"Sunny\"},{\"code\":\"30\",\"date\":\"02 Jun 2017\",\"day\":\"Fri\",\"high\":\"61\",\"low\":\"37\",\"text\":\"Partly Cloudy\"},{\"code\":\"30\",\"date\":\"03 Jun 2017\",\"day\":\"Sat\",\"high\":\"60\",\"low\":\"47\",\"text\":\"Partly Cloudy\"},{\"code\":\"30\",\"date\":\"04 Jun 2017\",\"day\":\"Sun\",\"high\":\"62\",\"low\":\"43\",\"text\":\"Partly Cloudy\"},{\"code\":\"12\",\"date\":\"05 Jun 2017\",\"day\":\"Mon\",\"high\":\"55\",\"low\":\"47\",\"text\":\"Rain\"},{\"code\":\"34\",\"date\":\"06 Jun 2017\",\"day\":\"Tue\",\"high\":\"61\",\"low\":\"43\",\"text\":\"Mostly Sunny\"},{\"code\":\"28\",\"date\":\"07 Jun 2017\",\"day\":\"Wed\",\"high\":\"65\",\"low\":\"45\",\"text\":\"Mostly Cloudy\"},{\"code\":\"34\",\"date\":\"08 Jun 2017\",\"day\":\"Thu\",\"high\":\"59\",\"low\":\"48\",\"text\":\"Mostly Sunny\"},{\"code\":\"32\",\"date\":\"09 Jun 2017\",\"day\":\"Fri\",\"high\":\"69\",\"low\":\"48\",\"text\":\"Sunny\"},{\"code\":\"32\",\"date\":\"10 Jun 2017\",\"day\":\"Sat\",\"high\":\"64\",\"low\":\"49\",\"text\":\"Sunny\"}],\"description\":\"<![CDATA[<img src=\\\"http://l.yimg.com/a/i/us/we/52/31.gif\\\"/>\\n<BR />\\n<b>Current Conditions:</b>\\n<BR />Clear\\n<BR />\\n<BR />\\n<b>Forecast:</b>\\n<BR /> Thu - Sunny. High: 60Low: 40\\n<BR /> Fri - Partly Cloudy. High: 61Low: 37\\n<BR /> Sat - Partly Cloudy. High: 60Low: 47\\n<BR /> Sun - Partly Cloudy. High: 62Low: 43\\n<BR /> Mon - Rain. High: 55Low: 47\\n<BR />\\n<BR />\\n<a href=\\\"http://us.rd.yahoo.com/dailynews/rss/weather/Country__Country/*https://weather.yahoo.com/country/state/city-466861/\\\">Full Forecast at Yahoo! Weather</a>\\n<BR />\\n<BR />\\n(provided by <a href=\\\"http://www.weather.com\\\" >The Weather Channel</a>)\\n<BR />\\n]]>\",\"guid\":{\"isPermaLink\":\"false\"}}}}}}");
        
        replay(proxyMock);

        AdapterYahooResponseToDay adapter = new AdapterYahooResponseToDay();
        adapter.setProxy(proxyMock);
        
        Day dayResponse = adapter.requestForecastCurrentDay(location, country);
        assertEquals("01 Jun 2017",dayResponse.getDate());

        verify(proxyMock);
    }

}
