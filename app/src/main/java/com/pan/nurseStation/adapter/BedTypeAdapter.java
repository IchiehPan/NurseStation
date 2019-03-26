package com.pan.nurseStation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pan.nurseStation.R;
import com.pan.nurseStation.bean.response.LevelResponseBean;

import java.util.List;

public class BedTypeAdapter extends BaseAdapter {
    Context mContext;
    List<LevelResponseBean.Data> mLevelDataList;

    public BedTypeAdapter(Context content, List<LevelResponseBean.Data> levelDataList) {
        this.mContext = content;
        this.mLevelDataList = levelDataList;
    }

    @Override
    public int getCount() {
        return mLevelDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout linearLayout;
        if (convertView == null) {
            linearLayout = (LinearLayout) View.inflate(mContext, R.layout.item_simple_example_icon, null);
            TextView headTV = linearLayout.findViewById(R.id.head1);
            TextView contentTV = linearLayout.findViewById(R.id.content1);
            headTV.setText(mLevelDataList.get(position).getName());
        } else {
            linearLayout = (LinearLayout) convertView;
        }
        return linearLayout;
    }
}
