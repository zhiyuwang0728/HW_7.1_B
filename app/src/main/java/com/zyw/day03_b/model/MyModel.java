package com.zyw.day03_b.model;

import android.annotation.SuppressLint;

import com.zyw.day03_b.api.ApiService;
import com.zyw.day03_b.bean.Bean;
import com.zyw.day03_b.view.ResultCallBack;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyModel {

    @SuppressLint("CheckResult")
    public void getResult(final ResultCallBack callBack) {

        new Retrofit.Builder()
                .baseUrl(ApiService.baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class)
                .getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<Bean>() {
                    @Override
                    public void onNext(Bean bean) {
                        List<Bean.DataBean.DatasBean> datas = bean.getData().getDatas();
                        callBack.onSuccess(datas);
                    }

                    @Override
                    public void onError(Throwable t) {
                        callBack.onFail(t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
