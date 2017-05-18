package main;



/**
 *
 * @author federico
 */
public class Day{
    private String name;
    private String date;
    private String description;
    private Location location;
    private Atmosphere atmosphere;
    private Wind wind;
    private Temperature temp;
    
    public Day(){ }

    public Day(String name, String date, String description, Location location, Atmosphere atmosphere, Wind wind, Temperature temp) {
        this.name = name;
        this.date = date;
        this.description = description;
        this.location = location;
        this.atmosphere = atmosphere;
        this.wind = wind;
        this.temp = temp;
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Temperature getTemp() {
        return temp;
    }

    public void setTemp(Temperature temp) {
        this.temp = temp;
    }

    @Override
    public String toString() {
        return "\nDay-----------------\n" + 
                "Name: " + name + ", date: " + date + ", description: " + description + ", " + location.toString() + ", " + atmosphere.toString() + ", " + wind.toString() + ", " + temp.toString();
    }
    
    
    
}
