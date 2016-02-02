package com.example.fangyi.learncontext;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by FANGYI on2016/1/25.
        */
public class Main2 extends Activity {
    private TextView textView;
    private EditText editText;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.main2);
        textView = (TextView) findViewById(R.id.textView);
        editText = (EditText) findViewById(R.id.editText);

        textView.setText("共享的数据是："+ editText.getText().toString());
        findViewById(R.id.btnSaveData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((App) getApplicationContext()).setTextData(editText.getText().toString());
                textView.setText("共享的数据是：" + editText.getText().toString());
            }
        });
    }
}
