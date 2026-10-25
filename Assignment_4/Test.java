public class Test {

    public static void main(String[] args) {
        // Testing question 1:
        System.out.println(underscore("Please entre a rote:"));
        double[] route = Race.getRouteFromSysIn();

        System.out.println();
        System.out.println(underscore("Please entre a set of teams:"));
        Team[] teams = Race.getTeamsFromSysIn();

        System.out.println();
        System.out.println(underscore("Race results:"));
        Race.runRace(teams, route);

        // Testing question 2:
        System.out.println();
        System.out.println(underscore("Running all pair race:"));
        Race.runAllPairRace(teams, route);
    }

    public static String underscore(String s){
        return s + "\n" + "-".repeat(s.length());
    }
}
