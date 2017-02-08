package com.jypj.yuexiu;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.jypj.yuexiu.adapter.AcquisitionDetailAdapter;
import com.jypj.yuexiu.base.BaseActivity;
import com.jypj.yuexiu.http.HttpResultSubscriber;
import com.jypj.yuexiu.http.ServiceFactory;
import com.jypj.yuexiu.http.YuexiuService;
import com.jypj.yuexiu.model.AwardM;
import com.jypj.yuexiu.widget.AppLoading;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/18.
 */

public class AcquistionDetailActivity extends BaseActivity {
    private TextView mdetailtitle;
    private ListView mListView;

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        mdetailtitle = (TextView) findViewById(R.id.acq_title);
        mdetailtitle.setText(getIntent().getStringExtra("detailtitle"));
        mListView = (ListView) findViewById(R.id.acqdet_list);
        mListView.setVerticalScrollBarEnabled(false);
        addStatusBarView();
        getlistview();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_acquistiondetail;
    }

    private void getlistview() {
        AppLoading.show(this);
        ServiceFactory.getInstance().createService(YuexiuService.class).award(getIntent().getStringExtra("id"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpResultSubscriber<List<AwardM.DataEntity>>() {
                    @Override
                    public void onSuccess(List<AwardM.DataEntity> dataEntities) {
                        AcquisitionDetailAdapter acquisitionDetailAdapter = new AcquisitionDetailAdapter(AcquistionDetailActivity.this, dataEntities);
                        mListView.setAdapter(acquisitionDetailAdapter);
                        AppLoading.close();
                    }

                    @Override
                    public void _onError(Throwable e) {

                    }
                });


    }

}
