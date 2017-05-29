
package pattern.builder;

import domain.Atmosphere;

/**
 *
 * @author federico
 */
public class AtmosphereBuilder {
    private float humidity;
    private float pressure;
    private float visibility;
    
    public AtmosphereBuilder withHumidity(float h){
        humidity = h;
        return this;
    }
    
    public AtmosphereBuilder withPressure(float p){
        pressure = p;
        return this;
    }
    
    public AtmosphereBuilder withVisibility(float v){
        visibility = v;
        return this;
    }
    
    public Atmosphere build(){
        Atmosphere a = new Atmosphere();
        a.setHumidity(humidity);
        a.setPressure(pressure);
        a.setVisibility(visibility);
        return a;
    }
}
