package cab_invoice_generator;

public class Ride {
    public double time;
    public double distance;
    private String type;
    public Ride(double distance, double time, String type) {
        this.time = time;
        this.distance = distance;
        this.type = type;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    
}
