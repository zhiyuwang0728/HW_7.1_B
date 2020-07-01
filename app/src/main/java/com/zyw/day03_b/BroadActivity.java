package com.zyw.day03_b;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.zyw.day03_b.adapter.ShowAdapter;
import com.zyw.day03_b.bean.Bean;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BroadActivity extends AppCompatActivity {

    @BindView(R.id.mViewPager)
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad);
        ButterKnife.bind(this);


        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        List<Bean.DataBean.DatasBean> list = (List<Bean.DataBean.DatasBean>) intent.getSerializableExtra("list");
        int pos = intent.getIntExtra("pos", 0);

        ShowAdapter showAdapter = new ShowAdapter(this, list);
        mViewPager.setAdapter(showAdapter);
        mViewPager.setCurrentItem(pos);
        showAdapter.notifyDataSetChanged();

    }
}
