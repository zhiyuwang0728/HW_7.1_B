package com.zyw.day03_b.view;

public interface ResultCallBack<T> {

    void onSuccess(T t);

    void onFail(String msg);

}
