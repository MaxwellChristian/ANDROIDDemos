package in.maxwellchristian.androiddemos.recycler_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Date;

import in.maxwellchristian.androiddemos.R;
import in.maxwellchristian.androiddemos.shared_preferences_demo.SharedPreferencesActivity;

public class RecyclerDemo extends AppCompatActivity {

    RecyclerView rvItemList;
    ArrayList<Person> people;
    PersonListAdapter adapter;

    private void writeToSharedPreferences() {

        // get the reference to shared preferences for the app
        SharedPreferences sPrefs =
                RecyclerDemo.this.getSharedPreferences("MyAppSP",
                        MODE_PRIVATE);

        // write the key value pair to the shared preferences

        // a shared preference editor is required to modify/add the
        // shared preferences
        SharedPreferences.Editor spEditor = sPrefs.edit();

        // store the key value pair to the shard preferences
        spEditor.putString("Recycler Demo", new Date().toString());

        // to save the key value pair, commit is mandatory
        spEditor.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        writeToSharedPreferences();

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