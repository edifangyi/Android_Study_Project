A.I-Android智能机器人“小慕”的实现

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

--------------------------------------------------------------------------------------------

1-1 编写消息发送与接收的工具类 HttpUtils.class


package com.example.fangyi.ai_smallf;

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
 * Created by FANGYI on 2016/3/24.
 */
public class HttpUtils {
    private static final String URL = "http://www.tuling123.com/openapi/api";
    private static final String API_KEY = "c12dae806af96440cb2967cdb9d0933d";

    /**
     *发送一个消息，得到返回的消息
     * @param msg
     * @return
     */
    private static ChatMessage sendMessage(String msg) {
        ChatMessage chatMessage = new ChatMessage();
        String jsonRes = doGet(msg);
        Gson gson = new Gson();
        Result result = null;
        try {
            result = gson.fromJson(jsonRes, Result.class);
            chatMessage.setMsg(result.getText());//转换成功，输出转换后的消息
        } catch (JsonSyntaxException e) {
            chatMessage.setMsg("服务器繁忙，请稍候再试");
        }
        chatMessage.setDate(new Date());
        chatMessage.setType(ChatMessage.Type.INCOMING);
        return chatMessage;
    }

    /**
     *
     * @param msg
     * @return
     */
    public static String doGet(String msg) {
        String result = "";
        String url = setParams(msg);
        ByteArrayOutputStream baos = null;
        InputStream is = null;
        try {
            java.net.URL urlNet = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlNet.openConnection();
            conn.setReadTimeout(5*1000);//5秒
            conn.setConnectTimeout(5*1000);
            conn.setRequestMethod("GET");//请求方式

            is = conn.getInputStream();//拿到服务器返回的流
            int len = -1;
            byte[] buf = new byte[128];//缓冲区，128个字节
            baos = new ByteArrayOutputStream();//把byte转换为String
            while ((len = is.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }
            baos.flush();//清除缓冲区
            result = new String(baos.toByteArray());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     *
     * @param msg
     * @return
     */
    private static String setParams(String msg) {
        String url = "";
        try {
            url = URL + "?key=" + API_KEY +"&info=" + URLEncoder.encode(msg,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }
}


--------------------------------------------------------------------------------------------

1-2 测试工具类中方法


1-2-1 搭建环境

<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.fangyi.ai_smallf">
    <uses-permission android:name="android.permission.INTERNET"/>	//网络权限

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <uses-library android:name="AndroidTestRunner"/>	//第一步，引入uses-library
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>
    <instrumentation 	//第二步，引入instrumentation 
        android:name="android.test.InstrumentationTestRunner"
        android:targetPackage="com.example.fangyi.ai_smallf"
        android:label="this is a test">
    </instrumentation>

</manifest>

//////////////////////////////////////////////////////////////////////////////////

1-2-2	TestHttpUtils.class

package com.example.fangyi.ai_smallf;

import android.test.AndroidTestCase;
import android.util.Log;

/**
 * Created by FANGYI on 2016/3/24.
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

1-2-3 Result.class

package com.example.fangyi.ai_smallf;

/**
 * Created by FANGYI on 2016/3/24.
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

1-2-4 ChatMessage.class

package com.example.fangyi.ai_smallf;

import java.util.Date;

/**
 * Created by FANGYI on 2016/3/24.
 */
public class ChatMessage {
    private String name;    //名称
    private String msg;     //消息内容
    private Type type;      //类型，用于发送消息还是接收消息
    private Date date;

    public enum Type
    {
        INCOMING,OUTCOMING
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


--------------------------------------------------------------------------------------------

1-3 实现聊天布局

1-3-1 回复消息的布局 item_from_msg.xml

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical" >

    <TextView
        android:id="@+id/id_form_msg_date"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:background="#bebebe"
        android:textColor="#f5f5f5"
        android:textSize="12sp"
        android:text=" 2012-12-12 12:12:12 "/>

    <LinearLayout
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal" >

        <LinearLayout
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:orientation="vertical" >

            <ImageView
                android:layout_width="49dp"
                android:layout_height="49dp"
                android:src="@drawable/icon" />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                android:text="小F"
                android:textSize="18sp" />

        </LinearLayout>

        <TextView
            android:id="@+id/id_from_msg_info"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:background="@drawable/chatfrom_bg_normal"
            android:gravity="center_vertical"
            android:textSize="16sp"
            android:text="喵喵ฅ(^ω^ )ฅ" />

    </LinearLayout>

</LinearLayout>

//////////////////////////////////////////////////////////////////////////////////

1-3-2 发送消息的布局 item_to_msg.xml


<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:gravity="right"
    android:orientation="vertical" >

    <TextView
        android:id="@+id/id_to_msg_date"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:background="#bebebe"
        android:textColor="#f5f5f5"
        android:textSize="12sp"
        android:text=" 2012-12-12 12:12:12 " />

    <LinearLayout
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:gravity="right"
        android:orientation="horizontal" >

        <TextView
            android:id="@+id/id_to_msg_info"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:background="@drawable/chatto_bg_normal"
            android:gravity="center_vertical"
            android:textSize="16sp"
            android:text=" 你好" />

        <LinearLayout
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:orientation="vertical" >

            <ImageView
                android:layout_width="49dp"
                android:layout_height="49dp"
                android:src="@drawable/me" />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                android:textSize="18sp"
                android:text="大魔王" />
        </LinearLayout>


    </LinearLayout>

</LinearLayout>

//////////////////////////////////////////////////////////////////////////////////

1-3-2 发送消息的布局 item_to_msg.xml

<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:gravity="right"
    android:orientation="vertical" >

    <TextView
        android:id="@+id/id_to_msg_date"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center"
        android:background="#bebebe"
        android:textColor="#f5f5f5"
        android:textSize="12sp"
        android:text=" 2012-12-12 12:12:12 " />

    <LinearLayout
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        android:gravity="right"
        android:orientation="horizontal" >

        <TextView
            android:id="@+id/id_to_msg_info"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:background="@drawable/chatto_bg_normal"
            android:gravity="center_vertical"
            android:textSize="16sp"
            android:text=" 你好" />

        <LinearLayout
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:orientation="vertical" >

            <ImageView
                android:layout_width="49dp"
                android:layout_height="49dp"
                android:src="@drawable/me" />

            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                android:textSize="18sp"
                android:text="大魔王" />
        </LinearLayout>


    </LinearLayout>

</LinearLayout>

//////////////////////////////////////////////////////////////////////////////////

1-3-3 主布局 item_to_msg.xml

<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <RelativeLayout
        android:id="@+id/id_ly_top"
        android:layout_width="fill_parent"
        android:layout_height="45dp"
        android:layout_alignParentTop="true"
        android:background="@drawable/title_bar" >

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerInParent="true"
            android:textColor="#ffffff"
            android:textSize="22sp"
            android:text="小F" />
    </RelativeLayout>

    <RelativeLayout
        android:id="@+id/id_ly_bottom"
        android:layout_width="fill_parent"
        android:layout_height="55dp"
        android:layout_alignParentBottom="true"
        android:background="@drawable/bottom_bar" >

        <Button
            android:id="@+id/id_send_msg"
            android:layout_width="60dp"
            android:layout_height="40dp"
            android:layout_alignParentRight="true"
            android:layout_centerVertical="true"
            android:background="@drawable/send_btn_bg"
            android:text="发送" />

        <EditText
            android:id="@+id/id_input_msg"
            android:layout_width="fill_parent"
            android:layout_height="40dp"
            android:layout_centerVertical="true"
            android:layout_marginLeft="10dp"
            android:layout_marginRight="10dp"
            android:layout_toLeftOf="@id/id_send_msg"
            android:background="@drawable/login_edit_normal"
            android:textSize="18sp" />
    </RelativeLayout>

    <ListView
        android:id="@+id/id_listview_msgs"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        android:layout_above="@id/id_ly_bottom"
        android:layout_below="@id/id_ly_top"
        android:divider="@null"
        android:dividerHeight="5dp" >
    </ListView>

</RelativeLayout>


--------------------------------------------------------------------------------------------

1-4 实现基本的信息展示

--------------------------------------------------------------------------------------------
1-5 实现与小慕的对话


























































































































