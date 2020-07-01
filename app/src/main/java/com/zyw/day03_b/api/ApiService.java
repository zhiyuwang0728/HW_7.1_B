package com.zyw.day03_b.api;

import com.zyw.day03_b.bean.Bean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface ApiService {

    String baseUrl="https://www.wanandroid.com/";

    @GET("project/list/1/json?cid=294")
    Flowable<Bean> getData();


}
