package com.example.apptest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUp extends AppCompatActivity {
    TextInputLayout regName, regUsername, regEmail, regPhoneNo,regPassword;
    Button regBtn, regToLoginBtn;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    String userID;
    String name, username, email, phoneNo, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //Hooks to all xml elements in activity_sign_up.xml
        regName = findViewById(R.id.reg_name);
        regUsername = findViewById(R.id.reg_username);
        regEmail = findViewById(R.id.reg_email);
        regPhoneNo = findViewById(R.id.reg_phoneNo);
        regPassword = findViewById(R.id.reg_password);
        regBtn = findViewById(R.id.reg_btn);
        regToLoginBtn = findViewById(R.id.reg_login_btn);

        rootNode = FirebaseDatabase.getInstance();
        //Save data in FireBase on button click
//        regBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                rootNode = FirebaseDatabase.getInstance();
//                reference = rootNode.getReference("users");
//                //Get all the values
//                String name = regName.getEditText().getText().toString();
//                String username = regUsername.getEditText().getText().toString();
//                String email = regEmail.getEditText().getText().toString();
//                String phoneNo = regPhoneNo.getEditText().getText().toString();
//                String password = regPassword.getEditText().getText().toString();
//                UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNo, password);
//                reference.child(username).setValue(helperClass);
//            }
//        });//Register Button method end

    }//onCreate Method End


    private Boolean validateName() {
        String val = regName.getEditText().getText().toString();
        if (val.isEmpty()) {
            regName.setError("Field cannot be empty");
            return false;
        }
        else {
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername() {
        String val = regUsername.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if (val.isEmpty()) {
            regUsername.setError("Field cannot be empty");
            return false;
        }
        else if (val.length() >= 25) {
            regUsername.setError("Username too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            regUsername.setError("White Spaces are not allowed");
            return false;
        }
        else {
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail() {
        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            regEmail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            regEmail.setError("Invalid email address");
            return false;
        } else {
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePhoneNo() {
        String val = regPhoneNo.getEditText().getText().toString();
        if (val.isEmpty()) {
            regPhoneNo.setError("Field cannot be empty");
            return false;
        } else {
            regPhoneNo.setError(null);
            regPhoneNo.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword() {
        String val = regPassword.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (val.isEmpty()) {
            regPassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            regPassword.setError("Password is too weak");
            return false;
        } else {
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }

    public void registerUser(View view){
        if(!validateName() | !validatePassword() | !validatePhoneNo() | !validateEmail() | !validateUsername())
        {
            return;
        }

        reference = rootNode.getReference("users");

         name = regName.getEditText().getText().toString();
         username = regUsername.getEditText().getText().toString();
         email = regEmail.getEditText().getText().toString();
         phoneNo = regPhoneNo.getEditText().getText().toString();
         password = regPassword.getEditText().getText().toString();

//        Intent intent = new Intent(getApplicationContext(),VerifyPhoneNo.class);
//        intent.putExtra("phoneNo",phoneNo);
//        startActivity(intent);


        UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNo, password);
        reference.child(username).setValue(helperClass);

        Toast.makeText(this,"Your Account has been created successfully!", Toast.LENGTH_SHORT) .show();
//
        Intent intent2 = new Intent(getApplicationContext(), Login.class);
        startActivity(intent2);
//        finish();

    }



}