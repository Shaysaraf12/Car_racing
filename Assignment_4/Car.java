public class Car {

    String manufacturer;
    double acceleration;
    double maxSpeed;

    public Car(String manufacturer, double acceleration, double maxSpeed){
        this.manufacturer=manufacturer;
        this.acceleration=acceleration;
        this.maxSpeed=maxSpeed;
    }
    public String getManufacturer(){
        return manufacturer;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }
}
