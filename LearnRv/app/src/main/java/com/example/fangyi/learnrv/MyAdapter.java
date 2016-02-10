package com.example.fangyi.learnrv;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import javax.security.auth.callback.Callback;

/**
 * Created by FANGYI on 2016/2/10.
 */
class MyAdapter extends RecyclerView.Adapter {

    //使用onCreateViewHolder()，创建一个自定义类
    class ViewHolder extends RecyclerView.ViewHolder {

        private View root;//绑定一个子对象的视图，用他呈现一些数据
        private TextView tvTitle, tvContent;

        public ViewHolder(View root) {
            super(root);

            tvTitle = (TextView) root.findViewById(R.id.tvTitle);
            tvContent = (TextView) root.findViewById(R.id.tvContent);
        }

        public TextView getTvTitle() {
            return tvTitle;
        }

        public TextView getTvContent() {
            return tvContent;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cell, null));//根据一个资源进行创建,布局解释器
    }

    @Override//第二个参数是索引，当前显式的是哪一条
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;

        CellData cd = data[position];

        vh.getTvTitle().setText(cd.title);
        vh.getTvContent().setText(cd.content);
    }

    @Override//获取 RecycleView 子对象的数量，比如返回10，创建了10个子对象
    public int getItemCount() {
//                return 1000;
        return data.length;
    }

    private CellData[] data = new CellData[]{new CellData("Hello","fangyi1896@gmail.com"), new CellData("大笨蛋","fangyi186@outlook.com"),
            new CellData("今井麻美","朝焼けのスターマイン"), new CellData("水樹奈々","深爱"),
            new CellData("今井麻美","朝焼けのスターマイン"), new CellData("水樹奈々","深爱"),
            new CellData("今井麻美","朝焼けのスターマイン"), new CellData("水樹奈々","深爱"),
            new CellData("今井麻美","朝焼けのスターマイン"), new CellData("水樹奈々","深爱"),
            new CellData("今井麻美","朝焼けのスターマイン"), new CellData("水樹奈々","深爱"),
            new CellData("今井麻美","朝焼けのスターマイン"), new CellData("水樹奈々","深爱")};
}
