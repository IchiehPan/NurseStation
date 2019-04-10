package com.pan.nurseStation;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.pan.lib.util.BeanKit;
import com.pan.lib.util.DateKit;
import com.pan.lib.util.StringKit;
import com.pan.nurseStation.animate.AnimateBusiness;
import com.pan.nurseStation.bean.Constants;
import com.pan.nurseStation.bean.request.SignsDoRequestBean;
import com.pan.nurseStation.bean.response.PatientDetailResponseBean;
import com.pan.nurseStation.bean.response.SignsDoResponseBean;
import com.pan.nurseStation.business.DBHisBusiness;
import com.pan.nurseStation.widget.button.RoundButton;
import com.pan.nurseStation.widget.dialog.ScanErrorDialog;
import com.pan.nurseStation.widget.dialog.ScanInputDialog;

import java.util.Calendar;
import java.util.Objects;

public class EnterVitalSignActivity extends AppCompatActivity {
    private static final String TAG = EnterVitalSignActivity.class.getSimpleName();
    private ScrollView scrollView;
    private RadioGroup temperatureGroup;
    private RadioGroup pulseGroup;
    private RadioButton temperatureButton;
    private RadioButton pulseButton;
    private EditText temperatureEditText;
    private EditText pulseEditText;
    private EditText heartEditText;
    private EditText breatheEditText;
    private EditText bloodPressureEditText;
    private EditText stoolFrequencyEditText;
    private EditText urineVolumeEditText;
    private EditText inputVolumeEditText;
    private EditText outputVolumeEditText;
    private EditText weightEditText;
    private EditText heightEditText;
    private EditText otherEditText;
    private EditText skinTestEditText;
    private CheckBox physicalCoolingCheckBox;
    private CheckBox assistedBreatheCheckBox;
    private TextView patientName;
    private ImageView patientSex;
    private TextView patientAge;
    private TextView hosNumber;
    private TextView departmentName;
    private TextView bedId;
    private ScanInputDialog inputDialog;
    private RoundButton successButtonBar;
    private TextView scanSuccessTip;
    private ScanErrorDialog errorDialog;
    private PatientDetailResponseBean.Data data;
    private TextView dateTextView;
    private TextView timeTextView;


