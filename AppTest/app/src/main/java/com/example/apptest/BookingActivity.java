package com.example.apptest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingActivity extends AppCompatActivity
          implements View.OnClickListener
//        implements CarAdapterInterface
{
    String DRIVERNAME, DRIVERNUMBER, NUMBERPLATE, DATE, STATUS;
    public CardView card1, card2, card3, card4;
    public Button buttonregister, buttonupdate;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    TextInputLayout DriverName, DriverNumber, NumberPlate, Date, Status;
    CarsAdapter adapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        card1 = (CardView) findViewById(R.id.c1);
        card2 = (CardView) findViewById(R.id.c2);
        card3 = (CardView) findViewById(R.id.c3);
        card4 = (CardView) findViewById(R.id.c4);
//        buttonregister = (Button) findViewById(R.id.registerVehicle);
//        DriverName = findViewById(R.id.driverName);
//        DriverNumber = findViewById(R.id.driverNumber);
//        NumberPlate = findViewById(R.id.numberPlate);
//        Date = findViewById(R.id.Date);
//        Status = findViewById(R.id.Status);

//        showUserData();
//        buttonupdate = findViewById(R.id.btnupdate);

//        buttonupdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                 DRIVERNAME = DriverName.getEditText().getText().toString();
////                 DRIVERNUMBER = DriverNumber.getEditText().getText().toString();
////                 NUMBERPLATE = NumberPlate.getEditText().getText().toString();
////                updateinf();
//            }
//        });

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
//        buttonregister.setOnClickListener(this);
//        loaddata();


    }

    private void showUserData(){
        fStore.collection("parking")
                .whereEqualTo("userId",fAuth.getCurrentUser().getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> dsList=queryDocumentSnapshots.getDocuments();

                        for(DocumentSnapshot ds:dsList){
                            ParkCarModel model=ds.toObject(ParkCarModel.class);
                            if(model.getStatus().equals("PARKED")){
//                                String fee = model.getFee();
//                                parked = parked + Integer.parseInt(fee);
                            }

                            if(model.getStatus().equals("PAID")){
//                                String fee = model.getFee();
//                                paid = paid + Integer.parseInt(fee);
                            }
                            adapter.add(model);
                        }
//                        binding.paid.setText(paid+"");
//                        binding.pending.setText(parked+"");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(BookingActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        ProgressDialog progressDialo = new ProgressDialog(this);
        progressDialo.setTitle("Wait");
        progressDialo.setMessage("processing");

        progressDialo.show();
        if(fAuth.getCurrentUser()==null){
            fAuth.signInAnonymously()
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            progressDialo.cancel();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialo.cancel();
                            Toast.makeText(BookingActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
        else{
            progressDialo.cancel();
        }

    }

//    private void loaddata(){
//        fStore.collection("parking")
//                .whereEqualTo("userId",fAuth.getCurrentUser().getUid())
//                .get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        List<DocumentSnapshot> dsList=queryDocumentSnapshots.getDocuments();
//
//                        for(DocumentSnapshot ds:dsList){
//                            ParkCarModel model=ds.toObject(ParkCarModel.class);
//                            if(model.getStatus().equals("PAID")){
////                                String fee = model.getFee();
////                                parked = parked + Integer.parseInt(fee);
//                            }
//
//                            if(model.getStatus().equals("Paid")){
////                                String fee = model.getFee();
////                                paid = paid + Integer.parseInt(fee);
//                            }
//                            adapter.add(model);
//                        }
////                        binding.paid.setText(paid+"");
////                        binding.pending.setText(parked+"");
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(BookingActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }

    //
//    @Override
//    public void OnLongClick(int pos, String id) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("status")
//                .setPositiveButton("PAID", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        changeStatus("PARKED", id);
//
//                    }
//                })
//                .setNegativeButton("Cancelled", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        changeStatus("Cancelled", id);
//                    }
//                });
//        builder.show();
//    }
//
//    private void changeStatus(String status, String id) {
//        fStore.collection("parking")
//                .document(id)
//                .update("status",status)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Toast.makeText(BookingActivity.this,"Status Update",Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(BookingActivity.this, e.getMessage(),Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }

//    public void RegisterVehicle(){
//        Intent intent = new Intent(getApplicationContext(), RegisterForm.class);
//        startActivity(intent);
//    }
    @Override
    public void onClick(View v){

        switch (v.getId()){
            case R.id.c1:
                card1.setCardBackgroundColor(getResources().getColor(R.color.red));
                break;
            case R.id.c2:
                card2.setCardBackgroundColor(getResources().getColor(R.color.green));
                break;
            case R.id.c3:
                card3.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                break;
            case R.id.c4:
                card4.setCardBackgroundColor(getResources().getColor(R.color.red));

                break;
//            case R.id.registerVehicle:
//                Intent intent = new Intent(getApplicationContext(), RegisterForm.class);
//                startActivity(intent);
//                break;
            default:
                break;



        }

    }

}