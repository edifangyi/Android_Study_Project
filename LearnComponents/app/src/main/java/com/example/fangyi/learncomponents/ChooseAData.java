package com.example.fangyi.learncomponents;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class ChooseAData extends AppCompatActivity {

    private Button btnChooseData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_adata);


        btnChooseData = (Button) findViewById(R.id.btnChooseData);
        btnChooseData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在这里呈现一个日期选择器
                new DatePickerDialog(ChooseAData.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        //把数字呈现在按钮里
                        String theDate = String.format("%d-%d-%d", year, monthOfYear + 1, dayOfMonth);

                        btnChooseData.setText(theDate);

                        //月份从0快开始的
                        System.out.println(theDate);
                    }
                },2016,1,10).show();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
