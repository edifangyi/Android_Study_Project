package com.example.fangyi.myviewdemo.view_1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by FANGYI on 2016/6/30.
 */

public class VpSimpleFragment extends Fragment {
    private String mTitle;
    public static final String BUNDLE_TITLE = "title";

    public static VpSimpleFragment newInstance(String title) {

        /**
         * 传入数据
         */
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);

        VpSimpleFragment fragment = new VpSimpleFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        super.onCreateView(inflater, container, savedInstanceState);

        /**
         * 取出数据
         */
        Bundle bundle = getArguments();
        if (bundle !=null) {
            mTitle = bundle.getString(BUNDLE_TITLE);
        }

        /**
         * 可以传入一个布局
         */
        TextView tv = new TextView(getActivity());
        tv.setText(mTitle);
        tv.setGravity(Gravity.CENTER);

        return tv;

    }
}
