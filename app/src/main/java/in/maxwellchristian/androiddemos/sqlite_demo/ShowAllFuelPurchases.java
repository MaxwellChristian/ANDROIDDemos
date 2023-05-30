package in.maxwellchristian.androiddemos.sqlite_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import in.maxwellchristian.androiddemos.R;

public class ShowAllFuelPurchases extends AppCompatActivity {

    FuelPurchaseAdapter fuelPurchaseAdapter;
    RecyclerView rvFuelPurchases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // fetch the received extra values
        Bundle bundle = getIntent().getExtras();

        // retrieve the transactions from the bundle of key-value pairs received
        ArrayList<FuelPurchase> fuelPurchaseTransactions =
                (ArrayList<FuelPurchase>) bundle.get(FuelTrackerActivity.KEY_EXTRAS_FUEL_PURCHASES);

        // create the adapter using the applicable context and the value items
        fuelPurchaseAdapter =
                new FuelPurchaseAdapter(ShowAllFuelPurchases.this,
                        fuelPurchaseTransactions);

        // load the layout to view
        setContentView(R.layout.activity_show_all_fuel_purchases);

        // bind the controls from the layout
        rvFuelPurchases = findViewById(R.id.rvFuelPurchases);

        // apply the appropriate layout manager to the container view
        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(ShowAllFuelPurchases.this);
        rvFuelPurchases.setLayoutManager(linearLayoutManager);

        // set the adapter to the container view
        rvFuelPurchases.setAdapter(fuelPurchaseAdapter);
    }
}