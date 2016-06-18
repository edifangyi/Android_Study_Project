package com.malin.animation.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.malin.animation.R;
import com.malin.animation.util.ToastUtil;

/**
 * BiLiBiLi 登陆注册界面
 *
 * @author malin.myemail@163.com
 * @date 16-6-15.23:07
 * <p/>
 */
public class MainActivity extends AppCompatActivity {

    private LoginFragment mLoginFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        if (savedInstanceState == null) {
            setDefaultFragment();
        }
        initToolBar();
    }


    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("");
            toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));//标题颜色
            toolbar.setSubtitle("");
            toolbar.setSubtitleTextColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));//副标题颜色
            toolbar.setLogo(null);
            toolbar.setNavigationIcon(ContextCompat.getDrawable(getApplicationContext(), R.mipmap.toolbar_back_icon));//导航图标,最左边的图标
            setSupportActionBar(toolbar);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showToast(MainActivity.this, "Back");
                }
            });
        }
    }

    private void setDefaultFragment() {
        if (mLoginFragment == null) {
            Bundle bundle = new Bundle();
            mLoginFragment = LoginFragment.newInstance(bundle);
        }
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_content_layout, mLoginFragment, "mLoginFragment");
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.forget_password: {
                ToastUtil.showToast(MainActivity.this, getResources().getString(R.string.forget_password));
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
