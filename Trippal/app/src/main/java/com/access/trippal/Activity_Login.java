package com.access.trippal;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Activity_Login extends AppCompatActivity {
    private enum LOGIN_STATE {
        ENTERING_NUMBER,
        ENTERING_CODE,
    }

    private ProgressDialog dialog;
    TextView main_BTN_continue_txt;
    private CardView main_BTN_continue;
    private TextInputLayout main_EDT_phone;

    private LOGIN_STATE login_state = LOGIN_STATE.ENTERING_NUMBER;
    private String phoneInput = "";
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    // Declare verificationId at the class level
    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(Activity_Login.this, Activity_Search_Places.class));
            finish();
        }

        findViews();
        initViews();
        updateUI();
//        main_EDT_phone.setError("Wrong number.");
    }

    private void continueClicked() {
        if (login_state == LOGIN_STATE.ENTERING_NUMBER) {
            startLoginProcess();
        } else if (login_state == LOGIN_STATE.ENTERING_CODE) {
            codeEntered();
        }
    }

    private void codeEntered() {
        dialog.setTitle("Processing");
        dialog.setCancelable(false);
        dialog.setMessage("Please wait");
        dialog.show();

        String smsVerificationCode = main_EDT_phone.getEditText().getText().toString().trim();
        Log.d("pttt", "smsVerificationCode: " + smsVerificationCode);

        // Use the stored verificationId to create the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, smsVerificationCode);

        // Sign in with the credential
        signInWithPhoneAuthCredential(credential);
    }

    private void startLoginProcess() {
        phoneInput = main_EDT_phone.getEditText().getText().toString();
        Log.d("pttt", "phoneInput:" + phoneInput);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber(phoneInput)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(onVerificationStateChangedCallbacks)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            Log.d("pttt", "onCodeSent: " + verificationId);
            // Store the verificationId for later use
            Activity_Login.this.verificationId = verificationId;
            login_state = LOGIN_STATE.ENTERING_CODE;
            updateUI();
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            Log.d("pttt", "onVerificationCompleted");
            signInWithPhoneAuthCredential(phoneAuthCredential);
        }

        @Override
        public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
            Log.d("pttt", "onCodeAutoRetrievalTimeOut " + s);
            super.onCodeAutoRetrievalTimeOut(s);
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Log.d("pttt", "onVerificationFailed: " + e.getMessage());
            e.printStackTrace();
            Toast.makeText(Activity_Login.this, "VerificationFailed " + e.getMessage(), Toast.LENGTH_SHORT).show();
            login_state = LOGIN_STATE.ENTERING_NUMBER;
            dialog.dismiss();
            updateUI();
        }
    };

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        dialog.dismiss(); // Close the dialog on completion

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("pttt", "signInWithCredential:success");

                            FirebaseUser user = task.getResult().getUser();
                            userSignedIn();

                        } else {
                            // Sign in failed, display a message and update the UI
                            Log.w("pttt", "signInWithCredential:failure", task.getException());
                            Toast.makeText(Activity_Login.this, "Sign-in failed", Toast.LENGTH_SHORT).show();

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(Activity_Login.this, "Invalid verification code", Toast.LENGTH_SHORT).show();
                            }

                            updateUI();
                        }
                    }
                });
    }

    private void userSignedIn() {
        Intent myIntent = new Intent(this, Activity_Search_Places.class);
        startActivity(myIntent);
        finish();
    }

    private void updateUI() {
        if (login_state == LOGIN_STATE.ENTERING_NUMBER) {
            main_EDT_phone.getEditText().setText("+972501111111");
            main_EDT_phone.setHint(getString(R.string.phone_number));
            main_EDT_phone.setPlaceholderText("+972 55 1234567");
            main_BTN_continue_txt.setText(getString(R.string.continue_));
        } else if (login_state == LOGIN_STATE.ENTERING_CODE) {
            main_EDT_phone.getEditText().setText("");
            main_EDT_phone.setHint(getString(R.string.enter_code));
            main_EDT_phone.setPlaceholderText("******");
            main_BTN_continue_txt.setText(getString(R.string.login));
        }
    }

    private void initViews() {
        main_BTN_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueClicked();
            }
        });
    }

    private void findViews() {
        main_BTN_continue = findViewById(R.id.main_BTN_continue);
        main_EDT_phone = findViewById(R.id.main_EDT_phone);
        main_BTN_continue_txt = findViewById(R.id.main_BTN_continue_txt);
        dialog = new ProgressDialog(this);
    }
}
