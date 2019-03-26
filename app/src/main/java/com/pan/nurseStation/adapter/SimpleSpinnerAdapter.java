package com.pan.nurseStation.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pan.nurseStation.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SimpleSpinnerAdapter<T> extends ArrayAdapter<T> {
    private Context mContext;
    private int mResource;
    private int mTextViewResourceId;
    private float mTextSize;
    private int mDropDownResource;
    private float mDropDownTextSize;
    private int mSpaceDistance;

    public SimpleSpinnerAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull T[] objects) {
        super(context, resource, textViewResourceId, objects);

        this.mContext = context;
        this.mResource = resource;
        this.mTextViewResourceId = textViewResourceId;
    }

    public void setTextSize(float textSize) {
        this.mTextSize = textSize;
    }

    public void setDropDownTextSize(float textSize) {
        this.mDropDownTextSize = textSize;
    }

    public void setDropDownViewResource(int dropDownResource) {
        this.mDropDownResource = dropDownResource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
        }

        if (mTextSize != 0) {
            TextView tv = convertView.findViewById(mTextViewResourceId);
            tv.setTextSize(mTextSize);
        }

        if (mSpaceDistance != 0) {
            ImageView imageView = convertView.findViewById(R.id.image_view);
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) imageView.getLayoutParams();
            lp.setMargins(mSpaceDistance, 0, 0, 0);
            imageView.setLayoutParams(lp);
        }

//        return convertView;
        return super.getView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mDropDownResource, parent, false);
        }

        if (mDropDownTextSize != 0) {
            TextView tv = convertView.findViewById(mTextViewResourceId);
            tv.setTextSize(mDropDownTextSize);
        }

        return super.getDropDownView(position, convertView, parent);
    }

    public void setSpaceDistance(int spaceDistance) {
        this.mSpaceDistance = spaceDistance;
    }
}
