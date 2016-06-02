SQLite是一种关系型数据库，并且SQLite是轻量级的数据库解决方案。SQLite支持多数的SQL92标准，在一些场合下其性能优于MySql等数据库引擎。本课程介绍了SQLite存储的使用方法。


SQLite数据库的数据读取和写入 22:47

创建扩展自SQLiteOpenHelper的类用于操作数据库，本课时讲解数据库的读取和写入方法，并将结果呈现在ListView当中。

1.

创建 Db.class 文件

		package com.example.fangyi.preferenceactivity;

		import android.content.Context;
		import android.database.sqlite.SQLiteDatabase;
		import android.database.sqlite.SQLiteOpenHelper;

		/**
		 * Created by FANGYI on 2016/3/2.
		 */
		public class Db extends SQLiteOpenHelper {

			//name是存储的数据库的名字。CursorFactory数据库查询的结果，相当于一个指针，指向第一行。
			//version 存储数据库的版本，最低是1，他与onUpgrade有关，他是数据库升级的脚本
		    public Db(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
		        super(context, "db", null, version);
		    }

            // 为了方便，可以把一些参数去掉，把数据写死
            // public Db(Context context) {
            //     super(context, "people.db", null, 1);
            // }

            /**
             * 数据库创建时，此方法调用
             * @param db [description]
             */
		    @Override
		    public void onCreate(SQLiteDatabase db) {
		        //创建表 在SQLlist官网，语法部分有详细介绍

                //表 user
                db.execSQL("CREATE TABLE user(" +
                        "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT DEFAULT \"\"," +
                        "sex TEXT DEFAULT \"\")");
                //表 person
                db.execSQL("CREATE TABLE person(_id INTEGER PRIMARY KEY AUTOINCREMENT,
                        name char(10), phone char(20), money integer(10))");
		    }

            /**
             * 数据库升级，调用此方法
             * @param db         [description]
             * @param oldVersion [description]
             * @param newVersion [description]
             */
		    @Override
		    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		    }
		}


2.

在 MainActivity 中添加  对数据库 进行操作的代码

        //创建OpneHelper对象
        //A.在AndroidTestCase中运行A类数据库
        //                    获取一个虚拟上下文
        //A. Db oh = new Db(getContext(), "people.db", null, 1);
        Db db = new Db(this);



