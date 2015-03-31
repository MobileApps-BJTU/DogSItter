package com.example.ontheroad;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.interfaces.OnFragmentInteractionListener;


public class MainActivity extends Activity implements OnFragmentInteractionListener {

    private FragmentManager fm;
    private SharedPreferences savedSearches;
    private static final String SEARCHES = "steps_per_day";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getFragmentManager();
        savedSearches = getSharedPreferences(SEARCHES, MODE_PRIVATE);

        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.id_container, MainFragment.getInstance())
          .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStepsClicked() {
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.id_container, StepsFragment.getInstance())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onDrawingClicked() {
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.id_container, DrawingFragment.getInstance())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onGainClicked() {
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.id_container, GainFragment.getInstance())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onStepsPerDayShow() {
        StepsPerDayFragment.getInstance().setSavedSearches(savedSearches);
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.id_container, StepsPerDayFragment.getInstance())
                .addToBackStack(null)
                .commit();
    }
}
