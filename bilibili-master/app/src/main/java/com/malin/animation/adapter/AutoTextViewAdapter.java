package com.malin.animation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.malin.animation.R;

import java.util.ArrayList;
import java.util.List;


public class AutoTextViewAdapter extends BaseAdapter implements Filterable {

    public List<String> mList;
    private Context mContext;
    private MyFilter mFilter;

    public AutoTextViewAdapter(Context context) {
        mContext = context;
        mList = new ArrayList<String>();
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return (mList != null && position < mList.size()) ? mList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.auto_text_nick_name_item, parent, false);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_auto_nick_name);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(mList.get(position));
        return convertView;
    }

    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new MyFilter();
        }
        return mFilter;
    }

    private class MyFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if (mList == null) {
                mList = new ArrayList<String>();
            }
            results.values = mList;
            results.count = mList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }

    }

    private static class ViewHolder {
        public TextView textView;
    }
}