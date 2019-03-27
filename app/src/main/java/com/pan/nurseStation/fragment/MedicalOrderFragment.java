package com.pan.nurseStation.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Spinner;

import com.pan.nurseStation.R;
import com.pan.nurseStation.adapter.SimpleSpinnerAdapter;
import com.pan.nurseStation.widget.web.JWebSetting;
import com.pan.nurseStation.widget.web.JWebViewClient;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MedicalOrderFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MedicalOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MedicalOrderFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private WebView mWebView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MedicalOrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MedicalOrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MedicalOrderFragment newInstance(String param1, String param2) {
        MedicalOrderFragment fragment = new MedicalOrderFragment();
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
        View root = inflater.inflate(R.layout.fragment_medical_order, container, false);
        initView(root);
        return root;
    }

    private void initView(View root) {
        mWebView = root.findViewById(R.id.web_view);
        //声明WebSettings子类
        JWebSetting.setParam(mWebView.getSettings());
        mWebView.loadUrl("http://192.168.8.104:8080/DyWeb_war_exploded/advice-implement.html");
        mWebView.setWebViewClient(JWebViewClient.getInstance());
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

    public WebView getWebView() {
        return mWebView;
    }
}
