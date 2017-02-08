package com.jypj.yuexiu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jypj.yuexiu.adapter.AcquisitionAdapter;
import com.jypj.yuexiu.base.BaseActivity;
import com.jypj.yuexiu.http.HttpResultSubscriber;
import com.jypj.yuexiu.http.ServiceFactory;
import com.jypj.yuexiu.http.YuexiuService;
import com.jypj.yuexiu.model.QueryAward;
import com.jypj.yuexiu.widget.AppLoading;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/18 0018.
 * 获奖信息
 */
public class AcquisitionActivity extends BaseActivity {

    private static AcquisitionAdapter mAcquisitionadapter;
    private ListView mListView;
    public TextView space_error, acquisition_titletv;

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        addStatusBarView();
        mListView = (ListView) findViewById(R.id.acquisition_list);
        space_error = (TextView) findViewById(R.id.space_error);
        acquisition_titletv = (TextView) findViewById(R.id.acquisition_titletv);
        mListView.setVerticalScrollBarEnabled(false);
        getlistview();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_acquistion;
    }


    private void getlistview() {
        AppLoading.show(this);
        ServiceFactory.getInstance().createService(YuexiuService.class).award()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpResultSubscriber<List<QueryAward.DataBean>>() {
                    @Override
                    public void onSuccess(final List<QueryAward.DataBean> dataEntities) {
                        mAcquisitionadapter = new AcquisitionAdapter(AcquisitionActivity.this, dataEntities);
                        mListView.setAdapter(mAcquisitionadapter);
                        if (dataEntities.size() > 0) {
                            space_error.setVisibility(View.GONE);
                        } else {
                            space_error.setVisibility(View.VISIBLE);
                        }
                        AppLoading.close();

                        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(AcquisitionActivity.this, AcquistionDetailActivity.class);
                                intent.putExtra("id", dataEntities.get(position).getId());
                                intent.putExtra("title", getIntent().getStringExtra("title"));
                                intent.putExtra("detailtitle", dataEntities.get(position).getAwardInfo());
                                startActivity(intent);
                            }
                        });

                    }

                    @Override
                    public void _onError(Throwable e) {
                        space_error.setVisibility(View.VISIBLE);
                        AppLoading.close();
                    }
                });
    }
}
