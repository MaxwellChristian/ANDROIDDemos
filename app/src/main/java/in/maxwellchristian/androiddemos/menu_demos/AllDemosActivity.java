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
import in.maxwellchristian.androiddemos.custom_toast.CustomToastActivity;

public class AllDemosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_demos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
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

        AllDemosActivity.this.startActivity(intent);

        return super.onOptionsItemSelected(item);
    }
}