//API 写入
//        SQLiteDatabase dbWrite = db.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put("name", "小张");
//        cv.put("sex", "男");
//        dbWrite.insert("user", null, cv);
//
//        cv.put("name", "小李");
//        cv.put("sex", "女");
//        dbWrite.insert("user", null, cv);
//        dbWrite.close();//需要在用getWritableDatabase()获取



		//创建数据库
        // A. 如果数据库不存在，那么先创建，再打开，如果数据库已经存在，则直接打开，（可读写数据库）
        // A. SQLiteDatabase dbWrite = oh.getWritableDatabase()
        
        // 测试 表person 的数据库
        // SQL: insert into person(name, phone, money) values('小明','13884388888',10000); 加不加'' 都无所谓，反正都会按字段插入
        // SQL: delete form person; 删除整个表
        // SQL：delete form person where name = '张三';删除这个表
        // SQL：selest name, money from person; 列出只有 name 和 money 两列的表        

        // //全局变量
        // Db oh;
        // SQLiteDatabase dbWrite
        
        // protected setup() throws Exception {
        //     super.setUp();
        //     oh = new Db(getContext(), "people.db", null, 1);
        //     dbWrite = oh.getWritableDatabase();
        // }
        
        // protected tearDown() throws Exception {
        //     super.tearDown();
        //     dbWrite.close();
        // }

        /**
         * 增添表
         */
        // public void insert() {
        //     dbWrite.execSQL("insert into person(name, phone, money) values(?, ? , ?);", new Object[] {"张三", "13888", 1000});
        //     dbWrite.execSQL("insert into person(name, phone, money) values(?, ? , ?);", new Object[] {"李四", "15999", 2000});
        //     dbWrite.execSQL("insert into person(name, phone, money) values(?, ? , ?);", new Object[] {"赵四", "12345", 3000});
        //     dbWrite.execSQL("insert into person(name, phone, money) values(?, ? , ?);", new Object[] {"刘能", "17777", 4000});
        // }
        // 
        // public void insertAPI() {
        //     ContentValues cv = new ContentValues();
        //     cv.put("name", "小张");
        //     cv.put("phone", "16777");
        //     cv.put("money", 8500);
        //     long id =  db.insert("person", null, cv);//第二个参数是在第三个参数不传东西时候调用的，但是这种情况基本不可能出现
        //     System.out.println(id); //输出为_id = 第几个输进去 输出几
        // }
        
        /**
         * 删减表
         */
        // public void delete() {
        //     db.execSQL("delete form person where name = ?;", new Object[] {"张三"});
        // }
        // 
        // public void deleteAPI() {
        //     long id = db.delete("person", "name = ?", new String[] {"张三"});//删一行返回1
        // }

        // /**
        //  * 改
        //  */
        // public void update() {
        //     db.execSQL("update person set money = ? where name = ?", new object[] {13000, "刘能"});
        // }
        // public void updateAPI() {
        //     ContentValues cv = new ContentValues();
        //     cv.put("money", 8500);
        //     db.update("person", cv, "name = ?", new object[] {"刘能"});//改一行返回1，改两行返回2
        // }

        // /**
        //  * 查
        //  */
        // public void select() {
        //     Cursor cursor = db.rawQuery("selest name, money from person", null);
        //      //往下一行(第零行什么都没有，表从第一行开始数的)
        //      //索引
        //      //现在查出来的表的顺序是 ：RecNo(不用管他) name(0) money(1); 这时候name顺序是0
        //      //假如加一个：selest _id, name, money from person 顺序是 ：RecNo(不用管他) _id(0) name(1) money(2); 这时候name顺序是1
        //      
        //      /*指定列的索引*/ 查看 下面uesr表的 修改
        //      //
        //     while (cursor.moveToNext()) {
        //         String name = c.getString(0);
        //         String money = c.getString(1);
        //         System.out.println(name + ";" + money);
        //     }
        // }
        // public void selectAPI() {
        //    Cursor cursor = db.query("person", new String[]{"name", "money"}, "name = ?", new String[]{"小张"},null, null, null, null);
        //    while (cursor.moveToNext()) {
        //         String name = c.getString(0);
        //         String money = c.getString(1);
        //         System.out.println(name + ";" + money);
        //    }
        // }
        // 
        // query 支持 分页查询  limit 0,10 (从0开始插10页，想查10到15的数据，，是"10,5") 
        // 
        // db.query("person", null, null,null, null, null, null, "0,10");
        // 
        




        // /**
        //  * 事务
        //  * 形象点就是李四想给张三转200块钱之类的操作
        //  */
        // public void transaction() {
        //     try{
        //         //开启事务
        //         db.beginTransaction();

        //         ContentValues cv = new ContentValues();
        //         cv.put("money", 12005);
        //         db.update("person", cv, "name = ?", new String[]{"刘超"});
                
        //         cv.close();
        //         cv.put("money", 14000);
        //         db.update("person", cv, "name = ?", new String[]{"子豪"});

        //         db.setTransactionSuccessful();//设置事务成功
        //     } finally {
        //         db.endTransaction();//关闭事务，如果事务已经设置了执行成功，那么所有语句生效，如果没有设置，则回滚
        //     }
        // }
        // 
        // 
        // 
        // 
        // 


        SQLiteDatabase dbRead = db.getReadableDatabase();//磁盘不足，Readable只能读，没满功能跟上面的一样可读写

        
        //第一个参数 表名 uesr表
        //第二个参数new String[]{"name"}，返回name的
        //第三个参数，查询的条件"name =\"小张\"，这样只查询小张
        //第四个参数，查询的条件的参数，有时候我们防止SQL注入攻击，第三个参数位置我们写上"name=？",第四个参数写上new String[]{"小张"}，多个人名继续往下写
        Cursor c = dbRead.query("uesr", null, "name=", null, null, null, null);

        while (c.moveToNext()) {
            //只要可以移到下一个，我们就读它的值
            String name = c.getString(c.getColumnIndex("name")/*指定列的索引*/);
            String sex = c.getString(c.getColumnIndex("sex"));

            //输出
            System.out.println(String.format("name=%s,sex=%s", name,sex));
        }


/**
 
 */


MVC 结构
JavaWeb:
    M : 模型层     各种 javaBean : 就是要显示给用户看的数据
    V : 视图层     jsp : 就是用户能直接看到的界面
    C : 控制层     servlet : 把 javaBean 的数据显示至 jsp
