package in.maxwellchristian.androiddemos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class TipCalculator extends AppCompatActivity {

    EditText etAmount;
    EditText etPercentage;
    EditText etTip;

    Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);

        etAmount = findViewById(R.id.etAmount);
        etPercentage = findViewById(R.id.etPercentage);
        etTip = findViewById(R.id.etTip);

        btnCalculate = findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(view -> {

            double amount = Double.parseDouble(String.valueOf(etAmount.getText())) ;
            double percentage = Double.parseDouble(String.valueOf(etPercentage.getText())) ;

            double tip = amount * percentage / 100;

            etTip.setText(String.valueOf(tip));
        });
    }
}




