package in.maxwellchristian.androiddemos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText etUsername;
    EditText etPassword;
    Button btnLogin;

    TextView tvForgotPassword;
    TextView tvDial;
    TextView tvCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        tvForgotPassword = findViewById(R.id.tvForgotPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(view -> {

            // check for the correct credentials
            if (etUsername.getText().toString().equals("maxwell") && etPassword.getText().toString().equals("maxwell")) {

                // to move/transit to a new activity follow the below steps

                // 1. create an intent which specifies the start and the destination activities
                Intent nextActivity = new Intent();
                nextActivity.setClass(Login.this, ListOfDemos.class);

                // alternate way of creating the intent
                // Intent intent = new Intent(Login.this, ListOfDemos.class);

                // if you want to pass values to the next activity, follow the below steps
                nextActivity.putExtra("username", etUsername.getText().toString());

                // 2. start the activity using intent
                Login.this.startActivity(nextActivity);

                // if you do no wish to come back to the starting activity
                // then complete/finish the starting activity
                Login.this.finish();

            } else {
                // show a message as a pop up
                Toast.makeText(Login.this, "Incorrect username or password", Toast.LENGTH_SHORT).show();
            }

        });

        tvForgotPassword.setOnClickListener(view -> {

            // navigate to the URL using browser

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
            Login.this.startActivity(browserIntent);

        });

        tvDial = findViewById(R.id.tvDialCustomerCare);
        tvCall = findViewById(R.id.tvCallExpert);

        tvDial.setOnClickListener(view -> {
            Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:3069990000"));
            Login.this.startActivity(dialIntent);
        });

        tvCall.setOnClickListener(view -> {

            if (ActivityCompat.checkSelfPermission(Login.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Login.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
            }
            else {
                callExpert("tel:3069990000");
            }

        });

    }

    private void callExpert(String number) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse(number));

        Login.this.startActivity(callIntent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {

            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
                    callExpert("tel:3069990000");
                } else {
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}