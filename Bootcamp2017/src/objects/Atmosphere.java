/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

/**
 *
 * @author federico
 */
public class Atmosphere {
    private float humididy;
    private float pressure;
    private float visibility;

    public Atmosphere(float humididy, float pressure, float visibility) {
        this.humididy = humididy;
        this.pressure = pressure;
        this.visibility = visibility;
    }

    public float getHumididy() {
        return humididy;
    }

    public void setHumididy(float humididy) {
        this.humididy = humididy;
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

    @Override
    public String toString() {
        return "\nAtmosphere \n" + 
                "Humididy: " + humididy + "%, pressure: " + pressure + ", visibility: " + visibility + "km";
    }
}
