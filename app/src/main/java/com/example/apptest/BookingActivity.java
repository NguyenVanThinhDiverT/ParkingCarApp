package com.example.apptest;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import org.checkerframework.common.subtyping.qual.Bottom;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BookingActivity extends AppCompatActivity
        implements View.OnClickListener
        , CarAdapterInterface
        , View.OnLongClickListener {
    //    String DRIVERNAME, DRIVERNUMBER, NUMBERPLATE, DATE, STATUS;
    public CardView card1, card2, card3, card4, cardAll;
    String _USERNAME;
    //    public Button buttonregister, buttonupdate;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    ProgressDialog dialog;
    CarsAdapter adapter;
    TextView username;
    //    AlertDialog.Builder builder;
    long time;
    String id;
    ParkCarModel parkCarModel;
    TextView Status, Date;
    String STATUS;
    String status_init;
    Button buttoncheck, buttondelete;
    RecyclerView CarRecycle;


    //    TextInputLayout DriverName, DriverNumber, NumberPlate, Date, Status;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_booking);
        status_init = "PAIR";

        adapter = new CarsAdapter(this, this);

        parkCarModel = new ParkCarModel();
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        dialog = new ProgressDialog(this);
        card1 = (CardView) findViewById(R.id.c1);
        card2 = (CardView) findViewById(R.id.c2);
        card3 = (CardView) findViewById(R.id.c3);
        card4 = (CardView) findViewById(R.id.c4);

        buttoncheck = findViewById(R.id.btncheck);
        Status = findViewById(R.id.statuspark);
        Date = findViewById(R.id.Date);
        CarRecycle = findViewById(R.id.Recycle);
        username = findViewById(R.id.usernamebook);


        buttoncheck.setOnClickListener(this);
//        buttondelete.setOnClickListener(this);
        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);


        card1.setOnLongClickListener(this);
        card2.setOnLongClickListener(this);
        card3.setOnLongClickListener(this);
        card4.setOnLongClickListener(this);

//        buttonregister.setOnClickListener(this);
        CarRecycle.setAdapter(adapter);
        CarRecycle.setLayoutManager(new LinearLayoutManager(this));
        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            public void run() {
//                checkdata("1");
//
//            }
//        }, 2500);
//        handler.postDelayed(new Runnable() {
//            public void run() {
//                checkdata("2");
//
//            }
//        }, 5000);
//
//        handler.postDelayed(new Runnable() {
//            public void run() {
//                checkdata("3");
//
//            }
//        }, 7500);
//        handler.postDelayed(new Runnable() {
//            public void run() {
//                checkdata("4");
//
//            }
//        }, 10000);

        BottomNavigationView nav = (BottomNavigationView) findViewById(R.id.navigation);
        nav.setSelectedItemId(R.id.home);
        nav.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:

                    case R.id.UserProfile:
                        CheckUser();
//                        startActivity(new Intent(getApplicationContext(),UserProfile.class));
//                        finish();
//                        overridePendingTransition(0,0);
                        return;


                }
            }
        });

        showUserData();

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.c1:
//                checkPark("1");

                break;
            case R.id.c2:
//                checkPark("2");

                break;
            case R.id.c3:
//                checkPark("3");

                break;
            case R.id.c4:

//                checkPark("4");

                break;
            case R.id.btncheck:
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        checkdata("1");

                    }
                }, 2000);
                handler.postDelayed(new Runnable() {
                    public void run() {
                        checkdata("2");

                    }
                }, 4000);


                handler.postDelayed(new Runnable() {
                    public void run() {
                        checkdata("3");

                    }
                }, 6000);

                handler.postDelayed(new Runnable() {
                    public void run() {
                        checkdata("4");

                    }
                }, 8000);


                break;

