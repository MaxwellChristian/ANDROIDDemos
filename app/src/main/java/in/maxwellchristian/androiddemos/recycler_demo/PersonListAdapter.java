package in.maxwellchristian.androiddemos.recycler_demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import in.maxwellchristian.androiddemos.R;

public class PersonListAdapter extends
        RecyclerView.Adapter<PersonListAdapter.ViewHolder> {

    private final Context context;
    private ArrayList<Person> people;
    private LayoutInflater layoutInflater;

    public PersonListAdapter(Context context, ArrayList<Person> people) {
        this.context = context;
        this.people = people;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                         int viewType) {
        // used to get/fetch/inflate the view for each record
        return new ViewHolder(
                layoutInflater.inflate(R.layout.persom_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // used to bind the controls from the view holder to the individual
        // record values
        Person person = people.get(position);
        holder.tvName.setText(person.getName());
        holder.tvAddress.setText(person.getAddress());
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        // declaration of controls to bind from the view for individual records
        TextView tvName;
        TextView tvAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // bind the controls using the IDs
            tvName = itemView.findViewById(R.id.tvName);
            tvAddress = itemView.findViewById(R.id.tvAddress);

            // attach the event listeners [if needed]
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedItem = getBindingAdapterPosition();
            Toast.makeText(PersonListAdapter.this.context,
                            people.get(clickedItem).getName(), Toast.LENGTH_SHORT)
                    .show();
        }
    }
}
