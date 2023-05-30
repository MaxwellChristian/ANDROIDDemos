package in.maxwellchristian.androiddemos.sqlite_demo;

import android.database.Cursor;

import java.io.Serializable;
import java.util.List;

public class FuelPurchase implements Serializable {

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
