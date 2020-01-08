package com.moon.libbase.widget.dialog;

/**
 * Created by TimoRD on 2016/3/14.
 */
public interface ProgressDialog {

    void setTitle(String title);

    void setMessage(String message);

    boolean isShowing();

    void show();

    void dismiss();

    void setCancelable(boolean cancel);
}
