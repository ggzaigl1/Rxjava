package com.jypj.yuexiu.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jypj.yuexiu.http.MainHttp;


/**
 * Created by laucherish on 16/3/15.
 */
public abstract class BaseFragment extends Fragment {
    public MainHttp http = new MainHttp();
    public static final String TAG = BaseFragment.class.getSimpleName();
    public View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        afterCreate(savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    //绑定页面
    protected abstract int getLayoutId();

    protected abstract void afterCreate(Bundle savedInstanceState);
}
