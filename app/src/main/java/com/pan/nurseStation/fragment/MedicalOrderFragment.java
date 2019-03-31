package com.pan.nurseStation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.pan.lib.util.BeanKit;
import com.pan.nurseStation.R;
import com.pan.nurseStation.bean.Constants;
import com.pan.nurseStation.bean.response.BedListResponseBean;
import com.pan.nurseStation.widget.web.JWebSetting;
import com.pan.nurseStation.widget.web.JWebViewClient;

public class MedicalOrderFragment extends Fragment {
    private WebView mWebView;
    private BedListResponseBean.PatientInfo patientInfoBean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_medical_order, container, false);
        initView(root);
        return root;
    }

    private void initView(View root) {
        mWebView = root.findViewById(R.id.web_view);
        Bundle bundle = this.getArguments();
        String patientInfo = bundle.getString("patientInfo");
        patientInfoBean = BeanKit.string2Bean(patientInfo, BedListResponseBean.PatientInfo.class);

        //声明WebSettings子类
        JWebSetting.setParam(mWebView.getSettings());

        mWebView.loadUrl(Constants.MEDICAL_ORDER_URL + patientInfoBean.getHos_number());
        mWebView.setWebViewClient(JWebViewClient.getInstance());
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

    public WebView getWebView() {
        return mWebView;
    }

    public void inEnterMedicalOrderActivity(View view) {


    }
}
