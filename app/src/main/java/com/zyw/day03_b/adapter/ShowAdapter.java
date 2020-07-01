package com.zyw.day03_b.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zyw.day03_b.R;
import com.zyw.day03_b.bean.Bean;

import java.util.ArrayList;
import java.util.List;

public class ShowAdapter extends PagerAdapter {
    private Context context;
    List<Bean.DataBean.DatasBean> list;

    public ShowAdapter(Context context, List<Bean.DataBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.show, container, false);

        ImageView iv_img = inflate.findViewById(R.id.iv_img);
        TextView tv_page = inflate.findViewById(R.id.tv_page);
        Glide.with(context).load(list.get(position).getEnvelopePic()).into(iv_img);
        tv_page.setText("第" + (position + 1) + "张/共" + list.size() + "张");

        container.addView(inflate);
        return inflate;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
