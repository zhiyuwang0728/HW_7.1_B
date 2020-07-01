package com.zyw.day03_b.view;

import com.zyw.day03_b.bean.Bean;

import java.util.List;

public interface MyView {

    void onSuccess(List<Bean.DataBean.DatasBean> data);

    void onFail(String msg);

}
