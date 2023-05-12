package in.maxwellchristian.androiddemos.menu_demos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import in.maxwellchristian.androiddemos.Login;
import in.maxwellchristian.androiddemos.MessageDemo;
import in.maxwellchristian.androiddemos.R;
import in.maxwellchristian.androiddemos.TipCalculator;
import in.maxwellchristian.androiddemos.bottom_navigation_demo.BottomNavigationActivity;
import in.maxwellchristian.androiddemos.constraint_layout_demo.ConstraintLayoutDemo;
import in.maxwellchristian.androiddemos.course_demo.CourseActivity;
import in.maxwellchristian.androiddemos.custom_toast.CustomToastActivity;
import in.maxwellchristian.androiddemos.recycler_demo.RecyclerDemo;
import in.maxwellchristian.androiddemos.shared_preferences_demo.SharedPreferencesActivity;


public class AllDemosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_demos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // as per the selected menu item navigate to the appropriate activity

        Intent intent = new Intent();

        if (item.getItemId() == R.id.omiToast) {
            intent.setClass(AllDemosActivity.this, MessageDemo.class);
        }

        if (item.getItemId() == R.id.omiLogin) {
            intent.setClass(AllDemosActivity.this, Login.class);
        }

        if (item.getItemId() == R.id.omiTipCalculator) {
            intent.setClass(AllDemosActivity.this, TipCalculator.class);
        }

        if (item.getItemId() == R.id.omiCustomToast) {
            intent.setClass(AllDemosActivity.this, CustomToastActivity.class);
        }

        if (item.getItemId() == R.id.omiRecyclerDemo) {
            intent.setClass(AllDemosActivity.this, RecyclerDemo.class);
        }

        if (item.getItemId() == R.id.omiConstraintLayoutDemo) {
            intent.setClass(AllDemosActivity.this, ConstraintLayoutDemo.class);
        }

        if (item.getItemId() == R.id.omiBottomNavigationDemo) {
            intent.setClass(AllDemosActivity.this, BottomNavigationActivity.class);
        }

        if (item.getItemId() == R.id.omiFragmentsDemo) {
            intent.setClass(AllDemosActivity.this, CourseActivity.class);
        }

        if (item.getItemId() == R.id.omiSharedPreferencesDemo) {
            intent.setClass(AllDemosActivity.this, SharedPreferencesActivity.class);
        }

        AllDemosActivity.this.startActivity(intent);

        return super.onOptionsItemSelected(item);
    }
}