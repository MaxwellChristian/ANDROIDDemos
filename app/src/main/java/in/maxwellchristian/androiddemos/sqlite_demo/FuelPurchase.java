package in.maxwellchristian.androiddemos.sqlite_demo;

public class FuelPurchase {

    //Identifier for specific purchase
    public long id;

    //Data fields for date, litres and cost
    public String date = "";
    public double litres = 0.0;
    public double cost = 0.0;

    public FuelPurchase(String date, double litres, double cost) {
        this.date = date;
        this.litres = litres;
        this.cost = cost;
    }

    public FuelPurchase(long id, String date, double litres, double cost) {
        this.id = id;
        this.date = date;
        this.litres = litres;
        this.cost = cost;
    }
}
