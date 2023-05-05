package in.maxwellchristian.androiddemos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

public class ListOfDemos extends AppCompatActivity {

    TextView tvLoggedUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_demos);

        // receive all the values transferred through intent
        Bundle bundle = getIntent().getExtras();

        // use the key to fetch the value received
        String userName = bundle.getString("username");

        // display the received value

        // currently for debugging, following code logs the value
        Log.d("Received username", userName);

        tvLoggedUsername = findViewById(R.id.tvLoggedUserName);

        // tvLoggedUsername.setText("Welcome " + userName.toUpperCase());
        tvLoggedUsername.setText(getResources().getText(R.string.message_welcome) + userName.toUpperCase());
    }
}