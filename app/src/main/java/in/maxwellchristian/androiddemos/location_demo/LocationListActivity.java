package in.maxwellchristian.androiddemos.location_demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import in.maxwellchristian.androiddemos.R;

public class LocationListActivity extends AppCompatActivity {

    private static final int CODE_REQUEST_PERMISSION_LOCATION = 0;
    RecyclerView rvLocationList;

    private Location location;

    ArrayList<Location> alLocations;
    private LocationAdapter adapter;
    private LinearLayoutManager rvLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        alLocations = new ArrayList<>();

        setContentView(R.layout.activity_location_list);

        rvLocationList = findViewById(R.id.rvLocationList);

        rvLayoutManager =
                new LinearLayoutManager(LocationListActivity.this);
        rvLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvLocationList.setLayoutManager(rvLayoutManager);

        DividerItemDecoration dividerItem =
                new DividerItemDecoration(LocationListActivity.this,
                        rvLayoutManager.getOrientation());
        rvLocationList.addItemDecoration(dividerItem);

        ArrayList<Location> alLocations = new ArrayList<Location>();
        adapter =
                new LocationAdapter(LocationListActivity.this, alLocations);

        fetchLocations();
    }

    private void fetchLocations() {

        LocationManager locationManager =
                (LocationManager) LocationListActivity.this.getSystemService(
                        LOCATION_SERVICE);

        // check if the list is empty, then add the new location
        // check if the new fetched location is not the last location
        // visited
        LocationListener locationListener = location -> {
            LocationListActivity.this.location = location;
            updateLocation();
        };

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) !=
                        PackageManager.PERMISSION_GRANTED) {

            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    CODE_REQUEST_PERMISSION_LOCATION);
            return;
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    100, 0, locationListener);

            rvLocationList.setAdapter(adapter);
        }

    }

    private void updateLocation() {
        ArrayList<Location> locationsInAdapter =
                ((LocationAdapter) Objects.requireNonNull(
                        rvLocationList.getAdapter())).getLocations();

        // check if the list is empty, then add the new location
        if (adapter.getItemCount() == 0) {
            alLocations.add(location);
            showLocation(alLocations, location);
        } else {
            // check if the new fetched location is not the last location
            // visited

            Location lastLocation =
                    locationsInAdapter.get(locationsInAdapter.size() - 1);

            Log.d("Last Location",
                    lastLocation.toString());

            if (location.getLatitude() !=
                    lastLocation.getLatitude() && location.getLongitude() !=
                    lastLocation.getLongitude()) {
                alLocations.add(location);
                showLocation(alLocations, location);
            }

        }
    }

    private void showLocation(ArrayList<Location> alLocations,
                              Location location) {
        adapter.setLocations(alLocations);

        // adapter.notifyDataSetChanged();
        adapter.notifyItemInserted(adapter.getLocations().indexOf(location));

        rvLocationList.smoothScrollToPosition(
                Objects.requireNonNull(rvLocationList.getAdapter())
                        .getItemCount() - 1);

        Log.d("Location",
                location.toString() + " : added at : " +
                        alLocations.indexOf(location));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions,
                grantResults);

        if (requestCode == CODE_REQUEST_PERMISSION_LOCATION) {
            if (grantResults.length > 0
                    &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                // if location permission granted then display the
                // fetched location


                // add the updated location to the list
                alLocations.add(location);
            } else {
                // display an appropriate message when the permission is
                // denied
                Toast.makeText(getApplicationContext(),
                        "Location Permission denied",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

}