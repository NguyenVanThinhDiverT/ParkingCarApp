package com.example.apptest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.UUID;

public class ParkCarActivity extends AppCompatActivity {
    TextInputLayout DriverName_1, DriverNumber_1, NumberPlate_1, Amount_1, VehicleType_1;
    Button ParkCarBtn_1;
    String DriverName_2, DriverNumber_2, NumberPlate_2, Amount_2, VehicleType_2;
    long time;
    String id;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_park_car);
        fAuth=FirebaseAuth.getInstance();
        fStore=FirebaseFirestore.getInstance();

        DriverName_1 = findViewById(R.id.driverName);
        DriverNumber_1 = findViewById(R.id.driverNumber);
        NumberPlate_1 = findViewById(R.id.numberPlate);
        Amount_1 = findViewById(R.id.Amount);
        VehicleType_1 = findViewById(R.id.vehicleType);
        ParkCarBtn_1 = findViewById(R.id.parkCarBtn);
        rootNode = FirebaseDatabase.getInstance();


//        showdata();
        ParkCarBtn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParkAVehicle();
            }
        });

    }

    public void ParkAVehicle() {
        showdata();
        Toast.makeText(this,"Success Register", Toast.LENGTH_SHORT) .show();
        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
        startActivity(intent);
    }

    public void showdata() {

        DriverName_2 = DriverName_1.getEditText().getText().toString();
        DriverNumber_2 = DriverNumber_1.getEditText().getText().toString();
        NumberPlate_2 = NumberPlate_1.getEditText().getText().toString();
        VehicleType_2 = VehicleType_1.getEditText().getText().toString();
        Amount_2 = Amount_1.getEditText().getText().toString();

        time = Calendar.getInstance().getTimeInMillis();
        id= UUID.randomUUID().toString();

        ParkCarModel parkCarModel = new ParkCarModel();
        parkCarModel.setId(id);
//        parkCarModel.setNumberPlate(NumberPlate_2);
//        parkCarModel.setDriverName(DriverName_2);
        parkCarModel.setTime(time);
//        parkCarModel.setVehicleType(VehicleType_2);
//        parkCarModel.setFee(Amount_2);
//        parkCarModel.setDriverNumber(DriverNumber_2);
        parkCarModel.setUserId(fAuth.getCurrentUser().getUid());
        parkCarModel.setStatus("Parked");

    }
}
