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
















