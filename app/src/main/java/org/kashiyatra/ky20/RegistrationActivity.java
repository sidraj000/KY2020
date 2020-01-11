package org.kashiyatra.ky20;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * A login screen that offers login via email/password.
 */
public class RegistrationActivity extends AppCompatActivity {


    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private AutoCompleteTextView mNameView;
    private AutoCompleteTextView mCollegeView;
    private AutoCompleteTextView mMobileNumberView;
    private AutoCompleteTextView mReferralCodeView;
    private EditText mPasswordView;
    private EditText mConfirmPasswordView;
    private Spinner mGenderView;
    private Spinner mYearView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        // Set up the login form.
        mEmailView = findViewById(R.id.email);
        mNameView = findViewById(R.id.name);
        mCollegeView = findViewById(R.id.college_name);
        mMobileNumberView = findViewById(R.id.mobile_number);
        mReferralCodeView = findViewById(R.id.referral_code);
        mPasswordView = findViewById(R.id.password);
        mConfirmPasswordView = findViewById(R.id.confirm_password);
        mGenderView = findViewById(R.id.gender_view);
        mYearView = findViewById(R.id.year_view);

        Button mEmailSignInButton = findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegistration();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        if (email != null) {
            mEmailView.setText(email);
            mNameView.requestFocus();
        }
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptRegistration() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mNameView.setError(null);
        mCollegeView.setError(null);
        mMobileNumberView.setError(null);
        mReferralCodeView.setError(null);
        mPasswordView.setError(null);
        mConfirmPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String fullName = mNameView.getText().toString();
        String collegeName = mCollegeView.getText().toString();
        String mobileNumber = mMobileNumberView.getText().toString();
        String referralCode = mReferralCodeView.getText().toString();
        String password = mPasswordView.getText().toString();
        String confirmPassword = mConfirmPasswordView.getText().toString();
        String gender = mGenderView.getSelectedItem().toString().toLowerCase();
        String year = String.valueOf(mYearView.getSelectedItemPosition() + 1);

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!confirmPassword.equals(password)) {
            mConfirmPasswordView.setError("Password does not match");
            focusView = mConfirmPasswordView;
            cancel = true;
        }

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password) || password.length() < 6) {
            mPasswordView.setError("Atleast 6 characters");
            focusView = mPasswordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(mobileNumber)) {
            mMobileNumberView.setError(getString(R.string.error_field_required));
            focusView = mMobileNumberView;
            cancel = true;
        } else if (mobileNumber.length() != 10) {
            mMobileNumberView.setError("Invalid Mobile Number");
            focusView = mMobileNumberView;
            cancel = true;
        }

        if (TextUtils.isEmpty(collegeName)) {
            mCollegeView.setError(getString(R.string.error_field_required));
            focusView = mCollegeView;
            cancel = true;
        }

        if (TextUtils.isEmpty(fullName)) {
            mNameView.setError(getString(R.string.error_field_required));
            focusView = mNameView;
            cancel = true;
        }

        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!email.contains("@") || !email.contains(".")) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(email, fullName, collegeName, mobileNumber, referralCode, password, gender, year);
            mAuthTask.execute((Void) null);
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, JSONObject> {

        private final String mEmail, mName, mCollege, mMobile, mReferralCode, mPassword, mGender, mYear;

        UserLoginTask(String email, String name, String college, String mobile, String referralCode, String password, String gender, String year) {
            mEmail = email;
            mName = name;
            mCollege = college;
            mMobile = mobile;
            mReferralCode = referralCode;
            mPassword = password;
            mGender = gender;
            mYear = year;
        }

        @Override
        protected JSONObject doInBackground(Void... params) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("email", mEmail)
                        .put("secret", getResources().getString(R.string.app_secret))
                        .put("name", mName)
                        .put("pass1", mPassword)
                        .put("ref", mReferralCode)
                        .put("gender", mGender)
                        .put("college", mCollege)
                        .put("year", mYear)
                        .put("mobile_number", mMobile);

                Log.e("Registration Request", jsonObject.toString());
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url("https://kashiyatra.herokuapp.com/api/mobile/register/")
                        .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonObject.toString()))
                        .build();
                Response response = client.newCall(request).execute();
                Log.e("statusCode", String.valueOf(response.code()));
                JSONObject jsonResponse = new JSONObject();
                jsonResponse.put("status_code", response.code());

//                Bitmap profilePic = BitmapFactory.decodeStream(new URL(jsonResponse.getString("profile_picture")).openConnection().getInputStream());
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                profilePic.compress(Bitmap.CompressFormat.PNG, 100, baos);
//                byte[] b = baos.toByteArray();
//                String propic = Base64.encodeToString(b, Base64.DEFAULT);
//                jsonResponse.put("profile_picture_string", propic);

                return jsonResponse;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new JSONObject();
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            mAuthTask = null;
            showProgress(false);
            int statusCode;
            try {
                statusCode = jsonObject.getInt("status_code");
            } catch (JSONException je) {
                statusCode = -1;
            }
            switch (statusCode) {
                case 200:
                    Toast.makeText(getApplicationContext(), "Confirmation Mail has been sent", Toast.LENGTH_SHORT).show();
                    finish();
                    break;
                case 403:
                    Toast.makeText(getApplicationContext(), "Email already in use!", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(getApplicationContext(), "Registration failed. Please sample again later", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

