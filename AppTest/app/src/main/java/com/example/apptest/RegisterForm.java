package com.example.apptest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.UUID;

public class RegisterForm extends AppCompatActivity {
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    ProgressDialog dialog;
    TextInputLayout DriverName, DriverNumber, NumberPlate;
    String DRIVERNAME, DRIVERNUMBER, NUMBERPLATE;
    Button btnPark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);
        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();
        dialog = new ProgressDialog(this);

        DriverName = findViewById(R.id.driverName);
        DriverNumber = findViewById(R.id.driverNumber);
        NumberPlate = findViewById(R.id.numberPlate);
        btnPark = findViewById(R.id.ParkBtn);

        btnPark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String driverName   = DriverName.getEditText().getText().toString();
                String driverNumber = DriverNumber.getEditText().getText().toString();
                String numberPlate  = NumberPlate.getEditText().getText().toString();
                long time = Calendar.getInstance().getTimeInMillis();
                String id= UUID.randomUUID().toString();
                ParkCarModel parkCarModel = new ParkCarModel();
                parkCarModel.setId(id);
                parkCarModel.setNumberPlate(numberPlate);
                parkCarModel.setDriverName(driverName);
                parkCarModel.setTime(time);
                parkCarModel.setDriverNumber(driverNumber);
                parkCarModel.setUserId(fAuth.getCurrentUser().getUid());
                parkCarModel.setStatus("PAID");

                dialog.setTitle("Uploading");
                dialog.setMessage("Data to the database");
                dialog.show();
                fStore.collection("parking")
                        .document(id)
                        .set(parkCarModel)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                dialog.cancel();
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                dialog.cancel();
                                Toast.makeText(RegisterForm.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
                RegisterForm();
            }

        });

    }
    public void RegisterForm(){
        Intent intent = new Intent(getApplicationContext(), BookingActivity.class);
        startActivity(intent);
    }

}