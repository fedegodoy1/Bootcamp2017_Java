/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.yahoo;

import com.fasterxml.jackson.databind.JsonNode;

/**
 *
 * @author federico
 */
public class DayYahoo {
    private String name;
    private String date;
    private String description;
    private AtmosphereYahoo atmosphere;
    private LocationYahoo location;
    private TemperatureYahoo temperature;
    private WindYahoo wind;

    public DayYahoo(){
        
    }
    
    public DayYahoo(String name, String date, String description, AtmosphereYahoo atmosphere, LocationYahoo location, TemperatureYahoo temperature, WindYahoo wind) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.atmosphere = atmosphere;
        this.location = location;
        this.temperature = temperature;
        this.wind = wind;
    } 
    
    public DayYahoo(JsonNode j, AtmosphereYahoo atmosphere, LocationYahoo location, TemperatureYahoo temperature, WindYahoo wind) {
        this.name = j.get("query").get("results").get("channel").get("item").get("pubDate").asText();
        this.date = j.get("query").get("results").get("channel").get("item").get("condition").get("date").asText();
        this.description = j.get("query").get("results").get("channel").get("item").get("condition").get("text").asText();
        this.atmosphere = atmosphere;
        this.location = location;
        this.temperature = temperature;
        this.wind = wind;
    }
    
      public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AtmosphereYahoo getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(AtmosphereYahoo atmosphere) {
        this.atmosphere = atmosphere;
    }

    public LocationYahoo getLocation() {
        return location;
    }

    public void setLocation(LocationYahoo location) {
        this.location = location;
    }

    public TemperatureYahoo getTemperature() {
        return temperature;
    }

    public void setTemperature(TemperatureYahoo temperature) {
        this.temperature = temperature;
    }

    public WindYahoo getWind() {
        return wind;
    }

    public void setWind(WindYahoo wind) {
        this.wind = wind;
    }
    
    
}
