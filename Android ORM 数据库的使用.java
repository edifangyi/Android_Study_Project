ORM 数据库框架 ActiveAndroid 的特点和优势

	ORM (Object Relational Mapping) 框架采用元数据来描述对象与关系映射细节。
	把对象持久化到数据库中
	就是利用 JAVA 的反射机制把对象和数据库记录映射关联起来

    ·基于 ORM 关系操作数据库
    ·方便配置
    ·几乎不需要编写任何 SQL 语句就能保存和检索 SQLite 数据库记录
    ·每一个操作都封装为一个类
    ·对象形式存取数据

/**
 
 */

基本用法:

    ·配置:
    	配置 AndroidManifest 的 application 的 name属性，同时在 meta-data 标签中可选的配置 db 的 name 和 version；
    	在自己的 Application 类中继承 ActiveAndroid 的 Application
		
		<meta-data
            android:name="AA_DB_NAME"		//数据库的名称 
            android:value="Pickrand.db" />
        <meta-data
            android:name="AA_DB_VERSION"	//数据库版本号
            android:value="2" />
       


    ·定义实体类
    	实体类需继承 Model，可自定义表名和属性对应的字段名。
    
    ·进行增删改查操作


/**
 
 */

官方的Demo


1. AndroidManifest 添加 <eta-data />


<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.fangyi.activeandroiddemo">

    <application
        android:name=".MyApplication"
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">

		<meta-data
            android:name="AA_DB_NAME"		//数据库的名称 
            android:value="Pickrand.db" />
        <meta-data
            android:name="AA_DB_VERSION"	//数据库版本号
            android:value="1" />

        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>

/**
 
 */

2. 定义一个全局的数据库的初始化


package com.example.fangyi.activeandroiddemo;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by FANGYI on 2016/4/3.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize();
    }
}



3. 在 AndroidManifest 添加



<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.fangyi.activeandroiddemo">

    <application
        android:name=".MyApplication"
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">

        <meta-data
            android:name="AA_DB_NAME"
            android:value="Pickrand.db" />
        <meta-data
            android:name="AA_DB_VERSION"
            android:value="1" />

        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>


/**
 
 */

4.定义一个实体类 表

package com.example.fangyi.activeandroiddemo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by FANGYI on 2016/4/3.
 */
@Table(name = "Items")  //表名
public class Item extends Model {
    @Column(name = "Name")  //字段名
    public String name;     //字段类型
    @Column(name = "Category")
    public Category category;

    public Item() {
        super();
    }

    public Item(String name, Category category) {
        super();
        this.name = name;
        this.category = category;
    }
}


/**
 
 */




5.

package com.example.fangyi.activeandroiddemo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by FANGYI on 2016/4/3.
 */
@Table(name = "Categoryes")  //表名
public class Category extends Model {
    @Column(name = "Name")  //字段名
    public String name;     //字段类型
}


/**
 
 */


package com.example.fangyi.activeandroiddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.activeandroid.query.Select;
import com.activeandroid.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //保存数据，修改方法
        Category cate = new Category();
        cate.name = "OutLook";
        cate.save();
        //给上添加关联
        Item item = new Item();
        item.name = "Lilei";
        item.category = cate;
        item.save();

        // 删除操作
        // Item item2 = Item.load(Item.class, 1);
        // item2.delete();
        // new Delete().from(Item.class).where("Name=?", "Lilei").execute();

        // 对数据的查询方法
        Log.i("info", new Select().from(Item.class).where("Name=?", "Lilei")
                .orderBy("Name ASC").execute().size()
                + "");
    }
}


/**
 
 */


数据库升级

1.新建文件夹 migrations，新建 2.sql

app/build/intermediates/assets/migrations

添加一个项目：
	
	ALTER TABLE Categoryes ADD COLUMN Stuid INTEGER;

2.在 AndroidManifest 中 改成 2

        <meta-data
            android:name="AA_DB_VERSION"	//数据库版本号
            android:value="2" />


3.升级相应的表
	

package com.example.fangyi.activeandroiddemo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by FANGYI on 2016/4/3.
 */
