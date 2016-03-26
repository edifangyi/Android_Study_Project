package com.example.fangyi.preferenceactivity;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity {

    private SimpleCursorAdapter adapter;
    private EditText etName,etSex;
    private Button btnAdd;
    private Db db;
    private SQLiteDatabase dbRead,dbWrite;
    private View.OnClickListener btnAddListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ContentValues cv = new ContentValues();
            cv.put("name", etName.getText().toString());
            cv.put("sex", etSex.getText().toString());

            dbWrite.insert("user", null, cv);

            refreshListView();//刷新列表
        }
    };


    private AdapterView.OnItemLongClickListener ListViewItemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
            //弹出对话框是否真的删除
            new AlertDialog.Builder(MainActivity.this).setTitle("提醒").setMessage("您确定要删除该项嘛").setNegativeButton("取消", null).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Cursor c = adapter.getCursor();
                    c.moveToPosition(position);//在参数里面加上final 就可以解除错误

                    //数据所在数据库中的id，不是上边函数中的id，_id列
                    int itemId = c.getInt(c.getColumnIndex("_id"));
                    //删除
                    dbWrite.delete("user", "_id=?", new String[]{itemId+""});

                    refreshListView();
                }
            }).show();
            return true;//长按返回true
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etSex = (EditText) findViewById(R.id.etSex);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(btnAddListener);

        db = new Db(this);
        dbRead = db.getReadableDatabase();
        dbWrite = db.getWritableDatabase();

        adapter = new SimpleCursorAdapter(this, R.layout.user_list_cell, null, new String[]{"name","sex"}, new int[]{R.id.tvName,R.id.tvSex});
        setListAdapter(adapter);

        refreshListView();
        getListView().setOnItemLongClickListener(ListViewItemLongClickListener);



        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MyPreferenceActivity.class));
            }
        });




//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }


    //刷新列表
    private void refreshListView() {
        Cursor c = dbRead.query("user", null, null, null, null, null, null);
        adapter.changeCursor(c);
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
}
