package cab_invoice_generator;

public class InvoiceGenerator {

    static final double COST_PER_MINUTE = 1.0;
    static final double COST_PER_KM = 10.0;
    static final double MINIMUM_FARE = 5.0;

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
    
}
