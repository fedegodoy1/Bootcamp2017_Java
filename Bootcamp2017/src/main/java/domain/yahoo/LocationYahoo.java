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
public class LocationYahoo {
    private String city;
    private String country;
    private String region;

    public LocationYahoo(String city, String country, String region) {
        this.city = city;
        this.country = country;
        this.region = region;
    }

    public LocationYahoo(JsonNode j) {
        this.city = j.get("query").get("results").get("channel").get("location").get("city").asText();
        this.country = j.get("query").get("results").get("channel").get("location").get("country").asText();
        this.region = j.get("query").get("results").get("channel").get("location").get("region").asText();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    
    
}
