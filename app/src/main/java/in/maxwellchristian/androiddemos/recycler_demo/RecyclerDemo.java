package in.maxwellchristian.androiddemos.recycler_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

import in.maxwellchristian.androiddemos.R;

public class RecyclerDemo extends AppCompatActivity {

    RecyclerView rvItemList;
    ArrayList<Person> people;
    PersonListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // data structure to hold multiple records
        people = new ArrayList<>();

        // the records to work with
        people.add(new Person("Maxwell", "Add1, Regina"));
        people.add(new Person("Alex", "Add2, Regina"));
        people.add(new Person("Joseph", "Add3, Regina"));
        people.add(new Person("Tahmina", "Add4, Regina"));

        // the layout with the recycler view
        setContentView(R.layout.activity_recycler_demo);

        // the recycler view to display the list of records
        rvItemList = findViewById(R.id.itemView);

        // the layout manager to arrange the output
        LinearLayoutManager llManager = new LinearLayoutManager(RecyclerDemo.this);
        rvItemList.setLayoutManager(llManager);

        // the adapter to hold the multiple records to be used with the
        // recycler view
        adapter = new PersonListAdapter(this, people);

        // attach the adapter to the recycler view
        rvItemList.setAdapter(adapter);
    }
}