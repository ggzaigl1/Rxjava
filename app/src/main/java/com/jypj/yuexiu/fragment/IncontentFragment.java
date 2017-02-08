package com.jypj.yuexiu.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jypj.yuexiu.OAdetailsActivity;
import com.jypj.yuexiu.R;
import com.jypj.yuexiu.adapter.OAListAdapter;
import com.jypj.yuexiu.base.BaseFragment;
import com.jypj.yuexiu.http.HttpResultSubscriber;
import com.jypj.yuexiu.http.ServiceFactory;
import com.jypj.yuexiu.http.YuexiuService;
import com.jypj.yuexiu.model.FiledealM;
import com.jypj.yuexiu.widget.AppLoading;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/9.
 * OA来文的fragment
 */


public class IncontentFragment extends BaseFragment {
    private ListView mListView;
    private OAListAdapter mOalistAdapter;

    public TextView space_error;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_incontent;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        mListView = (ListView) mRootView.findViewById(R.id.incontent_list);
        space_error = (TextView) mRootView.findViewById(R.id.space_error);
        getlistview();
    }


    private void getlistview() {
        AppLoading.show(getActivity());
        ServiceFactory.getInstance().createService(YuexiuService.class).filedeal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpResultSubscriber<List<FiledealM.DataEntity>>() {
                    @Override
                    public void onSuccess(final List<FiledealM.DataEntity> dataEntities) {
                        mOalistAdapter = new OAListAdapter(getActivity(), dataEntities);
                        mListView.setAdapter(mOalistAdapter);
                        if (dataEntities.size() < 0) {
                            space_error.setVisibility(View.VISIBLE);
                        } else {
                            space_error.setVisibility(View.GONE);
                        }
                        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent intent = new Intent(getActivity(), OAdetailsActivity.class);
                                intent.putExtra("MsgContent", new Gson().toJson(dataEntities.get(position).getMsgContent()));
                                intent.putExtra("title", "来文");
                                startActivity(intent);
                            }
                        });

                        AppLoading.close();
                    }

                    @Override
                    public void _onError(Throwable e) {
                        space_error.setVisibility(View.VISIBLE);
                        AppLoading.close();
                    }
                });
    }

}
