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
public class AtmosphereYahoo {
    private float humidity;
    private float pressure;
    private float visibility;


    public AtmosphereYahoo(float humidity, float pressure, float visibility) {
        this.humidity = humidity;
        this.pressure = pressure;
        this.visibility = visibility;
    }
    
    public AtmosphereYahoo(JsonNode j) {
        this.humidity = (float)j.get("query").get("results").get("channel").get("atmosphere").get("humidity").asDouble();
        this.pressure = (float)j.get("query").get("results").get("channel").get("atmosphere").get("pressure").asDouble();
        this.visibility = (float)j.get("query").get("results").get("channel").get("atmosphere").get("visibility").asDouble();
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getVisibility() {
        return visibility;
    }

    public void setVisibility(float visibility) {
        this.visibility = visibility;
    }
    
}
