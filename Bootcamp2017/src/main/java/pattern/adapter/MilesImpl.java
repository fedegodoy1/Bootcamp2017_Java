/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pattern.adapter;

import org.springframework.stereotype.Component;

/**
 *
 * @author federico
 */
@Component
public class MilesImpl implements Miles{
    private float speed;

    public void setSpeed(float speed) {
        this.speed = speed;
    }
    
    @Override
    public float getSpeed(){
        return speed;
    }
}
