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
