package com.pan.nurseStation.widget.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.pan.nurseStation.BedInfoActivity;
import com.pan.nurseStation.EnterMedicalOrderActivity;
import com.pan.nurseStation.EnterVitalSignActivity;
import com.pan.nurseStation.R;
import com.pan.nurseStation.bean.response.PatientDetailResponseBean;

import java.util.Objects;


public class JAlertDialog extends AlertDialog {
    private static final String TAG = JAlertDialog.class.getSimpleName();
    private TextView patientName;
    private ImageView patientSex;
    private TextView bedId;
    private TextView patientAge;
    private TextView departmentName;
    private TextView hosNumber;
    private TextView patientLevel;
    private TextView bedInfoDoor;
    private TextView enterMedicalOrderDoor;
    private TextView enterVitalSignDoor;
    private int mResource;

    public JAlertDialog(Context context, int themeResId, @LayoutRes int resource) {
        super(context, themeResId);
        this.mResource = resource;
    }

//    public JAlertDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
//        super(context, cancelable, cancelListener);
//        this.mContext = context;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = View.inflate(getContext(), mResource, null);
        setContentView(view);

        patientName = view.findViewById(R.id.patient_name);
        patientSex = view.findViewById(R.id.patient_sex);
        bedId = view.findViewById(R.id.bed_id);
        patientAge = view.findViewById(R.id.patient_age);
        departmentName = view.findViewById(R.id.department_name);
        hosNumber = view.findViewById(R.id.hos_number);
        patientLevel = view.findViewById(R.id.patient_level);

        bedInfoDoor = view.findViewById(R.id.bed_info_activity_door);
        enterMedicalOrderDoor = view.findViewById(R.id.enter_medical_order_activity_door);
        enterVitalSignDoor = view.findViewById(R.id.enter_vital_sign_activity_door);

//        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        lp.width = (int) mWidth;
//        lp.height = (int) mHeight;
//        getWindow().setAttributes(lp);
    }

    public void setData(PatientDetailResponseBean.Data data) {
        String sex = data.getSex();
        patientName.setText(data.getName());
        if (Objects.equals(sex, getContext().getString(R.string.sex_type_male))) {
            patientSex.setBackgroundResource(R.drawable.ic_male);
        } else if (Objects.equals(sex, getContext().getString(R.string.sex_type_female))) {
            patientSex.setBackgroundResource(R.drawable.ic_female);
        }
        bedId.setText(data.getBed_id());
        patientAge.setText(data.getAge());
        departmentName.setText(data.getDepartment_name());
        hosNumber.setText(data.getHos_number());
        patientLevel.setText(data.getLevel());

        //绑定一些跳转事件
        bedInfoDoor.setOnClickListener(view -> clickInActivity(data, BedInfoActivity.class));

        enterMedicalOrderDoor.setOnClickListener(view -> clickInActivity(data, EnterMedicalOrderActivity.class));

        enterVitalSignDoor.setOnClickListener(view -> clickInActivity(data, EnterVitalSignActivity.class));
    }

    public void clickInActivity(PatientDetailResponseBean.Data data, Class clazz) {
        Intent intent = new Intent(getContext(), clazz);
        Bundle bundle = new Bundle();

        bundle.putString("bedId", data.getBed_id());
        bundle.putString("name", data.getName());
        bundle.putString("hosNumber", data.getHos_number());
        intent.putExtras(bundle);
        getContext().startActivity(intent);
    }
}
