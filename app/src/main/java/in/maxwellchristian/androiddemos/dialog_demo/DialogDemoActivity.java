package in.maxwellchristian.androiddemos.dialog_demo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import in.maxwellchristian.androiddemos.R;

public class DialogDemoActivity extends AppCompatActivity {

    Button btnAlertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_demo);

        btnAlertDialog = findViewById(R.id.btnAlertDialog);
        btnAlertDialog.setOnClickListener(v -> {
            showAlertDialog();
        });
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