Android:
    M : 模型层     personlist， 保存着要让用户看到的数据集合
    V : 视图层     ListView， 就是用户能直接看到的界面
    C : 控制层     Adapter，把 personlist 的数据显示至 ListView

    ListView中，每一个条目，都是一个View对象
    ListView的条目，一旦被划出屏幕，并不会被立刻销毁，而是缓存在内存中，如果系统内存不足，才会销毁

    /**
     * 返回一个View对象，被返回的View对象，会作为ListView的一个条目显示至屏幕
     * position：返回的View对象，在整个ListView当中，属于第几个条目
     * convertView：当条目被划出屏幕时，该条目会被缓存起来，当getView方法再次调用时，缓存条目就会作为convertView传入getView方法中
     */
    
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = null;
        //优化ListView
        if (convertView == null) {
            //通过资源id，把自定的布局文件填充成View对象
            View v = View.inflate(context, R.layout.listview_item, null);
        } else {
            v = convertView;
        }
    }

    String[] s = new String[] {
        "大波q",
        "吹波q",
        "跨抓q",
        "喷他q",
    };


SimpleAdapter & ArrayAdapter


    ListView lv = (ListView) findViewById(R.id.lv);
    
    //resource:布局文件的资源ID,同时只能操作一种数据
    lv.setAdapter(new ArrayAdapter<String>(this, R.layout.arrayadapter_item, R.id.tv, s));
    
    //集合中的每一个元素，都包含了listView一个条目所需要的所有数据
    List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

    Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("name", "小明");
        map1.put("image", R.drawable.photo1);
        data.add(map1);

        Map<String, Object> map2= new HashMap<String, Object>();
        map2.put("name", "小花");
        map2.put("image", R.drawable.photo2);
        data.add(map3);

        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("name", "小红");
        map3.put("image", R.drawable.photo3);
        data.add(map3);
 
    //                                             传入的数据                     组件的资源ID，资源的位置都要对应好
    lv.SimpleAdapter(new SimpleAdapter(this, data, new String[]{"name", "image"}, new int[]{R.id.tv, R.id.iv}))

3.
    如何把数据库和 Lsitview 结合起来

    通过界面操作数据库 28:28

    本课讲解在界面中实现数据的查找、插入、删除。




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

    }

    //刷新列表
    private void refreshListView() {
        Cursor c = dbRead.query("user", null, null, null, null, null, null);
        adapter.changeCursor(c);
    }
}

jintianduanwang

4. 

    在 content_main 文件中添加

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    app:layout_behavior="@string/appbar_scrolling_view_behavior"
    tools:context="com.example.fangyi.preferenceactivity.MainActivity"
    tools:showIn="@layout/activity_main"
    android:weightSum="1"
    android:orientation="vertical">

    <Button
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Android 读写首选项"
        android:id="@+id/btn"
        android:layout_alignParentTop="true"
        android:layout_alignParentLeft="true"
        android:layout_alignParentStart="true" />

    <ListView
        android:id="@android:id/list"
        android:layout_width="fill_parent"
        android:layout_height="293dp"
        android:layout_weight="0.24">
    </ListView>

    <LinearLayout
        android:layout_width="fill_parent"
        android:layout_height="wrap_content">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Name:"/>


        <EditText
            android:id="@+id/etName"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"/>

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Sex:"/>

        <EditText
            android:id="@+id/etSex"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_weight="1"/>

        <Button
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="添加"
            android:id="@+id/btnAdd"/>

    </LinearLayout>
</LinearLayout>




5.

    在 layout 创建 user_list_cell.xml

    <?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent">

//大文本显示名字
    <TextView
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:textAppearance="?android:attr/textAppearanceLarge"
        android:text="Large Text"
        android:id="@+id/tvName" />
//中文本显示性别
    <TextView
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:textAppearance="?android:attr/textAppearanceMedium"
        android:text="tvSex"
        android:id="@+id/textView2" />
</LinearLayout>


/**
 


 
 */

使用 ContentProvider 在应用间传递数据 19:58

本课时讲解使用 ContentProvider 在应用间传递数据。




1. 创建 Myprovider.class 

package com.example.fangyi.contenwriter;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by FANGYI on 2016/3/3.
 */
public class MyProvder extends ContentProvider {

    public  static final Uri URI = Uri.parse("content://com.example.fangyi.cp");
    SQLiteDatabase database;

    @Override
    public boolean onCreate() {
        database = getContext().openOrCreateDatabase("mucp.db3", Context.MODE_PRIVATE, null);
        database.execSQL("create table tab(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL)");
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = database.query("tab",  null, null, null, null, null, null);
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        database.insert("tab", "id", values);
//        database.close();
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}



2.
    在 AndroidManifest 中进行配置

        <provider
            android:authorities="com.example.fangyi.cp"
            android:name=".MyProvder"
            android:exported="true">    //默认或者写false，外部应用无法访问

        </provider>


3.

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                write();
            }
        });



    public void write() {
        ContentValues values = new ContentValues();
        values.put("name", "Java");
        values.put("name", "Swift");
        values.put("name", "C#");
        values.put("name", "C++");
        values.put("name", "Python");

        getContentResolver().insert(MyProvder.URI, values);

    }


