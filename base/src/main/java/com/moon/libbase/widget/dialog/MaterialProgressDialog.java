package com.moon.libbase.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.moon.libbase.R;

/**
 * Created by TimoRD on 2016/3/10.
 */
public class MaterialProgressDialog extends Dialog implements ProgressDialog {

    private Activity mActivity;
    private String mTitle;
    private String mMessage;
    private boolean mFinish;
    private TextView title;
    private TextView message;

    public MaterialProgressDialog(Activity activity, boolean finish) {
        super(activity, R.style.Theme_AppCompat_Light_Dialog);
        mActivity = activity;
        mFinish = finish;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_dialog);
        title = findViewById(R.id.title);
        message = findViewById(R.id.message);
        if (!TextUtils.isEmpty(mTitle)) {
            title.setText(mTitle);
            title.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(mMessage)) {
            message.setText(mMessage);
        }

        setCancelable(true);
        setCanceledOnTouchOutside(false);
        getWindow().setGravity(Gravity.CENTER);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mFinish) {
            mActivity.finish();
        }
    }

    @Override
    public void setTitle(String title) {
        mTitle = title;
    }

    @Override
    public void setMessage(String message) {
        mMessage = message;
    }
}
