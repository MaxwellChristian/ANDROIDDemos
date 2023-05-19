package in.maxwellchristian.androiddemos.location_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import in.maxwellchristian.androiddemos.R;

public class TestLocationActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_LOCATION_PERMISSION = 1;
    TextView tvLatitude;
    TextView tvLongitude;
    private LocationListener locationListener;
    private LocationManager locationManager;
    private Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_location);

        tvLatitude = findViewById(R.id.tvLatitude);
        tvLongitude = findViewById(R.id.tvLongitude);

        // get the system service of location fetch
        locationManager =
                (LocationManager) TestLocationActivity.this.getSystemService(
                        LOCATION_SERVICE);

        // bind the location listener, so that when ever the location changes,
        // the listener provides back the new location
        locationListener = location -> {
            TestLocationActivity.this.location = location;
            displayLocation(TestLocationActivity.this.location);
        };

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {
            return;
        }

        // request the location updates using provider. This will provide the
        // updated location and the responsible listener will fetch it
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                0, 0, locationListener);

        // display/use the fetched/provided location
        displayLocation(TestLocationActivity.this.location);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions,
                grantResults);

        switch (requestCode) {
            case REQUEST_CODE_LOCATION_PERMISSION:
                if (grantResults.length > 0
                        &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    // if location permission granted then display the
                    // fetched location
                    displayLocation(location);
                } else {
                    // display an appropriate message when the permission is
                    // denied
                    Toast.makeText(getApplicationContext(),
                            "Location Permission denied",
                            Toast.LENGTH_SHORT).show();
                }

                break;
            default:
                break;
        }
    }

    private void displayLocation(Location location) {

        // check if location fetch is permitted
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {

            // if not permitted, then request the permissions
            ActivityCompat.requestPermissions(TestLocationActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_CODE_LOCATION_PERMISSION);

        } else {

            // if permitted, then display the fetched location
            if (location != null) {
                tvLatitude.setText(String.valueOf(location.getLatitude()));
                tvLongitude.setText(String.valueOf(location.getLongitude()));
            }

        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        locationManager.removeUpdates(locationListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayLocation(location);
    }
}