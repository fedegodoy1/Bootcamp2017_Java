
package builder;

import main.Temperature;

/**
 *
 * @author federico
 */
public class TemperatureBuilder {
    private float currentTemperature;
    private float highTemperature;
    private float lowTemperature;
    
    public TemperatureBuilder withCTemp(float ct){
        currentTemperature = ct;
        return this;
    }
    
    public TemperatureBuilder withHTemp(float ht){
        highTemperature = ht;
        return this;
    }
    
    public TemperatureBuilder withLTemp(float lt){
        lowTemperature = lt;
        return this;
    }
    
    public Temperature build(){
        Temperature t = new Temperature();
        t.setCurrentTemperature(currentTemperature);
        t.setHighTemperature(highTemperature);
        t.setLowTemperature(lowTemperature);
        return t;
    }
}
