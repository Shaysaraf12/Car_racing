public class Driver {

    String name;
    double breakingRate;
    double delayTime;
    public Driver(String name, double breakingRate, double delayTime){
        this.name=name;
        this.breakingRate=breakingRate;
        this.delayTime=delayTime;
    }
    public String getName(){
        return name;
    }

    public double getBreakingRate() {
        return breakingRate;
    }

    public double getDelayTime() {
        return delayTime;
    }
}
