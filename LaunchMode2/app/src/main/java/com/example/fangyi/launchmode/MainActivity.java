package com.example.fangyi.launchmode;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Handler;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

//import java.util.logging.Handler;
import java.util.logging.LogRecord;

import javax.security.auth.callback.Callback;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {
    protected TextView tv;
    protected TextView textView4;
    protected Intent intent;
    private EditText etData;
    private MyService.Binder binder = null;
    private TextView tvOut;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    private static String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        intent = new Intent(MainActivity.this, MyService.class);

        setContentView(R.layout.activity_main);

        Hello.sayHello(this);

        Log.e(TAG, "错误信息");
        Log.w(TAG, "警告信息");
        Log.i(TAG, "普通信息");
        Log.d(TAG, "调试信息");
        Log.v(TAG, "无用信息");
        Log.e("MSG", "其他信息");
        tv = (TextView) findViewById(R.id.tv);

        tv.setText(String.format("TaskID:%d, \n Current Activity id:%s", getTaskId(), toString()));

        findViewById(R.id.btnMainAty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });


        findViewById(R.id.btnStartBAty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BAty.class));
            }
        });


        findViewById(R.id.btnStartMyAty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyAty.ACTION));
//              显式  startActivity(new Intent(MainActivity.this, MyAty.class));
            }
        });

        findViewById(R.id.btnStartLocal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LocalAppAty.class));
            }
        });

        findViewById(R.id.btnStarLearnContext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LearnContext.class));
//                textView4 = new TextView(MainActivity.this);
//                textView4.setText("hello android");
//                setContentView(textView4);
            }
        });



        etData = (EditText) findViewById(R.id.etData);
        tvOut = (TextView) findViewById(R.id.tvOut);

        findViewById(R.id.btnStartService).setOnClickListener(this);
        findViewById(R.id.btnStopService).setOnClickListener(this);
        findViewById(R.id.btnBindService).setOnClickListener(this);
        findViewById(R.id.btnUnBindService).setOnClickListener(this);
        findViewById(R.id.btnSyncData).setOnClickListener(this);

        findViewById(R.id.btnSendMsg).setOnClickListener(this);
        findViewById(R.id.btnReg).setOnClickListener(this);
        findViewById(R.id.btnUnReg).setOnClickListener(this);

        findViewById(R.id.btnStartWebView).setOnClickListener(this);

        findViewById(R.id.btnStartMyAty2).setOnClickListener(this);

        startService(new Intent(this,AppService.class));



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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this,AppService.class));
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
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.fangyi.launchmode/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.fangyi.launchmode/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnStartService:
                intent.putExtra("data",etData.getText().toString());
                startService(intent);
                break;
            case R.id.btnStopService:
                stopService(intent);
                break;
            case R.id.btnBindService:
                bindService(intent, this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnUnBindService:
                unbindService(this);
                break;
            case R.id.btnSyncData:
                if (binder!=null){
                    binder.setData(etData.getText().toString());
                }
                break;

            case R.id.btnSendMsg:
                Intent i = new Intent(MyReceiver.ACTION);
                i.putExtra("data", "ABCD");
//                sendBroadcast(i);
                sendOrderedBroadcast(i, null);
                break;
            case R.id.btnReg:
                if(receiver==null){
                    receiver = new MyReceiver();
                    registerReceiver(receiver,new IntentFilter(MyReceiver.ACTION));
                }
                break;
            case R.id.btnUnReg:
                if(receiver!=null){
                    unregisterReceiver(receiver);
                    receiver = null;
                }
                break;

            case R.id.btnStartWebView:
                startActivity(new Intent(MainActivity.this,MyWebView.class));
                break;
            case R.id.btnStartMyAty2:
                startActivity(new Intent(MainActivity.this,MyAty2.class));
                break;
        }
    }

    private MyReceiver receiver = null;

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        binder = (MyService.Binder) service;
        binder.getService().setCallback(new MyService.Callback() {
            @Override
            public void onDataChange(String data) {
                Message msg = new Message();
                Bundle b = new Bundle();
                b.putString("data", data);
                msg.setData(b);
                handler.sendMessage(msg);
            }
        });
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    private android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tvOut.setText(msg.getData().getString("data"));
        }
    };
}
