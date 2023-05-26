package in.maxwellchristian.androiddemos.dialog_demo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import in.maxwellchristian.androiddemos.R;

public class DialogDemoActivity extends AppCompatActivity {

    Button btnAlertDialog;
    Button btnCustomDialog;

    Button btnDatePicker;
    Button btnTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_demo);

        btnAlertDialog = findViewById(R.id.btnAlertDialog);
        btnAlertDialog.setOnClickListener(v -> {
            showAlertDialog();
        });

        btnCustomDialog = findViewById(R.id.btnCustomDialog);
        btnCustomDialog.setOnClickListener(v -> {
            showCustomDialog();
        });

        btnDatePicker = findViewById(R.id.btnDatePicker);
        btnDatePicker.setOnClickListener(v -> {
            DatePickerDialog dpDialog =
                    new DatePickerDialog(DialogDemoActivity.this);
            dpDialog.setOnDateSetListener((view, year, month, dayOfMonth) -> {
                // be careful with month which are starting from ZERO
                // i.e. January will be returned as 0
                // i.e. December will be returned as 11
                Toast.makeText(DialogDemoActivity.this,
                        "You selected: " + year + "-" + (month + 1) + "-" +
                                dayOfMonth,
                        Toast.LENGTH_SHORT).show();
            });
            dpDialog.show();
        });

        btnTimePicker = findViewById(R.id.btnTimePicker);
        btnTimePicker.setOnClickListener(v -> {
            TimePickerDialog tpDialog =
                    new TimePickerDialog(DialogDemoActivity.this, (view,
                                                                   hourOfDay,
                                                                   minute) -> {
                        Toast.makeText(DialogDemoActivity.this, "Selected " +
                                        "time: " + hourOfDay + ":" + minute,
                                Toast.LENGTH_SHORT).show();
                    }, 9, 55, true);
            tpDialog.show();
        });

    }

    private void showCustomDialog() {

        // get the builder
        AlertDialog.Builder adBuilder =
                new AlertDialog.Builder(DialogDemoActivity.this);

        // set the properties and the view using the builder
        adBuilder.setCancelable(false);
        adBuilder.setTitle("Terms and conditions");

        View dialogLayout =
                LayoutInflater.from(DialogDemoActivity.this)
                        .inflate(R.layout.dialog_agreement, null);
        adBuilder.setView(dialogLayout);

        // create the dialog box
        AlertDialog aDialog = adBuilder.create();

        Button btnOkFromDialog = dialogLayout.findViewById(R.id.btnOKForDialog);
        btnOkFromDialog.setOnClickListener(v -> {
            aDialog.cancel();
            Toast.makeText(DialogDemoActivity.this, "You accepted the terms " +
                    "and agreement", Toast.LENGTH_SHORT).show();
        });

        // show
        aDialog.show();

    }

    private void showAlertDialog() {
        // get an instance of the dialog builder
        AlertDialog.Builder aadBuilder =
                new AlertDialog.Builder(DialogDemoActivity.this);

        // set the required properties of the dialog box desired
        aadBuilder.setTitle("Survey Alert Dialog");
        aadBuilder.setMessage("How do you like this alert dialog demo?");

        // does not cancel the dialog box when touched outside of the dialog
        // box
        aadBuilder.setCancelable(false);

        // attach any buttons with listeners if required
        aadBuilder.setPositiveButton("Good", (dialog, which) -> {
            Toast.makeText(DialogDemoActivity.this, "We are happy to hear " +
                    "that. Thanks for the support", Toast.LENGTH_SHORT).show();
        });

        aadBuilder.setNegativeButton("Ok", (dialog, which) -> {
            Toast.makeText(DialogDemoActivity.this, "Thanks for your feedback" +
                    ". We will surely get in touch with you for more insights" +
                    ".", Toast.LENGTH_LONG).show();
        });

        aadBuilder.setNeutralButton("Can't Say", (dialog, which) -> {
            Toast.makeText(DialogDemoActivity.this, "No worries.",
                    Toast.LENGTH_SHORT).show();
        });

        // get the alert dialog box using the builder
        AlertDialog aDialog = aadBuilder.create();

        // show the dialog box
        aDialog.show();
    }
}