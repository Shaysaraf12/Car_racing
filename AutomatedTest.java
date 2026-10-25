import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AutomatedTest {

    public static void main(String[] args) {

        boolean isShorterVersion = true;

        // Defining the routes:
        String[] input_route1 = new String[]{"3", "1200.5", "-1000", "3500", "2100"}; // route segment lengths;
        String[] input_route2 = new String[]{"4", "700", "3000", "100", "500"}; // route segment lengths;

        // Defining the drivers:
        String[] kateDescription = {"Kate", "4", "2.2", "20"};
        String[] suzanDescription = {"Suzan", "5", "1.1", "25"};
        String[] johnDescription = {"John", "6", "2.5", "30"};
        String[] paulDescription = {"Paul", "3", "6.4", "35"};
        String[] ethelDescription = {"Ethel", "4", "10", "90", "3",
                "Don't forget to wear a sweater!",
                "When I was your age we never did any of those things!",
                "Nothing like sittin' on the balcony with a hot cup of tea!"
        };

        // Defining the cars:
        String[] porscheDescription = {"Porsche", "5", "60"};
        String[] lamborghiniDescription = {"Lamborghini", "4.5", "62"};
        String[] bugattiDescription = {"Bugatti", "5.3", "58"};
        String[] ferrariDescription = {"Ferrari", "4.8", "61"};
        String[] sussitaDescription = {"Sussita", "2.5", "24"};


        String[][] drivers, cars;
        if (isShorterVersion) {
            drivers = new String[][]{
                    kateDescription, suzanDescription, johnDescription,
                    paulDescription
            };
            cars = new String[][]{
                    porscheDescription, lamborghiniDescription,
                    bugattiDescription, ferrariDescription
            };
        }
        else {
            drivers = new String[][]{
                    kateDescription, suzanDescription, johnDescription,
                    paulDescription, ethelDescription
            };
            cars = new String[][]{
                    porscheDescription, lamborghiniDescription,
                    bugattiDescription, ferrariDescription, sussitaDescription
            };
        }

        List<String[]> input = new ArrayList<>();

        input.add(new String[]{"-3", "0"}); // Invalid route lengths
        input.add(input_route1);
        input.add(new String[]{"-5", String.valueOf(drivers.length)});
        for (int i = 0; i < drivers.length; i++) {
            input.add(drivers[i]);
            input.add(cars[i]);
        }
        input.add(input_route2);
        String q1Input = concatenate(input);

        InputStream origIn = System.in;
        try {
            EchoingInputStream inputStream = new EchoingInputStream(
                    new ByteArrayInputStream(q1Input.getBytes()));
            System.setIn(inputStream);

            System.out.println(Test.underscore("Long segment race:"));
            double[] route1 = Race.getRouteFromSysIn();
            Team[] teams = Race.getTeamsFromSysIn();

            Race.runRace(teams, route1);

            System.out.println();
            System.out.println(Test.underscore("Short segment race:"));
            double[] route2 = Race.getRouteFromSysIn();
            Race.runRace(teams, route2);

            System.out.println();
            System.out.println(Test.underscore("All pair race:"));
            Race.runAllPairRace(teams, route1);

        } finally {
            System.setIn(origIn);
        }
    }

    private static String concatenate(List<String[]> strArrays) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String[] arr: strArrays) {
            for (String s: arr) {
                stringBuilder.append(s);
                stringBuilder.append("\n");
            }
        }

        return stringBuilder.toString();
    }

    public static class EchoingInputStream extends InputStream {

        private final InputStream in;
        boolean foundLineEnd;

        public EchoingInputStream(InputStream in) {
            this.in = in;
            foundLineEnd = false;
        }

        @Override
        public int read() throws IOException {
            if (foundLineEnd) {
                foundLineEnd = false;
                return -1;
            }
            else {
                int b = in.read();
                System.out.print((char) b);
                if (b == 10) {
                    // '\n' character, signaling end of string
                    foundLineEnd = true;
                }
                return b;
            }
        }
    }
}
