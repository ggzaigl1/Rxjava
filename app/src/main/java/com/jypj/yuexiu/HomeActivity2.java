package com.jypj.yuexiu;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;

import com.jypj.yuexiu.adapter.HomeListAdapter;
import com.jypj.yuexiu.base.BaseActivity;
import com.jypj.yuexiu.http.HttpResultSubscriber;
import com.jypj.yuexiu.http.ServiceFactory;
import com.jypj.yuexiu.http.YuexiuService;
import com.jypj.yuexiu.model.Myrecorditem;
import com.jypj.yuexiu.model.QuMyProM;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeActivity2 extends BaseActivity {

    private ListView mListview;
    private HomeListAdapter mHomeListAdapter;

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        addStatusBarView();
        mListview = (ListView) findViewById(R.id.listview);
        mListview.setVerticalScrollBarEnabled(false);
        getlistview();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_home2;
    }

    private void getlistview() {
        ServiceFactory.getInstance().createService(YuexiuService.class)
                .quMyPro()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpResultSubscriber<List<QuMyProM.DataEntity>>() {

                    @Override
                    public void onSuccess(final List<QuMyProM.DataEntity> dataEntities) {
                        List<Myrecorditem> Myrecorditems = new ArrayList<>();
                        for (int i = 0; i < dataEntities.size(); i++) {
                            Myrecorditems.add(dataEntities.get(i));
                        }
                        mHomeListAdapter = new HomeListAdapter(HomeActivity2.this, Myrecorditems);
                        mListview.setAdapter(mHomeListAdapter);
                    }

                    @Override
                    public void _onError(Throwable e) {

                    }
                });
    }

    @Override
    protected void onResume() {
        HomeActivity.TABTAG = "2";
        super.onResume();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            HomeActivity.tabHost.setCurrentTabByTag("1");
            HomeActivity.radioderGroup.check(R.id.mainTabs_radio_home1);
        }
        return true;
    }

    /**
     * 设置
     *
     * @param view
     */
    public void Settings(View view) {
        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
    }
}
