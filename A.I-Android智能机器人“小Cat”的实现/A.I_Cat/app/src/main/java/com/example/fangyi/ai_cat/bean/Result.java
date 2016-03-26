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
