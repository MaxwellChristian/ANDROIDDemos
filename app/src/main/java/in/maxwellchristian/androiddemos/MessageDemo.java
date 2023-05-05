package in.maxwellchristian.androiddemos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MessageDemo extends AppCompatActivity {

    EditText etMessage;
    Button btnShow;
    TextView tvMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_demo);

        // link/connect controls from layout to variables in the activity
        etMessage = findViewById(R.id.etMessage);
        btnShow = findViewById(R.id.btnShow);
        tvMessage = findViewById(R.id.tvMessage);

        // add the appropriate event listeners for controls
        btnShow.setOnClickListener(view -> {
            tvMessage.setText(etMessage.getText());
        });
    }
}