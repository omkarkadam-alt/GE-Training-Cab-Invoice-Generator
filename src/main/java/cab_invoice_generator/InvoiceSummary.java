package cab_invoice_generator;

public class InvoiceSummary {

    public double numberOfFares;
    public double totalFare;
    public double averageFare;

    public InvoiceSummary(double numberOfFares, double totalFare){
        this.numberOfFares = numberOfFares;
        this.totalFare = totalFare;
        this.averageFare = this.totalFare/this.numberOfFares;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        InvoiceSummary other = (InvoiceSummary) obj;
        if (Double.doubleToLongBits(numberOfFares) != Double.doubleToLongBits(other.numberOfFares))
            return false;
        if (Double.doubleToLongBits(totalFare) != Double.doubleToLongBits(other.totalFare))
            return false;
        if (Double.doubleToLongBits(averageFare) != Double.doubleToLongBits(other.averageFare))
            return false;
        return true;
    }    
}

