package com.pan.nurseStation;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bben.ydcf.scandome.CommonView;
import com.bben.ydcf.scandome.receiver.ScanResultReceiver;
import com.pan.lib.util.BeanKit;
import com.pan.lib.util.NumberKit;
import com.pan.nurseStation.animate.AnimateBusiness;
import com.pan.nurseStation.bean.Constants;
import com.pan.nurseStation.bean.request.EnjoinDoInfoRequestBean;
import com.pan.nurseStation.bean.request.EnjoinDoRequestBean;
import com.pan.nurseStation.bean.response.EnjoinDoInfoResponseBean;
import com.pan.nurseStation.bean.response.EnjoinDoResponseBean;
import com.pan.nurseStation.bean.response.PatientDetailResponseBean;
import com.pan.nurseStation.business.DBHisBusiness;
import com.pan.nurseStation.widget.button.RoundButton;
import com.pan.nurseStation.widget.dialog.ScanErrorDialog;
import com.pan.nurseStation.widget.dialog.ScanInputDialog;
import com.pan.nurseStation.widget.layout.ImageExampleLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class EnterMedicalOrderActivity extends AppCompatActivity implements CommonView {
    private static final String TAG = EnterMedicalOrderActivity.class.getSimpleName();
    private ScanResultReceiver resultReceiver;
    private RoundButton successButtonBar;
    private ScanErrorDialog errorDialog;
    private ScanInputDialog inputDialog;
    private LinearLayout linearLayout;
    private TextView quantityContent;
    private TextView scanSuccessTip;
    private TextView patientName;
    private ImageView patientSex;
    private TextView patientAge;
    private TextView hosNumber;
    private TextView departmentName;
    private TextView bedId;
    private Map<Object, Object> checkedMap;
    private PatientDetailResponseBean.Data data;
    private boolean isVerified = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_medical_order);
        initView();

        resultReceiver = new ScanResultReceiver(this);
        registerReceiver(resultReceiver, new IntentFilter(com.bben.ydcf.scandome.Constants.DECODE_RESULT_FILTER));

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            Toast.makeText(this, getString(R.string.request_fail_tip), Toast.LENGTH_SHORT).show();
            return;
        }

        String patientInfo = bundle.getString("patientInfo");
        data = BeanKit.string2Bean(patientInfo, PatientDetailResponseBean.Data.class);
        checkedMap = new HashMap<>();
        initData(data);
    }

    private void initData(PatientDetailResponseBean.Data data) {
        String sex = data.getSex();
        patientName.setText(data.getName());
        if (Objects.equals(sex, getString(R.string.sex_type_male))) {
            patientSex.setBackground(getResources().getDrawable(R.drawable.ic_male));
        } else if (Objects.equals(sex, getString(R.string.sex_type_female))) {
            patientSex.setBackground(getResources().getDrawable(R.drawable.ic_female));
        }
        patientAge.setText(data.getAge());
        hosNumber.setText(data.getHos_number());
        departmentName.setText(data.getDepartment_name());
        bedId.setText(data.getBed_id());

        getMedicalOrderData(data);
    }

    private void initView() {
        successButtonBar = findViewById(R.id.success_button_bar);
        linearLayout = findViewById(R.id.linearLayout1);
        quantityContent = findViewById(R.id.quantity_content);
        scanSuccessTip = findViewById(R.id.scan_success_tip);

        patientName = findViewById(R.id.patient_name);
        patientSex = findViewById(R.id.patient_sex);
        patientAge = findViewById(R.id.patient_age);
        hosNumber = findViewById(R.id.hos_number);
        departmentName = findViewById(R.id.department_name);
        bedId = findViewById(R.id.bed_id);
    }


    @Override
    public void setScan(String str) {
        if (Objects.equals(str, data.getHos_number())) {
            scanSuccess();
        } else {
            scanFail();
        }
    }

    private void scanFail() {
        showErrorDialog();
    }

    private void scanSuccess() {
        AnimateBusiness.slideToggle(successButtonBar, 40, Constants.SLIDE_DURATION_MS, Constants.SLIDE_DURATION_MS);
        scanSuccessTip.setText(getString(R.string.scan_success_tip2));
        isVerified = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (resultReceiver != null) {
            unregisterReceiver(resultReceiver);
            resultReceiver = null;
        }
    }

    public void showErrorDialog() {
        errorDialog = new ScanErrorDialog(this, R.style.JDialogStyle, R.layout.dialog_custom_scan_error);
        errorDialog.setCanceledOnTouchOutside(false);
        errorDialog.show();
    }

    public void hideErrorDialog(View view) {
        Log.i(TAG, "hideErrorDialog: --------------------------");
        errorDialog.hide();
    }

    public void showInputDialog(View view) {
        inputDialog = new ScanInputDialog(this, R.style.JDialogStyle, R.layout.dialog_custom_scan_input);
        inputDialog.setCanceledOnTouchOutside(false);
        inputDialog.show();
    }

    public void hideInputDialog(View view) {
        Log.i(TAG, "hideInputDialog: --------------------------");
        inputDialog.hide();
    }

    public void submitInputDialog(View view) {
        EditText editText = inputDialog.findViewById(R.id.hos_id);
        String hosId = editText.getText().toString();
        Log.i(TAG, "submitInputDialog: --------------------------hosId=" + hosId);
        if (Objects.equals(hosId, data.getHos_number())) {
            scanSuccess();
        } else {
            scanFail();
        }
        inputDialog.hide();
    }

    public View getCheckHeadView(String exampleString) {
        ImageExampleLayout imageExampleLayout = new ImageExampleLayout(this);
        imageExampleLayout.setText(exampleString);
        return imageExampleLayout;
    }

    public View getCheckContentView(EnjoinDoInfoResponseBean.Data.MedicalOrder medicalOrder) {
        View contentView = getLayoutInflater().inflate(R.layout.item_checkbox_text_content, null);
        TextView typeText = contentView.findViewById(R.id.medical_type_text);
        TextView unitText = contentView.findViewById(R.id.medical_unit_text);
        CheckBox cb = contentView.findViewById(R.id.checkbox);
        String title = medicalOrder.getTitle();
        String dosage = medicalOrder.getDosage();
        String amount = medicalOrder.getAmount();
        String useMethod = medicalOrder.getUse_method();
        String id = medicalOrder.getId();
        String status = medicalOrder.getStatus();
        typeText.setText(title);
        unitText.setText(dosage + "/" + amount + "/" + useMethod);
        List<EnjoinDoInfoResponseBean.Data.MedicalOrder> data = new ArrayList<>();
        data.add(medicalOrder);
        cb.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
            if (isChecked) {
                checkedMap.put(data, status);
                increaseQuantityNum();
            } else {
                checkedMap.remove(data);
                decreaseQuantityNum();
            }
        });
        return contentView;
    }

    public void addCheckContentView(LinearLayout view, List<List<EnjoinDoInfoResponseBean.Data.MedicalOrder>> dataList) {
        for (List<EnjoinDoInfoResponseBean.Data.MedicalOrder> data : dataList) {
            if (dataList.size() > 1) {
                int index = 0;
                for (EnjoinDoInfoResponseBean.Data.MedicalOrder medicalOrder : data) {
                    String title = medicalOrder.getTitle();
                    String dosage = medicalOrder.getDosage();
                    String amount = medicalOrder.getAmount();
                    String useMethod = medicalOrder.getUse_method();
                    String id = medicalOrder.getId();
                    String status = medicalOrder.getStatus();

                    View contentView = getLayoutInflater().inflate(R.layout.item_checkbox_text_content, null);
                    TextView typeText = contentView.findViewById(R.id.medical_type_text);
                    TextView unitText = contentView.findViewById(R.id.medical_unit_text);
                    CheckBox cb = contentView.findViewById(R.id.checkbox);

                    typeText.setText(title);
                    unitText.setText(dosage + "/" + amount + "/" + useMethod);

                    if (index != 0) {
                        cb.setVisibility(View.INVISIBLE);
//                ((ViewGroup) cb.getParent()).removeView(cb);
                    } else {
                        cb.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
                            if (isChecked) {
                                checkedMap.put(data, status);
                                increaseQuantityNum();
                            } else {
                                checkedMap.remove(data);
                                decreaseQuantityNum();
                            }
                        });
                    }

                    LinearLayout marginLinearLayout = contentView.findViewById(R.id.margin_linear_layout);
                    LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) marginLinearLayout.getLayoutParams();
                    lp.setMargins(0, 0, 0, 0);
                    lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                    marginLinearLayout.setLayoutParams(lp);

                    view.addView(contentView);
                    ++index;
                }
            } else if (dataList.size() == 0) {
                linearLayout.addView(getCheckContentView(data.get(0)));
            }
        }
    }

    public void getMedicalOrderData(PatientDetailResponseBean.Data data) {
        DBHisBusiness dbHisBusiness = new DBHisBusiness();
        EnjoinDoInfoRequestBean requestBean = new EnjoinDoInfoRequestBean();
        requestBean.setHos_number(data.getHos_number());
        dbHisBusiness.patientEnjoinDoInfo(requestBean, response -> {
            Log.d(TAG, "getMedicalOrderData: response=" + response);
            EnjoinDoInfoResponseBean responseBean = BeanKit.string2Bean(response, EnjoinDoInfoResponseBean.class);

            for (EnjoinDoInfoResponseBean.Data infoData : responseBean.getData()) {
                int status = infoData.getStatus();
                List<List<EnjoinDoInfoResponseBean.Data.MedicalOrder>> list = infoData.getList();
                switch (status) {
                    case Constants.ORDER_STATUS_WAIT_MEDICAL:
                        linearLayout.addView(getCheckHeadView(getString(R.string.order_status_waiting_for_medicine)));
                        addCheckContentView(linearLayout, list);
                        break;
                    case Constants.ORDER_STATUS_WAIT_EXEC:
                        linearLayout.addView(getCheckHeadView(getString(R.string.order_status_waiting_for_execution)));
                        addCheckContentView(linearLayout, list);
                        break;
                    case Constants.ORDER_STATUS_DO_EXEC:
                        linearLayout.addView(getCheckHeadView(getString(R.string.order_status_execute)));
                        addCheckContentView(linearLayout, list);
                        break;
                }

            }
        }, error -> {
            Log.e(TAG, "getMedicalOrderData: ", error);
        });
    }


    public void increaseQuantityNum() {
        String count = quantityContent.getText().toString();
        if (NumberKit.isAllDigit(count)) {
            int num = Integer.valueOf(count) + 1;
            quantityContent.setText(String.valueOf(num));
        }
    }

    public void decreaseQuantityNum() {
        String count = quantityContent.getText().toString();
        if (NumberKit.isAllDigit(count)) {
            int num = Integer.valueOf(count) - 1;
            quantityContent.setText(String.valueOf(num));
        }
    }

    public void resetQuantity(View view) {
        for (Object key : checkedMap.keySet()) {
            CheckBox cb = (CheckBox) key;
            cb.setChecked(false);
        }
        checkedMap.clear();
        quantityContent.setText(String.valueOf(0));
    }

    public void submitQuantity(View view) {
        if (!isVerified) {
            Toast.makeText(this, getString(R.string.scan_not_tip), Toast.LENGTH_SHORT).show();
            return;
        }

        List<EnjoinDoRequestBean.Data> dataList = new ArrayList<>();
        for (Map.Entry entry : checkedMap.entrySet()) {
            List<EnjoinDoInfoResponseBean.Data.MedicalOrder> medicalOrderList = (List<EnjoinDoInfoResponseBean.Data.MedicalOrder>) entry.getKey();
            for (EnjoinDoInfoResponseBean.Data.MedicalOrder medicalOrder : medicalOrderList) {
                EnjoinDoRequestBean.Data data = new EnjoinDoRequestBean.Data();
                data.setId(medicalOrder.getId());
                data.setStatus(medicalOrder.getStatus());
                dataList.add(data);
            }
        }

        DBHisBusiness dbHisBusiness = new DBHisBusiness();
        EnjoinDoRequestBean requestBean = new EnjoinDoRequestBean();
        requestBean.setIds(dataList);

        Log.i(TAG, "submitQuantity: EnjoinDoRequestBean=" + JSON.toJSONString(requestBean));

        dbHisBusiness.patientEnjoinDo(requestBean, response -> {
            Log.i(TAG, "submitQuantity: response=" + response);
            EnjoinDoResponseBean responseBean = BeanKit.string2Bean(response, EnjoinDoResponseBean.class);
            if (responseBean.getRet() == Constants.MESSAGE_SUCCESS_CODE) {
                Toast.makeText(this, getString(R.string.request_success_tip), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, BedInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("patientInfo", JSON.toJSONString(data));
                bundle.putInt("fragmentIndex", BedInfoActivity.FRAG_MEDICAL_ORDER_INDEX);
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else {
                Toast.makeText(this, getString(R.string.request_fail_tip), Toast.LENGTH_SHORT).show();
            }

        }, error -> {
            Log.e(TAG, "submitQuantity: ", error);
        });
    }

    public void inBedListActivity(View view) {
        Intent intent = new Intent(this, BedListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