4.

    在另一个应用中

    Uri URI = Uri.parse("content://com.example.fangyi.cp");


        Cursor cursor = getContentResolver().query(URI, null, null, null, null);
        cursor.moveToFirst();
        for (int i = 0; i <cursor.getCount(); i++) {
            String value = cursor.getString(cursor.getColumnIndex("name"));
            Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
            cursor.moveToNext();
        }



/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


XML数据格式简介 03:44

XML，即可扩展标记语言（Extensible Markup Language），标准通用标记语言的子集，一种用于标记电子文件使其具有结构性的标记语言。它可以用来标记数据、定义数据类型，
是一种允许用户对自己的标记语言进行定义的源语言。本课时对XML数据格式进行介绍。


2
读取与解析XML数据 11:42

使用Android平台自带的API加载XML数据，并且按照XML的结构将所有数据解析出来。本课时讲解读取与解析XML数据。



3
生成与输出XML数据 12:31

使用Android平台自带的API创建符合XML规范的数据，并且将XML数据输出。本课时讲解生成与输出XML数据。




1.


新建 assets 文件夹  ,Languages.xml 

            <?xml version="1.0" encoding="utf-8"?>
            <Languages cat="it">
                <lan id="1">
                    <name>Java</name>
                    <ide>Eclipse</ide>
                </lan>
                <lan id="2">
                    <name>Swift</name>
                    <ide>Xcode</ide>
                </lan>
                <lan id="3">
                    <name>C#</name>
                    <ide>Visual Studio</ide>
                </lan>
            </Languages>

2.

在 content_main 中添加一个 TextView



3.



import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.text);

        try {


            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(getAssets().open("languages.xml"));
            Element element = document.getDocumentElement();//获取根元素

            NodeList list = element.getElementsByTagName("lan");
            for (int i = 0; i <list.getLength(); i++) {
                Element lan = (Element) list.item(i);
                text.append(lan.getAttribute("id") + "\n");
                text.append(lan.getElementsByTagName("name").item(0).getTextContent() + "\n");
                text.append(lan.getElementsByTagName("ide").item(0).getTextContent() + "\n");

            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }




生成与输出XML数据 12:31

使用Android平台自带的API创建符合XML规范的数据，并且将XML数据输出。本课时讲解生成与输出XML数据。


            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            


            Document newxml = builder.newDocument();
            Element languages = newxml.createEkement("languages");
            languages.setAttribute("cat","it");

            Element lanl = newxml.createEkement("lan");
            lan1.setAttribute("id", "1");
            Element name1 = newxml.createEkement("name");
            name1.setTextContent("Java");
            Element ide1 = newxml.createEkement("ide");
            ide1.setTextContent("Eclipse");
            lan1.appendChild(name1);
            lan1.appendChild(ide1);
            languages.appendChild(lan1);

            Element lan2 = newxml.createEkement("lan");
            lan1.setAttribute("id", "2");
            Element name2 = newxml.createEkement("name");
            name1.setTextContent("Seift");
            Element ide2 = newxml.createEkement("ide");
            ide2.setTextContent("Xcode");
            lan2.appendChild(name2);
            lan2.appendChild(ide2);
            languages.appendChild(lan2);


            newxml.adoptChild(languages);

            TransFormerFactory tansFormerFactory = TransFormerFactory.newInstance();
            Transformer transformer = transFormerFactory.newTransformer();
            transformer.setOutputProperty("encoding", "utf-8");
            StringWriter sw =  new StringWriter();
            transformer.transform(new DOMSource(newxml), new streamResult(sw));
            text.setText(sw.toString());


/**
 













 */


http://www.w3school.com.cn/sql/index.asp

SQL SELECT 语法

SELECT 列名称 FROM 表名称
以及：

SELECT * FROM 表名称

注释：SQL 语句对大小写不敏感。SELECT 等效于 select。




public class DataBaseUtil {
    public static void main(String[] args) throws Exception {
        // 加载驱动
        Class.forName("org.sqlite.JDBC");
        // 获取连接
        Connection conn = DriverManager.getConnection("jdbc:sqlite:/d:/workspace/login.db");
        // 路径：相对路径，绝对路径
        System.out.println(conn);
        
        
        Statement stat = conn.createStatement();
        
        ResultSet rs = stat.executeQuery("select * from login;");
        while (rs.next()) {
            System.out.println("username = " + rs.getString("username"));
            System.out.println("pawssword = " + rs.getString("pawssword"));
        }
        rs.close();
        conn.close();
    }
}














