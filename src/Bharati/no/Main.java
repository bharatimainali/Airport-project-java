package Bharati.no;

import java.util.Locale;
import java.util.Scanner;

public class Main {

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in).useLocale(Locale.US);
            System.out.println("How many time units should the simulation run?: ");
            int n = sc.nextInt();

            System.out.println("Enpected number of arrivals per time unit?:");
            double avg_arrivals = sc.nextDouble();

            System.out.println("Enpected number of departures per time unit?:");
            double avg_departures = sc.nextDouble();

            Airport air = new Airport(n, avg_arrivals, avg_departures);
            air.run_simulation();
            air.result();

        }

    }