@Table(name = "Categoryes")  //表名
public class Category extends Model {
    @Column(name = "Name")  //字段名
    public String name;     //字段类型

    @Column(name = "Stuid")
    public  Integer stuid;	//添加这个
}


/**
 
 */

定义其他 meta-data

在 AndroidManifest 中 添加
        <meta-data
            android:name="AA_MODELL"
            android:value="com.example.fangyi.activeandroiddemo.Item,com.example.fangyi.activeandroiddemo.Category" />



/**
 




 
 */

实现效果:

	使用 ActiveAndroid 建立学生信息库，实现对学生信息库的增删改查操作

 /**
  
  */
 
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.fangyi.activeandroiddemo">

    <application
        android:name=".MyApplication"
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">

        <meta-data
            android:name="AA_DB_NAME"
            android:value="Stu.db" />
        <meta-data
            android:name="AA_DB_VERSION"
            android:value="1" />

        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
 

 /**
  
  */

1.
 package com.example.fangyi.activeandroiddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.activeandroid.query.Select;
import com.example.fangyi.activeandroiddemo.adapter.StuAdapter;
import com.example.fangyi.activeandroiddemo.model.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assignViews();

    }

    private Button btnAdd;
    private ListView lv;
    private StuAdapter adapter;
    private ArrayList<Student> lists = new ArrayList<Student>();

    private void assignViews() {
        btnAdd = (Button) findViewById(R.id.btn_add);
        lv = (ListView) findViewById(R.id.lv);

        adapter = new StuAdapter(this, lists);
        lv.setAdapter(adapter);

//        lists.addAll((Collection<? extends Student>) new Select().from(Student.class).execute());
//        lists.addAll(new Select().from(Student.class).<Student>execute());
        //查询数据库
        List<Student> stus = new Select().from(Student.class).execute();

        //修改学生信息
        for (int i = 0; i< stus.size(); i++) {
            Student student = stus.get(i);
            student.name = " HanMeiMei";
            student.save();
        }
        lists.addAll(stus);
        //添加学生信息
        for (int i = 0; i <8; i++) {
            Student stu = new Student();
            stu.name = "Lilei" + i;
            stu.age = 10 + i;
            stu.save();

            lists.add(stu);
        }
        adapter.notifyDataSetChanged();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student stu = new Student();
                stu.name = "Lucy";
                stu.age = 12;
                stu.save();

                lists.add(stu);
                adapter.notifyDataSetChanged();
            }
        });
    }

}

 /**
  
  */

package com.example.fangyi.activeandroiddemo;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by FANGYI on 2016/4/3.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}

 /**
  
  */
 
package com.example.fangyi.activeandroiddemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.fangyi.activeandroiddemo.R;
import com.example.fangyi.activeandroiddemo.model.Student;

import java.util.ArrayList;

/**
 * Created by FANGYI on 2016/4/3.
 */
public class StuAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Student> list;

    public StuAdapter(Context context, ArrayList<Student> list) {
        this.context = context;
        this.list = list;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return list.size();
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
        return list.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {//如果布局为空
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_stu, null);
            holder.btn_del = (Button) convertView.findViewById(R.id.btn_del);
            holder.tv = (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Student stu = list.get(position);
        holder.tv.setText(stu.name + "\n" + stu.age);
        holder.btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stu.delete();
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView tv;
        Button btn_del;
    }
}


  /**
  
  */
 

 package com.example.fangyi.activeandroiddemo.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by FANGYI on 2016/4/3.
 */
@Table(name = "Students")
public class Student extends Model {

    @Column(name = "Name")
    public String name;

    @Column(name = "Age")
    public Integer age;
}


  /**
  
  */
 <?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    tools:context="com.example.fangyi.activeandroiddemo.MainActivity">

    <Button
        android:id="@+id/btn_add"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="添加"/>


    <ListView
        android:id="@+id/lv"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content">
    </ListView>


</LinearLayout>


  /**
  
  */
 <?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="horizontal" android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/tv"
        android:layout_width="100dp"
        android:layout_height="wrap_content" />

    <Button
        android:id="@+id/btn_del"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="删除"/>

</LinearLayout>