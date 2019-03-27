package com.pan.nurseStation;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bben.ydcf.scandome.CommonView;
import com.bben.ydcf.scandome.receiver.ScanResultReceiver;
import com.pan.anlib.util.DensityKit;
import com.pan.lib.util.BeanKit;
import com.pan.nurseStation.adapter.BedListAdapter;
import com.pan.nurseStation.adapter.BedTypeAdapter;
import com.pan.nurseStation.adapter.SimpleSpinnerAdapter;
import com.pan.nurseStation.bean.Constants;
import com.pan.nurseStation.bean.request.BedListRequestBean;
import com.pan.nurseStation.bean.request.LevelRequestBean;
import com.pan.nurseStation.bean.response.BedListResponseBean;
import com.pan.nurseStation.bean.response.LevelResponseBean;
import com.pan.nurseStation.business.DBHisBusiness;
import com.pan.nurseStation.listener.AutoLoadListener;
import com.pan.nurseStation.widget.dialog.JAlertDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class BedListActivity extends AppCompatActivity implements CommonView {
    private static final String TAG = BedListActivity.class.getSimpleName();

    private ScanResultReceiver resultReceiver;
    private Spinner spinner;
    private GridView bedTypeView;
    private GridView bedListView;
    private RelativeLayout searchBarView;
    private LinearLayout searchEditTextBar;
    private JAlertDialog dialog;
    private TextView departmentName;
    private TextView totalCount;
    private TextView leaveCount;
    private int page = 1;
    private List<BedListResponseBean.PatientInfo> dataList = new ArrayList<>();
    private BedListAdapter bedListAdapter;

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
        initNavData();
        initListData("", "", page);
    }

    public void initNavData() {
        DBHisBusiness dbHisBusiness = new DBHisBusiness();
        LevelRequestBean levelRequestBean = new LevelRequestBean();
        levelRequestBean.setDepartment_id(DBHisBusiness.loginBean.getDepartment_id());
        dbHisBusiness.level(levelRequestBean, response -> {
            LevelResponseBean responseBean = BeanKit.string2Bean(response, LevelResponseBean.class);
            DBHisBusiness.levelDataList = responseBean.getData();
            DBHisBusiness.initBedTypeColorMap(this);

            String[] bedTypes;
            if (Constants.ISDEBUG) {
                bedTypes = this.getResources().getStringArray(R.array.bed_types);
            }
            if (!DBHisBusiness.levelDataList.isEmpty()) {
                List<String> bedTypeList = new ArrayList<>();
                bedTypeList.add(getString(R.string.bed_list_level_head_tip));
                for (LevelResponseBean.Data data : DBHisBusiness.levelDataList) {
                    bedTypeList.add(data.getName());
                }

                bedTypes = bedTypeList.toArray(new String[bedTypeList.size()]);
            }
            SimpleSpinnerAdapter<String> arrayAdapter = new SimpleSpinnerAdapter<>(this, R.layout.item_simple_spinner, android.R.id.text1, bedTypes);
            arrayAdapter.setDropDownViewResource(R.layout.item_simple_spinner_dropdown);
            spinner.setAdapter(arrayAdapter);

            // 绑定颜色
            bedTypeView.setAdapter(new BedTypeAdapter(this, DBHisBusiness.levelDataList));

        }, error -> Log.e(TAG, "onCreate: error=" + error, error));

    }

    public void initListData(String level, String search, int page) {
        DBHisBusiness dbHisBusiness = new DBHisBusiness();
        BedListRequestBean bedListRequestBean = new BedListRequestBean();
        bedListRequestBean.setNumber(DBHisBusiness.loginBean.getNumber());
        bedListRequestBean.setLevel(level);
        bedListRequestBean.setSearch(search);
        bedListRequestBean.setPage(page);
        dbHisBusiness.bedlist(bedListRequestBean, response -> {
            Log.i(TAG, "onResponse: " + response);
            BedListResponseBean responseBean = BeanKit.string2Bean(response, BedListResponseBean.class);
            // 房子组件的绑定数据
            dataList.addAll(responseBean.getData().getList());
            bedListAdapter.notifyDataSetChanged();

            // 底下的数据
            departmentName.setText(DBHisBusiness.loginBean.getDepartment_name());
            totalCount.setText(responseBean.getData().getRecords());
            leaveCount.setText(responseBean.getData().getRemain_bed());
        }, error -> Log.e(TAG, "onResponse: " + error.toString(), error));
    }

    private void initView() {
        searchBarView = findViewById(R.id.search_bar_view);
        searchEditTextBar = findViewById(R.id.search_edit_text_bar);

        spinner = findViewById(R.id.spinner);
        bedTypeView = findViewById(R.id.bed_type_view);
        bedListView = findViewById(R.id.bed_list_view);

        departmentName = findViewById(R.id.department_name);
        totalCount = findViewById(R.id.total_count);
        leaveCount = findViewById(R.id.leave_count);

        bedListAdapter = new BedListAdapter(this, dataList);
        bedListView.setAdapter(bedListAdapter);

        AutoLoadListener autoLoadListener = new AutoLoadListener(() -> {
            initListData("", "", ++page);
        });
        bedListView.setOnScrollListener(autoLoadListener);
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
