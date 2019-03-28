package com.pan.nurseStation.widget.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.support.v7.appcompat.R.attr;

import com.pan.nurseStation.R;

public class RoundButton extends AppCompatButton {
    private float cornerSize;//圆角大小
    private int backgroundColor;

    public RoundButton(Context context) {
        this(context, null);
    }

    public RoundButton(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
        this(context, attrs, attr.buttonStyle);
    }

    public RoundButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundButton, defStyleAttr, 0);
        cornerSize = a.getDimensionPixelSize(R.styleable.RoundButton_corner_radius, 14);

        backgroundColor = a.getColor(R.styleable.RoundButton_background_color, Color.WHITE);
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        int w = getWidth();
        int h = getHeight();
        //这里对path添加一个圆角区域，这里一般需要将dp转换为pixel
        path.addRoundRect(0, 0, w, h, cornerSize, cornerSize, Path.Direction.CW);
        canvas.clipPath(path);//将Canvas按照上面的圆角区域截取
        canvas.drawColor(backgroundColor);
        super.onDraw(canvas);

//        // 实例化一支画笔
//        Paint paint = new Paint();
//        paint.setStyle(Paint.Style.FILL);    // 设置样式
//
//        //android:background="#60b0fe"
//
//        paint.setColor(0xFF60b0fe);    // 设置颜色
//        paint.setAntiAlias(true);    //抗锯齿
//        RectF rectF = new RectF(0, 0, this.getWidth(), this.getHeight());
//        canvas.drawRoundRect(rectF, cornerSize, cornerSize, paint);    // 绘制一个矩形
//
//        paint.setColor(0xffffffff);    // 设置颜色
//        canvas.drawText("haha", this.getWidth() / 2, this.getHeight() / 2, paint);
    }

    public void setCornerSize(int cornerSize) {
        this.cornerSize = cornerSize;
    }
}
