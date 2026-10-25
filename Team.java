public class Team  implements Pair <Driver,Car>{
    Driver driver;
    Car car;
    double time;


   public Driver getFirst(){return driver;}
    public Car getSecond(){return car;}


    public Team(Driver driver, Car car){
        this.driver=driver;
        this.car=car;
    }


    public double timeToMaxSpeed() {

        time=0;
        time = car.getMaxSpeed() / car.getAcceleration();
        return time;
    }
    public double distanceToMaxSpeed(){
        double distanceToMax;
        distanceToMax=0.5*(car.getMaxSpeed())*timeToMaxSpeed();
        return distanceToMax;
    }
    public double timeToFullBreak(){
        double timeBreak;

        timeBreak=car.getMaxSpeed()/driver.getBreakingRate();
        return timeBreak;
    }
    public double distanceToFullBreak(){
       double distanceToBreak;
       distanceToBreak=0.5*car.getMaxSpeed()*timeToFullBreak();
       return distanceToBreak;
    }
    public double getSegmentTime(double segmentLength) {
        double segmentTime;
        double distanceConstSpeed = segmentLength - distanceToMaxSpeed() - distanceToFullBreak();//distance in max speed
        double timeConstSpeed = distanceConstSpeed / car.getMaxSpeed(); //time in max speed
        double speedForSmallSeg=Math.sqrt((2*segmentLength)/((1/car.getAcceleration())+(1/driver.getBreakingRate())));//speed in short segment
        double timeForSpeedUp=speedForSmallSeg/car.getAcceleration();
        double timeForBreak=speedForSmallSeg/driver.getBreakingRate();

        if(segmentLength >= (distanceToMaxSpeed()+distanceToFullBreak()))
        {
            segmentTime = timeToMaxSpeed() + timeConstSpeed + timeToFullBreak();
        }
        else
        {
            segmentTime=timeForSpeedUp+timeForBreak;
        }
        return segmentTime;
    }
    public double getRouteTime(double []route){
       double sumRoutes=0;
       double routeTime;
       double delayTimes=driver.getDelayTime()*(route.length-1);//calculate time without delay in last part
       for(double totalTime:route)//sum route parts
       {
           sumRoutes+=getSegmentTime(totalTime);
       }
       routeTime=sumRoutes+delayTimes;
       return routeTime;
   }


    public String toString() {
        return "Driver: " +driver.getName()+", Car: "+car.getManufacturer();
    }
    private static double timeToSpeed(double acceleration, double speed){
       double timeToSpeed;
       timeToSpeed=speed/acceleration;
       return timeToSpeed;
    }
    private static double distanceToSpeed(double acceleration, double speed){
       double distanceToSpeed;
       distanceToSpeed=0.5*speed*speed/acceleration;
       return distanceToSpeed;
    }

}


