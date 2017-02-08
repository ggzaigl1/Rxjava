package com.jypj.yuexiu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jypj.yuexiu.adapter.NewsletterlistAdapter;
import com.jypj.yuexiu.base.BaseActivity;
import com.jypj.yuexiu.http.HttpResultSubscriber;
import com.jypj.yuexiu.http.ServiceFactory;
import com.jypj.yuexiu.http.YuexiuService;
import com.jypj.yuexiu.model.FlashM;
import com.jypj.yuexiu.widget.AppLoading;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/10.
 * 越秀快讯界面
 */

public class NewsletterActivity extends BaseActivity {

    private NewsletterlistAdapter mNewsletterlistAdapter;
    private ListView mListView;


    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        addStatusBarView();
        mListView = (ListView) findViewById(R.id.newsletter_list);
        mListView.setVerticalScrollBarEnabled(false);
        getlistview();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.actcivity_newsletter;
    }

    private void getlistview() {
        AppLoading.show(this);
        ServiceFactory.getInstance().createService(YuexiuService.class)
                .flash()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpResultSubscriber<List<FlashM.DataEntity>>() {
                    @Override
                    public void onSuccess(final List<FlashM.DataEntity> dataEntities) {
                        mNewsletterlistAdapter = new NewsletterlistAdapter(NewsletterActivity.this, dataEntities);
                        mListView.setAdapter(mNewsletterlistAdapter);
                        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(NewsletterActivity.this, WebActivity.class);
                                intent.putExtra("url", dataEntities.get(position).getHref());
                                intent.putExtra("title", "快讯详情");
                                startActivity(intent);
                            }
                        });
                        AppLoading.close();
                    }

                    @Override
                    public void _onError(Throwable e) {
                        AppLoading.close();
                    }
                });


    }
}
