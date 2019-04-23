package com.pan.nurseStation.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.pan.lib.util.BeanKit;
import com.pan.nurseStation.R;
import com.pan.nurseStation.config.Constants;
import com.pan.nurseStation.bean.response.BedListResponseBean;
import com.pan.nurseStation.widget.fragment.BackHandledInterface;
import com.pan.nurseStation.widget.web.JWebSetting;
import com.pan.nurseStation.widget.web.JWebViewClient;

import java.util.Objects;

public class PatientInfoFragment extends Fragment implements BackHandledInterface {
    private static final String TAG = PatientInfoFragment.class.getSimpleName();
    private WebView mWebView;
    private TabLayout mTabLayout;
    private BedListResponseBean.PatientInfo patientInfoBean;

    public PatientInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_patient_info, container, false);
        initView(root);
        return root;
    }

    private void initView(View root) {
        mWebView = root.findViewById(R.id.webView);
        Bundle bundle = this.getArguments();
        String patientInfo = bundle.getString("patientInfo");
        patientInfoBean = BeanKit.string2Bean(patientInfo, BedListResponseBean.PatientInfo.class);
        String hosNumber = patientInfoBean.getHos_number();

        //声明WebSettings子类
        JWebSetting.setParam(mWebView.getSettings());
        mWebView.loadUrl(Constants.PATIENT_INFO_URL + hosNumber);
        mWebView.setWebViewClient(JWebViewClient.getInstance());

        mTabLayout = root.findViewById(R.id.tabs);
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.page_basic_info)), true);
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.page_order_info)));
//        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.page_nursing_record)));
//        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.page_progress_note)));

        mTabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d(TAG, "onTabSelected: ---------------------------" + tab.getText());
                // 选中事件
                String text = tab.getText().toString();
                if (Objects.equals(text, getString(R.string.page_basic_info))) {
                    mWebView.loadUrl(Constants.PATIENT_INFO_URL + hosNumber);
                } else if (Objects.equals(text, getString(R.string.page_order_info))) {
                    mWebView.loadUrl(Constants.MEDICAL_INFO_URL + hosNumber);
                } else if (Objects.equals(text, getString(R.string.page_nursing_record))) {
                    mWebView.loadUrl("http://192.168.8.44/course-record.html#");
                } else if (Objects.equals(text, getString(R.string.page_progress_note))) {
                    mWebView.loadUrl("http://192.168.8.44/record-details.html");
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
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


    @Override
    public void onBackPressed() {
    }

    public WebView getWebView() {
        return mWebView;
    }
}
