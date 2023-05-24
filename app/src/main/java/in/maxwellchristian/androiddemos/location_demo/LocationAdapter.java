package in.maxwellchristian.androiddemos.location_demo;

import android.content.Context;
import android.location.Location;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import in.maxwellchristian.androiddemos.R;

public class LocationAdapter extends
        RecyclerView.Adapter<LocationAdapter.ViewHolder> implements
        View.OnClickListener {

    private ArrayList<Location> locations;

    LayoutInflater layoutInflater;

    public LocationAdapter(Context context,
                           ArrayList<Location> alLocations) {
        this.locations = alLocations;
        layoutInflater = LayoutInflater.from(context);
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Location> locations) {
        this.locations = locations;
    }

    @NonNull
    @Override
    public LocationAdapter.ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                layoutInflater.inflate(R.layout.item_location_list, parent,
                        false));
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.ViewHolder holder,
                                 int position) {
        Location location = locations.get(position);

        // set the current local date
        holder.tvLocationDate.setText(LocalDate.now().toString());

        // set the current local time with zone
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(
                "H:m:s Z");
        holder.tvLocationTime.setText(
                ZonedDateTime.now().format(dateTimeFormatter));

        // set the coordinates
        holder.tvLocationCoordinates.setText(
                location.getLatitude() + ", " + location.getLongitude());
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    @Override
    public void onClick(View v) {

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvLocationDate;
        TextView tvLocationTime;
        TextView tvLocationCoordinates;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvLocationDate = itemView.findViewById(R.id.tvMyLocationDate);
            tvLocationTime = itemView.findViewById(R.id.tvMyLocationTime);
            tvLocationCoordinates =
                    itemView.findViewById(R.id.tvMyLocationCoordinates);
        }
    }
}
