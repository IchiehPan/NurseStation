package com.pan.nurseStation.widget.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

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
    private Context mContext;
    private int mResource;

    public JAlertDialog(Context context, int themeResId, @LayoutRes int resource) {
        super(context, themeResId);
        this.mContext = context;
        this.mResource = resource;
    }

//    public JAlertDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
//        super(context, cancelable, cancelListener);
//        this.mContext = context;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = View.inflate(getContext(), R.layout.dialog_custom_alert_patient, null);
        setContentView(view);

        patientName = view.findViewById(R.id.patient_name);
        patientSex = view.findViewById(R.id.patient_sex);
        bedId = view.findViewById(R.id.bed_id);
        patientAge = view.findViewById(R.id.patient_age);
        departmentName = view.findViewById(R.id.department_name);
        hosNumber = view.findViewById(R.id.hos_number);
        patientLevel = view.findViewById(R.id.patient_level);

//        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        lp.width = (int) mWidth;
//        lp.height = (int) mHeight;
//        getWindow().setAttributes(lp);
    }

    public void setData(PatientDetailResponseBean.Data data) {
        String sex = data.getSex();
        patientName.setText(data.getName());
        if (Objects.equals(sex, mContext.getString(R.string.sex_type_male))) {
            patientSex.setBackground(mContext.getResources().getDrawable(R.drawable.ic_male));
        } else if (Objects.equals(sex, mContext.getString(R.string.sex_type_female))) {
            patientSex.setBackground(mContext.getResources().getDrawable(R.drawable.ic_female));
        }
        bedId.setText(data.getBed_id());
        patientAge.setText(data.getAge());
        departmentName.setText(data.getDepartment_name());
        hosNumber.setText(data.getHos_number());
        patientLevel.setText(data.getLevel());
    }


}
