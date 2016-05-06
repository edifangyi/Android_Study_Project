<?xml version="1.0" encoding="utf-8"?>
<ScrollView xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:orientation="vertical" android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/SQLlayout">
    </LinearLayout>
</ScrollView>

/**
 
 */


package com.example.fangyi.myapp.sql_lsitview;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.fangyi.myapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FANGYI on 2016/5/6.
 */
public class SQL extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sql_listview);

        personList = new ArrayList<>();
        insertAPI();
        selectSQL();

    }
    List<Person> personList;
    Person person;

    MyOpenHelper oh;
    SQLiteDatabase db;
    /**
     * 查询数据库
     */
    private void selectSQL() {
        oh = new MyOpenHelper(this, "people.db", null, 1);
        db = oh.getWritableDatabase();
        Cursor cursor = db.query("person", null, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            String money = cursor.getString(3);

            person = new Person(name, phone, money);
            personList.add(person);
        }
        db.close();
        tvShow(personList);
    }

    /**
     * 添加数据库数据
     */
     public void insertAPI() {
         oh = new MyOpenHelper(this, "people.db", null, 1);
         db = oh.getWritableDatabase();
         db.execSQL("insert into person(name, phone, money) values(?, ? , ?);", new Object[] {"张三", "13888", 1000});
         db.execSQL("insert into person(name, phone, money) values(?, ? , ?);", new Object[] {"李四", "15999", 2000});
         db.execSQL("insert into person(name, phone, money) values(?, ? , ?);", new Object[] {"赵四", "12345", 3000});
         db.execSQL("insert into person(name, phone, money) values(?, ? , ?);", new Object[] {"刘能", "17777", 4000});
         db.close();
     }



    /**
     * 通过new TextView 来展示 数据库
     * @param personList
     */
    private void tvShow(List<Person> personList) {
        //拿到线性布局的对象
        LinearLayout layout = (LinearLayout) findViewById(R.id.SQLlayout);

        for (Person pe : personList) {
            //每有一条数据，创建 TextView 对象
            TextView tv = new TextView(this);
            //设置textView 显示的内容
            tv.setText(pe.toString());
            tv.setTextSize(20);
            //把tv变成线性布局的子节点
            layout.addView(tv);
            System.out.println(pe.toString());
        }

    }
}


/**
 
 */


package com.example.fangyi.myapp.sql_lsitview;

/**
 * Created by FANGYI on 2016/5/6.
 */
public class Person {
    private String name;
    private String phone;
    private String money;

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", money='" + money + '\''
                ;
    }

    public Person(String name, String phone, String money) {
        this.name = name;
        this.phone = phone;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}


/**
 
 */


package com.example.fangyi.myapp.sql_lsitview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by FANGYI on 2016/5/6.
 */
public class MyOpenHelper extends SQLiteOpenHelper {
    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use to open or create the database
     * @param name    of the database file, or null for an in-memory database
     * @param factory to use for creating cursor objects, or null for the default
     * @param version number of the database (starting at 1); if the database is older,
     *                {@link #onUpgrade} will be used to upgrade the database; if the database is
     *                newer, {@link #onDowngrade} will be used to downgrade the database
     */
    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "people.db", null, 1);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("数据库创建好了@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        db.execSQL("CREATE TABLE person(_id INTEGER PRIMARY KEY AUTOINCREMENT, name char(10), phone char(20), money integer(10))");
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}



/**
 

 */

/**
 

 */

/**
 

 */

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <ScrollView
        android:layout_weight="1"
        android:layout_width="match_parent"
        android:layout_height="0dp">
        <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
            android:orientation="vertical" android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:id="@+id/SQLlayout">
        </LinearLayout>
    </ScrollView>

    <ListView
        android:layout_weight="1"
        android:id="@+id/lv"
        android:layout_width="match_parent"
        android:layout_height="0dp"></ListView>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal">

        <EditText
            android:id="@+id/et_sql_name"
            android:layout_weight="1"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:hint="name"/>

        <EditText
            android:id="@+id/et_sql_phone"
            android:layout_weight="1"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:hint="phone"/>

        <EditText
            android:id="@+id/et_sql_money"
            android:layout_weight="1"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:hint="money"/>

    </LinearLayout>

    <Button
        android:id="@+id/btn_sql_add"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="添加一次数据集"/>


</LinearLayout>


/**
 
 */


package com.example.fangyi.myapp.sql_lsitview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by FANGYI on 2016/5/6.
 */
public class MyAdapter extends BaseAdapter {

    private Context context;
    private List<Person> personList;

    public MyAdapter(Context context, List<Person> personList) {
        this.context = context;
        this.personList = personList;
    }

    /**
     * 返回模型层中数据的数量
     *
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return personList.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return null;
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * 返回一个View对象，被返回的View对象，会作为ListView的一个条目显示至屏幕
     * position：返回的View对象，在整个ListView当中，属于第几个条目
     *
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        textView.setText(personList.get(position).toString());
        textView.setTextSize(18);
        return textView;
    }
}



/**
 
 */


package com.example.fangyi.myapp.sql_lsitview;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fangyi.myapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FANGYI on 2016/5/6.
 */
public class SQL extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sql_listview);

        personList = new ArrayList<>();
        selectSQL();
        assignViews();

    }

