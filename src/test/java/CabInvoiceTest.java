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
    void testCalculatePremiumFare() {
        
        double dist = 5.0;
        int time = 5;
        
        double fare = invoiceGenerator.calculatePremiumFare(dist, time);
        assertEquals(85.0, fare);
    }

    @Test
    public void testMinimumPremiumFare(){

            double dist = 0.2;
            double time = 1.0;
            double fare = invoiceGenerator.calculatePremiumFare(dist,time);
            assertEquals(20.0, fare, 0.01); 
    }


    @Test
    public void testCalculateTotalFare(){

        Ride [] rides = {
            new Ride(2.0,5.0, "normal"),
            new Ride(1.5, 1.0, "premium"),
            new Ride(0.2, 1.0, "premium")
        };

        double fare = invoiceGenerator.calculateFareForMultipleRides(rides);

        assertEquals(69.5, fare, 0.01);
    }

    @Test
    public void testGenerateSummary(){
        Ride [] rides = {
            new Ride(2.0,5.0, "normal"),
            new Ride(1.5, 1.0, "premium"),
            new Ride(0.2, 1.0, "premium")
        };

        InvoiceSummary summary = invoiceGenerator.generateFareSummary(rides);
        InvoiceSummary expectedSummary = new InvoiceSummary(3.0, 69.5);
        assertEquals(expectedSummary, summary);
    }

    @Test
    public void testInvoiceReportByUser() {
        Integer user1 = 1;
        Integer user2 = 2;
        invoiceGenerator.addRideRecord(user1, new Ride(10.0, 6, "normal"));
        invoiceGenerator.addRideRecord(user1, new Ride(0.2, 1, "premium"));
        invoiceGenerator.addRideRecord(user2, new Ride(1.5, 4, "premium"));
        

        InvoiceSummary user1Report = invoiceGenerator.getInvoiceReport(user1);
        InvoiceSummary user1ExpectedReport = new InvoiceSummary(2, 126.0);

        InvoiceSummary user2Report = invoiceGenerator.getInvoiceReport(user2);
        InvoiceSummary user2ExpectedReport = new InvoiceSummary(1, 30.5);

        assertEquals(user1ExpectedReport, user1Report);
        assertEquals(user2ExpectedReport, user2Report);
    }
}
