/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package builder;

import main.Wind;

/**
 *
 * @author federico
 */
public class WindBuilder {
    private String direction;
    private float speed;
    
    public WindBuilder withDirection(String d){
        direction = d;
        return this;
    }
    
    public WindBuilder withSpeed(float s){
        speed = s;
        return this;
    }
    
    public Wind build(){
        Wind w = new Wind();
        w.setDirection(direction);
        w.setSpeed(speed);
        return w;
    }
}
