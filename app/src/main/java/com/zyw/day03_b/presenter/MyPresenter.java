package com.zyw.day03_b.presenter;

import com.zyw.day03_b.bean.Bean;
import com.zyw.day03_b.model.MyModel;
import com.zyw.day03_b.view.MyView;
import com.zyw.day03_b.view.ResultCallBack;

import java.util.List;

public class MyPresenter {

    private final MyModel myModel;
    private final MyView view;

    public MyPresenter(MyView view) {
        myModel = new MyModel();
        this.view = view;
    }

    public void toModel() {
        myModel.getResult(new ResultCallBack<List<Bean.DataBean.DatasBean>>() {

            @Override
            public void onSuccess(List<Bean.DataBean.DatasBean> datasBeans) {
                view.onSuccess(datasBeans);
            }

            @Override
            public void onFail(String msg) {
                view.onFail(msg);
            }
        });
    }
}
