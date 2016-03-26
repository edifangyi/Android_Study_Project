A.I-Android智能机器人“小Cat”的实现

1、第三方API的使用
		注册图灵机器人账号，申请API接口
		http://www.tuling123.com/
		机器人接入、设定

2、LisView多钟Item布局时的处理，实现聊天对话的界面

3、
	3-1 编写消息发送与接收的工具类
	3-2 搭建测试环境，测试工具类中方法, 完成消息实体的编写
	3-3 实现聊天布局
	3-4 实现基本的信息展示
	3-5 实现与小慕的对话

/**

**/
1-1 编写消息发送与接收的工具类 utils/HttpUtils.class


package com.example.fangyi.ai_cat.utils;

import com.example.fangyi.ai_cat.bean.ChatMessage;
import com.example.fangyi.ai_cat.bean.Result;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

/**
 * Created by FANGYI on 2016/3/25.
 */
public class HttpUtils {
    private static final String URL = "http://www.tuling123.com/openapi/api";
    private static final String API_KEY = "c12dae806af96440cb2967cdb9d0933d";

    /**
     * 发送一个消息，得到返回的消息
     * @param msg
     * @return
     */
    public static ChatMessage sendMessage(String msg) {
        ChatMessage chatMessage = new ChatMessage();

        String jsonRes = doGet(msg);//通过get请求拿到返回的数据
        Gson gson = new Gson();
        Result result = null;//将返回的数据转换为Result对象


        try {   //转换成功
            result = gson.fromJson(jsonRes, Result.class);
            chatMessage.setMsg((result.getText()));//聊天的消息
        } catch (JsonSyntaxException e) {
            //转换失败
            chatMessage.setMsg("服务器繁忙，请稍候再试");
        }
        chatMessage.setDate(new Date());
        chatMessage.setType(ChatMessage.Type.INCOMING);
        return chatMessage;
    }

