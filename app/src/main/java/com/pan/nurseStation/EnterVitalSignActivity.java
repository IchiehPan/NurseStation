package com.pan.nurseStation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.pan.lib.util.BeanKit;
import com.pan.nurseStation.adapter.SimpleSpinnerAdapter;
import com.pan.nurseStation.bean.response.PatientDetailResponseBean;

import java.util.Objects;

public class EnterVitalSignActivity extends AppCompatActivity {
    private static final String TAG = EnterVitalSignActivity.class.getSimpleName();
    private Spinner dateSpinner;
    private Spinner timeSpinner;
    private ScrollView scrollView;
    private RadioGroup temperatureGroup;
    private RadioGroup pulseGroup;
    private RadioButton temperatureButton;
    private RadioButton pulseButton;
    private EditText temperatureEditText;
    private EditText pulseEditText;
    private EditText breatheEditText;
    private EditText bloodPressureEditText;
    private EditText stoolFrequencyEditText;
    private EditText urineVolumeEditText;
    private EditText inputVolumeEditText;
    private EditText weightEditText;
    private EditText otherEditText;
    private CheckBox assistedBreatheCheckBox;
    private TextView patientName;
    private ImageView patientSex;
    private TextView patientAge;
    private TextView hosNumber;
    private TextView departmentName;
    private TextView bedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_vital_sign);

        Bundle bundle = getIntent().getExtras();
        String patientInfo = bundle.getString("patientInfo");
        PatientDetailResponseBean.Data data = BeanKit.string2Bean(patientInfo, PatientDetailResponseBean.Data.class);

        initView();
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


        String[] dateData = {"2018-02-01", "2018-02-02"};
        SimpleSpinnerAdapter<String> arrayAdapter1 = new SimpleSpinnerAdapter<>(this, R.layout.item_simple_spinner, android.R.id.text1, dateData);
        arrayAdapter1.setDropDownViewResource(R.layout.item_simple_spinner_dropdown);
        arrayAdapter1.setSpaceDistance(30);
        dateSpinner.setAdapter(arrayAdapter1);

        String[] timeData = {"08：00", "10：00"};
        SimpleSpinnerAdapter<String> arrayAdapter2 = new SimpleSpinnerAdapter<>(this, R.layout.item_simple_spinner, android.R.id.text1, timeData);
        arrayAdapter2.setDropDownViewResource(R.layout.item_simple_spinner_dropdown);
        arrayAdapter2.setSpaceDistance(30);
        timeSpinner.setAdapter(arrayAdapter2);
    }

    private void initView() {
        dateSpinner = findViewById(R.id.date_spinner);
        timeSpinner = findViewById(R.id.time_spinner);
        scrollView = findViewById(R.id.scroll_view);

        scrollView.addView(View.inflate(this, R.layout.view_form_table, null));

        temperatureGroup = findViewById(R.id.radio_temperature);
        pulseGroup = findViewById(R.id.pulse_radio);
        temperatureButton = findViewById(temperatureGroup.getCheckedRadioButtonId());
        pulseButton = findViewById(pulseGroup.getCheckedRadioButtonId());
        temperatureEditText = findViewById(R.id.temperature);
        pulseEditText = findViewById(R.id.pulse);
        breatheEditText = findViewById(R.id.breathe);
        bloodPressureEditText = findViewById(R.id.blood_pressure);
        stoolFrequencyEditText = findViewById(R.id.stool_frequency);
        urineVolumeEditText = findViewById(R.id.urine_volume);
        inputVolumeEditText = findViewById(R.id.input_volume);
        weightEditText = findViewById(R.id.weight);
        otherEditText = findViewById(R.id.other);
        assistedBreatheCheckBox = findViewById(R.id.assisted_breathe);

        patientName = findViewById(R.id.patient_name);
        patientSex = findViewById(R.id.patient_sex);
        patientAge = findViewById(R.id.patient_age);
        hosNumber = findViewById(R.id.hos_number);
        departmentName = findViewById(R.id.department_name);
        bedId = findViewById(R.id.bed_id);
    }

    public void resetForm(View view) {
        temperatureGroup.clearCheck();
        pulseGroup.clearCheck();
        temperatureEditText.setText("");
        pulseEditText.setText("");
        breatheEditText.setText("");
        bloodPressureEditText.setText("");
        stoolFrequencyEditText.setText("");
        urineVolumeEditText.setText("");
        inputVolumeEditText.setText("");
        weightEditText.setText("");
        otherEditText.setText("");
        assistedBreatheCheckBox.setChecked(false);
    }

    public void submitForm(View view) {
        //
        String temperatureType = temperatureButton.getText().toString();
        String pulseType = pulseButton.getText().toString();
        String temperature = temperatureEditText.getText().toString();
        String pulse = pulseEditText.getText().toString();
        String breathe = breatheEditText.getText().toString();
        String bloodPressure = bloodPressureEditText.getText().toString();
        String stoolFrequency = stoolFrequencyEditText.getText().toString();
        String urineVolume = urineVolumeEditText.getText().toString();
        String inputVolume = inputVolumeEditText.getText().toString();
        String weight = weightEditText.getText().toString();
        String other = otherEditText.getText().toString();
        boolean isAssistedBreathe = assistedBreatheCheckBox.isChecked();

        Log.i(TAG, "submitForm: temperatureType=" + temperatureType);
        Log.i(TAG, "submitForm: pulseType=" + pulseType);
        Log.i(TAG, "submitForm: temperature=" + temperature);
        Log.i(TAG, "submitForm: pulse=" + pulse);
        Log.i(TAG, "submitForm: breathe=" + breathe);
        Log.i(TAG, "submitForm: bloodPressure=" + bloodPressure);
        Log.i(TAG, "submitForm: stoolFrequency=" + stoolFrequency);
        Log.i(TAG, "submitForm: urineVolume=" + urineVolume);
        Log.i(TAG, "submitForm: inputVolume=" + inputVolume);
        Log.i(TAG, "submitForm: weight=" + weight);
        Log.i(TAG, "submitForm: other=" + other);
        Log.i(TAG, "submitForm: isAssistedBreathe=" + isAssistedBreathe);


    }

    public void backToList(View view) {
        Intent intent = new Intent(this, BedListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
