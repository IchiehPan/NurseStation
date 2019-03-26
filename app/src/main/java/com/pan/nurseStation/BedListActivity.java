package com.pan.nurseStation;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.bben.ydcf.scandome.CommonView;
import com.bben.ydcf.scandome.receiver.ScanResultReceiver;
import com.pan.anlib.util.DensityKit;
import com.pan.nurseStation.adapter.BedListAdapter;
import com.pan.nurseStation.adapter.BedTypeAdapter;
import com.pan.nurseStation.adapter.SimpleSpinnerAdapter;
import com.pan.nurseStation.bean.Constants;
import com.pan.nurseStation.bean.request.BedListRequestBean;
import com.pan.nurseStation.business.DBHisBusiness;
import com.pan.nurseStation.widget.dialog.JAlertDialog;


public class BedListActivity extends AppCompatActivity implements CommonView {
    private static final String TAG = BedListActivity.class.getSimpleName();

    private ScanResultReceiver resultReceiver;
    private Spinner spinner;
    private GridView bedTypeView;
    private GridView bedListView;
    private RelativeLayout searchBarView;
    private LinearLayout searchEditTextBar;
    private JAlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bed_list);

        initView();
        initData();

        resultReceiver = new ScanResultReceiver(this);
        registerReceiver(resultReceiver, new IntentFilter(com.bben.ydcf.scandome.Constants.DECODE_RESULT_FILTER));
    }

    private void initData() {
        BedListRequestBean requestBean = new BedListRequestBean();
        requestBean.setLevel("lev");
        requestBean.setPage(2);
        requestBean.setSearch("sear");
        DBHisBusiness dbHisBusiness = new DBHisBusiness();
        dbHisBusiness.bedlist(requestBean, response -> {
            Log.i(TAG, "onResponse: " + response);
        }, error -> {
            Log.e(TAG, "onResponse: " + error.toString(), error);
        });

    }

    private void initView() {
        searchBarView = findViewById(R.id.search_bar_view);
        searchEditTextBar = findViewById(R.id.search_edit_text_bar);

        String[] bedTypes = this.getResources().getStringArray(R.array.bed_types);

        spinner = findViewById(R.id.spinner);
        SimpleSpinnerAdapter<String> arrayAdapter = new SimpleSpinnerAdapter<>(this, R.layout.item_simple_spinner, android.R.id.text1, bedTypes);
        arrayAdapter.setDropDownViewResource(R.layout.item_simple_spinner_dropdown);
        arrayAdapter.setTextSize(10);
        spinner.setAdapter(arrayAdapter);

        bedTypeView = findViewById(R.id.bed_type_view);
        bedTypeView.setAdapter(new BedTypeAdapter(this, 6));

        bedListView = findViewById(R.id.bed_list_view);
        bedListView.setAdapter(new BedListAdapter(this, 7));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (resultReceiver != null) {
            unregisterReceiver(resultReceiver);
            resultReceiver = null;
        }
    }

    @Override
    public void setScan(String str) {
        Log.i(TAG, "setScan: scanResult=" + str);

        showDialog();
    }

    public void slideIn(View view) {
        Log.i(TAG, "slideIn: ---");
        int translationX = searchBarView.getWidth() - DensityKit.getPxByResId(this, R.dimen.search_edit_text_margin_left);
        Log.i(TAG, "initView: translationX=" + translationX);
        searchEditTextBar.setVisibility(View.VISIBLE);

        Log.i(TAG, "initView: searchEditTextBar=" + searchEditTextBar);
        Log.i(TAG, "initView: getWidth()=" + searchEditTextBar.getWidth());
        Log.i(TAG, "initView: getHeight()=" + searchEditTextBar.getHeight());

        Log.i(TAG, "initView: getTranslationX()=" + searchEditTextBar.getTranslationX());
        Log.i(TAG, "initView: getTranslationY()=" + searchEditTextBar.getTranslationY());
        //RESTART表示从头开始，REVERSE表示从末尾倒播
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(searchEditTextBar, "translationX", translationX, 0);
//        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setDuration(Constants.SLIDE_DURATION_MS).start();
    }

    public void slideOut(View view) {
        Log.i(TAG, "slideOut: ---");
        int translationX = searchBarView.getWidth() - DensityKit.getPxByResId(this, R.dimen.search_edit_text_margin_left);
        //RESTART表示从头开始，REVERSE表示从末尾倒播
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(searchEditTextBar, "translationX", 0, translationX);
        objectAnimator.setDuration(Constants.SLIDE_DURATION_MS).start();
    }

    public void signOut(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void showDialog() {
        dialog = new JAlertDialog(this, R.style.JDialogStyle, R.layout.dialog_custom_alert_patient);
        dialog.show();
    }

    public void hideDialog(View view) {
        Log.i(TAG, "hideErrorDialog: --------------------------");
        dialog.hide();
    }
}
