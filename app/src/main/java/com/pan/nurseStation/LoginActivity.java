package com.pan.nurseStation;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.pan.lib.util.BeanKit;
import com.pan.nurseStation.config.Constants;
import com.pan.nurseStation.bean.request.LoginRequestBean;
import com.pan.nurseStation.bean.response.LoginResponseBean;
import com.pan.nurseStation.business.DBHisBusiness;
import com.umeng.analytics.MobclickAgent;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();

    // UI references.
    private TextInputEditText mUsernameView;
    private TextInputEditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mUsernameView = findViewById(R.id.username);

        mPasswordView = findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener((TextView textView, int id, KeyEvent keyEvent) -> {
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin();
                return true;
            }
            return false;
        });

        Button mEmailSignInButton = findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(view -> attemptLogin());

//        Log.d(TAG, "onCreate: " + InfoKit.getScreenMetrics(this));
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mUsernameView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
//        if (TextUtils.isEmpty(password)) {
//            mPasswordView.setError(getString(R.string.error_invalid_password));
//            focusView = mPasswordView;
//            cancel = true;
//        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.

            // 模拟内网环境下登录成功
//            if (Constants.ISDEBUG) {
//                if (Objects.equals(username, "admin") && Objects.equals(password, "123456")) {
//                    // success
//                    loginSuccess();
//                    return;
//                }
//            }


            LoginRequestBean requestBean = new LoginRequestBean(username, password);
            DBHisBusiness dbHisBusiness = new DBHisBusiness();
            dbHisBusiness.login(requestBean, response -> {
                        Log.i(TAG, "onResponse: " + response);
//                    try {
//                        JSONObject jsonObject = new JSONObject(response);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }

                        LoginResponseBean responseBean = BeanKit.string2Bean(response, LoginResponseBean.class);
                        DBHisBusiness.loginBean = responseBean.getData();

                        if (Constants.MESSAGE_SUCCESS_CODE == responseBean.getRet()) {
                            loginSuccess();
                        } else {
                            loginFail();
                        }
                    },
                    (VolleyError error) -> {
                        Log.e(TAG, "onResponse: " + error.toString(), error);
                        loginFail();
                    });
        }
    }

    public void loginSuccess() {
        Toast.makeText(LoginActivity.this, R.string.login_success_tip, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(LoginActivity.this, BedListActivity.class));
    }

    public void loginFail() {
        mPasswordView.setError(getString(R.string.error_incorrect_password));
        mPasswordView.requestFocus();
    }

    @Override
    public void onResume() {
        MobclickAgent.onResume(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        MobclickAgent.onPause(this);
        super.onPause();
    }


}

