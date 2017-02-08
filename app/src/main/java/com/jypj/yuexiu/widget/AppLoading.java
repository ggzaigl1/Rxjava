package com.jypj.yuexiu.widget;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.jypj.yuexiu.R;


public class AppLoading {

    private static Dialog dialog;

    public static void show(Context context) {
        dialog = new Dialog(context, R.style.app_dialog);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.setCanceledOnTouchOutside(false);
        TextView msg = (TextView) dialog.findViewById(R.id.id_tv_loadingmsg);
        msg.setText("数据加载中...");
        dialog.show();
    }

    public static void loginshow(Context context) {
        dialog = new Dialog(context, R.style.app_dialog);
        dialog.setContentView(R.layout.dialog_loading);
        dialog.setCanceledOnTouchOutside(false);
        TextView msg = (TextView) dialog.findViewById(R.id.id_tv_loadingmsg);
        msg.setText("正在登录中...");
        dialog.show();
    }

    public static void close() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

}