//            case R.id.btndelete:
//                adapter.remove();

            default:
                break;


        }

    }


    public void registerPark(String slot, String status) {
        time = Calendar.getInstance().getTimeInMillis();
        id = UUID.randomUUID().toString();
        parkCarModel.setId(id);
//                parkCarModel.setNumberPlate(numberPlate);
//                parkCarModel.setDriverName(driverName);
        parkCarModel.setTime(time);
//                parkCarModel.setDriverNumber(driverNumber);
        parkCarModel.setUserId(fAuth.getCurrentUser().getUid());
        parkCarModel.setStatus(status);
        parkCarModel.setSlot(slot);

        dialog.setTitle("Uploading");
        dialog.setMessage("Data to the database");
        dialog.show();

        fStore.collection("parking")
                .document(slot)
                .set(parkCarModel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
//                        dialog.setTitle("Parking");
//                        dialog.setMessage("Successful");

                        dialog.cancel();
//                                finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        dialog.cancel();
                        Toast.makeText(BookingActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }

    public void checkPark(String slot) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
//
//        Query checkUser = reference.orderByChild("username").equalTo(_USERNAME);
//
//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
//                    String statusFromDB = snapshot.child(_USERNAME).child("status").getValue(String.class);
//                    String slotFromDB = snapshot.child(_USERNAME).child("slot").getValue(String.class);
//                    chooseCard(slotFromDB);
//
//
//                }
//                else{
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
////                Toast.makeText(Login.this, error.getMessage(),Toast.LENGTH_SHORT).show();
//
//            }
//        });

        fStore.collection("parking")
                .whereEqualTo("slot", slot)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> dsList = queryDocumentSnapshots.getDocuments();

                        for (DocumentSnapshot ds : dsList) {
                            ParkCarModel model = ds.toObject(ParkCarModel.class);
                                        if (model.getStatus().equals("PAIR")) {
//                            if (statusFromDB == "PAIR") {
//                                cardAll.setCardBackgroundColor(getResources().getColor(R.color.green));
                                builder.setTitle("PARKED")
                                        .setMessage("Do you want to book this parking lot?")
                                        .setCancelable(true)
                                        .setIcon(R.drawable.parked_car_icon)
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                model.setStatus("PARKED");
                                                changeStatus("PARKED", slot);

                                                Toast.makeText(BookingActivity.this, "You Park Successful", Toast.LENGTH_SHORT).show();
//                                                    finish();
                                                adapter.add(model);

                                            }
                                        })
                                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                dialog.cancel();
                                            }
                                        })
                                        .show();

                                //


                                        } else if (model.getStatus().equals("PARKED")) {
//                            } else if (statusFromDB == "PARKED") {
//                                    cardAll.setCardBackgroundColor(getResources().getColor(R.color.red));
//                                changeStatus("PARKED",slot);
                                adapter.add(model);

                                Toast.makeText(BookingActivity.this, "No Apply Park", Toast.LENGTH_SHORT).show();
                                        } else if (model.getStatus().equals("REVERSED")) {
//                            } else if (statusFromDB == "REVERSED") {

//                                    cardAll.setCardBackgroundColor(getResources().getColor(R.color.yellow));

//                                changeStatus("REVERSED",slot);
                                adapter.add(model);

                                Toast.makeText(BookingActivity.this, "Park is waiting vehicle", Toast.LENGTH_SHORT).show();

                            } else {
//                                adapter.add(model);

                                Toast.makeText(BookingActivity.this, "Out Range Click", Toast.LENGTH_SHORT).show();
                            }

                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(BookingActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    } // xong

    public void checkREVERSED(String slot) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        chooseCard(slot);
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
//
//        Query checkUser = reference.orderByChild("username").equalTo(_USERNAME);
//
//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//
//                    String statusFromDB = snapshot.child(_USERNAME).child("status").getValue(String.class);
//
//
//                }
//                else {
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
////                Toast.makeText(Login.this, error.getMessage(),Toast.LENGTH_SHORT).show();
//
//            }
//        });
        fStore.collection("parking")
                .whereEqualTo("slot", slot)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> dsList = queryDocumentSnapshots.getDocuments();

                        for (DocumentSnapshot ds : dsList) {
                            ParkCarModel model = ds.toObject(ParkCarModel.class);
                                        if (model.getStatus().equals("PAIR")) {
//                            if(statusFromDB == "PAIR") {
//                                cardAll.setCardBackgroundColor(getResources().getColor(R.color.green));
                                builder.setTitle("REVERSED")
                                        .setMessage("You have 15 minutes to get to the parking spot!")
                                        .setCancelable(true)
                                        .setIcon(R.drawable.waiting_car_icon)
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                model.setStatus("REVERSED");
                                                changeStatus("REVERSED", slot);
                                                adapter.add(model);

                                                Toast.makeText(BookingActivity.this, "You book REVERSED successful", Toast.LENGTH_SHORT).show();
//                                                                finish();
                                            }
                                        })

