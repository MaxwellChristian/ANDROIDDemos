package in.maxwellchristian.androiddemos.shared_preferences_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.util.Date;

import in.maxwellchristian.androiddemos.R;

public class SharedPreferencesActivity extends AppCompatActivity {

    Button btnWriteToSharedPreferences;
    Button btnReadFromSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        btnWriteToSharedPreferences =
                findViewById(R.id.btnWriteToSharedPreferences) ;
        btnReadFromSharedPreferences =
                findViewById(R.id.btnReadFromSharedPreferences);

        btnWriteToSharedPreferences.setOnClickListener(view -> {
            writeToSharedPreferences();
        });

        btnReadFromSharedPreferences.setOnClickListener(view -> {
            readFromSharedPreferences();
        });
    }

    private void readFromSharedPreferences() {

        // get the reference to shared preferences for the app
        SharedPreferences sPrefs =
                SharedPreferencesActivity.this.getSharedPreferences("MyAppSP",
                        MODE_PRIVATE);

        // fetch the value as per the key
        String lastValueStored =
                sPrefs.getString("last used", "No last value found");

        Toast.makeText(SharedPreferencesActivity.this, lastValueStored,
                Toast.LENGTH_SHORT).show();

        for (String key: sPrefs.getAll().keySet()){
            Log.d(key, sPrefs.getString(key, "No value found"));
        }

    }

    private void writeToSharedPreferences() {

        // get the reference to shared preferences for the app
        SharedPreferences sPrefs =
                SharedPreferencesActivity.this.getSharedPreferences("MyAppSP",
                        MODE_PRIVATE);

        // write the key value pair to the shared preferences

            // a shared preference editor is required to modify/add the
        // shared preferences
        SharedPreferences.Editor spEditor = sPrefs.edit();

        // store the key value pair to the shard preferences
        spEditor.putString("last used", new Date().getTime() + "");

        // to save the key value pair, commit is mandatory
        spEditor.commit();
    }
}