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
        super();
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
