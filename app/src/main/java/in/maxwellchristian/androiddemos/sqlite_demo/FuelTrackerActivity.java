package in.maxwellchristian.androiddemos.sqlite_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import in.maxwellchristian.androiddemos.R;

public class FuelTrackerActivity extends AppCompatActivity {

    private Spinner spinnerDate;

    private EditText etID;
    private EditText etDate;
    private EditText etLitres;
    private EditText etCost;

    private Button btnNew;
    private Button btnDelete;
    private Button btnSave;
    private FuelDBHelper fuelDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fuelDBHelper = new FuelDBHelper(FuelTrackerActivity.this);

        setContentView(R.layout.activity_tracker_fuel);

        bindControls();

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newClicked(view);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get an editor object so we can write
                deleteClicked(view);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get an editor object so we can write
                saveClicked(view);
            }
        });

    }

    private void saveClicked(View view) {
        if (etID.getText().length() > 0) {
            if (updateFuelPurchase() > 0) {
                refreshData();
            }
        } else {
            if( addFuelPurchase() > 0 ) {
                refreshData();
            }
        }
    }

    private long addFuelPurchase() {
        FuelPurchase purchase = new FuelPurchase(etDate.getText().toString(),
                Double.parseDouble(etLitres.getText().toString()),
                Double.parseDouble(etCost.getText().toString()));

        return fuelDBHelper.addFuelPurchase(purchase);
    }

    private int updateFuelPurchase() {

        FuelPurchase purchase =
                new FuelPurchase(Integer.parseInt(etID.getText().toString()),
                        etDate.getText().toString(),
                        Double.parseDouble(etLitres.getText().toString()),
                        Double.parseDouble(etCost.getText().toString()));

        return fuelDBHelper.updateFuelPurchase(purchase);
    }

    private void deleteClicked(View view) {
        if (etID.getText().length() > 0) {
            if (deleteFuelRecord() > 0) {
                // refresh the data in the spinner
                refreshData();
            }
        }
    }

    private int deleteFuelRecord() {

        FuelPurchase purchase =
                new FuelPurchase(Integer.parseInt(etID.getText().toString()),
                        etDate.getText().toString(),
                        Double.parseDouble(etLitres.getText().toString()),
                        Double.parseDouble(etCost.getText().toString()));

        // delete the record using the helper
        return fuelDBHelper.remove(purchase);
    }

    private void refreshData() {

        Cursor cursor = fuelDBHelper.getAllFuelPurchases();

        spinnerDate = findViewById(R.id.spinnerDate);

        String[] cols = new String[]{FuelDBHelper.PURCHASE_DATE};
        int[] views = new int[]{android.R.id.text1};

        SimpleCursorAdapter adapter =
                new SimpleCursorAdapter(this,
                        android.R.layout.simple_list_item_1, cursor, cols,
                        views);

        spinnerDate.setAdapter(adapter);

        fuelDBHelper.close();
    }

    private void newClicked(View view) {
        etID.setText("");
        etDate.setText("");
        etLitres.setText("");
        etCost.setText("");
    }

    private void bindControls() {
        spinnerDate = findViewById(R.id.spinnerDate);

        etID = findViewById(R.id.etID);
        etID.setEnabled(false);

        etDate = findViewById(R.id.etDate);
        etLitres = findViewById(R.id.etLitres);
        etCost = findViewById(R.id.etCost);

        btnNew = findViewById(R.id.btnNew);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnSave = (Button) findViewById(R.id.btnSave);
    }

}