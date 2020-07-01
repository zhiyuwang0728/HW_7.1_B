package com.zyw.day03_b.fragment;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zyw.day03_b.R;
import com.zyw.day03_b.adapter.DataAdapter;
import com.zyw.day03_b.bean.Bean;
import com.zyw.day03_b.broad.MyBroad;
import com.zyw.day03_b.presenter.MyPresenter;
import com.zyw.day03_b.view.MyView;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeFragment extends Fragment implements MyView {
    @BindView(R.id.mRecycler)
    RecyclerView mRecycler;
    Unbinder unbinder;
    private DataAdapter dataAdapter;
    private MyBroad myBroad;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.home, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        initView();
        initPresenter();
        initRegisterBroad();
        return inflate;
    }

    private void initRegisterBroad() {
        myBroad = new MyBroad();
        IntentFilter intentFilter = new IntentFilter("this is broad");
        getActivity().registerReceiver(myBroad, intentFilter);
    }

    private void initPresenter() {
        MyPresenter myPresenter = new MyPresenter(this);
        myPresenter.toModel();
    }

    private void initView() {
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        dataAdapter = new DataAdapter(getActivity());
        mRecycler.setAdapter(dataAdapter);


        dataAdapter.setOnClickItem(new DataAdapter.OnClickItem() {
            @Override
            public void onClick(Bean.DataBean.DatasBean bean, List<Bean.DataBean.DatasBean> datasBeans, int i) {
                initBroad(bean, datasBeans, i);
            }
        });

    }

    private void initBroad(Bean.DataBean.DatasBean bean, List<Bean.DataBean.DatasBean> datasBeans, int i) {

        Intent intent = new Intent("this is broad");
        intent.putExtra("title", bean.getTitle());
        intent.putExtra("list", (Serializable) datasBeans);
        intent.putExtra("pos", i);
        getActivity().sendBroadcast(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        getActivity().unregisterReceiver(myBroad);
    }

    @Override
    public void onSuccess(List<Bean.DataBean.DatasBean> data) {
        dataAdapter.addData(data);
    }

    @Override
    public void onFail(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
