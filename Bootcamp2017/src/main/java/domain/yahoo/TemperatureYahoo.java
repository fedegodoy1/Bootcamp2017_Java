package domain.yahoo;

import com.fasterxml.jackson.databind.JsonNode;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author federico
 */
public class TemperatureYahoo {
    private float currentTemp;
    private float highTemp;
    private float lowTemp;

    public TemperatureYahoo(float currentTemp, float highTemp, float lowTemp) {
        this.currentTemp = currentTemp;
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
    }
    
    public TemperatureYahoo(JsonNode j) {
        this.currentTemp = (float)j.get("query").get("results").get("channel").get("item").get("condition").get("temp").asDouble();
        this.highTemp = (float)j.get("query").get("results").get("channel").get("item").get("forecast").get(1).get("high").asDouble();
        this.lowTemp = (float)j.get("query").get("results").get("channel").get("item").get("forecast").get(1).get("low").asDouble();;
    }

    public float getCurrentTemp() {
        return currentTemp;
    }

    public void setCurrentTemp(float currentTemp) {
        this.currentTemp = currentTemp;
    }

    public float getHighTemp() {
        return highTemp;
    }

    public void setHighTemp(float highTemp) {
        this.highTemp = highTemp;
    }

    public float getLowTemp() {
        return lowTemp;
    }

    public void setLowTemp(float lowTemp) {
        this.lowTemp = lowTemp;
    }
    
    
}
