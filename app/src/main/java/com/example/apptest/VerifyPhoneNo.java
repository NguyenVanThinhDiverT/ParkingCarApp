//package com.example.apptest;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.arch.core.executor.TaskExecutor;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.android.gms.tasks.TaskExecutors;
//import com.google.android.material.textfield.TextInputLayout;
//import com.google.firebase.FirebaseException;
//import com.google.firebase.FirebaseTooManyRequestsException;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
//import com.google.firebase.auth.PhoneAuthCredential;
//import com.google.firebase.auth.PhoneAuthOptions;
//import com.google.firebase.auth.PhoneAuthProvider;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import java.util.concurrent.TimeUnit;
//
//public class VerifyPhoneNo extends AppCompatActivity {
//    FirebaseAuth mAuth;
//    String verificationCodeBySystem;
//    Button verify_btn;
//    EditText phoneNoEnteredByTheUser;
//    ProgressBar progressBar;
//
//    TextInputLayout regName, regUsername, regEmail, regPhoneNo,regPassword;
//    Button regBtn, regToLoginBtn;
//
//    FirebaseDatabase rootNode;
//    DatabaseReference reference;
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_verify_phone_no);
//
//        verify_btn = findViewById(R.id.verify_btn);
//        phoneNoEnteredByTheUser = findViewById(R.id.verification_code_entered_by_user);
//        progressBar = findViewById(R.id.progress_bar);
//
//        progressBar.setVisibility(View.GONE);
//
//        mAuth = FirebaseAuth.getInstance();
//        String phoneNo = getIntent().getStringExtra("phoneNo");
//
//        sendVerificationCodeToUser(phoneNo);
//
//        rootNode = FirebaseDatabase.getInstance();
//        regName = findViewById(R.id.reg_name);
//        regUsername = findViewById(R.id.reg_username);
//        regEmail = findViewById(R.id.reg_email);
//        regPhoneNo = findViewById(R.id.reg_phoneNo);
//        regPassword = findViewById(R.id.reg_password);
//        regBtn = findViewById(R.id.reg_btn);
//        regToLoginBtn = findViewById(R.id.reg_login_btn);
//
//        verify_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String code = phoneNoEnteredByTheUser.getText().toString();
//                if (code.isEmpty() || code.length() < 6) {
//                    phoneNoEnteredByTheUser.setError("Wrong OTP...");
//                    phoneNoEnteredByTheUser.requestFocus();
//                    return;
//                }
//                progressBar.setVisibility(View.VISIBLE);
//                verifyCode(code);
//            }
//        });
//
//    }
//
//
//    private void sendVerificationCodeToUser(String phoneNo) {
//        // [START start_phone_auth]
//        PhoneAuthOptions options =
//                PhoneAuthOptions.newBuilder(mAuth)
//                        .setPhoneNumber("+84" + phoneNo)       // Phone number to verify
//                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
//                        .setActivity(this)                 // Activity (for callback binding)
//                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
//                        .build();
//        PhoneAuthProvider.verifyPhoneNumber(options);
//        // [END start_phone_auth]
//    }
//
//
//    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//        @Override
//        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//            super.onCodeSent(s, forceResendingToken);
//            //Get the code in global variable
//            verificationCodeBySystem = s;
//        }
//
//        @Override
//        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
//            final String code = phoneAuthCredential.getSmsCode();
//            if (code != null) {
//                progressBar.setVisibility(View.VISIBLE);
//                verifyCode(code);
//            }
//        }
//
//        @Override
//        public void onVerificationFailed(FirebaseException e) {
//            Toast.makeText(VerifyPhoneNo.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    };
//
//    private void verifyCode(String codeByUser) {
//        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCodeBySystem, codeByUser);
//        signInTheUserByCredentials(credential);
//    }
//    private void signInTheUserByCredentials(PhoneAuthCredential credential) {
//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//        firebaseAuth.signInWithCredential(credential)
//                .addOnCompleteListener(VerifyPhoneNo.this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//
//                            Toast.makeText(VerifyPhoneNo.this, "Your Account has been created successfully!", Toast.LENGTH_SHORT).show();
//
////                            reference = rootNode.getReference("users");
////
////                            String name = regName.getEditText().getText().toString();
////                            String username = regUsername.getEditText().getText().toString();
////                            String email = regEmail.getEditText().getText().toString();
////                            String phoneNo = regPhoneNo.getEditText().getText().toString();
////                            String password = regPassword.getEditText().getText().toString();
////
////                            UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNo, password);
////                            reference.child(username).setValue(helperClass);
//
//                            Intent intent = new Intent(getApplicationContext(), Login.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                            startActivity(intent);
//
//                        } else {
//                            Toast.makeText(VerifyPhoneNo.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }
//
//}
//
