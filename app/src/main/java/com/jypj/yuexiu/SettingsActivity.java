package com.jypj.yuexiu;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.jypj.yuexiu.base.BaseActivity;

/**
 * Created by Administrator on 2016/10/12 0012.
 */

public class SettingsActivity extends BaseActivity {
    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        addStatusBarView();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.actcivity_settings;
    }

    public void Exit(View view) {
        new AlertDialog.Builder(SettingsActivity.this, R.style.AlertDialogTheme).setTitle("提示").setMessage("确定退出登录吗?").setCancelable(false).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this).edit();
                editor.putBoolean("islogin", false);
                editor.commit();
                finish();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }
}
