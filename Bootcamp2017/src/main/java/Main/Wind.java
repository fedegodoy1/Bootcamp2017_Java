package Main;



/**
 *
 * @author federico
 */
public class Wind {
    private String direction;
    private float speed;

    public Wind(String direction, float speed) {
        this.direction = direction;
        this.speed = speed;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "\nWind \n" + "Direction: " + direction + ", speed: " + speed + "km";
    }
}
