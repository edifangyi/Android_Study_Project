package com.example.fangyi.waterheater;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;

import com.example.fangyi.waterheater.MenuBarService.dummy.AboutFragment;
import com.example.fangyi.waterheater.MenuBarService.dummy.DataAnalysisFragment;
import com.example.fangyi.waterheater.MenuBarService.dummy.FaultAnalysisFragment;
import com.example.fangyi.waterheater.MenuBarService.dummy.HelpFragment;
import com.example.fangyi.waterheater.MenuBarService.dummy.Modes;
import com.example.fangyi.waterheater.MenuBarService.dummy.TimerSelectedFragment;
import com.example.fangyi.waterheater.WaveView.WaveView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WaveView();//水波
        displayView(R.id.nav_view);//Menu选择



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar =getSupportActionBar();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        if (id == R.id.kaiguan) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        displayView(item.getItemId());
        return true;
    }

    /**
     * 水波纹效果
     */
    private SeekBar seekBar;
    private WaveView waveView;

    private void WaveView() {
        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        waveView = (WaveView) findViewById(R.id.wave_view);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                waveView.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    /**
     * Menu选择
     */
    public void displayView(int viewId) {
        Fragment fragment = null;


        String title = getString(R.string.app_name);

        switch (viewId) {
            case R.id.nav_mode_selected:
                startActivity(new Intent(MainActivity.this, Modes.class));
                break;

            case R.id.nav_timer_selected:
                fragment = new TimerSelectedFragment();
                title = "预约用水";
                break;


            case R.id.nav_data_analysis:
                fragment = new DataAnalysisFragment();
                title = "数据分析";
                break;

            case R.id.nav_fault_analysis:
                fragment = new FaultAnalysisFragment();
                title = "故障分析";
                break;

            case R.id.nav_help:
                fragment = new HelpFragment();
                title = "帮助";
                break;

            case R.id.nav_about:
                fragment = new AboutFragment();
                title = "关于";
                break;

        }



        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        // set the toolbar title
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }




}
