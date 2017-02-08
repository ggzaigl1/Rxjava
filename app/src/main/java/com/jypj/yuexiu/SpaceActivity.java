package com.jypj.yuexiu;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.jypj.yuexiu.adapter.SpacelistAdapter;
import com.jypj.yuexiu.base.BaseActivity;
import com.jypj.yuexiu.http.HttpResultSubscriber;
import com.jypj.yuexiu.http.ServiceFactory;
import com.jypj.yuexiu.http.YuexiuService;
import com.jypj.yuexiu.model.QueryMsgM;
import com.jypj.yuexiu.widget.AppLoading;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/14.
 * 空間消息
 */

public class SpaceActivity extends BaseActivity {

    private SpacelistAdapter mSpacelistAdapter;
    private ListView mListView;
    public TextView space_error;

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        addStatusBarView();
        space_error = (TextView) findViewById(R.id.space_error);
        mListView = (ListView) findViewById(R.id.space_list);
        mListView.setVerticalScrollBarEnabled(false);
        getlistview();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_spacer;
    }


    private void getlistview() {
        AppLoading.show(this);
        ServiceFactory.getInstance().createService(YuexiuService.class).queryMsg()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpResultSubscriber<List<QueryMsgM.DataEntity>>() {
                    @Override
                    public void onSuccess(List<QueryMsgM.DataEntity> dataEntities) {
                        mSpacelistAdapter = new SpacelistAdapter(SpaceActivity.this, dataEntities);
                        mListView.setAdapter(mSpacelistAdapter);
                        if (dataEntities.size() > 0) {
                            space_error.setVisibility(View.GONE);
                        } else {
                            space_error.setVisibility(View.VISIBLE);
                        }
                        AppLoading.close();
                    }

                    @Override
                    public void _onError(Throwable e) {

                    }
                });

    }
}
