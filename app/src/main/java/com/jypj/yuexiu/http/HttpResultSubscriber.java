package com.jypj.yuexiu.http;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by Administrator on 2016/11/4.
 */

public abstract class HttpResultSubscriber<T> extends Subscriber<HttpResult<T>> {

    @Override
    public void onCompleted() {
    }
    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        //在这里做全局的错误处理
        if (e instanceof HttpException) {
            // ToastUtils.getInstance().showToast(e.getMessage());
        }
        _onError(e);
    }
    @Override
    public void onNext(HttpResult<T> t) {
        if (t.code == 0)
            onSuccess(t.data);
        else
            _onError(new Throwable("error=" + t.msg));
    }

    public abstract void onSuccess(T t);

    public abstract void _onError(Throwable e);
}