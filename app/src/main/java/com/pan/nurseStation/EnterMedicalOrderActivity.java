package com.pan.nurseStation;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bben.ydcf.scandome.CommonView;
import com.bben.ydcf.scandome.receiver.ScanResultReceiver;
import com.pan.lib.util.NumberKit;
import com.pan.nurseStation.animate.AnimateBusiness;
import com.pan.nurseStation.bean.Constants;
import com.pan.nurseStation.widget.button.RoundButton;
import com.pan.nurseStation.widget.dialog.ScanErrorDialog;
import com.pan.nurseStation.widget.dialog.ScanInputDialog;
import com.pan.nurseStation.widget.layout.ImageExampleLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnterMedicalOrderActivity extends AppCompatActivity implements CommonView {
    private static final String TAG = EnterMedicalOrderActivity.class.getSimpleName();
    private ScanResultReceiver resultReceiver;
    private RoundButton successButtonBar;
    private ScanErrorDialog errorDialog;
    private ScanInputDialog inputDialog;
    private LinearLayout linearLayout;
    private TextView quantityContent;
    private TextView scan_success_tip;
    static Map<Object, Object> checkedMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_medical_order);

        Bundle bundle = getIntent().getExtras();
        String hosNumber = bundle.getString("hos_number");
        Log.d(TAG, "onCreate: hosNumber=" + hosNumber);
        String patientInfo = bundle.getString("patientInfo");
        Log.d(TAG, "onCreate: patientInfo=" + patientInfo);




        resultReceiver = new ScanResultReceiver(this);
        registerReceiver(resultReceiver, new IntentFilter(com.bben.ydcf.scandome.Constants.DECODE_RESULT_FILTER));

        initView();

        checkedMap = new HashMap<>();

        AnimateBusiness.slideToggle(successButtonBar, 200, Constants.SLIDE_DURATION_MS, Constants.SLIDE_DURATION_MS);
    }

    private void initView() {
        successButtonBar = findViewById(R.id.success_button_bar);
        linearLayout = findViewById(R.id.linearLayout1);
        quantityContent = findViewById(R.id.quantity_content);
        scan_success_tip = findViewById(R.id.scan_success_tip);

        getMedicalOrderData();
    }


    @Override
    public void setScan(String str) {
        scanSuccess();
        scanFail();
    }

    private void scanFail() {
        showErrorDialog();
    }

    private void scanSuccess() {
        scan_success_tip.setText(getString(R.string.scan_success_tip2));
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
        Log.i(TAG, "submitInputDialog: --------------------------");
    }

    public View getCheckHeadView(String exampleString) {
        ImageExampleLayout imageExampleLayout = new ImageExampleLayout(this);
        imageExampleLayout.setText(exampleString);
        return imageExampleLayout;
    }

    public View getCheckContentView(String type, String unit) {
        View contentView = getLayoutInflater().inflate(R.layout.item_checkbox_text_content, null);
        TextView typeText = contentView.findViewById(R.id.medical_type_text);
        TextView unitText = contentView.findViewById(R.id.medical_unit_text);
        CheckBox cb = contentView.findViewById(R.id.checkbox);
        typeText.setText(type);
        unitText.setText(unit);
        cb.setTag(type);
        cb.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
            Object tag = buttonView.getTag();
            if (isChecked) {
                checkedMap.put(buttonView, tag);
                increaseQuantityNum();
            } else {
                checkedMap.remove(tag);
                decreaseQuantityNum();
            }
        });
        return contentView;
    }

    public void batchAddCheckContentView(LinearLayout view, List<String> texts) {
        int index = 0;
        for (String text : texts) {
            String type = text.split(",")[0];
            String unit = text.split(",")[1];
            View contentView = getLayoutInflater().inflate(R.layout.item_checkbox_text_content, null);
            TextView typeText = contentView.findViewById(R.id.medical_type_text);
            TextView unitText = contentView.findViewById(R.id.medical_unit_text);
            CheckBox cb = contentView.findViewById(R.id.checkbox);

            typeText.setText(type);
            unitText.setText(unit);

            if (index != 0) {
                cb.setVisibility(View.INVISIBLE);
//                ((ViewGroup) cb.getParent()).removeView(cb);
            } else {
                cb.setOnCheckedChangeListener((CompoundButton buttonView, boolean isChecked) -> {
                    if (isChecked) {
                        checkedMap.put(buttonView, texts);
                        increaseQuantityNum();
                    } else {
                        checkedMap.remove(texts);
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
    }

    public void getMedicalOrderData() {
        linearLayout.addView(getCheckHeadView(getString(R.string.order_status_execute)));
        linearLayout.addView(getCheckContentView(getString(R.string.example_medical_type), getString(R.string.example_medical_unit)));
        linearLayout.addView(getCheckContentView(getString(R.string.example_medical_type), getString(R.string.example_medical_unit)));
        linearLayout.addView(getCheckContentView(getString(R.string.example_medical_type), getString(R.string.example_medical_unit)));
        linearLayout.addView(getCheckHeadView(getString(R.string.order_status_finish)));
        linearLayout.addView(getCheckContentView(getString(R.string.example_medical_type), getString(R.string.example_medical_unit)));
        linearLayout.addView(getCheckContentView(getString(R.string.example_medical_type), getString(R.string.example_medical_unit)));
        linearLayout.addView(getCheckContentView(getString(R.string.example_medical_type), getString(R.string.example_medical_unit)));
        linearLayout.addView(getCheckContentView(getString(R.string.example_medical_type), getString(R.string.example_medical_unit)));
        linearLayout.addView(getCheckContentView(getString(R.string.example_medical_type), getString(R.string.example_medical_unit)));
        linearLayout.addView(getCheckContentView(getString(R.string.example_medical_type), getString(R.string.example_medical_unit)));
        linearLayout.addView(getCheckContentView(getString(R.string.example_medical_type), getString(R.string.example_medical_unit)));

        List<String> list = new ArrayList<>();
        list.add("维生素C注射液[规格],0.5g/Q12h/输液");
        list.add("维生素C注射液[规格],0.5g / Q12h / 输液");
        list.add("维生素C注射液[规格],0.5g / Q12h / 输液");
        batchAddCheckContentView(linearLayout, list);
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
        for (Object value : checkedMap.values()) {
            System.out.println(value);
        }
    }

    public void backToList(View view) {
        Intent intent = new Intent(this, BedListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
