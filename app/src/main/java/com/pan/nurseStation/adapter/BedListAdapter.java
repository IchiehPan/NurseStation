package com.pan.nurseStation.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pan.nurseStation.R;
import com.pan.nurseStation.bean.response.BedListResponseBean;
import com.pan.nurseStation.business.DBHisBusiness;

import java.util.List;
import java.util.Objects;

public class BedListAdapter extends BaseAdapter {
    private static final String TAG = BedListAdapter.class.getSimpleName();
    Context mContext;
    List<BedListResponseBean.PatientInfo> mList;

    public BedListAdapter(Context content, List<BedListResponseBean.PatientInfo> list) {
        this.mContext = content;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        if (mList != null) {
            return mList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout linearLayout;
        if (convertView == null) {
            linearLayout = (LinearLayout) View.inflate(mContext, R.layout.item_simple_house, null);
        } else {
            linearLayout = (LinearLayout) convertView;
        }

        TextView bedNo = linearLayout.findViewById(R.id.bed_id);
        TextView patientName = linearLayout.findViewById(R.id.patient_name);
        ImageView patientSex = linearLayout.findViewById(R.id.patient_sex);
        TextView patientAge = linearLayout.findViewById(R.id.patient_age);
        TextView patientNumber = linearLayout.findViewById(R.id.hos_id);

        BedListResponseBean.PatientInfo patientInfo = mList.get(position);
        bedNo.setText(patientInfo.getBed_id());
        GradientDrawable myGrad = (GradientDrawable) bedNo.getBackground();
        if (DBHisBusiness.bedTypeColorMap.containsKey(patientInfo.getLevel())) {
            myGrad.setColor(DBHisBusiness.bedTypeColorMap.get(patientInfo.getLevel()));
        }

        patientName.setText(patientInfo.getName());
        if (Objects.equals(patientInfo.getSex(), mContext.getString(R.string.sex_type_male))) {
            patientSex.setBackgroundResource(R.drawable.ic_male);
        } else if (Objects.equals(patientInfo.getSex(), mContext.getString(R.string.sex_type_female))) {
            patientSex.setBackgroundResource(R.drawable.ic_female);
        }
        patientAge.setText(patientInfo.getAge());
        patientNumber.setText(patientInfo.getHos_number());

        return linearLayout;
    }
}
