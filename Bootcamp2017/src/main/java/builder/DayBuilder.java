/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import main.Atmosphere;
import main.Day;
import main.Location;
import main.Temperature;
import main.Wind;

/**
 *
 * @author federico
 */
public class DayBuilder {
    private String name;
    private String date;
    private String description;
    private Location location;
    private Atmosphere atmosphere;
    private Wind wind;
    private Temperature temp;
    
    public DayBuilder withName(String n){
        name = n;
        return this;
    }
    
    public DayBuilder withDate(String d){
        date = d;
        return this;
    }
    
    public DayBuilder withDescription(String des){
        description = des;
        return this;
    }
    
    public DayBuilder witLocation(Location l){
        location = l;
        return this;
    }
    
    public DayBuilder withAtmosphere(Atmosphere a){
        atmosphere = a;
        return this;
    }
    
    public DayBuilder withWind(Wind w){
        wind = w;
        return this;
    }
    
    public DayBuilder withTemperature(Temperature t){
        temp = t;
        return this;
    }
    
    public Day build(){
        Day d = new Day();
        d.setName(name);
        d.setDate(date);
        d.setDescription(description);
        d.setAtmosphere(atmosphere);
        d.setTemp(temp);
        d.setLocation(location);
        d.setWind(wind);
        return d;
    }
    
}
