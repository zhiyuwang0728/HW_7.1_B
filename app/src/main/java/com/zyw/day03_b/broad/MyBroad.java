package com.zyw.day03_b.broad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.zyw.day03_b.BroadActivity;
import com.zyw.day03_b.bean.Bean;

import java.io.Serializable;
import java.util.List;

public class MyBroad extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String title = intent.getStringExtra("title");
        List<Bean.DataBean.DatasBean> list = (List<Bean.DataBean.DatasBean>) intent.getSerializableExtra("list");
        int pos = intent.getIntExtra("pos", 0);
        Toast.makeText(context, title, Toast.LENGTH_SHORT).show();

        Intent intent1 = new Intent(context, BroadActivity.class);
        intent1.putExtra("list", (Serializable) list);
        intent1.putExtra("pos", pos);
        context.startActivity(intent1);

    }
}
