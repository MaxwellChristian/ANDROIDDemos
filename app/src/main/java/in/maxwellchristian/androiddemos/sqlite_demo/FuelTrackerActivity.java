package in.maxwellchristian.androiddemos.sqlite_demo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import in.maxwellchristian.androiddemos.R;

public class FuelTrackerActivity extends AppCompatActivity {

    public static final String KEY_EXTRAS_FUEL_PURCHASES =
            "KEY_EXTRAS_FUEL_PURCHASES";
    private Spinner spinnerDate;

    private EditText etID;
    private EditText etDate;
    private EditText etLitres;
    private EditText etCost;

    private Button btnNew;
    private Button btnDelete;
    private Button btnSave;

    private Button btnShowAllRecords;

    private FuelDBHelper fuelDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fuelDBHelper = new FuelDBHelper(FuelTrackerActivity.this);

        setContentView(R.layout.activity_tracker_fuel);

        bindControls();

        refreshData();

        btnNew.setOnClickListener(this::newClicked);
        btnDelete.setOnClickListener(this::deleteClicked);
        btnSave.setOnClickListener(this::saveClicked);
        btnShowAllRecords.setOnClickListener(this::showAllRecordsClicked);

    }

    private void showAllRecordsClicked(View view) {

        Cursor cursor = fuelDBHelper.getAllFuelPurchases();
        ArrayList<FuelPurchase> transactions = fuelDBHelper.toList(cursor);

        Intent nextActivityIntent = new Intent(FuelTrackerActivity.this,
                ShowAllFuelPurchases.class);
        nextActivityIntent.putExtra(KEY_EXTRAS_FUEL_PURCHASES, transactions);
        FuelTrackerActivity.this.startActivity(nextActivityIntent);
    }

    private void saveClicked(View view) {
        if (etID.getText().length() > 0) {
            if (updateFuelPurchase() > 0) {
                refreshData();
            }
        } else {
            if (addFuelPurchase() > 0) {
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
            confirmDelete();
        }
    }

    private void confirmDelete() {

        AlertDialog.Builder builder =
                new AlertDialog.Builder(FuelTrackerActivity.this);

        builder.setCancelable(false);

        builder.setTitle("Confirm Delete");
        builder.setMessage("Sure to delete this record?");

        builder.setPositiveButton("Yes",
                (dialog, which) -> {
                    if (deleteFuelRecord() > 0) {
                        // refresh the data in the spinner
                        refreshData();
                        Toast.makeText(FuelTrackerActivity.this, "Record " +
                                        "successfully deleted",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        builder.setNegativeButton("No", null);

        AlertDialog confirmDialog = builder.create();
        confirmDialog.show();
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

        spinnerDate.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view,
                                               int position, long id) {
                        Cursor fetchedFuelPurchase =
                                (Cursor) parent.getItemAtPosition(position);

                        showRecord(fetchedFuelPurchase);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        Toast.makeText(FuelTrackerActivity.this, "No record " +
                                "selected", Toast.LENGTH_SHORT).show();
                    }
                });

        spinnerDate.setAdapter(adapter);

        fuelDBHelper.close();
    }

    private void showRecord(Cursor fetchedFuelPurchase) {
        etID.setText(fetchedFuelPurchase.getString(0));
        etDate.setText(fetchedFuelPurchase.getString(1));
        etLitres.setText(fetchedFuelPurchase.getString(2));
        etCost.setText(fetchedFuelPurchase.getString(3));
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
        btnDelete = findViewById(R.id.btnDelete);
        btnSave = findViewById(R.id.btnSave);
        btnShowAllRecords = findViewById(R.id.btnShowAllRecords);
    }

}