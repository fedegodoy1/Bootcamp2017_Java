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
public class WindYahoo {
    private String direction;
    private float speed;

    public WindYahoo(String direction, float speed) {
        this.direction = direction;
        this.speed = speed;
    }

    public WindYahoo(JsonNode j) {
        this.direction = j.get("query").get("results").get("channel").get("wind").get("direction").asText();
        this.speed = (float)j.get("query").get("results").get("channel").get("wind").get("speed").asDouble();
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
    
    
}