    /**
     * 由用户传入一个消息
     * @param msg
     * @return
     */
    public static String doGet(String msg) {
        String result = "";
        String url = setParams(msg);
        ByteArrayOutputStream byteArrayOutputStream = null;
        InputStream inputStream = null;

        try {
            java.net.URL urlNet = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlNet.openConnection();
            connection.setReadTimeout(5*1000);//5秒
            connection.setConnectTimeout(5*1000);
            connection.setRequestMethod("GET");//请求方式

            inputStream = connection.getInputStream();//拿到服务器返回的流
            int len = -1;
            byte[] buf = new byte[128];//缓冲区，128个字节

            byteArrayOutputStream = new ByteArrayOutputStream();//把byte转换为String
            //对流进行读操作
            while ((len = inputStream.read(buf)) != -1) {
                byteArrayOutputStream.write(buf, 0, len);
            }
            byteArrayOutputStream.flush();//清除缓冲区
            result = new String(byteArrayOutputStream.toByteArray());//将本地流转换为字符串

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally { //释放所有的资源
            if (byteArrayOutputStream != null ) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    /**
     * 为 URL设置参数
     * @param msg
     * @return
     */
    public static String setParams(String msg) {
        String url = "";
        try {
            url = URL + "?key=" + API_KEY +"&info=" + URLEncoder.encode(msg,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }

}





/**

**/

1-2 测试工具类中方法


1-2-1 搭建环境

<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.fangyi.ai_cat">
    <uses-permission android:name="android.permission.INTERNET"/>	//网络权限
    <application
        android:allowBackup="true"
        android:icon="@drawable/nyan_cat"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/Theme.AppCompat.NoActionBar">
        <uses-library
            android:required="false"			//重点
            android:name="AndroidTestRunner"/>	//第一步，引入uses-library
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
    <instrumentation
        android:name="android.test.InstrumentationTestRunner"
        android:targetPackage="com.example.fangyi.ai_cat"
        android:label="this is a test">     //第二步，引入instrumentation
    </instrumentation>

</manifest>

//////////////////////////////////////////////////////////////////////////////////

1-2-2	TestHttpUtils.class

package com.example.fangyi.ai_cat.text;

import android.test.AndroidTestCase;
import android.util.Log;

import com.example.fangyi.ai_cat.utils.HttpUtils;

/**
 * Created by FANGYI on 2016/3/25.
 */
public class TestHttpUtils extends AndroidTestCase {
    public void testSendInfo() {
        String res = HttpUtils.doGet("给我讲个笑话");
        Log.e("TAG",res);
        res = HttpUtils.doGet("给我讲个鬼故事");
        Log.e("TAG",res);
        res = HttpUtils.doGet("你好");
        Log.e("TAG",res);
        res = HttpUtils.doGet("你真美");
        Log.e("TAG",res);


    }
}


//////////////////////////////////////////////////////////////////////////////////


1-2-3 bean/ChatMessage.class

package com.example.fangyi.ai_cat.bean;

import java.util.Date;

/**
 * Created by FANGYI on 2016/3/25.
 */
public class ChatMessage {
    private String name;    //名称
    private String msg;     //消息内容
    private Type type;      //类型，用于发送消息还是接收消息
    private Date date;

    public enum Type 	//枚举，定义类型
    {
        INCOMING,OUTCOMING
    }

    public ChatMessage() {
    }

    public ChatMessage(String msg, Type type, Date date) {
        this.msg = msg;
        this.type = type;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

//////////////////////////////////////////////////////////////////////////////////

1-2-4 Result.class

package com.example.fangyi.ai_cat;

/**
 * Created by FANGYI on 2016/3/24.
 * 专门映射服务器返回的结果
 */
public class Result {
    private int code;
    private String text;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}



//////////////////////////////////////////////////////////////////////////////////

1-2-5 回头完善工具类 HttpUtils.class

    /**
     * 发送一个消息，得到返回的消息
     * @param msg
     * @return
     */
    public static ChatMessage sendMessage(String msg) {
        ChatMessage chatMessage = new ChatMessage();

        String jsonRes = doGet(msg);//通过get请求拿到返回的数据
        Gson gson = new Gson();
        Result result = null;//将返回的数据转换为Result对象


        try {   //转换成功
            result = gson.fromJson(jsonRes, Result.class);
            chatMessage.setMsg((result.getText()));//聊天的消息
        } catch (JsonSyntaxException e) {
            //转换失败
            chatMessage.setMsg("服务器繁忙，请稍候再试");
        }
        chatMessage.setDate(new Date());
        chatMessage.setType(ChatMessage.Type.INCOMING);
        return chatMessage;
    }


/**

**/

1-3 实现聊天布局

1-3-1 回复消息的布局 item_from_msg.xml

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:textSize="8sp"
        android:text=""/>
    <TextView
        android:id="@+id/id_form_msg_date"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:background="#bebebe"
        android:textColor="#f5f5f5"
        android:textSize="12sp"
        android:text=" 2012-12-12 12:12:12"/>
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:textSize="8sp"
        android:text=""/>

    <LinearLayout
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:layout_marginLeft="10dp"
        android:layout_marginRight="10dp"
        android:orientation="horizontal">

        <LinearLayout
            android:layout_width="wrap_content"
            android:layout_height="wrap_content">

            <ImageView
                android:layout_width="41.33dp"
                android:layout_height="41.33dp"
                android:gravity="center"
                android:src="@drawable/icon"/>

        </LinearLayout>

        <TextView
            android:id="@+id/id_form_msg_info"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:background="@drawable/chatfrom_bg_normal"
            android:gravity="center_vertical"
            android:textSize="16sp"
            android:textColor="#000000"
            android:text="喵喵ฅ(^ω^ )ฅ"/>

    </LinearLayout>

</LinearLayout>




//////////////////////////////////////////////////////////////////////////////////

1-3-2 发送消息的布局 item_to_msg.xml


<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:gravity="right"
    android:orientation="vertical">

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:textSize="8sp"
        android:text=""/>
    <TextView
        android:id="@+id/id_to_msg_date"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:background="#bebebe"
        android:textColor="#f5f5f5"
        android:textSize="12sp"
        android:text=" 2012-12-12 12:12:12"/>
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:textSize="8sp"
        android:text=""/>

    <LinearLayout
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:layout_marginLeft="10dp"
        android:layout_marginRight="10dp"
        android:gravity="right"
        android:orientation="horizontal">

        <TextView
            android:id="@+id/id_to_msg_info"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:background="@drawable/chatto_bg_normal"
            android:gravity="center_vertical"
            android:textSize="16sp"
            android:textColor="#000000"
            android:text="喵喵ฅ(^ω^ )ฅ"/>

        <LinearLayout
            android:layout_width="wrap_content"
            android:layout_height="wrap_content">

            <ImageView
                android:layout_width="41.33dp"
                android:layout_height="41.33dp"
                android:gravity="center"
                android:src="@drawable/me"/>

        </LinearLayout>

    </LinearLayout>

</LinearLayout>




//////////////////////////////////////////////////////////////////////////////////


1-3-3 发送按钮颜色布局 drawable/send_btn_bg.xml

<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">

        <item android:drawable="@drawable/send_btn_pressed" android:state_pressed="true"></item>

        <item android:drawable="@drawable/send_btn_normal"></item>

</selector>


//////////////////////////////////////////////////////////////////////////////////

1-3-3 主布局 activity_main.xml


<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:background="@drawable/chat_bg_default"
    >

    <RelativeLayout
        android:id="@+id/id_ly_top"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content">

        <RelativeLayout
            android:layout_width="fill_parent"
            android:layout_height="50dp"
            android:background="#393A3F"
            android:layout_alignParentTop="true">

            <ImageView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content" />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="50dp"
                android:gravity="center"
                android:textSize="20dp"
                android:textColor="#FFFFFF"
                android:text="   A.I Cat"/>

        </RelativeLayout>

    </RelativeLayout>
    
    <RelativeLayout
        android:id="@+id/id_ly_bottom"
        android:layout_width="fill_parent"
        android:layout_alignParentBottom="true"
        android:layout_height="50dp"
        android:background="#FFFFFF">

        <Button
            android:id="@+id/id_send_msg"
            android:layout_width="60dp"
            android:layout_height="40dp"
            android:layout_marginRight="10dp"
            android:layout_alignParentRight="true"
            android:layout_centerVertical="true"
            android:background="@drawable/send_btn_bg"
            android:textSize="18dp"
            android:textColor="#ffffff"
            android:text="送信" />

        <EditText
            android:id="@+id/id_input_msg"
            android:layout_width="fill_parent"
            android:layout_height="40dp"
            android:layout_marginLeft="10dp"
            android:layout_marginRight="10dp"
            android:layout_toLeftOf="@id/id_send_msg"
            android:layout_alignTop="@+id/id_send_msg"
            android:layout_alignParentBottom="true"
            android:textSize="18sp"
            android:textColorHint="#AAAAAA"
            android:hint="请输入..." />

    </RelativeLayout>

    <ListView
        android:id="@+id/id_listview_msgs"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        android:layout_below="@id/id_ly_top"
        android:layout_above="@id/id_ly_bottom"
        android:divider="@null"
        android:dividerHeight="5dp">
    </ListView>

</RelativeLayout>



/**

**/

1-4 实现基本的信息展示


//////////////////////////////////////////////////////////////////////////////////
1-4-1 MainActivity.class


package com.example.fangyi.ai_cat;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fangyi.ai_cat.bean.ChatMessage;
import com.example.fangyi.ai_cat.utils.HttpUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mMsgs; //用来展示所有的消息，聊天记录
    private ChatMessageAdapter messageAdapter;  //适配器
    private List<ChatMessage> mDatas;   //数据源

    private EditText mInputMsg;
    private Button mSendMsg;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //等待接收，子线程完成数据的返回
            ChatMessage fromeMessage = (ChatMessage) msg.obj;
            mDatas.add(fromeMessage);
            messageAdapter.notifyDataSetChanged();
            mMsgs.setSelection(mDatas.size()-1);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initDatas();
        initListener();
    }

    /**
     * 数据初始化
     */
    private void initDatas() {
        mDatas = new ArrayList<ChatMessage>();
        mDatas.add(new ChatMessage("你好，A.ICat为您服务", ChatMessage.Type.INCOMING, new Date()));
        messageAdapter = new ChatMessageAdapter(this, mDatas);

        mMsgs.setAdapter(messageAdapter);
    }

    /**
     * 布局初始化
     */
    private void initView() {
        mMsgs = (ListView) findViewById(R.id.id_listview_msgs);
        mInputMsg = (EditText) findViewById(R.id.id_input_msg);
        mSendMsg = (Button) findViewById(R.id.id_send_msg);
    }

    /**
     * 初始化事件
     */
    private void initListener() {
        mSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String toMsg = mInputMsg.getText().toString();
                if (TextUtils.isEmpty(toMsg)) {
                    Toast.makeText(MainActivity.this, "发送消息不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                //输入的数据也应该封装成ChatMessage
                ChatMessage toMessage = new ChatMessage();
                toMessage.setDate(new Date());
                toMessage.setMsg(toMsg);
                toMessage.setType(ChatMessage.Type.OUTCOMING);
                mDatas.add(toMessage);//加入ListView中
                messageAdapter.notifyDataSetChanged();//通知更新
                mMsgs.setSelection(mDatas.size()-1);

                mInputMsg.setText("");//输入框文本清空

                //子线程
                new Thread() {
                    @Override
                    public void run() {
                        ChatMessage fromMessage = HttpUtils.sendMessage(toMsg);
                        Message message = Message.obtain();
                        message.obj = fromMessage;
                        mHandler.sendMessage(message);
                    }
                }.start();
            }
        });
    }
}



//////////////////////////////////////////////////////////////////////////////////
///
1-4-2 ChatMessageAdapter


package com.example.fangyi.ai_cat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fangyi.ai_cat.bean.ChatMessage;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by FANGYI on 2016/3/26.
 */
public class ChatMessageAdapter extends BaseAdapter {

    private LayoutInflater mInflater;   //LayoutInflater 用来压榨 布局
    private List<ChatMessage> mDatas;   //数据集

    public ChatMessageAdapter(Context context, List<ChatMessage> mDatas) {
        mInflater = LayoutInflater.from(context);
        this.mDatas = mDatas;

    }

    /**
     * 消息的个数
     * @return
     */
    @Override
    public int getCount() {
        return mDatas.size();
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
        return mDatas.get(position);
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMessage chatMessage = mDatas.get(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            //通过ItemTypeshe设置不同的布局
            if (getItemViewType(position) == 0) {
                //接收消息的布局
                convertView = mInflater.inflate(R.layout.item_from_msg, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.mDate = (TextView) convertView.findViewById(R.id.id_form_msg_date);
                viewHolder.mMsg = (TextView) convertView.findViewById(R.id.id_form_msg_info);
            } else {
                //发送消息的布局
                convertView = mInflater.inflate(R.layout.item_to_msg, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.mDate = (TextView) convertView.findViewById(R.id.id_to_msg_date);
                viewHolder.mMsg = (TextView) convertView.findViewById(R.id.id_to_msg_info);
            }
            convertView.setTag(viewHolder);//存储viewHolder
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //设置数据
        SimpleDateFormat df = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ");
        viewHolder.mDate.setText(df.format(chatMessage.getDate()));

        viewHolder.mMsg.setText(chatMessage.getMsg());

        return convertView;
    }

    private final class ViewHolder {
        TextView mDate;
        TextView mMsg;
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage chatMessage = mDatas.get(position);
        if (chatMessage.getType() == ChatMessage.Type.INCOMING) {
            return 0;//接收消息
        }
        return 1;//发送消息
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }
}




//////////////////////////////////////////////////////////////////////////////////

1-4-3 bean/Result.class

package com.example.fangyi.ai_cat.bean;

/**
 * Created by FANGYI on 2016/3/25.
 * 专门映射服务器返回的结果
 */
public class Result {
    private int code;
    private String text;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}


//////////////////////////////////////////////////////////////////////////////////
///
///

1-4-4 text/TestHttpUtils.class

package com.example.fangyi.ai_cat.text;

import android.test.AndroidTestCase;
import android.util.Log;

import com.example.fangyi.ai_cat.utils.HttpUtils;

/**
 * Created by FANGYI on 2016/3/25.
 */
public class TestHttpUtils extends AndroidTestCase {
    public void testSendInfo() {
        String res = HttpUtils.doGet("给我讲个笑话");
        Log.e("TAG",res);
        res = HttpUtils.doGet("给我讲个鬼故事");
        Log.e("TAG",res);
        res = HttpUtils.doGet("你好");
        Log.e("TAG",res);
        res = HttpUtils.doGet("你真美");
        Log.e("TAG",res);
    }
}























































































































