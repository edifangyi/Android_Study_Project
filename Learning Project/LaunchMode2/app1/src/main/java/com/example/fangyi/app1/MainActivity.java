package com.example.fangyi.app1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fangyi.launchmode.IAPPServiceRemoteBinder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {

    private Intent serviceIntent;
    private EditText etInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serviceIntent = new Intent();
        serviceIntent.setComponent(new ComponentName("com.example.fangyi.app1", "com.example.fangyi.launchmode.AppService"));

        etInput = (EditText) findViewById(R.id.etInput);



        findViewById(R.id.btnStartLaunchModeAppService).setOnClickListener(this);
        findViewById(R.id.btnStopLaunchModeAppService).setOnClickListener(this);
        findViewById(R.id.btnBindLaunchModeAppService).setOnClickListener(this);
        findViewById(R.id.btnUnBindLaunchModeAppService).setOnClickListener(this);
        findViewById(R.id.btnSync).setOnClickListener(this);

        findViewById(R.id.btnStartMyAty2).setOnClickListener(this);


        findViewById(R.id.btnStartMyAty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent("com.example.fangyi.launchmode.intent.action.MyAty", Uri.parse("app://hello")));
                }catch (Exception e) {
                    Toast.makeText(MainActivity.this, "无法启动指定的Activity", Toast.LENGTH_SHORT).show();
                }
            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStartLaunchModeAppService:
                startService(serviceIntent);
                break;
            case R.id.btnStopLaunchModeAppService:
                stopService(serviceIntent);
                break;
            case R.id.btnBindLaunchModeAppService:
                bindService(serviceIntent, this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnUnBindLaunchModeAppService:
                unbindService(this);
                binder = null;
                break;
            case R.id.btnSync:
                if (binder!=null){
                    try {
                        binder.setData(etInput.getText().toString());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.btnStartMyAty2:
                startActivity(new Intent("com.example.fangyi.launchmode.intent.action.MyAty2"));
                break;
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        System.out.println("Bind Service");
        System.out.println(service);

        binder = IAPPServiceRemoteBinder.Stub.asInterface(service);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    private IAPPServiceRemoteBinder binder = null;
}
