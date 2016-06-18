package com.malin.animation.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.InflateException;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.malin.animation.R;
import com.malin.animation.adapter.AutoTextViewAdapter;
import com.malin.animation.util.ClickUtils;
import com.malin.animation.util.ColorBitmapUtilTwo;
import com.malin.animation.util.RxUtils;
import com.malin.animation.util.ToastUtil;
import com.malin.animation.util.UnBindResourceUtil;
import com.malin.animation.view.ResizeLayout;
import com.orhanobut.logger.Logger;

import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * BiLiBiLi 登陆注册界面
 *
 * @author malin.myemail@163.com
 * @date 16-5-16.04:07
 * <p/>
 */
public class LoginFragment extends Fragment implements ResizeLayout.OnResizeListener {

    private static final String TAG = LoginFragment.class.getSimpleName();

    //left top image and right top image
    @BindView(R.id.iv_22)
    ImageView mImageView22;
    @BindView(R.id.iv_33)
    ImageView mImageView33;

    //the left image of nick name and pass word when they are focused
    private Bitmap mNickNameFocusedBitmap;
    private Bitmap mPassWordFocusedBitmap;

    //nick name and pass word
    @BindView(R.id.ed_user_nickname)
    AutoCompleteTextView mNickNameEditText;
    @BindView(R.id.ed_user_pwd)
    EditText mPassWordEditText;

    //the bottom line of nick name and pass word when they are focused
    @BindView(R.id.v_nick_name_bottom_line)
    View mViewNickNameBottomLine;
    @BindView(R.id.v_pass_word_bottom_line)
    View mViewPassWordBottomLine;

    //register and login button
    @BindView(R.id.btn_register)
    Button mRegisterButton;
    @BindView(R.id.btn_login)
    Button mLoginButton;

    //the clear layout of nick name and pass word when they are focused
    @BindView(R.id.ll_clear_name_layout)
    LinearLayout mLinearLayoutClearNickName;
    @BindView(R.id.ll_clear_pwd_layout)
    LinearLayout mLinearLayoutClearPassWord;

    @BindView(R.id.scroll_view)
    ScrollView mScrollView;
    @BindView(R.id.rl_login_root_layout)
    ResizeLayout mResizeLayout;


    private static final int BIGGER = 1;
    private static final int SMALLER = 2;
    private static final int MSG_RESIZE = 1;
    private static boolean isSoftOut = false;

    private InputHandler mHandler;
    private SmoothRunnable mSmoothRunnable;
    private Activity mActivity;


    private boolean mNickNameHasFocus;
    private boolean mPassWordHasFocus;

    private AutoTextViewAdapter mAutoTextViewAdapter;

    private String[] mAUTO_EMAILS;

