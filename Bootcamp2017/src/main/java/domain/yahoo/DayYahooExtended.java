
package domain.yahoo;

/**
 *
 * @author federico
 */
public class DayYahooExtended {
    private String name;
    private String date;
    private String description;
    private float highTemp;
    private float lowTemp;

    public DayYahooExtended(String name, String date, String description, float highTemp, float lowTemp) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getHighTemp() {
        return highTemp;
    }

    public void setHighTemp(float highTemp) {
        this.highTemp = highTemp;
    }

    public float getLowTemp() {
        return lowTemp;
    }

    public void setLowTemp(float lowTemp) {
        this.lowTemp = lowTemp;
    }
    
    
}
