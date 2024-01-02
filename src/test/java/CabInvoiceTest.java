import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cab_invoice_generator.InvoiceGenerator;
import cab_invoice_generator.Ride;
import cab_invoice_generator.InvoiceSummary;

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

    @Test
    public void testGenerateSummary(){
        Ride [] rides = {
            new Ride(2.0,5.0),
            new Ride(1.0, 1.0),
            new Ride(0.2, 1.0)
        };

        InvoiceSummary summary = invoiceGenerator.generateFareSummary(rides);
        InvoiceSummary expectedSummary = new InvoiceSummary(3.0, 41.0);
        assertEquals(expectedSummary, summary);
    }

    @Test
    public void testInvoiceReportByUser() {
        Integer user1 = 1;
        Integer user2 = 2;
        invoiceGenerator.addRideRecord(user1, new Ride(10.0, 6));
        invoiceGenerator.addRideRecord(user1, new Ride(0.2, 1));
        invoiceGenerator.addRideRecord(user2, new Ride(1.5, 4));
        

        InvoiceSummary user1Report = invoiceGenerator.getInvoiceReport(user1);
        InvoiceSummary user1ExpectedReport = new InvoiceSummary(2, 111.0);

        InvoiceSummary user2Report = invoiceGenerator.getInvoiceReport(user2);
        InvoiceSummary user2ExpectedReport = new InvoiceSummary(1, 19.0);

        assertEquals(user1ExpectedReport, user1Report);
        assertEquals(user2ExpectedReport, user2Report);
    }

}