    List<Person> personList;
    Person person;

    MyOpenHelper oh;
    SQLiteDatabase db;

    private Button btnSqlAdd;
    private Button btnSqlDelete;    
    private void assignViews() {
        btnSqlAdd = (Button) findViewById(R.id.btn_sql_add);
        btnSqlDelete = (Button) findViewById(R.id.btn_sql_delete);

        btnSqlAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertAPI();
            }
        });
        
    }

    /**
     * 查询数据库
     */
    private void selectSQL() {
        oh = new MyOpenHelper(this, "people.db", null, 1);
        db = oh.getWritableDatabase();
        Cursor cursor = db.query("person", null, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            String money = cursor.getString(3);

            person = new Person(name, phone, money);
            personList.add(person);
        }
        db.close();
        tvShow(personList);
        lvShow(personList);
    }

    /**
     * 添加数据库数据
     */
     public void insertAPI() {
         oh = new MyOpenHelper(this, "people.db", null, 1);
         db = oh.getWritableDatabase();
         db.execSQL("insert into person(name, phone, money) values(?, ? , ?);", new Object[] {"张三", "13888", 1000});
         db.execSQL("insert into person(name, phone, money) values(?, ? , ?);", new Object[] {"李四", "15999", 2000});
         db.execSQL("insert into person(name, phone, money) values(?, ? , ?);", new Object[] {"赵四", "12345", 3000});
         db.execSQL("insert into person(name, phone, money) values(?, ? , ?);", new Object[] {"刘能", "17777", 4000});
         db.close();
     }

    /**
     * 通过new TextView 来展示 数据库
     * @param personList
     */
    private void tvShow(List<Person> personList) {
        //拿到线性布局的对象
        LinearLayout layout = (LinearLayout) findViewById(R.id.SQLlayout);

        for (Person pe : personList) {
            //每有一条数据，创建 TextView 对象
            TextView tv = new TextView(this);
            //设置textView 显示的内容
            tv.setText(pe.toString());
            tv.setTextSize(20);
            //把tv变成线性布局的子节点
            layout.addView(tv);
            System.out.println(pe.toString());
        }

    }

    private void lvShow(List<Person> personList) {

        ListView lv = (ListView) findViewById(R.id.lv);
        //给ListView设置一个适配器，适配器的作用就是把集合中的数据显示至ListView
        lv.setAdapter(new MyAdapter(this,personList));
    }

}



/**
 

 */

/**
 

 */

/**
 

 */  

    /**
     * 返回一个View对象，被返回的View对象，会作为ListView的一个条目显示至屏幕
     * position：返回的View对象，在整个ListView当中，属于第几个条目
     */

    public View getView(int position, View convertView, ViewGroup parent) {
        
        /**
         * 三种方法 填写布局
         */

        //通过资源id，把自定的布局文件填充成View对象
        View v = View.inflate(context, R.layout.listview_item, null);

        //布局填充器，拿到布局填充器对象
        LayoutInflater li = LayoutInflater.from(context);
        View v = li.inflate(R.layout.listview_item, null);

        //通过系统服务拿到布局填充器对象（这个方法没实现成功，可能被放弃了）
        LayoutInflater li = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View v = li.inflate(R.layout.listview_item, null);



        TextView tv_name = (TextView) v.findViewById(R.id.tv_name);
        TextView tv_phone = (TextView) v.findViewById(R.id.tv_phone);
        TextView tv_money = (TextView) v.findViewById(R.id.tv_money);

        Person p = personList.get(position);
        tv_name.setText(p.getName());
        tv_phone.setText(p.getPhone());
        tv_money.setText(p.getMoney());
        return v;
    }


/**
 
 */  
