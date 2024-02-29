package Bharati.no;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class Airport {

    private final int n;
    private final double avg_arrivals, avg_departures;

    private final Queue<Flight> landing, departure;
    private int landed,taken_off,rejected,free_time,landing_Wait,departure_wait;

    private int getPoissonRandom(double mean) {
        Random r = new Random();
        double L = Math.exp(-mean);
        int k = 0;
        double p = 1.0;
        do {
            p = p * r.nextDouble();
            k++;
        } while (p > L);
        return k - 1;
    }

    public Airport(int n, double avg_arrivals, double avg_departures) {
        this.n = n;
        this.avg_arrivals = avg_arrivals;
        this.avg_departures = avg_departures;
        landing = new LinkedList<>();
        departure = new LinkedList<>();



    }
    public void result()
    {
        System.out.println("The simulation finished after "+n+" time units");
        System.out.println("Total number of aircraft processed : "+n);
        System.out.println("Number of flights landed : "+landed);
        System.out.println("Number of flights taken by : "+taken_off);
        System.out.println("Number of flights rejected : "+rejected);
        System.out.println("Number of aircrafts ready for landing : "+landing.size());
        System.out.println("Number of planes ready to take off : "+departure.size());
        double percentage = ((double)free_time/(double)n)*100;
        System.out.println("Percentage of free time: "+percentage);
        double avg_landing = (((double)landing_Wait/(double)(landing_Wait+departure_wait))*100);
        double avg_dep = (((double)departure_wait/(double)(landing_Wait+departure_wait))*100);
        System.out.println("Avg. waiting time, landing "+avg_landing+" time units");
        System.out.println("Avg. waiting time, departure "+avg_dep+" time units");



    }

    public void run_simulation() {

        for (int i = 0; i < n; i++) {
            // for landing
            int no_of_planes_to_land = getPoissonRandom(avg_arrivals);
            for (int j = 0; j < no_of_planes_to_land; j++) {
                // TO check if the landing queue is full
                if (landing.size() == 10) {
                    System.out.println("Landing queue is full");
                    rejected++;
                } else {
                    landing.add(new Flight());
                }
            }

            // for departure
            int no_of_planes_to_depart = getPoissonRandom(avg_departures);
            for (int j = 0; j < no_of_planes_to_depart; j++) {
                // TO check if the departure queue is full
                if (departure.size() == 10) {
                    System.out.println("Departure queue is full");
                    rejected++;
                } else {
                    departure.add(new Flight());
                }
            }

            System.out.print((i + 1) + ": ");
            for (Flight flight : landing) {
                flight.set_waiting_time();
                System.out.println(flight.getMessage("landing"));
            }
            for (Flight flight : departure) {
                flight.set_waiting_time();
                System.out.println(flight.getMessage("departure"));
            }
            if (!landing.isEmpty()) {

                Flight f = landing.poll();
                landed++;
                landing_Wait+=f.get_waiting_time()-1;
                System.out.println(" Flight " + f.getFlight_no() + " landed, waiting time " + (f.get_waiting_time() - 1) + " units");

            } else if (!departure.isEmpty()) {
                // implement for departure

                Flight f = departure.poll();
                taken_off++;
                departure_wait+=f.get_waiting_time()-1;
                System.out.println(" Flight " + f.getFlight_no() + " taken off, waiting time " + (f.get_waiting_time() - 1) + " units");

            } else {
                System.out.println("The Airport is Empty");
                free_time++;
            }

        }

    }

}

