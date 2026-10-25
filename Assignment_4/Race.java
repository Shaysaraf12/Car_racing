import java.util.Scanner;
import java.util.Arrays;




public class Race {


    public static double[] getRouteFromSysIn() {
        Scanner scanner = new Scanner(System.in);

        int numberOfSegment;
        do {
            System.out.print("How many segments does the route contain?\n");
            numberOfSegment = scanner.nextInt();

            if (numberOfSegment <= 0) {
                System.out.print("Input must be a positive integer, got " + numberOfSegment + ".\n");
            }
        } while (numberOfSegment <= 0);//check if number is unpositive


        int[] Segments = new int[numberOfSegment];
        double[] distances = new double[numberOfSegment];


        for (int i = 0; i < Segments.length; i++) {
            System.out.print("What is the length of segment " + (i+1) + "?\n");
            double distance = scanner.nextDouble();
            if (distance <= 0) {
                System.out.print("Input must be a positive double, got " + distance + ".\n");
                distance = scanner.nextDouble();
            }
            distances[i] = distance;
        }
        return distances;
    }

    public static Team[] getTeamsFromSysIn() {
        Scanner scanner = new Scanner(System.in);
        int numOfTeams;
        do {
            System.out.print("How many teams are there in the race?\n");
            numOfTeams = scanner.nextInt();

            if (numOfTeams <= 0) {
                System.out.print("Input must be a positive integer, got " + numOfTeams + ".\n");
            }
        } while (numOfTeams <= 0);
        Team[] teams = new Team[numOfTeams];
        for (int i = 0; i < teams.length; i++) { //scan all the info about the team
            System.out.print("Team " + (i+1) + ":\n");
            System.out.print("What is the driver's name?\n");
            String name = scanner.next();
            System.out.print("What is the driver's breaking rate?\n");
            double breakingRate = scanner.nextDouble();
            System.out.print("What is the driver's delay time?\n");
            double delayTime = scanner.nextDouble();
            System.out.print("What is the driver's age?\n");
            double age = scanner.nextDouble();
            System.out.print("What is the car's manufacturer?\n");
            String manufacturer = scanner.next();
            System.out.print("What is the car's acceleration?\n");
            double acceleration = scanner.nextDouble();
            System.out.print("What is the car's maximum speed?\n");
            double maxSpeed = scanner.nextDouble();

            Driver driver=new Driver(name,breakingRate,delayTime);

            Car car= new Car(manufacturer,acceleration,maxSpeed);

            teams[i]=new Team(driver,car);//insert all the drivers/cars into array

        }
        return teams;
    }


    public static void runRace(Team[] teams, double[] route) {
        RouteResults[] results = new RouteResults[teams.length];

        for (int i = 0; i < teams.length; i++) {
            results[i] = new RouteResults(teams[i], route);//insert all the score into array
        }
        Arrays.sort(results);
        System.out.print("Race results, sorted from fastest to slowest:\n");

        for (RouteResults str : results) {
            System.out.println(str.toString()+" seconds");
        }

    }

    public static void runAllPairRace(Team[] teams, double[] route) {
        Driver[]drivers=new Driver[teams.length];
        Car[]cars=new Car[teams.length];
        Team[]allTeams=new Team[drivers.length* cars.length];
        RouteResults[] results = new RouteResults[allTeams.length];
        int index=0;
        for (Team team : teams) {//insert all the drivers/cars into array
            drivers[index] = team.getFirst();
             cars[index] = team.getSecond();
             index++;
        }
        int k = 0;
        for (Driver driver : drivers) {
            for (Car car : cars) {
                allTeams[k++] = new Team(driver, car);//combine all the options of driver:car
            }
        }




        for (int i = 0; i < allTeams.length; i++) {
            results[i] = new RouteResults(allTeams[i], route);
        }
        Arrays.sort(results);
        System.out.print("Race results, sorted from fastest to slowest:\n");

        for (RouteResults str : results) {
            System.out.println(str.toString()+" seconds");
        }
    }
}


