package com.jypj.yuexiu;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jypj.yuexiu.adapter.HomeGridAdapter;
import com.jypj.yuexiu.adapter.RolelistAdapter;
import com.jypj.yuexiu.base.BaseActivity;
import com.jypj.yuexiu.http.HttpResultSubscriber;
import com.jypj.yuexiu.http.ServiceFactory;
import com.jypj.yuexiu.http.YuexiuService;
import com.jypj.yuexiu.model.ChangeRoleM;
import com.jypj.yuexiu.model.Griditem;
import com.jypj.yuexiu.model.LoginM;
import com.jypj.yuexiu.widget.AppLoading;
import com.jypj.yuexiu.widget.CircleBitmapDisplayer;
import com.jypj.yuexiu.widget.SystemUtils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeActivity1 extends BaseActivity {
    public HomeGridAdapter mHomeGridAdapter;
    private long exitTime = 0; // 退出判断
    private GridView gridview;
    private PopupWindow popupWindow;
    private LoginM.DataEntity mDataEntity;
    private TextView Role;
    private DisplayImageOptions options;

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        addStatusBarView();
        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setVerticalScrollBarEnabled(false);
        Role = (TextView) findViewById(R.id.role);
        mDataEntity = new Gson().fromJson(getParent().getIntent().getStringExtra("login"), LoginM.DataEntity.class);
        options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisk(true).displayer(new CircleBitmapDisplayer()).build();
        getGridview();
        ImageLoader.getInstance().displayImage("", (ImageView) findViewById(R.id.PictureUrl), options);
        ((TextView) findViewById(R.id.name)).setText(mDataEntity.getName());
        ((TextView) findViewById(R.id.area)).setText(mDataEntity.getSchoolName());
        Role.setText(mDataEntity.getDefaultRole().getRole_name());
        Role.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
            }
        });
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_home1;
    }

    private void getGridview() {
        List<Griditem> griditems = new ArrayList<>();
        for (int i = 0; i < mDataEntity.getModules().size(); i++) {
            griditems.add(mDataEntity.getModules().get(i));
        }
        mHomeGridAdapter = new HomeGridAdapter(this, griditems);
        gridview.setAdapter(mHomeGridAdapter);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int num = Integer.parseInt(mDataEntity.getModules().get(position).getNum());
                Intent intent = new Intent();
                switch (num) {
                    case 2:
                        intent.setClass(HomeActivity1.this, NotifyActivity.class);
                        intent.putExtra("title", "通知公告");
                        startActivity(intent);
                        break;
                    /*空間消息*/
                    case 3:
                        intent.setClass(HomeActivity1.this, SpaceActivity.class);
                        startActivity(intent);
                        break;
                     /*越秀快讯界面*/
                    case 4:
                        intent.setClass(HomeActivity1.this, NewsletterActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent = new Intent(HomeActivity1.this, WebActivity.class);
                        intent.putExtra("title", "统计视图");
                        intent.putExtra("roleType", mDataEntity.getDefaultRole().getRole_type());
                        startActivity(intent);
                        break;
                     /*获奖信息*/
                    case 6:
                        intent.setClass(HomeActivity1.this, AcquisitionActivity.class);
                        startActivity(intent);
                        break;
                    case 7:
                        /*OA界面*/
                        intent.setClass(HomeActivity1.this, OAactivity.class);
                        startActivity(intent);
                        break;
                    case 8:
                        intent.setClass(HomeActivity1.this, NotifyActivity.class);
                        intent.putExtra("title", "招考信息");
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    //显示popupwindow
    private void showPopupWindow() {
        View view = LayoutInflater.from(this).inflate(R.layout.popupwindow_role, null);
        ListView mlistview = (ListView) view.findViewById(R.id.role_list);
        mlistview.setAdapter(new RolelistAdapter(this, mDataEntity));
        mlistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AppLoading.show(HomeActivity1.this);
                popupWindow.dismiss();
                Role.setText(mDataEntity.getRoles().get(position).getRole_name());
                String roleType = mDataEntity.getRoles().get(position).getRole_type();
                ServiceFactory.getInstance().createService(YuexiuService.class)
                        .changeRole(roleType)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new HttpResultSubscriber <List<ChangeRoleM.DataEntity>>() {
                            @Override
                            public void onSuccess(List<ChangeRoleM.DataEntity> dataEntities) {
                                List<Griditem> griditems = new ArrayList<>();
                                for (int i = 0; i < dataEntities.size(); i++) {
                                    griditems.add(dataEntities.get(i));
                                }
                                mHomeGridAdapter = new HomeGridAdapter(HomeActivity1.this, griditems);
                                gridview.setAdapter(mHomeGridAdapter);
                                AppLoading.close();
                            }

                            @Override
                            public void _onError(Throwable e) {

                            }
                        });


//                http.changeRole(roleType, new ResponseHandler() {
//                    @Override
//                    public void onSuccess(String response) {
//                        ChangeRoleM changeRoleM = new Gson().fromJson(response, ChangeRoleM.class);
//                        List<Griditem> griditems = new ArrayList<>();
//                        for (int i = 0; i < changeRoleM.getData().size(); i++) {
//                            griditems.add(changeRoleM.getData().get(i));
//                        }
//                        mHomeGridAdapter = new HomeGridAdapter(HomeActivity1.this, griditems);
//                        gridview.setAdapter(mHomeGridAdapter);
//                        AppLoading.close();
//                    }
//
//                    @Override
//                    public void onFailure(String message) {
//
//                    }
//                });
            }
        });
        popupWindow = new PopupWindow(view, 350, LinearLayout.LayoutParams.WRAP_CONTENT, false);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    //如果焦点不在popupWindow上，且点击了外面，不再往下dispatch事件：
                    //不做任何响应,不 dismiss popupWindow
                    return true;
                }
                //否则default，往下dispatch事件:关掉popupWindow，
                return false;
            }
        });

        popupWindow.showAsDropDown(Role, 0, 30);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });
    }


    @Override
    protected void onResume() {
        HomeActivity.TABTAG = "1";
        super.onResume();
    }

    //退出程序
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) >= 2000) {
                SystemUtils.showToast(this, "再按一次退出程序", false);
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
