package in.maxwellchristian.androiddemos.custom_toast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import in.maxwellchristian.androiddemos.R;

public class CustomToastActivity extends AppCompatActivity {

    EditText etCustomToastText;
    Button btnShowCustomToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_toast);

        etCustomToastText = findViewById(R.id.etCustomToastText);
        btnShowCustomToast = findViewById(R.id.btnShowCustomToast);

        btnShowCustomToast.setOnClickListener(view -> {

            LinearLayout llCustomToast = (LinearLayout) getLayoutInflater().inflate(R.layout.custom_toast_layout, null, false);
            TextView tvCustomToastMessage = llCustomToast.findViewById(R.id.tvCustomToastMessage);

            tvCustomToastMessage.setText(etCustomToastText.getText());

            Toast customToast = new Toast(CustomToastActivity.this);
            customToast.setView(llCustomToast);
            customToast.setDuration(Toast.LENGTH_SHORT);
            customToast.show();

        });
    }
}