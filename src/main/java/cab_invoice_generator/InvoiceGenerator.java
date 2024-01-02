package cab_invoice_generator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InvoiceGenerator {

    static Map<Integer, ArrayList<Ride>> rideRepository;

    public InvoiceGenerator(){
        rideRepository = new HashMap<Integer, ArrayList<Ride>>();
    }

    static final double COST_PER_MINUTE = 1.0;
    static final double COST_PER_KM = 10.0;
    static final double MINIMUM_FARE = 5.0;

    public void addRideRecord(Integer userId, Ride rideRecord) {
        if (rideRepository.containsKey(userId) == false) {
            rideRepository.put(userId, new ArrayList<Ride>());
        }
        rideRepository.get(userId).add(rideRecord);
    }

    public double calculateFare(double dist, double time) {

        return Math.max(MINIMUM_FARE, (COST_PER_KM * dist) + (COST_PER_MINUTE * time));
    }

    public double calculateFareForMultipleRides(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += calculateFare(ride.distance, ride.time);
        }
        return totalFare;
    }

    public InvoiceSummary generateFareSummary(Ride rides[]){
        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += calculateFare(ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    public InvoiceSummary getInvoiceReport(Integer userID) {
        
        if (rideRepository.containsKey(userID) == false) {
            return new InvoiceSummary(0, 0);
        }

        ArrayList<Ride> userRides = rideRepository.get(userID);

        int totalRides = userRides.size();

        double totalFare = 0.0;
        for (Ride ride : userRides) {
            totalFare += calculateFare(ride.distance, ride.time);
        }

        return new InvoiceSummary(totalRides, totalFare);
    }

    
}
