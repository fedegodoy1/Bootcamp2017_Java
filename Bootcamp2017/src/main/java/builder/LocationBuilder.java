/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import main.Location;

/**
 *
 * @author federico
 */
public class LocationBuilder {
    private String city;
    private String country;
    private String region;
    
    public LocationBuilder withCity(String c){
        city = c;
        return this;
    }
    
    public LocationBuilder withCountry(String c){
        country = c;
        return this;
    }
    
    public LocationBuilder withRegion(String r){
        region = r;
        return this;
    }
    
    public Location build(){
        Location l = new Location();
        l.setCity(city);
        l.setCountry(country);
        l.setRegion(region);
        return l;
    }

}
