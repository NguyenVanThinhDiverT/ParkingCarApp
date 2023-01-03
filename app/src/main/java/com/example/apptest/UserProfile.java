package com.example.apptest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserProfile extends AppCompatActivity {

    TextInputLayout fullName,email,phoneNo,password;
    TextView fullNameLabel,usernameLabel;
    TextView userName;
    String _USERNAME, _NAME, _EMAIL, _PHONENO, _PASSWORD;
    Button BookingBtn;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    DatabaseReference reference;
    Button logout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_user_profile);
        fAuth = FirebaseAuth.getInstance();

        reference = FirebaseDatabase.getInstance().getReference("users");

        //Hooks
//        userName = findViewById(R.id.username);
        fullName = findViewById(R.id.full_name_profile);
        email = findViewById(R.id.email_profile);
        phoneNo = findViewById(R.id.phone_no_profile);
        password = findViewById(R.id.password_profile);
        fullNameLabel = findViewById(R.id.fullname_field);
        usernameLabel = findViewById(R.id.username_field);
        BookingBtn = findViewById(R.id.BookingBtn);
        logout = findViewById(R.id.logout);


        //showAllData
        showAllUserData();
        BookingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Booking();
            }
        });
        BottomNavigationView nav = (BottomNavigationView)findViewById(R.id.navigation);
        nav.setSelectedItemId(R.id.UserProfile);
        nav.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        CheckUser();
//                        startActivity(new Intent(getApplicationContext(),BookingActivity.class));
//                        finish();

                        return;
                    case R.id.UserProfile:



                }
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogOut();
            }
        });
//        CheckUser();

//                (phoneNo.getEditText().getText().toString());

//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                Intent intent = getIntent();//                final String userEnteredUsername = name.getEditText().getText().toString().trim();
//////                Query checkUser = reference.orderByChild("username").equalTo()
////                String nameFromDB = snapshot.child("username").child("name").getValue(String.class);
////                String usernameFromDB = snapshot.child("username").child("username").getValue(String.class);
////                String phoneNoFromDB = snapshot.child("username").child("phoneNo").getValue(String.class);
////                String emailFromDB = snapshot.child("username").child("email").getValue(String.class);
////                String passwordFromDB = snapshot.child("username").child("password").getValue(String.class);
////
////                intent.putExtra("name", nameFromDB);
////                intent.putExtra("username", usernameFromDB);
////                intent.putExtra("email", emailFromDB);
////                intent.putExtra("phoneNo", phoneNoFromDB);
////                intent.putExtra("password", passwordFromDB);
////                UserHelperClass helperClass = new UserHelperClass(name, username, email, phoneNo, password);
////                reference.child(username).setValue(helperClass);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(UserProfile.this,"No change",Toast.LENGTH_SHORT).show();
//            }
//        });
//        ValueEventListener postListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Login post = snapshot.getValue(Login.class);
////                fullNameLabel.setText(post.getText().toString().trim());
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(UserProfile.this,"Can't update information",Toast.LENGTH_SHORT).show();
//            }
//        };
    }

    private void LogOut() {
        Intent intent = new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
    }

    public void Booking(){
//        Toast.makeText(this, "Booking Interface", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(getApplicationContext(), BookingActivity.class);
        startActivity(intent);

    }


    private void showAllUserData() {
        Intent intent = getIntent();
         _USERNAME = intent.getStringExtra("username");
         _NAME = intent.getStringExtra("name");
         _EMAIL = intent.getStringExtra("email");
         _PHONENO = intent.getStringExtra("phoneNo");
         _PASSWORD = intent.getStringExtra("password");


         fullNameLabel.setText(_NAME);
        usernameLabel.setText(_USERNAME);
//        userName.setText(_USERNAME);
        fullName.getEditText().setText(_NAME);
        email.getEditText().setText(_EMAIL);
        phoneNo.getEditText().setText(_PHONENO);
        password.getEditText().setText(_PASSWORD);

//        showAllUserData();

    }

    public void update(View view){
        if(isNameChanged() || isPasswordChanged() || isEmailChanged() || isphoneNoChanged()){
            Toast.makeText(this, "Data has been updated", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Data is cannot be updated", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isPasswordChanged() {
        if(!_PASSWORD.equals(password.getEditText().getText().toString())){

            reference.child(_USERNAME).child("password").setValue(password.getEditText().getText().toString());
            _PASSWORD = password.getEditText().getText().toString();
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isNameChanged() {
        if(!_NAME.equals(fullName.getEditText().getText().toString())){

            reference.child(_USERNAME).child("name").setValue(fullName.getEditText().getText().toString());
            _NAME = fullName.getEditText().getText().toString();

            return true;
        }
        else{
            return false;
        }
    }
    private boolean isphoneNoChanged() {
        if(!_PHONENO.equals(phoneNo.getEditText().getText().toString())){

            reference.child(_USERNAME).child("phoneNo").setValue(phoneNo.getEditText().getText().toString());
            _PHONENO = phoneNo.getEditText().getText().toString();

            return true;
        }
        else{
            return false;
        }
    }
    private boolean isEmailChanged() {
        if(!_EMAIL.equals(email.getEditText().getText().toString())){

            reference.child(_USERNAME).child("email").setValue(email.getEditText().getText().toString());
            _EMAIL = email.getEditText().getText().toString();

            return true;
        }
        else{
            return false;
        }
    }

    private void CheckUser() {
//        final String userEnteredUsername = username.getEditText().getText().toString().trim();
//        final String userEnteredPassword = password.getEditText().getText().toString().trim();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        Query checkUser = reference.orderByChild("username").equalTo(_USERNAME);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
//                    username.setError(null);
//                    username.setErrorEnabled(false);

                    String passwordFromDB = snapshot.child(_USERNAME).child("password").getValue(String.class);

//                    if(passwordFromDB.equals(userEnteredPassword)){

                        String nameFromDB = snapshot.child(_USERNAME).child("name").getValue(String.class);
                        String usernameFromDB = snapshot.child(_USERNAME).child("username").getValue(String.class);
                        String phoneNoFromDB = snapshot.child(_USERNAME).child("phoneNo").getValue(String.class);
                        String emailFromDB = snapshot.child(_USERNAME).child("email").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(), BookingActivity.class);

                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("username", usernameFromDB);
                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("phoneNo", phoneNoFromDB);
                        intent.putExtra("password", passwordFromDB);

                        startActivity(intent);


////                    }
//                    else{
////                            ProgressBar.setVisibility(View.GONE);
//                        password.setError("Wrong Password");
//                        password.requestFocus();
//                    }
                }
                else{
//                    username.setError("No such User exist");
//                    username.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(Login.this, error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
}