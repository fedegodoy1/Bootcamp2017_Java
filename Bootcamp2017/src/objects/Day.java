package objects;

/**
 *
 * @author federico
 */
public class Day{
    private String name;
    private String date;
    private String description;
    private String highTemp;
    private String lowTemp;
    private String currentTemp;

    public Day(String name, String date, String description, String highTemp, String lowTemp, String currentTemp) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.highTemp = highTemp;
        this.lowTemp = lowTemp;
        this.currentTemp = currentTemp;
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

    public String getHighTemp() {
        return highTemp;
    }

    public void setHighTemp(String highTemp) {
        this.highTemp = highTemp;
    }

    public String getLowTemp() {
        return lowTemp;
    }

    public void setLowTemp(String lowTemp) {
        this.lowTemp = lowTemp;
    }

    public String getcurrentTemp() {
        return currentTemp;
    }

    public void setActTemp(String currentTemp) {
        this.currentTemp = currentTemp;
    }

    @Override
    public String toString() {
        return "Day: " + name + ", date: " + date + ", description: " + description + 
                ", high temperature: " + highTemp + ", low temperature: " + lowTemp + ", current temperature: " + currentTemp + "\n";
    } 
}
