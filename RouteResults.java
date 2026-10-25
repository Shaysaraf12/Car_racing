public  class RouteResults implements Pair <Team,double[]>,Comparable<RouteResults> {

    Team team;
    double [] route;
    double routeResults;
    public RouteResults(Team team, double[] route){
        this.team=team;
        this.route=route;
        routeResults=team.getRouteTime(route);
    }


    @Override
    public String toString() {
      return   team.toString()+", Route time: "+routeResults;
    }

    public Team getFirst(){
        return team;
    }
    public double[] getSecond(){
        return route;
    }




    public int compareTo(RouteResults other) {

        if (this.routeResults < other.routeResults) {
            return -1; // Current object is less than the specified object
        } else if (this.routeResults > other.routeResults) {
            return 1; // Current object is greater than the specified object
        } else {
            return 0; // Current object is equal to the specified object
        }
    }

}
