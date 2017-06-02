/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pattern.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * @author federico
 */
@Component
public class AdapterMilesToKilometers implements Kilometers{
    @Autowired
    private Miles miles;
    
    public AdapterMilesToKilometers(Miles miles){
        this.miles = miles;
    }
    
    @Override
    public float getSpeed(){
        float mph = miles.getSpeed();
        float kmh = 0;
        return kmh = (float)(mph*1.609);
    }
    
    public void setSpeed(float speed){
        this.setSpeed(speed);
    }
}
