事件分发处理及EventBus和Otto的简介和对比。

时间总线管理：
    ·将事件放到队列里，用于管理和分发
    ·保证应用的各个部分之间搞笑的通信及数据、事件分发
    ·模块间解耦

Event Bus 是一个发布/订阅的时间总线。Event Bus 模式 —— 也称为 Message Bus 或者发布者/订阅者 
(publisher/subscribe) 模式 —— 可以让两个组建互相通信，但是他们之间并不互相知晓。

基于时间总线管理/订阅/分发模式的。事件相应有更多的线程选择，EventBus 可以向不同的线程中发布
事件。EventBus 支持 Sticky Event。

使用时需要先注册订阅，然后订阅者分发消息数据即可。包含4个成分：分布着，订阅者，时间，总线。
订阅者可以订阅多个时间，发送者可以发布任何事件，发布者同时也可以是订阅者。分订阅、注册、发布
取消注册等步骤。



			Event 				---Event--> Subscribe
Publisher --------->  Event Bus 			onEvent()
(事件总线)	post()				
								---Event--> Subscribe
											onEvent()



						  EventsBus		Otto

声明事件处理的方法		采用命名规范	 注解
事件继承					Yes			 Yes
用户继承					Yes			 NO
缓存最近的事件				Yes,滞留事件 Yes
时间产生者(例如编码缓存时间)NO			 Yes
交互线程的时间传递			Yes			 Yes
主线程中的时间传递			Yes			 NO
后台线程的时间传递			Yes			 NO
异步的事件传递				Yes			 NO



Event Bus 的基本用法

分订阅、注册、发布、取消注册。

    ·注册:
    	EventBus.getDefault().register(this);
    	EventBus.getDefault().register(new MyClass());

    	//注册：三个参数分别是，消息订阅者（接受者），接收方法名，事件类
    	EventBus.getDefault().register(this,"setTextA",SetTextEvent.class);

    ·取消注册:
    	EventBus.getDefault.unregister(this);
    	EventBus.getDefault.unregister(new MyClass());    	

    ·订阅处理数据:
    	public void onEventMainThread {}
    	public void onEvent(AnyEventType event) {}
    	onEventPostThread	//交互
    	onEventBackgroundThread	//后台
    	onEventAsync	//异步
    
    ·发布:
    	EventBus.getDefault.postSticky(new SecondActivityEvent("Message From SecondActivity"));	//发不滞留的消息
    	EventBus.getDefault().post(new ChangelmgEvent(1));	//直接发布


/**
 
 */

库:
	eventbus.jar

实现效果:
	一处点击发送数据，另一处或多处注册点可以及时获取更新传输过来的数据

知识点:
	事件类自定义、注册，订阅事件、事件的发布、数据解析、取消注册。

/**
 
 */


package com.example.fangyi.eventbusdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);//注册
    }

    /**
     * 取消注册
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 发送事件
     */
    private void postData() {
        String string = "我是消息";
        EventBus.getDefault().post(string);
    }

    /**
     * 默认接收数据消息事件
     */
    private void Event(String string) {

    }

    /**
     * 接收数据消息事件
     * 主线程
     */
    public void onEventMainThread(String string) {

    }
    /**
     * 接收数据消息事件
     * 交互
     */
    public void onEventPostThread(String string) {

    }
    /**
     * 接收数据消息事件
     * 后台
     */
    public void onEventBackgroundThread(String string) {

    }
    /**
     * 接收数据消息事件
     * 异步
     */
    public void onEventAsync(String string) {

    }


}




/**
 
 */


    <Button
        android:id="@+id/btn_send"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="发送事件"/>

    <TextView
        android:id="@+id/tv_content"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        android:gravity="center"
        android:textSize="20sp"/>







package com.example.fangyi.eventbusdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {

    private TextView tv_content;
    private Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_content= (TextView) this.findViewById(R.id.tv_content);
        btn_send = (Button) this.findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {   //发送数据事件
                MyEvent myEvent = new MyEvent();
                myEvent.setType("1");
                myEvent.setContent("1内容");
                EventBus.getDefault().post(myEvent);

            }
        });

        EventBus.getDefault().register(this);//当前类注册
    }

    public void onEventMainThread(MyEvent event) {
        if (event.getType().equals("1")) {
            tv_content.setText(event.getContent());
        }
    }

    /**
     * 取消注册
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    /**
     * 接收数据
     */
    public void onEvent(MyEvent event) {
        if (event.getType().equals("0")) {
            tv_content.setText(event.getContent());
        }
    }
}




















