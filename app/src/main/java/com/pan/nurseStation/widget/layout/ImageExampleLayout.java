package com.pan.nurseStation.widget.layout;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pan.lib.util.StringKit;
import com.pan.nurseStation.R;

public class ImageExampleLayout extends LinearLayout {
    private TextView tv;

    public ImageExampleLayout(Context context) {
        this(context, null);
    }

    public ImageExampleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ImageExampleLayout);
        String text = a.getString(R.styleable.ImageExampleLayout_text);
        setText(text);
    }

    public void init(Context context) {
        View.inflate(context, R.layout.view_image_example, this);
        tv = findViewById(R.id.example_head);
    }

    public void setText(String mText) {
        if (StringKit.isValid(mText)) {
            tv.setText(mText);
        }
    }
}
