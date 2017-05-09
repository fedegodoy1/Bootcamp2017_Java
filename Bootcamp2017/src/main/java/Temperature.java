

/**
 *
 * @author federico
 */
public class Temperature {
    private float currentTemperature;
    private float highTemperature;
    private float lowTemperature;

    public Temperature(float currentTemperature, float highTemperature, float lowTemperature) {
        this.currentTemperature = currentTemperature;
        this.highTemperature = highTemperature;
        this.lowTemperature = lowTemperature;
    }

    public float getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(float currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public float getHighTemperature() {
        return highTemperature;
    }

    public void setHighTemperature(float highTemperature) {
        this.highTemperature = highTemperature;
    }

    public float getLowTemperature() {
        return lowTemperature;
    }

    public void setLowTemperature(float lowTemperature) {
        this.lowTemperature = lowTemperature;
    }

    @Override
    public String toString() {
        return "\nTemperature\n" + "Current Temperature: " + currentTemperature + "°C, High Temperature: " + highTemperature + "°C, Low Temperature: " + lowTemperature + "°C";
    }
    
    
}
