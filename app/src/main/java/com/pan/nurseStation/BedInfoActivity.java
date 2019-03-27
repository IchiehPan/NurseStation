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

import com.pan.nurseStation.fragment.MedicalOrderFragment;
import com.pan.nurseStation.fragment.PatientInfoFragment;
import com.pan.nurseStation.fragment.VitalSignFragment;

public class BedInfoActivity extends FragmentActivity {

    private static final String TAG = BedInfoActivity.class.getSimpleName();
    private PatientInfoFragment patientInfoFrag;
    private MedicalOrderFragment doctorToldFrag;
    private VitalSignFragment vitalSignFra;
    private Fragment[] fragments;
    private int lastShowFragment = 0;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = (@NonNull MenuItem item) -> {
        switch (item.getItemId()) {
            case R.id.navigation_info:
                if (lastShowFragment != 0) {
                    switchFragment(lastShowFragment, 0);
                    lastShowFragment = 0;
                }
                return true;
            case R.id.navigation_doctor:
                if (lastShowFragment != 1) {
                    switchFragment(lastShowFragment, 1);
                    lastShowFragment = 1;
                }
                return true;
            case R.id.navigation_sign:
                if (lastShowFragment != 2) {
                    switchFragment(lastShowFragment, 2);
                    lastShowFragment = 2;
                }
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bed_info);

        String hosNumber = savedInstanceState.getString("hos_number");
        Log.d(TAG, "onCreate: hosNumber=" + hosNumber);

        initView();
        initFragments();
    }

    private void initView() {
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void initFragments() {
        patientInfoFrag = new PatientInfoFragment();
        doctorToldFrag = new MedicalOrderFragment();
        vitalSignFra = new VitalSignFragment();
        fragments = new Fragment[]{patientInfoFrag, doctorToldFrag, vitalSignFra};
        lastShowFragment = 0;
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, patientInfoFrag)
                .show(patientInfoFrag)
                .commit();
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
                patientInfoFrag.onBackPressed();
                break;
        }
//        super.onBackPressed();
    }

    public void backToList(View view) {
        Intent intent = new Intent(this, BedListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