    private String date;
    private String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_vital_sign);
        initView();

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            Toast.makeText(this, getString(R.string.request_fail_tip), Toast.LENGTH_SHORT).show();
            return;
        }
        String patientInfo = bundle.getString("patientInfo");
        data = BeanKit.string2Bean(patientInfo, PatientDetailResponseBean.Data.class);
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
    }

    private void initView() {
        scrollView = findViewById(R.id.scroll_view);

        scrollView.addView(View.inflate(this, R.layout.view_form_table, null));

        temperatureGroup = findViewById(R.id.radio_temperature);
        pulseGroup = findViewById(R.id.radio_pulse);
        temperatureEditText = findViewById(R.id.temperature);
        pulseEditText = findViewById(R.id.pulse);
        heartEditText = findViewById(R.id.heart);
        breatheEditText = findViewById(R.id.breathe);
        bloodPressureEditText = findViewById(R.id.blood_pressure);
        stoolFrequencyEditText = findViewById(R.id.stool_frequency);
        urineVolumeEditText = findViewById(R.id.urine_volume);
        inputVolumeEditText = findViewById(R.id.input_volume);
        outputVolumeEditText = findViewById(R.id.output_volume);
        weightEditText = findViewById(R.id.weight);
        heightEditText = findViewById(R.id.height);
        otherEditText = findViewById(R.id.other);
        skinTestEditText = findViewById(R.id.skin_test);
        assistedBreatheCheckBox = findViewById(R.id.assisted_breathe);
        physicalCoolingCheckBox = findViewById(R.id.physical_cooling);

        patientName = findViewById(R.id.patient_name);
        patientSex = findViewById(R.id.patient_sex);
        patientAge = findViewById(R.id.patient_age);
        hosNumber = findViewById(R.id.hos_number);
        departmentName = findViewById(R.id.department_name);
        bedId = findViewById(R.id.bed_id);

        successButtonBar = findViewById(R.id.success_button_bar);
        scanSuccessTip = findViewById(R.id.scan_success_tip);

        dateTextView = findViewById(R.id.date_text);
        timeTextView = findViewById(R.id.time_text);

        Calendar calendar = Calendar.getInstance();
        date = DateKit.formatDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        time = DateKit.formatTime(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
        dateTextView.setText(date);
        timeTextView.setText(time);

        //获取系统的日期
        dateTextView.setOnClickListener(view -> {
            DatePickerDialog dateDialog = new DatePickerDialog(this, (DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) -> {
                date = DateKit.formatDate(year, monthOfYear, dayOfMonth);
                dateTextView.setText(date);
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            dateDialog.show();
        });

        //获取系统时间
        timeTextView.setOnClickListener(view -> {
            TimePickerDialog timeDialog = new TimePickerDialog(this, (TimePicker timePicker, int hourOfDay, int minute) -> {
                time = DateKit.formatTime(hourOfDay, minute);
                timeTextView.setText(time);
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
            timeDialog.show();
        });

        pulseGroup.setOnCheckedChangeListener((RadioGroup radioGroup, int i) -> {
            pulseButton = findViewById(i);
            String pulseType = pulseButton.getText().toString();
            if (Objects.equals(pulseType, getString(R.string.form_pulse_type_pulse))) {
                pulseEditText.setEnabled(true);
                heartEditText.setEnabled(false);
            }
            if (Objects.equals(pulseType, getString(R.string.form_pulse_type_heart))) {
                pulseEditText.setEnabled(false);
                heartEditText.setEnabled(true);
            }
            if (Objects.equals(pulseType, getString(R.string.form_pulse_type_all))) {
                pulseEditText.setEnabled(true);
                heartEditText.setEnabled(true);
            }
        });


//        // 这里有一个非空复制的操作
//        pulseEditText.setOnFocusChangeListener((View view, boolean b) -> {
//            if (!b && !StringKit.isValid(heartEditText.getText().toString())) {
//                heartEditText.setText(pulseEditText.getText().toString());
//            }
//        });
//        heartEditText.setOnFocusChangeListener((View view, boolean b) -> {
//            if (!b && !StringKit.isValid(pulseEditText.getText().toString())) {
//                pulseEditText.setText(heartEditText.getText().toString());
//            }
//        });
    }

    public void resetForm(View view) {
        temperatureGroup.clearCheck();
        pulseGroup.clearCheck();
        temperatureEditText.setText("");
        pulseEditText.setText("");
        heartEditText.setText("");
        breatheEditText.setText("");
        bloodPressureEditText.setText("");
        stoolFrequencyEditText.setText("");
        urineVolumeEditText.setText("");
        inputVolumeEditText.setText("");
        outputVolumeEditText.setText("");
        weightEditText.setText("");
        heightEditText.setText("");
        otherEditText.setText("");
        skinTestEditText.setText("");
        assistedBreatheCheckBox.setChecked(false);
        physicalCoolingCheckBox.setChecked(false);
    }

    public void submitForm(View view) {
        temperatureButton = findViewById(temperatureGroup.getCheckedRadioButtonId());
        pulseButton = findViewById(pulseGroup.getCheckedRadioButtonId());

        if (temperatureButton == null || pulseButton == null) {
            Toast.makeText(this, getString(R.string.input_not_tip), Toast.LENGTH_SHORT).show();
            return;
        }

        //
        String temperatureType = temperatureButton.getText().toString();
        String pulseType = pulseButton.getText().toString();
        String temperature = temperatureEditText.getText().toString();
        String pulse = pulseEditText.getText().toString();
        String heart = heartEditText.getText().toString();
        String breathe = breatheEditText.getText().toString();
        String bloodPressure = bloodPressureEditText.getText().toString();
        String stoolFrequency = stoolFrequencyEditText.getText().toString();
        String urineVolume = urineVolumeEditText.getText().toString();
        String inputVolume = inputVolumeEditText.getText().toString();
        String outputVolume = outputVolumeEditText.getText().toString();
        String weight = weightEditText.getText().toString();
        String height = heightEditText.getText().toString();
        String other = otherEditText.getText().toString();
        String skinTest = skinTestEditText.getText().toString();

        if (!StringKit.isValid(temperature) || !StringKit.isValid(pulse) || !StringKit.isValid(heart) || !StringKit.isValid(breathe) || !StringKit.isValid(bloodPressure)
                || !StringKit.isValid(stoolFrequency) || !StringKit.isValid(urineVolume) || !StringKit.isValid(inputVolume) || !StringKit.isValid(outputVolume)
                || !StringKit.isValid(weight) || !StringKit.isValid(height) || !StringKit.isValid(skinTest) || !StringKit.isValid(other)) {
            Toast.makeText(this, getString(R.string.input_not_tip), Toast.LENGTH_SHORT).show();
            return;
        }

        boolean isAssistedBreathe = assistedBreatheCheckBox.isChecked();
        boolean isPhysicalCooling = physicalCoolingCheckBox.isChecked();

        Log.i(TAG, "submitForm: temperatureType=" + temperatureType);
        Log.i(TAG, "submitForm: pulseType=" + pulseType);
        Log.i(TAG, "submitForm: temperature=" + temperature);
        Log.i(TAG, "submitForm: pulse=" + pulse);
        Log.i(TAG, "submitForm: breathe=" + breathe);
        Log.i(TAG, "submitForm: bloodPressure=" + bloodPressure);
        Log.i(TAG, "submitForm: stoolFrequency=" + stoolFrequency);
        Log.i(TAG, "submitForm: urineVolume=" + urineVolume);
        Log.i(TAG, "submitForm: inputVolume=" + inputVolume);
        Log.i(TAG, "submitForm: outputVolume=" + outputVolume);
        Log.i(TAG, "submitForm: weight=" + weight);
        Log.i(TAG, "submitForm: height=" + height);
        Log.i(TAG, "submitForm: other=" + other);
        Log.i(TAG, "submitForm: skinTest=" + skinTest);
        Log.i(TAG, "submitForm: isAssistedBreathe=" + isAssistedBreathe);
        Log.i(TAG, "submitForm: isPhysicalCooling=" + isPhysicalCooling);

        DBHisBusiness dbHisBusiness = new DBHisBusiness();
        SignsDoRequestBean requestBean = new SignsDoRequestBean();
        SignsDoRequestBean.Info info = new SignsDoRequestBean.Info();
        if (Objects.equals(temperatureType, getString(R.string.form_temperature_type_mouth))) {
            info.setTemp_type(String.valueOf(1));
        }
        if (Objects.equals(temperatureType, getString(R.string.form_temperature_type_underarm))) {
            info.setTemp_type(String.valueOf(2));
        }
        if (Objects.equals(temperatureType, getString(R.string.form_temperature_type_anus))) {
            info.setTemp_type(String.valueOf(3));
        }

        info.setTemperature(temperature);

        if (Objects.equals(pulseType, getString(R.string.form_pulse_type_pulse))) {
            info.setPulse(pulse);
        }
        if (Objects.equals(pulseType, getString(R.string.form_pulse_type_heart))) {
            info.setHeart_rate(heart);
        }
        if (Objects.equals(pulseType, getString(R.string.form_pulse_type_all))) {
            info.setPulse(pulse);
            info.setHeart_rate(heart);
        }

        info.setBreath(breathe);
        info.setBlood_pressure(bloodPressure);
        info.setStool_frequency(stoolFrequency);
        info.setUrine_volume(urineVolume);
        info.setInput_fluid_volume(inputVolume);
        info.setOutput_fluid_volume(outputVolume);
        info.setWeight(weight);
        info.setHeight(height);
        info.setRemark(other);

        info.setEday(date);
        info.setEtime(time);
        info.setHos_number(data.getHos_number());
        info.setNumber(DBHisBusiness.loginBean.getNumber());

        if (isAssistedBreathe) {
            info.setBreath_type(String.valueOf(1));
        } else {
            info.setBreath_type(String.valueOf(0));
        }
        if (isPhysicalCooling) {
            info.setIs_pcool(String.valueOf(1));
        } else {
            info.setIs_pcool(String.valueOf(0));
        }

        requestBean.setInfo(info);

        dbHisBusiness.patientSignsDo(requestBean, response -> {
            SignsDoResponseBean responseBean = BeanKit.string2Bean(response, SignsDoResponseBean.class);
            if (responseBean.getRet() == Constants.MESSAGE_SUCCESS_CODE) {
                Toast.makeText(this, getString(R.string.request_success_tip), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, BedInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("patientInfo", JSON.toJSONString(data));
                bundle.putInt("fragmentIndex", BedInfoActivity.FRAG_VITAL_SIGN_INDEX);
                intent.putExtras(bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else {
                Toast.makeText(this, getString(R.string.request_fail_tip), Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            Toast.makeText(this, getString(R.string.request_fail_tip), Toast.LENGTH_SHORT).show();
        });
    }

    public void inBedListActivity(View view) {
        Intent intent = new Intent(this, BedListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
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

    private void scanFail() {
        showErrorDialog();
    }

    private void scanSuccess() {
        AnimateBusiness.slideToggle(successButtonBar, 40, Constants.SLIDE_DURATION_MS, Constants.SLIDE_DURATION_MS);
        scanSuccessTip.setText(getString(R.string.scan_success_tip2));
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
}
