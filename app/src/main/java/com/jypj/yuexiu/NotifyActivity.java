package com.jypj.yuexiu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jypj.yuexiu.adapter.NotifylistAdapter;
import com.jypj.yuexiu.base.BaseActivity;
import com.jypj.yuexiu.http.HttpResultSubscriber;
import com.jypj.yuexiu.http.ServiceFactory;
import com.jypj.yuexiu.http.YuexiuService;
import com.jypj.yuexiu.model.NoticeM;
import com.jypj.yuexiu.widget.AppLoading;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/10.
 * 通知公告界面
 */

public class NotifyActivity extends BaseActivity {
    private NotifylistAdapter mNotifylistAdapter;
    private ListView mListView;

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        addStatusBarView();
        mListView = (ListView) findViewById(R.id.notify_list);
        mListView.setVerticalScrollBarEnabled(false);
        TextView textView = (TextView) findViewById(R.id.notify_titletv);
        textView.setText(getIntent().getStringExtra("title"));
        getlistview();
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_notify;
    }

    private void getlistview() {
        AppLoading.show(this);
        if (getIntent().getStringExtra("title").equals("通知公告")) {
            ServiceFactory.getInstance().createService(YuexiuService.class).notice()
                    //订阅发生在io线程
                    .subscribeOn(Schedulers.io())
                    //订阅者的回调在主线程
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(mHttpResultSubscriber);

        } else {
            //招考信息
            ServiceFactory.getInstance().createService(YuexiuService.class).exam()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(mHttpResultSubscriber);
        }
    }

    HttpResultSubscriber mHttpResultSubscriber = new HttpResultSubscriber<List<NoticeM.DataEntity>>() {
        @Override
        public void onSuccess(final List<NoticeM.DataEntity> dataEntities) {
            mNotifylistAdapter = new NotifylistAdapter(NotifyActivity.this, dataEntities);
            mListView.setAdapter(mNotifylistAdapter);
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(NotifyActivity.this, WebActivity.class);
                    intent.putExtra("url", dataEntities.get(position).getHref());
                    intent.putExtra("title", getIntent().getStringExtra("title"));
                    startActivity(intent);
                }
            });
            AppLoading.close();
        }

        @Override
        public void _onError(Throwable e) {
            AppLoading.close();
        }
    };
}