//                                        .setIcon(R.drawable.negative_button)
                                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.cancel();
                                            }
                                        })
                                        .show();


                                //


                                        } else if (model.getStatus().equals("PARKED")) {
//                            } else if(statusFromDB == "PARKED") {
//                                    cardAll.setCardBackgroundColor(getResources().getColor(R.color.red));
//                                changeStatus("PARKED",slot);
                                adapter.add(model);

                                Toast.makeText(BookingActivity.this, "No Apply Park", Toast.LENGTH_SHORT).show();
                                        } else if (model.getStatus().equals("REVERSED")) {
//                            }  else if(statusFromDB == "REVERSED"){
//                                    cardAll.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                                adapter.add(model);

//                                changeStatus("REVERSED",slot);

                                Toast.makeText(BookingActivity.this, "Park is waiting vehicle", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(BookingActivity.this, "Out Range Click", Toast.LENGTH_SHORT).show();
                            }

                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(BookingActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });



    } //xong

    //    private void loaddata(String slot) {
//
//        //
//        chooseCard(slot);
//        fStore.collection("parking")
//                .whereEqualTo("slot", slot)
//                .get()
//                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                    @Override
//                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                        List<DocumentSnapshot> dsList = queryDocumentSnapshots.getDocuments();
//
//                        for (DocumentSnapshot ds : dsList) {
//                            ParkCarModel model = ds.toObject(ParkCarModel.class);
//                            if (model.getStatus().equals("PAIR")) {
////                            if(statusFromDB == "PAIR"){
////                                cardAll.setCardBackgroundColor(getResources().getColor(R.color.green));
//                                changeStatus("PAIR", slot);
//                                adapter.add(model);
//
//                                Toast.makeText(BookingActivity.this, "Slot Empty", Toast.LENGTH_SHORT).show();
//
//
//                            } else if (model.getStatus().equals("PARKED")) {
////                                    cardAll.setCardBackgroundColor(getResources().getColor(R.color.red));
//                                changeStatus("PARKED", slot);
//
//                                Toast.makeText(BookingActivity.this, "Slot Full", Toast.LENGTH_SHORT).show();
//                            } else if (model.getStatus().equals("REVERSED")) {
////                                    cardAll.setCardBackgroundColor(getResources().getColor(R.color.yellow));
//
//                                changeStatus("REVERSED", slot);
//
//                                Toast.makeText(BookingActivity.this, "Slot is waiting", Toast.LENGTH_SHORT).show();
//
//                            } else {
//                                Toast.makeText(BookingActivity.this, "Out Range", Toast.LENGTH_SHORT).show();
//                            }
//
//                        }
//
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(BookingActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
    private void loaddata(String slot) {
        chooseCard(slot);
        //
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
//
//        Query checkUser = reference.orderByChild("username").equalTo(_USERNAME);
//
//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    String statusFromDB = snapshot.child(_USERNAME).child("status").getValue(String.class);
//                    String slotFromDB = snapshot.child(_USERNAME).child("slot").getValue(String.class);
//
//
//
//
//
//                }
//
//            }
//
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
////                Toast.makeText(Login.this, error.getMessage(),Toast.LENGTH_SHORT).show();
//
//            }
//        });

        //
        fStore.collection("parking")
                .whereEqualTo("slot", slot)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> dsList = queryDocumentSnapshots.getDocuments();

                        for (DocumentSnapshot ds : dsList) {
                            ParkCarModel model = ds.toObject(ParkCarModel.class);
                                    if (model.getStatus().equals("PAIR")) {
//                            if (statusFromDB == "PAIR") {
//                                cardAll.setCardBackgroundColor(getResources().getColor(R.color.green));
                                changeStatus("PAIR", slot);
                                adapter.add(model);

                                Toast.makeText(BookingActivity.this, "Slot Empty", Toast.LENGTH_SHORT).show();


                            }
                            else if (model.getStatus().equals("PARKED")) {
//                            else if (statusFromDB == "PARKED") {
//                                    cardAll.setCardBackgroundColor(getResources().getColor(R.color.red));
                                changeStatus("PARKED", slot);


                                Toast.makeText(BookingActivity.this, "Slot Full", Toast.LENGTH_SHORT).show();
                            }
                            else if (model.getStatus().equals("REVERSED")) {
//                            else if (statusFromDB == "REVERSED") {
//                                    cardAll.setCardBackgroundColor(getResources().getColor(R.color.yellow));

                                changeStatus("REVERSED", slot);

                                Toast.makeText(BookingActivity.this, "Slot is waiting", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(BookingActivity.this, "Out Range", Toast.LENGTH_SHORT).show();
                            }

                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(BookingActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    } // xong

    public void chooseCard(String slot) {
        switch (slot) {
            case "1":
                cardAll = card1;
                break;
            case "2":
                cardAll = card2;
                break;
            case "3":
                cardAll = card3;
                break;
            case "4":
                cardAll = card4;
                break;
            default:
                break;

        }
    }

    public void chooseColor(String status, CardView cardChoose) {
        switch (status) {
            case "PAIR":
                cardChoose.setCardBackgroundColor(getResources().getColor(R.color.green));
                break;
            case "REVERSED":
                cardChoose.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                break;
            case "PARKED":
                cardChoose.setCardBackgroundColor(getResources().getColor(R.color.red));
                break;
            default:
                break;

        }
    }


    @Override
    public void OnLongClick(int pos, String slot) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("STATUS")
//                .setMessage("Do you want to book this parking lot?")
//                .setPositiveButton("PARKED", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        changeStatus("PARKED", slot);
//
//                    }
//                })
//                .setNegativeButton("Cancelled", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//        builder.show();
    }

    @Override
    public void OnClick(int pos, String slot) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("DELETE")
                .setMessage("Status will be delete after click YES")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        changeStatus("PARKED", slot);
                        adapter.remove(pos);
                        adapter.notifyItemRemoved(pos);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        builder.show();
    }

    private void changeStatus(String status, String slot) {
        chooseCard(slot);
        fStore.collection("parking")
                .document(slot)
                .update("status", status)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        chooseColor(status, cardAll);
//                        Toast.makeText(BookingActivity.this, "Status Update", Toast.LENGTH_SHORT).show();
                        registerPark(slot, status);
//                        cardAll.setCardBackgroundColor(getResources().getColor(R.color.red));

//                        loaddata(slot);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(BookingActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void checkdata(String slot) {

        DocumentReference documentReference = fStore.collection("parking").document(slot);
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (!documentSnapshot.exists()) {

//                    Date.setText(String.valueOf(documentSnapshot.getString("time")));
                            registerPark(slot, status_init);
                            Toast.makeText(getApplicationContext(), "Data register Successful", Toast.LENGTH_LONG).show();
//                            loaddata(slot);
                        } else {
//                            registerPark(slot);
//                            checkPark(slot);
                            loaddata(slot);
//                            Toast.makeText(getApplicationContext(), "Data field empty", Toast.LENGTH_LONG).show();
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Data empty", Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.c1:
//                card1.setCardBackgroundColor(getResources().getColor(R.color.yellow));
//                changeStatus("REVERSED","1");
//                loaddata("1");
//                registerPark("1","REVERSED");
                checkREVERSED("1");
                break;
//                return true;
            case R.id.c2:
//                card2.setCardBackgroundColor(getResources().getColor(R.color.yellow));
//                changeStatus("REVERSED","2");
//                loaddata("2");
//                registerPark("2","REVERSED");
                checkREVERSED("2");

                break;
//                return true;
            case R.id.c3:
//                card3.setCardBackgroundColor(getResources().getColor(R.color.yellow));
//                changeStatus("REVERSED","3");
//                loaddata("3");
//                registerPark("3","REVERSED");
                checkREVERSED("3");

//                return true;
                break;
            case R.id.c4:
//                card4.setCardBackgroundColor(getResources().getColor(R.color.yellow));
//                changeStatus("REVERSED","4");
//                loaddata("4");
//                registerPark("4","REVERSED");
                checkREVERSED("4");

//                return true;
                break;
            default:
                break;

        }
        return true;
    }

    private void showUserData() {
        Intent intent = getIntent();
        _USERNAME = intent.getStringExtra("username");
        username.setText(_USERNAME);


    }

    private void CheckUser() {
//        final String userEnteredUsername = username.getEditText().getText().toString().trim();
//        final String userEnteredPassword = password.getEditText().getText().toString().trim();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        Query checkUser = reference.orderByChild("username").equalTo(_USERNAME);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
//                    username.setError(null);
//                    username.setErrorEnabled(false);

                    String passwordFromDB = snapshot.child(_USERNAME).child("password").getValue(String.class);


//                    if(passwordFromDB.equals(userEnteredPassword)){

                    String nameFromDB = snapshot.child(_USERNAME).child("name").getValue(String.class);
                    String usernameFromDB = snapshot.child(_USERNAME).child("username").getValue(String.class);
                    String phoneNoFromDB = snapshot.child(_USERNAME).child("phoneNo").getValue(String.class);
                    String emailFromDB = snapshot.child(_USERNAME).child("email").getValue(String.class);

                    Intent intent = new Intent(getApplicationContext(), UserProfile.class);

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
                } else {
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

//    private void checkstatusaccount() {
////        final String userEnteredUsername = username.getEditText().getText().toString().trim();
////        final String userEnteredPassword = password.getEditText().getText().toString().trim();
//
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
//
//        Query checkUser = reference.orderByChild("username").equalTo(_USERNAME);
//
//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    String statusFromDB = snapshot.child(_USERNAME).child("status").getValue(String.class);
//                    String slotFromDB = snapshot.child(_USERNAME).child("slot").getValue(String.class);
//
////                    username.setError(null);
////                    username.setErrorEnabled(false);
//
////                        String passwordFromDB = snapshot.child(_USERNAME).child("password").getValue(String.class);
////
//////                    if(passwordFromDB.equals(userEnteredPassword)){
////
////                        String nameFromDB = snapshot.child(_USERNAME).child("name").getValue(String.class);
////                        String usernameFromDB = snapshot.child(_USERNAME).child("username").getValue(String.class);
////                        String phoneNoFromDB = snapshot.child(_USERNAME).child("phoneNo").getValue(String.class);
////                        String emailFromDB = snapshot.child(_USERNAME).child("email").getValue(String.class);
//
//                    Intent intent = new Intent(getApplicationContext(), UserProfile.class);
//
////                        intent.putExtra("name", nameFromDB);
////                        intent.putExtra("username", usernameFromDB);
////                        intent.putExtra("email", emailFromDB);
////                        intent.putExtra("phoneNo", phoneNoFromDB);
////                        intent.putExtra("password", passwordFromDB);
//
//                    startActivity(intent);
//
//
//////                    }
////                    else{
//////                            ProgressBar.setVisibility(View.GONE);
////                        password.setError("Wrong Password");
////                        password.requestFocus();
////                    }
//                } else {
////                    username.setError("No such User exist");
////                    username.requestFocus();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
////                Toast.makeText(Login.this, error.getMessage(),Toast.LENGTH_SHORT).show();
//
//            }
//        });
//    }
}