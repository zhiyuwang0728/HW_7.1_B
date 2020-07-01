package com.zyw.day03_b.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class DataAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<Bean.DataBean.DatasBean> data = new ArrayList<>();

    public static final int TYPE_ONE = 0;
    public static final int TYPE_TWO = 1;

    public DataAdapter(Context context) {
        this.context = context;
    }

    public void addData(List<Bean.DataBean.DatasBean> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 3 == 0) {
            return TYPE_ONE;
        } else {
            return TYPE_TWO;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE_ONE:
                return new Ovh(LayoutInflater.from(context).inflate(R.layout.item_one, viewGroup, false));
            case TYPE_TWO:
                return new Tvh(LayoutInflater.from(context).inflate(R.layout.item_two, viewGroup, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final Bean.DataBean.DatasBean datasBean = data.get(i);

        String envelopePic = datasBean.getEnvelopePic();
        String title = datasBean.getTitle();
        String desc = datasBean.getNiceShareDate();

        switch (getItemViewType(i)) {
            case TYPE_ONE:
                Ovh ovh = (Ovh) viewHolder;
                Glide.with(context).load(envelopePic).into(ovh.iv_img_a);
                ovh.tv_title_a.setText(title);
                ovh.tv_desc_a.setText(desc);
                break;
            case TYPE_TWO:
                Tvh tvh = (Tvh) viewHolder;
                Glide.with(context).load(envelopePic).into(tvh.iv_img_b);
                tvh.tv_title_b.setText(title);
                tvh.tv_desc_b.setText(desc);
                break;
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickItem != null) {
                    onClickItem.onClick(datasBean, data,i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    OnClickItem onClickItem;

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    public interface OnClickItem {

        void onClick(Bean.DataBean.DatasBean bean, List<Bean.DataBean.DatasBean> datasBeans,int position);
    }

    public class Ovh extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_img_a)
        ImageView iv_img_a;
        @BindView(R.id.tv_title_a)
        TextView tv_title_a;
        @BindView(R.id.tv_desc_a)
        TextView tv_desc_a;

        public Ovh(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class Tvh extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_img_b)
        ImageView iv_img_b;
        @BindView(R.id.tv_title_b)
        TextView tv_title_b;
        @BindView(R.id.tv_desc_b)
        TextView tv_desc_b;

        public Tvh(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
