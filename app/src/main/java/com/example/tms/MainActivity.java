package com.example.tms;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import android.view.View;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.Menu;
import android.view.MenuItem;

import com.example.tms.FragmentReports.AvgSpeedFragment;
import com.example.tms.FragmentReports.LevelOfServiceFragment;
import com.example.tms.FragmentReports.VolumeFragment;
import com.parse.Parse;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String VOLUME_FRAGMENT= "VOLUME_FRAGMENT";
    private static final String LVLOFSERIVICE_FRAGMENT= "LVLOFSERIVICE_FRAGMENT";
    private static final String AVGSPEED_FRAGMENT= "AVGSPEED_FRAGMENT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializeParse();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VolumeFragment volumeFragment = (VolumeFragment)getSupportFragmentManager().findFragmentByTag(VOLUME_FRAGMENT);
                LevelOfServiceFragment levelOfServiceFragment = (LevelOfServiceFragment)getSupportFragmentManager().findFragmentByTag(LVLOFSERIVICE_FRAGMENT);
                AvgSpeedFragment avgSpeedFragment = (AvgSpeedFragment)getSupportFragmentManager().findFragmentByTag(AVGSPEED_FRAGMENT);
                if(volumeFragment != null){
                    new SyncTask(MainActivity.this).execute((Void)null);
                } else if(levelOfServiceFragment != null){

                } else if(avgSpeedFragment != null){

                }

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        new SyncTask(this).execute((Void)null);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_volume) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, new VolumeFragment(), VOLUME_FRAGMENT).commit();
        } else if (id == R.id.nav_lvl_of_service) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, new LevelOfServiceFragment(), LVLOFSERIVICE_FRAGMENT).commit();
        } else if (id == R.id.nav_avg_speed) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, new AvgSpeedFragment(), AVGSPEED_FRAGMENT).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initializeParse()
    {
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build());
    }
}
