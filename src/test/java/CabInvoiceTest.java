import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cab_invoice_generator.InvoiceGenerator;
import cab_invoice_generator.Ride;

public class CabInvoiceTest {

    InvoiceGenerator invoiceGenerator = null;

    @BeforeEach
    void setUp() {
        invoiceGenerator = new InvoiceGenerator();
    }

    @Test
    void testCalculateFare() {
        
        double dist = 5.0;
        int time = 5;
        
        double fare = invoiceGenerator.calculateFare(dist, time);
        assertEquals(55.0, fare);
    }

    @Test
    public void testMinimumFare(){

            double dist = 0.2;
            double time = 1.0;
            double fare = invoiceGenerator.calculateFare(dist,time);
            assertEquals(5.0, fare, 0.01); 
    }

    @Test
    public void testCalculateTotalFare(){

        Ride [] rides = {
            new Ride(2.0,5.0),
            new Ride(1.0, 1.0),
            new Ride(0.2, 1.0)
        };

        double fare = invoiceGenerator.calculateFareForMultipleRides(rides);

        assertEquals(41.0, fare, 0.01);
    }
}
