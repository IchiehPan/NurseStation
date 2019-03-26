package com.pan.nurseStation.fragment;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.pan.nurseStation.R;
import com.pan.nurseStation.widget.web.JWebChromeClient;
import com.pan.nurseStation.widget.web.JWebSetting;
import com.pan.nurseStation.widget.web.JWebViewClient;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VitalSignFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VitalSignFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VitalSignFragment extends Fragment {
    private static final String TAG = VitalSignFragment.class.getSimpleName();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private LineChart mChart;
    private WebView mWebView;

    public VitalSignFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VitalSignFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VitalSignFragment newInstance(String param1, String param2) {
        VitalSignFragment fragment = new VitalSignFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_vital_sign, container, false);
        initView(root);
//        initChart();
        return root;
    }

    /**
     * 1.初始化LineChart
     * 2.添加数据x，y轴数据
     * 3.刷新图表
     */
    private void initChart() {
        /**
         * ====================1.初始化-自由配置===========================
         */
        // 是否在折线图上添加边框
        mChart.setDrawGridBackground(false);
        mChart.setDrawBorders(false);
        // 设置右下角描述
//        mChart.setDescription("");
        mChart.getDescription().setEnabled(false);
        //设置透明度
        mChart.setAlpha(0.8f);
        //设置网格底下的那条线的颜色
        mChart.setBorderColor(Color.rgb(213, 216, 214));
        //设置高亮显示
//        mChart.setHighlightEnabled(true);
        //设置是否可以触摸，如为false，则不能拖动，缩放等
        mChart.setTouchEnabled(false);
        //设置是否可以拖拽
        mChart.setDragEnabled(false);
        //设置是否可以缩放
        mChart.setScaleEnabled(false);
        //设置是否能扩大扩小
        mChart.setPinchZoom(false);

//        mChart.getAxisRight().setEnabled(false);
        mChart.getAxisLeft().setValueFormatter((value, axis) -> {
            return String.valueOf((int) value);
        });
        mChart.getAxisLeft().setAxisMinimum(35);
        mChart.getAxisRight().setValueFormatter((value, axis) -> {
            return String.valueOf((int) value);
        });
        mChart.getAxisRight().setAxisMinimum(40);

        // 设置X轴
        mChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        mChart.getXAxis().setValueFormatter((value, axis) -> {
            return "2010年10月10日";
        });
        /**
         * ====================2.布局点添加数据-自由布局===========================
         */
        // 折线图的点，点击战士的布局和数据
//        MyMarkView mv = new MyMarkView(this);
//        mChart.setMarkerView(mv);
        // 加载数据
        LineData data = getLineData();
        mChart.setData(data);
        /**
         * ====================3.x，y动画效果和刷新图表等===========================
         */
        //从X轴进入的动画
        mChart.animateX(1000);
        //从Y轴进入的动画
//        mChart.animateY(3000);
//        mChart.animateXY(3000, 3000);    //从XY轴一起进入的动画
        //设置最小的缩放
        mChart.setScaleMinima(0.5f, 1f);
        Legend l = mChart.getLegend();
        l.setForm(Legend.LegendForm.CIRCLE);  //设置图最下面显示的类型
        l.setTextSize(15);
        l.setTextColor(Color.rgb(104, 241, 175));
        l.setFormSize(30f);
        // 刷新图表
        mChart.invalidate();
    }

    private LineData getLineData() {
        String[] xx = {"50", "100", "100", "140", "120", "110", "110", "100", "90"};
        String[] yy = {"30", "33.3", "37", "37.2", "30", "40", "42", "42", "39"};

        List<Entry> xVals = new ArrayList<>();
        for (int i = 0; i < xx.length; i++) {
            xVals.add(new Entry(i + 1, Float.parseFloat(xx[i])));
        }

        List<Entry> yVals = new ArrayList<>();
        for (int i = 0; i < yy.length; i++) {
            yVals.add(new Entry(i + 1, Float.parseFloat(yy[i])));
        }
        Log.i(TAG, "getLineData: " + yVals);

        LineDataSet set1 = new LineDataSet(yVals, "LineChart Test");
//        set1.setDrawCubic(true);  //设置曲线为圆滑的线
        set1.setCubicIntensity(0.2f);
        set1.setDrawFilled(false);  //设置包括的范围区域填充颜色
        set1.setDrawCircles(true);  //设置有圆点
        set1.setLineWidth(2f);    //设置线的宽度
        set1.setCircleSize(5f);   //设置小圆的大小
        set1.setHighLightColor(Color.rgb(244, 117, 117));
        set1.setColor(Color.rgb(104, 241, 175));    //设置曲线的颜色

        LineDataSet set2 = new LineDataSet(xVals, "LineChart Test2");

        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        set2.setAxisDependency(YAxis.AxisDependency.RIGHT);

//        return new LineData(xVals, set1);
        return new LineData(set1, set2);
    }

    private void initView(View root) {
//        mChart = root.findViewById(R.id.line_chart);

        mWebView = root.findViewById(R.id.web_view);
        //声明WebSettings子类
        JWebSetting.setParam(mWebView.getSettings());
        mWebView.loadUrl("http://192.168.8.104:8080/DyWeb_war_exploded/life.html");
        mWebView.setWebViewClient(JWebViewClient.getInstance());
//        mWebView.setWebChromeClient(JWebChromeClient.getInstance());
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onDestroy() {

        //避免WebView内存泄露
        //在 Activity 销毁（ WebView ）的时候，先让 WebView 加载null内容，然后移除 WebView，再销毁 WebView，最后置空。
        if (mWebView != null) {
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();

            ((ViewGroup) mWebView.getParent()).removeView(mWebView);
            mWebView.destroy();
            mWebView = null;
        }

        super.onDestroy();
    }
}
