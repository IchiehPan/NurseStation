package com.pan.nurseStation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.pan.lib.util.BeanKit;
import com.pan.nurseStation.R;
import com.pan.nurseStation.bean.response.BedListResponseBean;
import com.pan.nurseStation.widget.web.JWebSetting;
import com.pan.nurseStation.widget.web.JWebViewClient;

public class VitalSignFragment extends Fragment {
    private static final String TAG = VitalSignFragment.class.getSimpleName();
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
        View root = inflater.inflate(R.layout.fragment_vital_sign, container, false);
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
        mWebView.loadUrl("http://192.168.8.104:8080/DyWeb_war_exploded/life.html");
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
}
