package com.jypj.yuexiu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.jypj.yuexiu.base.BaseActivity;
import com.jypj.yuexiu.http.HttpBase;
import com.jypj.yuexiu.http.HttpResultSubscriber;
import com.jypj.yuexiu.http.ServiceFactory;
import com.jypj.yuexiu.http.YuexiuService;
import com.jypj.yuexiu.model.LoginM;
import com.jypj.yuexiu.widget.AppLoading;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.jypj.yuexiu.R.id.loginname;

public class MainActivity extends BaseActivity {

    private Button login;
    private EditText Password, Loginname;
    private SharedPreferences mPerferences;

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        translucentStatusBar();
        mPerferences = PreferenceManager.getDefaultSharedPreferences(this);
        login = (Button) findViewById(R.id.login);
        Loginname = (EditText) findViewById(loginname);
        Password = (EditText) findViewById(R.id.password);
        Loginname.addTextChangedListener(mTextWatcher);
        Password.addTextChangedListener(mTextWatcher);
        boolean islogin = mPerferences.getBoolean("islogin", false);
        String loginname = mPerferences.getString("loginname", "");
        String password = mPerferences.getString("password", "");
        if (!loginname.isEmpty()) {
            ((EditText) findViewById(R.id.loginname)).setText(loginname);
        }
        if (islogin) {
            Login(loginname, password);
        }
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * 沉浸式设计
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

    public void Login(final String loginname, final String password) {
        AppLoading.loginshow(this);
        ServiceFactory.getInstance()
                .createService(YuexiuService.class)
                .getlogin(loginname, getMD5(password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpResultSubscriber<LoginM.DataEntity>() {

                    @Override
                    public void onSuccess(LoginM.DataEntity dataEntityHttpResult) {
                        HttpBase.token = dataEntityHttpResult.getToken();
                        SharedPreferences.Editor editor = mPerferences.edit();
                        editor.putBoolean("islogin", true);
                        editor.putString("loginname", loginname); //保存账号
                        editor.putString("password", password);//保存密码
                        editor.putString("token", dataEntityHttpResult.getToken());
                        editor.commit();
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        intent.putExtra("login", new Gson().toJson(dataEntityHttpResult));
                        startActivity(intent);
                        AppLoading.close();
                        finish();
                    }

                    @Override
                    public void _onError(Throwable e) {
                        Log.e("msg",e.toString());
                        AppLoading.close();
                    }
                });


//        http.login(loginname, getMD5(password), new ResponseHandler() {
//            @Override
//            public void onSuccess(String response) {
//                AppLoading.close();
//                LoginM loginM = new Gson().fromJson(response, LoginM.class);
//                HttpBase.token = loginM.getData().getToken();
//                SharedPreferences.Editor editor = mPerferences.edit();
//                editor.putBoolean("islogin", true);
//                editor.putString("loginname", loginname); //保存账号
//                editor.putString("password", password);//保存密码
//                editor.putString("token", loginM.getData().getToken());
//                editor.commit();
//                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
//                intent.putExtra("login", response);
//                startActivity(intent);
//                finish();
//
//            }
//
//            @Override
//            public void onFailure(String message) {
//                AppLoading.close();
//                SystemUtils.showToast(getApplicationContext(), message, false);
//            }
//        });
    }


    public void login(View view) {
        String loginname = ((EditText) findViewById(R.id.loginname)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();
        Login(loginname, password);
    }


    /**
     * 清除edittext内容
     */
    TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (Loginname.getText().toString().length() > 0 && Password.getText().toString().length() > 0) {
                login.setEnabled(true);
            } else {
                login.setEnabled(false);
            }
        }
    };

    /**
     * 点击空白位置 隐藏软键盘
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }
}