    private float mNickNameLayoutHeightPx;
    private float mTipLayoutHeightPx;
    private float mEditBottomLineHeightPx;
    private float mNickNameAutoTextViewDropDownHeight;
    private ProgressDialog mProgressDialog;

    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView != null) {
            ViewGroup parent = (ViewGroup) mView.getParent();
            if (parent != null) {
                parent.removeView(mView);
            }
        }
        try {
            mView = inflater.inflate(R.layout.login_layout, container, false);
        } catch (InflateException e) {

        }
        ButterKnife.bind(this, mView);
        initValue();
        initListener();
        getBitmap();
        initAdapter();
        controlBack();
        return mView;
    }

    private static LoginFragment mLoginFragment;

    public static LoginFragment newInstance(Bundle bundle) {
        mLoginFragment = new LoginFragment();
        mLoginFragment.setArguments(bundle);
        return mLoginFragment;
    }


    private void initValue() {

        mActivity = getActivity();

        mHandler = new InputHandler();

        mNickNameHasFocus = true;
        mPassWordHasFocus = false;

        mTipLayoutHeightPx = mActivity.getResources().getDimension(R.dimen.common_dimen_106dip);
        mNickNameLayoutHeightPx = mActivity.getResources().getDimension(R.dimen.common_dimen_47dip);
        mEditBottomLineHeightPx = mActivity.getResources().getDimension(R.dimen.common_dimen_1dip);

        mNickNameEditText.setDropDownBackgroundDrawable(ContextCompat.getDrawable(mActivity, R.drawable.autocomplete_text_bg));
        mViewNickNameBottomLine.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.colorPrimary));
        mViewPassWordBottomLine.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.gray_dark));
    }


    private void initListener() {
        mResizeLayout.setOnSizeChangedListener(this);
    }


    private void initAdapter() {
        mAUTO_EMAILS = mActivity.getResources().getStringArray(R.array.mail_filter);
        mAutoTextViewAdapter = new AutoTextViewAdapter(mActivity);
        mNickNameEditText.setAdapter(mAutoTextViewAdapter);
        mNickNameEditText.setThreshold(1);//输入1个字符时就开始检测，默认为2个
    }


    /**
     * 在输入法中开启Done按钮，并监听其点击事件
     */

    @OnEditorAction({R.id.ed_user_nickname, R.id.ed_user_pwd})
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        switch (v.getId()) {

            // android:imeOptions="actionNext"
            // XML  android:singleLine="true"
            case R.id.ed_user_nickname: {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    mNickNameEditText.clearFocus();
                    mPassWordEditText.requestFocus();
                    return true;
                }
                break;
            }

            // XML android:imeOptions="actionDone"
            // XML  android:singleLine="true"
            case R.id.ed_user_pwd: {
                if (actionId == EditorInfo.IME_ACTION_DONE
                        || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {

                    goToLogin(false);
                    return true;
                }

                break;
            }

            default: {
                break;
            }
        }
        return false;
    }

    /**
     * EditText 文字编辑后的监听
     */

    @OnTextChanged(value = R.id.ed_user_nickname, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void onAfterTextChangedNickName(CharSequence s) {
        Logger.d("mNickNameEditText afterTextChanged");
        String nickName = s.toString();

        //1.自动补全邮箱
        mAutoTextViewAdapter.mList.clear();
        if (nickName.contains("@")) {
            autoAddEmails(nickName);
            mAutoTextViewAdapter.notifyDataSetChanged();
        }

        //2.其他逻辑
        String passWord = mPassWordEditText.getText().toString();
        if (!TextUtils.isEmpty(nickName) && !TextUtils.isEmpty(passWord)) {
            mLoginButton.setEnabled(true);
        } else {
            mLoginButton.setEnabled(false);
        }

        if (!TextUtils.isEmpty(nickName)) {
            mLinearLayoutClearNickName.setVisibility(View.VISIBLE);
        } else {
            mLinearLayoutClearNickName.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * EditText 文字编辑后的监听
     */

    @OnTextChanged(value = R.id.ed_user_nickname, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void onAfterTextChangedPassWord(CharSequence s) {
        Logger.d("mPassWordEditText afterTextChanged");
        String passWord = s.toString();
        String nickName = mNickNameEditText.getText().toString();
        if (!TextUtils.isEmpty(nickName) && !TextUtils.isEmpty(passWord)) {
            mLoginButton.setEnabled(true);
        } else {
            mLoginButton.setEnabled(false);
        }

        if (!TextUtils.isEmpty(passWord)) {
            mLinearLayoutClearPassWord.setVisibility(View.VISIBLE);
        } else {
            mLinearLayoutClearPassWord.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 自动填充邮箱列表
     *
     * @param input
     */

    private void autoAddEmails(String input) {
        Logger.d("input:" + input);
        String autoEmail;
        if (input != null && input.length() > 0) {
            for (int i = 0; i < mAUTO_EMAILS.length; ++i) {
                if (input.contains("@")) {//包含“@”则开始过滤
                    //12@16
                    //01234
                    String filter = input.substring(input.indexOf("@") + 1, input.length());//获取过滤器，即根据输入“@”之后的内容过滤出符合条件的邮箱

                    Logger.d("filter:" + filter);
                    if (mAUTO_EMAILS[i].contains(filter)) {//符合过滤条件
                        autoEmail = input.substring(0, input.indexOf("@") + 1) + mAUTO_EMAILS[i];//用户输入“@”之前的内容加上自动填充的内容即为最后的结果
                        mAutoTextViewAdapter.mList.add(autoEmail);
                    }
                } else {
                    autoEmail = input + mAUTO_EMAILS[i];
                    mAutoTextViewAdapter.mList.add(autoEmail);
                }
            }
            Logger.d("mAutoTextViewAdapter.mList.size():" + mAutoTextViewAdapter.mList.size());

            float textHeight = mActivity.getResources().getDimension(R.dimen.common_dimen_30dip);
            float sumHeight = mAutoTextViewAdapter.mList.size() * textHeight;

            if (sumHeight >= mNickNameAutoTextViewDropDownHeight) {
                mNickNameEditText.setDropDownHeight((int) mNickNameAutoTextViewDropDownHeight);
            } else {
                mNickNameEditText.setDropDownHeight((int) sumHeight);
            }
            Logger.d("mNickNameAutoTextViewDropDownHeight:" + mNickNameAutoTextViewDropDownHeight);
            Logger.d("sumHeight:" + sumHeight);
        }
    }


    /**
     * Interface definition for a callback to be invoked when the focus state of a view changed.
     *
     * @param v
     * @param hasFocus
     */

    @OnFocusChange({R.id.ed_user_nickname, R.id.ed_user_pwd})
    void onFocusChanged(View v, boolean hasFocus) {
        switch (v.getId()) {
            case R.id.ed_user_nickname: {
                if (hasFocus) {
                    Logger.d("nickname onFocusChange hasFocus = true");

                    if (mNickNameFocusedBitmap != null && !mNickNameFocusedBitmap.isRecycled()) {
                        //1.左边的小人变为粉红色
                        Drawable drawable = new BitmapDrawable(mActivity.getResources(), mNickNameFocusedBitmap);
                        // 这一步必须要做,否则不会显示.
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        mNickNameEditText.setCompoundDrawables(drawable, null, null, null);
                    }

                    //2.EditText的底部显示粉红色的细线
                    mViewNickNameBottomLine.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.colorPrimary));

                    //3.获取的焦点赋值
                    mNickNameHasFocus = true;

                    //4.获取了焦点同时文本内容不为空显示clear图标
                    if (mNickNameEditText != null && !TextUtils.isEmpty(mNickNameEditText.getText())) {
                        mLinearLayoutClearNickName.setVisibility(View.VISIBLE);
                    }

                } else {
                    Logger.d("nickname onFocusChange hasFocus != true");

                    //1.
                    Drawable drawable = ContextCompat.getDrawable(mActivity, R.mipmap.ic_login_username_default);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    mNickNameEditText.setCompoundDrawables(drawable, null, null, null);

                    //2.
                    mViewNickNameBottomLine.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.gray_dark));

                    //3.获取的焦点赋值
                    mNickNameHasFocus = false;

                    //4.
                    mLinearLayoutClearNickName.setVisibility(View.INVISIBLE);
                }
                break;
            }
            case R.id.ed_user_pwd: {
                if (hasFocus) {
                    Logger.d("password onFocusChange hasFocus = true");

                    if (mPassWordFocusedBitmap != null && !mPassWordFocusedBitmap.isRecycled()) {
                        //1.
                        Drawable drawable = new BitmapDrawable(mActivity.getResources(), mPassWordFocusedBitmap);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        mPassWordEditText.setCompoundDrawables(drawable, null, null, null);
                    }

                    //2.
                    mViewPassWordBottomLine.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.colorPrimary));

                    //3.
                    mImageView22.setImageResource(R.mipmap.ic_22_hide);
                    mImageView33.setImageResource(R.mipmap.ic_33_hide);

                    //4.
                    mPassWordHasFocus = true;

                    //5.

                    //4.获取了焦点同时文本内容不为空显示clear图标
                    if (mPassWordEditText != null && !TextUtils.isEmpty(mPassWordEditText.getText())) {
                        mLinearLayoutClearPassWord.setVisibility(View.VISIBLE);
                    }

                } else {
                    Logger.d("password onFocusChange hasFocus != true");

                    //1.
                    Drawable drawable = ContextCompat.getDrawable(mActivity, R.mipmap.ic_login_password_default);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    mPassWordEditText.setCompoundDrawables(drawable, null, null, null);

                    //2.
                    mViewPassWordBottomLine.setBackgroundColor(mActivity.getResources().getColor(R.color.gray_dark));

                    //3.
                    mImageView22.setImageResource(R.mipmap.ic_22);
                    mImageView33.setImageResource(R.mipmap.ic_33);

                    //4.
                    mPassWordHasFocus = false;

                    //5.
                    mLinearLayoutClearPassWord.setVisibility(View.INVISIBLE);
                }
                break;
            }
            default: {
                break;
            }
        }
    }


    @OnClick({R.id.btn_register, R.id.btn_login, R.id.ll_clear_name_layout, R.id.ll_clear_pwd_layout})
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_register: {
                if (ClickUtils.isFastDoubleClick()) return;
                ToastUtil.showToast(mActivity, R.string.register_doing);

                //TODO:delete
                if (mSmoothRunnable != null) {
                    mSmoothRunnable.stopTask();
                }

                if (isSoftOut) {
                    hideSoft();
                    Logger.d("open soft");
                } else {
                    Logger.d("close soft");
                }
                break;
            }


            case R.id.btn_login: {
                goToLogin(true);
                break;
            }

            case R.id.ll_clear_name_layout: {
                mNickNameEditText.requestFocus();
                mNickNameEditText.setText("");
                if (!isSoftOut) {
                    try {
                        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(mNickNameEditText, InputMethodManager.SHOW_FORCED);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                break;
            }

            case R.id.ll_clear_pwd_layout: {

                mPassWordEditText.requestFocus();
                mPassWordEditText.setText("");
                if (!isSoftOut) {
                    try {
                        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(mPassWordEditText, InputMethodManager.SHOW_FORCED);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                break;
            }

            default: {
                break;
            }

        }
    }

    /**
     * 登陆判断逻辑
     */

    private void goToLogin(boolean isCloseSoftInput) {
        if (ClickUtils.isFastDoubleClick()) return;

        if (mNickNameEditText != null && mPassWordEditText != null) {

            String nickName = mNickNameEditText.getText().toString().replaceAll(" ", "").trim();
            String passWord = mPassWordEditText.getText().toString().replaceAll(" ", "").trim();


            boolean nickNameIsEmpty = TextUtils.isEmpty(nickName);
            boolean passWordIdEmpty = TextUtils.isEmpty(passWord);
            if (nickNameIsEmpty) {
                mNickNameEditText.setText("");
                mNickNameEditText.setFocusable(true);
                mNickNameEditText.requestFocus();
                mNickNameEditText.setHint(mActivity.getResources().getString(R.string.please_input_your_nick_name));
                ToastUtil.showToast(mActivity, R.string.please_input_your_nick_name);

                //2.
                Animation shake = AnimationUtils.loadAnimation(mActivity, R.anim.shake);
                mNickNameEditText.startAnimation(shake);
                mViewNickNameBottomLine.startAnimation(shake);
                //3.
                if (!isSoftOut) {
                    InputMethodManager inputManager = (InputMethodManager) mNickNameEditText
                            .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputManager.showSoftInput(mNickNameEditText, 0);
                }
                return;
            }
            if (passWordIdEmpty) {
                mPassWordEditText.setText("");
                mPassWordEditText.setFocusable(true);
                mPassWordEditText.requestFocus();
                mPassWordEditText.setHint(mActivity.getResources().getString(R.string.please_input_your_password));
                ToastUtil.showToast(mActivity, R.string.please_input_your_password);

                //2.
                Animation shake = AnimationUtils.loadAnimation(mActivity, R.anim.shake);
                mPassWordEditText.startAnimation(shake);
                mViewPassWordBottomLine.startAnimation(shake);

                //3.
                if (!isSoftOut) {
                    try {
                        InputMethodManager inputManager = (InputMethodManager) mNickNameEditText
                                .getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputManager.showSoftInput(mNickNameEditText, 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                return;
            } else {
                if (isSoftOut) {
                    hideSoft();
                    Logger.d("open soft");
                } else {
                    Logger.d("close soft");
                }
            }

            if (isCloseSoftInput) {
                if (isSoftOut) {
                    hideSoft();
                    Logger.d("open soft");
                } else {
                    Logger.d("close soft");
                }
            }
            if (mActivity != null && !mActivity.isFinishing()) {
                showLoadingDialog();
            }
        }
    }

    private Subscription mSubscription;


    private void getBitmap() {
        mSubscription = createObservableBitmap()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .filter(new Func1<Bitmap, Boolean>() {
                    @Override
                    public Boolean call(Bitmap bitmap) {
                        return bitmap != null && !bitmap.isRecycled();
                    }
                })
                .observeOn(Schedulers.io())
                .map(new Func1<Bitmap, Drawable>() {
                    @Override
                    public Drawable call(Bitmap bitmap) {
                        mNickNameFocusedBitmap = bitmap;
                        Drawable drawable = new BitmapDrawable(mActivity.getResources(), bitmap);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                        return drawable;
                    }
                })
                .observeOn(Schedulers.io())
                .filter(new Func1<Drawable, Boolean>() {
                    @Override
                    public Boolean call(Drawable drawable) {
                        return drawable != null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Drawable>() {
                    @Override
                    public void call(Drawable drawable) {
                        if (mNickNameHasFocus) {
                            mNickNameEditText.setCompoundDrawables(drawable, null, null, null);
                        }
                    }
                });
        int color = ContextCompat.getColor(mActivity, R.color.theme_color_secondary);
        mPassWordFocusedBitmap = ColorBitmapUtilTwo.getColorBitmap(mActivity.getResources(), R.mipmap.ic_login_password_default, color, 255);
    }


    public Observable<Bitmap> createObservableBitmap() {
        return Observable.defer(new Func0<Observable<Bitmap>>() {
            @Override
            public Observable<Bitmap> call() {
                return Observable.just(getColorBitmap());
            }
        });
    }


    private Bitmap getColorBitmap() {
        int color = ContextCompat.getColor(mActivity, R.color.theme_color_secondary);
        return ColorBitmapUtilTwo.getColorBitmap(mActivity.getResources(), R.mipmap.ic_login_username_default, color, 255);
    }


    @Override
    public void onDestroy() {
        try {
            View rootView = mView.findViewById(R.id.rl_login_root_layout);
            UnBindResourceUtil.unBingListener(rootView);
            UnBindResourceUtil.unBindDrawables(rootView);
            UnBindResourceUtil.unBindDrawables(mProgressDialogRootView);
            UnBindResourceUtil.unBindBitmap(mNickNameFocusedBitmap);
            UnBindResourceUtil.unBindBitmap(mPassWordFocusedBitmap);
            RxUtils.unsubscribeIfNotNull(mSubscription);
            if (mSmoothRunnable != null) {
                mSmoothRunnable.stopTask();
            }
            if (mTimerTask != null) {
                mTimerTask.cancel();
                mTimerTask = null;
            }

            if (mHandler != null) {
                mHandler.removeCallbacksAndMessages(null);
                mHandler = null;
            }

            if (mAUTO_EMAILS != null) {
                mAUTO_EMAILS = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }


    public static class InputHandler extends Handler {


        public InputHandler() {
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_RESIZE: {
                    if (msg.arg1 == BIGGER) {
                        isSoftOut = false;
                        Logger.d("键盘隐藏");
                    } else {
                        isSoftOut = true;
                        Logger.d("键盘弹出");
                    }


                    break;
                }

                default: {
                    break;
                }
            }
            super.handleMessage(msg);
        }
    }

    @Override

    public void OnResize(int w, int h, int oldw, int oldh) {
        if (h < oldh && mScrollView != null) {
            mSmoothRunnable = new SmoothRunnable(mActivity, mScrollView, h, oldh);
            mScrollView.post(mSmoothRunnable);
        }
        int change = BIGGER;
        if (h < oldh) {
            change = SMALLER;
        }

        Logger.d("new:" + h);
        Logger.d("old:" + oldh);

        int newContentHeight = h;
        mNickNameAutoTextViewDropDownHeight = newContentHeight - (mNickNameLayoutHeightPx + mTipLayoutHeightPx + mEditBottomLineHeightPx);

        if (mNickNameAutoTextViewDropDownHeight < 0) {
            mNickNameAutoTextViewDropDownHeight = newContentHeight - mNickNameLayoutHeightPx - mEditBottomLineHeightPx;
        }


        Logger.d("newContentHeight:" + newContentHeight);
        Logger.d("mTipLayoutHeightPx:" + mTipLayoutHeightPx);
        Logger.d("mNickNameLayoutHeightPx:" + mNickNameLayoutHeightPx);
        Logger.d("mEditBottomLineHeightPx:" + mEditBottomLineHeightPx);
        Logger.d("mNickNameAutoTextViewDropDownHeight:" + mNickNameAutoTextViewDropDownHeight);
        Logger.d("(int)mNickNameAutoTextViewDropDownHeight:" + (int) mNickNameAutoTextViewDropDownHeight);
        //TODO:使用RxJava 替换
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mNickNameEditText.setDropDownHeight((int) mNickNameAutoTextViewDropDownHeight);
            }
        });

        Message msg = mHandler.obtainMessage();
        msg.what = MSG_RESIZE;
        msg.arg1 = change;
        msg.obj = h;
        mHandler.sendMessage(msg);
    }


    public static class SmoothRunnable implements Runnable {
        private volatile boolean finished = false;
        private final int h;
        private final int oldHeight;
        private WeakReference<Activity> mLoginActRef;
        private ScrollView mScrollView;

        public SmoothRunnable(Activity loginActivity, ScrollView scrollView, int h, int oldh) {
            this.mLoginActRef = new WeakReference<Activity>(loginActivity);
            this.h = h;
            this.oldHeight = oldh;
            this.mScrollView = scrollView;
        }

        public void stopTask() {
            finished = true;
        }

        @Override
        public void run() {
            synchronized (this) {
                try {
                    if (!finished) {
                        Logger.d("SmoothRunnable flag=true");
                        Activity activity = mLoginActRef.get();
                        if (activity != null && !activity.isFinishing()) {
                            mScrollView.smoothScrollBy(0, Math.abs(this.h - this.oldHeight));
                            Logger.d("mScrollView smoothScrollBy " + Math.abs(this.h - this.oldHeight));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Logger.d("SmoothRunnable Exception");
                }
            }

        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    private void controlBack() {
        final String TAG = "controlBackS";
        mNickNameEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    Logger.t(TAG).d("mNickNameEditText onKey");
                    onKeyDownAction();
                }
                return false;
            }
        });
        mPassWordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    Logger.t(TAG).d("mPassWordEditText onKey");
                    onKeyDownAction();
                }
                return false;
            }
        });
    }


    private void onKeyDownAction() {
        if (mActivity != null && !mActivity.isFinishing() && mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
        if (isSoftOut) {
            hideSoft();
            Logger.d("open soft");
        } else {
            Logger.d("close soft");
        }
        delayCloseActivity();
    }


    private void hideSoft() {
        /**
         * 避免出现如下的空指针错误
         * java.lang.NullPointerException:
         * Attempt to invoke virtual method 'android.os.IBinder android.view.View.getWindowToken()' on a null object reference
         */
        try {
            if (mActivity != null && !mActivity.isFinishing()) {
                Window window = mActivity.getWindow();
                if (window != null) {
                    View view = window.getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (imm != null) {
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private View mProgressDialogRootView;
    private LayoutInflater mLayoutInflater;
    private LinearLayout.LayoutParams mLayoutParams;


    private void showLoadingDialog() {
        mLayoutInflater = LayoutInflater.from(mActivity);
        mProgressDialogRootView = mLayoutInflater.inflate(R.layout.progress_dialog_layout, (ViewGroup) mView.getParent(), false);// 得到加载view
        mProgressDialog = new ProgressDialog(mActivity);// 创建自定义样式dialog
        mProgressDialog.setIndeterminate(true);//设置进度条为不明确
        mProgressDialog.setCancelable(true);// 可以用“返回键”取消
        mProgressDialog.setCanceledOnTouchOutside(false);//点击ProgressDialog之外的地方Dialog不消失

        if (mActivity != null && !mActivity.isFinishing()) {
            mProgressDialog.show();
        }
        mLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                (int) mActivity.getResources().getDimension(R.dimen.common_dimen_65dip)
        );
        mProgressDialog.setContentView(mProgressDialogRootView,
                mLayoutParams);// 设置布局
    }

    /**
     * 延时关闭页面
     */
    private TimerTask mTimerTask;


    private void delayCloseActivity() {
        mTimerTask = new TimerTask() {
            public void run() {
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mActivity != null && !mActivity.isFinishing()) {
                            mActivity.finish();
                        }
                    }
                });
            }
        };
        Timer timer = new Timer();
        timer.schedule(mTimerTask, 300);
    }
}
