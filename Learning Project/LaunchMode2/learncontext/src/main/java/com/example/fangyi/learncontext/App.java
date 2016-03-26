package com.example.fangyi.learncontext;

import android.app.Application;

/**
 * Created by FANGYI on 2016/1/25.
 */
public class App extends Application{
    private String textData = "default";

    public String getTextData() {
        return textData;
    }

    public void setTextData(String textData) {
        this.textData = textData;
    }
}
