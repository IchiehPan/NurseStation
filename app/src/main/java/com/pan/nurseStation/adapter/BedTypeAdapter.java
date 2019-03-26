package com.pan.nurseStation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.pan.nurseStation.R;

public class BedTypeAdapter extends BaseAdapter {
    Context mContext;
    int mCount;

    public BedTypeAdapter(Context content, int count) {
        this.mContext = content;
        this.mCount = count;
    }

    @Override
    public int getCount() {
        return mCount;
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
        } else {
            linearLayout = (LinearLayout) convertView;
        }
        return linearLayout;
    }
}
