package in.maxwellchristian.androiddemos.sqlite_demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.maxwellchristian.androiddemos.R;

public class FuelPurchaseAdapter extends
        RecyclerView.Adapter<FuelPurchaseAdapter.ViewHolder> {


    private final Context context;
    private final ArrayList<FuelPurchase> fuelPurchaseTransactions;

    public FuelPurchaseAdapter(Context context,
                               ArrayList<FuelPurchase> fuelPurchaseTransactions) {
        this.context = context;
        this.fuelPurchaseTransactions = fuelPurchaseTransactions;
    }

    @NonNull
    @Override
    public FuelPurchaseAdapter.ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_card_fuel_purchase, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FuelPurchaseAdapter.ViewHolder holder,
                                 int position) {
        FuelPurchase fuelPurchase = fuelPurchaseTransactions.get(position);

//        holder.tvPurchaseDate.setText(fuelPurchase.date);
//        holder.tvPurchasedLitres.setText( fuelPurchase.litres + " litres");
//        holder.tvPurchaseAtRate.setText("@"+ fuelPurchase.cost + " CAD");
//        holder.tvPurchaseCost.setText( String.valueOf(fuelPurchase.litres*fuelPurchase.cost) + " CAD");

        holder.tvPurchaseDate.setText(fuelPurchase.date);
        holder.tvPurchasedLitres.setText(context.getString(
                R.string.fuel_purchase_message_show_litres,
                fuelPurchase.litres));
        holder.tvPurchaseAtRate.setText(context.getString(R.string.fuel_purchase_message_show_rate, fuelPurchase.cost, "CAD"));
        holder.tvPurchaseCost.setText(context.getString(R.string.fuel_purchase_message_show_cost, fuelPurchase.litres * fuelPurchase.cost, "CAD"));

    }

    @Override
    public int getItemCount() {
        return fuelPurchaseTransactions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvPurchaseDate;
        TextView tvPurchasedLitres;
        TextView tvPurchaseAtRate;
        TextView tvPurchaseCost;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPurchaseDate = itemView.findViewById(R.id.tvPurchaseDate);
            tvPurchasedLitres = itemView.findViewById(R.id.tvPurchasedLitres);
            tvPurchaseAtRate = itemView.findViewById(R.id.tvPurchaseAtRate);
            tvPurchaseCost = itemView.findViewById(R.id.tvPurchaseCost);
        }
    }
}
