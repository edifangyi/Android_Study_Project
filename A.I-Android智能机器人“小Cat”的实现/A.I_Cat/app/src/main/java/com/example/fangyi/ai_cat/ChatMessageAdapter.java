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
