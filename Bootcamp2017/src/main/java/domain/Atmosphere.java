package domain;


/**
 *
 * @author federico
 */
public class Atmosphere {
    private float humidity;
    private float pressure;
    private float visibility;

    public Atmosphere(){
        this.humidity=0;
        this.pressure=0;
        this.visibility=0;
    }
    
    public Atmosphere(float humidity, float pressure, float visibility) {
        this.humidity = humidity;
        this.pressure = pressure;
        this.visibility = visibility;
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

    @Override
    public String toString() {
        return "\nAtmosphere \n" + 
                "Humididy: " + humidity + "%, pressure: " + pressure + ", visibility: " + visibility + "km";
    }
}
