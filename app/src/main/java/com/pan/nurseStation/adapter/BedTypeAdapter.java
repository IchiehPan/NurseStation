package com.pan.nurseStation.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pan.nurseStation.R;
import com.pan.nurseStation.bean.response.LevelResponseBean;
import com.pan.nurseStation.business.DBHisBusiness;

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
        return mLevelDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout linearLayout;
        if (convertView == null) {
            linearLayout = (LinearLayout) View.inflate(mContext, R.layout.item_simple_example_icon, null);
        } else {
            linearLayout = (LinearLayout) convertView;
        }
        ImageView imageView = linearLayout.findViewById(R.id.head_iv);
        TextView headTV = linearLayout.findViewById(R.id.head1);
        TextView contentTV = linearLayout.findViewById(R.id.content1);

        LevelResponseBean.Data data = mLevelDataList.get(position);
        String levelCode = data.getCode();

        GradientDrawable myGrad = (GradientDrawable) imageView.getBackground();
        if (DBHisBusiness.bedTypeColorMap.containsKey(levelCode)) {
            myGrad.setColor(DBHisBusiness.bedTypeColorMap.get(levelCode));
        }
        headTV.setText(data.getName());
        contentTV.setText(data.getTotal());
        return linearLayout;
    }
}
