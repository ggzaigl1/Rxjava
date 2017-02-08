package com.jypj.yuexiu;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TabHost;

import com.jypj.yuexiu.widget.SystemUtils;

@SuppressWarnings("deprecation")
/**
 *
 */
public class HomeActivity extends TabActivity implements RadioGroup.OnCheckedChangeListener {

    public static TabHost tabHost;
    public static RadioGroup radioderGroup;
    public static String TABTAG = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        translucentStatusBar();
        addStatusBarView();
        SystemUtils.getStatusHeight(this);
        tabHost = this.getTabHost();
        tabHost.addTab(tabHost.newTabSpec("1").setIndicator("1").setContent(new Intent(this, HomeActivity1.class)));
        tabHost.addTab(tabHost.newTabSpec("2").setIndicator("2").setContent(new Intent(this, HomeActivity2.class)));
        radioderGroup = (RadioGroup) findViewById(R.id.main_radio);
        radioderGroup.check(R.id.mainTabs_radio_home1);
        radioderGroup.setOnCheckedChangeListener(this);
    }

    /**
     * 设置一个view添加到状态栏
     */
    private void addStatusBarView() {
        View view = new View(this);
        view.setBackgroundColor(getResources().getColor(R.color.loginblue));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                getStatusBarHeight(this));
        ViewGroup decorView = (ViewGroup) findViewById(android.R.id.content);
        decorView.addView(view, params);
    }

    public int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    /**
     * 判断版本沉浸式
     */
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
    protected void onResume() {
        tabHost.setCurrentTabByTag(TABTAG);
        switch (Integer.parseInt(TABTAG)) {
            case 1:
                radioderGroup.check(R.id.mainTabs_radio_home1);
                break;
            case 2:
                radioderGroup.check(R.id.mainTabs_radio_home2);
                break;
            default:
                break;
        }

        super.onResume();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.mainTabs_radio_home1:
                tabHost.setCurrentTabByTag("1");
                break;
            case R.id.mainTabs_radio_home2:
                tabHost.setCurrentTabByTag("2");
                break;
        }
    }

}
