package com.jypj.yuexiu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

import com.jypj.yuexiu.base.BaseActivity;

/**
 * Created by Administrator on 2016/8/26 0026.
 */
public class StartActivity extends BaseActivity {

    private Boolean exit = false;

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        translucentStatusBar();
        if (!isTaskRoot()) {
            finish();
            return;
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (exit)
                    finish();
                else
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }, 1000);
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_start;
    }


    private void translucentStatusBar() {
        //5.0及以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            //4.4到5.0
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    @Override
    public void onBackPressed() {
        exit = true;
        super.onBackPressed();
    }
}
