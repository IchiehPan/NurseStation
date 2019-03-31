package com.pan.nurseStation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.pan.lib.util.BeanKit;
import com.pan.nurseStation.bean.response.BedListResponseBean;
import com.pan.nurseStation.fragment.MedicalOrderFragment;
import com.pan.nurseStation.fragment.PatientInfoFragment;
import com.pan.nurseStation.fragment.VitalSignFragment;

public class BedInfoActivity extends FragmentActivity {
    private static final String TAG = BedInfoActivity.class.getSimpleName();
    private TextView patientExampleInfo;
    private BottomNavigationView bottomNavigationView;
    private PatientInfoFragment patientInfoFragment;
    private MedicalOrderFragment medicalOrderFragment;
    private VitalSignFragment vitalSignFragment;
    private Fragment[] fragments;
    public final static int FRAG_PATIENT_INFO_INDEX = 0;
    public final static int FRAG_MEDICAL_ORDER_INDEX = 1;
    public final static int FRAG_VITAL_SIGN_INDEX = 2;


    private int lastShowFragment = 0;
    private BedListResponseBean.PatientInfo patientInfoBean;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = (@NonNull MenuItem item) -> {
        switch (item.getItemId()) {
            case R.id.navigation_info:
                if (lastShowFragment != FRAG_PATIENT_INFO_INDEX) {
                    switchFragment(lastShowFragment, FRAG_PATIENT_INFO_INDEX);
                    lastShowFragment = FRAG_PATIENT_INFO_INDEX;
                }
                return true;
            case R.id.navigation_doctor:
                if (lastShowFragment != 1) {
                    switchFragment(lastShowFragment, FRAG_MEDICAL_ORDER_INDEX);
                    lastShowFragment = FRAG_MEDICAL_ORDER_INDEX;
                }
                return true;
            case R.id.navigation_sign:
                if (lastShowFragment != 2) {
                    switchFragment(lastShowFragment, FRAG_VITAL_SIGN_INDEX);
                    lastShowFragment = FRAG_VITAL_SIGN_INDEX;
                }
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bed_info);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String patientInfo = bundle.getString("patientInfo");
            patientInfoBean = BeanKit.string2Bean(patientInfo, BedListResponseBean.PatientInfo.class);
        }

        initView();
        initData(patientInfoBean);
        initFragments(bundle);

        int fragmentIndex = bundle.getInt("fragmentIndex");
        if (fragmentIndex != lastShowFragment) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(fragments[lastShowFragment]);
            transaction.show(fragments[fragmentIndex]).commitAllowingStateLoss();
            bottomNavigationView.setSelectedItemId(bottomNavigationView.getMenu().getItem(fragmentIndex).getItemId());
        }
    }

    private void initData(BedListResponseBean.PatientInfo patientInfoBean) {
        patientExampleInfo.setText(patientInfoBean.getBed_id() + " " + patientInfoBean.getName());
    }

    private void initView() {
        patientExampleInfo = findViewById(R.id.patient_example_info);
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void initFragments(Bundle bundle) {
        patientInfoFragment = new PatientInfoFragment();
        medicalOrderFragment = new MedicalOrderFragment();
        vitalSignFragment = new VitalSignFragment();

        /*把数据设置到Fragment中*/
        patientInfoFragment.setArguments(bundle);
        medicalOrderFragment.setArguments(bundle);
        vitalSignFragment.setArguments(bundle);

        fragments = new Fragment[]{patientInfoFragment, medicalOrderFragment, vitalSignFragment};

        lastShowFragment = 0;
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, patientInfoFragment).show(patientInfoFragment).commit();
    }


    /**
     * 切换Fragment
     *
     * @param lastIndex 上个显示Fragment的索引
     * @param index     需要显示的Fragment的索引
     */
    public void switchFragment(int lastIndex, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastIndex]);
        if (!fragments[index].isAdded()) {
            transaction.add(R.id.fragment_container, fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }


    @Override
    public void onBackPressed() {
        switch (lastShowFragment) {
            case 0:
                patientInfoFragment.onBackPressed();
                break;
        }
//        super.onBackPressed();
    }

    public void inBedListActivity(View view) {
        Intent intent = new Intent(this, BedListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    public void refreshTheWeb(View view) {
        switch (lastShowFragment) {
            case 0:
                patientInfoFragment.getWebView().reload();
                break;
            case 1:
                medicalOrderFragment.getWebView().reload();
                break;
            case 2:
                vitalSignFragment.getWebView().reload();
                break;
        }
    }
}
