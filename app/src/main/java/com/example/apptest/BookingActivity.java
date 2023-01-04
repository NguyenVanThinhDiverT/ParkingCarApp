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
import com.google.firebase.auth.FirebaseUser;
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
    Integer statusreceived;
    //    public Button buttonregister, buttonupdate;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    //    ProgressDialog dialog;
    CarsAdapter adapter;
    TextView username;
    //    AlertDialog.Builder builder;
    long time;
    String id;
    ParkCarModel parkCarModel;
    TextView Status, Date;
    String STATUS;
    //    String status_init;
    Button buttoncheck, buttondelete;
    RecyclerView CarRecycle;
    FirebaseUser user;
    String UserId;

    //    TextInputLayout DriverName, DriverNumber, NumberPlate, Date, Status;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_booking);
//        status_init = "EMPTY";
//        statusreceived = 5;
        adapter = new CarsAdapter(this, this);

        parkCarModel = new ParkCarModel();
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

//        dialog = new ProgressDialog(this);
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
//        Handler handler = new Handler();

//        handler.postDelayed(new Runnable() {
//            public void run() {
//                checkdata("0");
//
//            }
//        }, 3000);
//        handler.postDelayed(new Runnable() {
//            public void run() {
//                checkdata("1");
//
//            }
//        }, 6000);
//
//        handler.postDelayed(new Runnable() {
//            public void run() {
//                checkdata("2");
//
//            }
//        }, 9000);
//        handler.postDelayed(new Runnable() {
//            public void run() {
//                checkdata("3");
//
//            }
//        }, 12000);

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
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
//                checkdata("0");
                loaddata1("0");

            }
        }, 3000);
        handler.postDelayed(new Runnable() {
            public void run() {
//                checkdata("1");
                loaddata2("1");

            }
        }, 6000);


        handler.postDelayed(new Runnable() {
            public void run() {
//                checkdata("2");
                loaddata3("2");

            }
        }, 9000);

        handler.postDelayed(new Runnable() {
            public void run() {
//                checkdata("3");
                loaddata4("3");

            }
        }, 12000);

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
                        loaddata1("0");

                    }
                }, 3000);
                handler.postDelayed(new Runnable() {
                    public void run() {
                        loaddata2("1");

                    }
                }, 6000);


                handler.postDelayed(new Runnable() {
                    public void run() {
                        loaddata3("2");

                    }
                }, 9000);

                handler.postDelayed(new Runnable() {
                    public void run() {
                        loaddata4("3");

                    }
                }, 12000);


                break;

//            case R.id.btndelete:
//                adapter.remove();

            default:
                break;


        }

    }


    public void chooseCard(String slot) {
        switch (slot) {
            case "0":
                cardAll = card1;
                break;
            case "1":
                cardAll = card2;
                break;
            case "2":
                cardAll = card3;
                break;
            case "3":
                cardAll = card4;
                break;
            default:
                break;

        }
    }

    public void chooseColor(String status, CardView cardChoose) {
        Handler handler = new Handler();
        switch (status) {
            case "EMPTY":

                cardChoose.setCardBackgroundColor(getResources().getColor(R.color.green));
                break;
            case "RESERVED":

                cardChoose.setCardBackgroundColor(getResources().getColor(R.color.yellow));

                break;
            case "PARKED":
                cardChoose.setCardBackgroundColor(getResources().getColor(R.color.red));
                break;
            default:
                break;

        }
    }


//    public void registerPark(String slot, String status) {
//        time = Calendar.getInstance().getTimeInMillis();
//        id = UUID.randomUUID().toString();
//        user = fAuth.getCurrentUser();
//        UserId = "123456";
//        parkCarModel.setId(id);
//        parkCarModel.setTime(time);
//        parkCarModel.setUserId(UserId);
//        parkCarModel.setStatus(status);
//        parkCarModel.setSlot(slot);
//
//
//        fStore.collection("parking")
//                .document(slot)
//                .set(parkCarModel)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
////                        Toast.makeText(BookingActivity.this, "register Success", Toast.LENGTH_SHORT).show();
//
//
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//
//                        Toast.makeText(BookingActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//    }


    public void checkRESERVED1(String slot) {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        chooseCard(slot);

        fStore.collection("parking")
                .whereEqualTo("slot", "0")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> dsList = queryDocumentSnapshots.getDocuments();

                        for (DocumentSnapshot ds : dsList) {
                            ParkCarModel model = ds.toObject(ParkCarModel.class);
                            if (model.getStatus().equals("EMPTY")) {
//        if (statusreceived == 3) {

                                builder.setTitle("RESERVED")
                                        .setMessage("You have 15 minutes to get to the parking spot!")
                                        .setCancelable(true)
                                        .setIcon(R.drawable.waiting_car_icon)
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Slot");
                                                reference.child(slot).child("status").setValue(5);
                                                reference.child(slot).child("UID").setValue("129 88 223 28");

                                                model.setStatus("RESERVED");
                                                adapter.add(model);


                                                Toast.makeText(BookingActivity.this, "You book RESERVED successful", Toast.LENGTH_SHORT).show();
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


                            } else if (model.getStatus().equals("PARKED")) {


                                Toast.makeText(BookingActivity.this, "No Apply Park", Toast.LENGTH_SHORT).show();
                            } else if (model.getStatus().equals("RESERVED")) {

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

    public void checkRESERVED2(String slot) {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        chooseCard(slot);

        fStore.collection("parking")
                .whereEqualTo("slot", "1")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> dsList = queryDocumentSnapshots.getDocuments();

                        for (DocumentSnapshot ds : dsList) {
                            ParkCarModel model = ds.toObject(ParkCarModel.class);
                            if (model.getStatus().equals("EMPTY")) {
//        if (statusreceived == 3) {

                                builder.setTitle("RESERVED")
                                        .setMessage("You have 15 minutes to get to the parking spot!")
                                        .setCancelable(true)
                                        .setIcon(R.drawable.waiting_car_icon)
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {

                                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Slot");
                                                reference.child(slot).child("status").setValue(5);
                                                reference.child(slot).child("UID").setValue("113 184 187 28");

                                                model.setStatus("RESERVED");
                                                adapter.add(model);


                                                Toast.makeText(BookingActivity.this, "You book RESERVED successful", Toast.LENGTH_SHORT).show();
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


                            } else if (model.getStatus().equals("PARKED")) {


                                Toast.makeText(BookingActivity.this, "No Apply Park", Toast.LENGTH_SHORT).show();
                            } else if (model.getStatus().equals("RESERVED")) {

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


    public void checkRESERVED3(String slot) {


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        chooseCard(slot);

        fStore.collection("parking")
                .whereEqualTo("slot", "2")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> dsList = queryDocumentSnapshots.getDocuments();

                        for (DocumentSnapshot ds : dsList) {
                            ParkCarModel model = ds.toObject(ParkCarModel.class);
                            if (model.getStatus().equals("EMPTY")) {
//        if (statusreceived == 3) {

                                builder.setTitle("RESERVED")
                                        .setMessage("You have 15 minutes to get to the parking spot!")
                                        .setCancelable(true)
                                        .setIcon(R.drawable.waiting_car_icon)
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
//                                                model.setStatus("RESERVED");
//                                                changeStatus("RESERVED", slot);
                                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Slot");
                                                reference.child(slot).child("status").setValue(5);
                                                reference.child(slot).child("UID").setValue("129 30 26 28");


                                                model.setStatus("RESERVED");
                                                adapter.add(model);
//                            loaddata(slot);
//                                                        model.setStatus("RESERVED");
//                                                        adapter.add(model);


                                                Toast.makeText(BookingActivity.this, "You book RESERVED successful", Toast.LENGTH_SHORT).show();
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
//        } else if (statusreceived == 4) {
//                                    cardAll.setCardBackgroundColor(getResources().getColor(R.color.red));
//                                changeStatus("PARKED", slot);
//            chooseColor("PARKED", cardAll);
//            registerPark(slot, "PARKED");
////                                        model.setStatus("PARKED");
//                                        adapter.add(model);
//                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Slot");
//                                reference.child(slot).child("status").setValue(4);
//                                loaddata(slot);


                                Toast.makeText(BookingActivity.this, "No Apply Park", Toast.LENGTH_SHORT).show();
                            } else if (model.getStatus().equals("RESERVED")) {
//                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Slot");
//                                reference.child(slot).child("status").setValue(5);
////        } else if (statusreceived == 5) {
////                                    cardAll.setCardBackgroundColor(getResources().getColor(R.color.yellow));
////                                changeStatus("RESERVED", slot);
////            chooseColor("RESERVED", cardAll);
//////            registerPark(slot, "RESERVED");
//////                                        model.setStatus("RESERVED");
////                                        adapter.add(model);
////                                        adapter.add(model);
//                                loaddata(slot);

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

//            }

//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        Query checkUser = reference.orderByChild("1").equalTo(slot);
//
//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
////                    username.setError(null);
////                    username.setErrorEnabled(false);
//                    String statusFromDB = snapshot.child("Slot").child(slot).child("status").getValue().toString();
//                    statusreceived = Integer.valueOf(statusFromDB);
//
//                    Toast.makeText(BookingActivity.this,"Receive data", Toast.LENGTH_SHORT).show();
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


    } //xong

    public void checkRESERVED4(String slot) {

//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Slot");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        chooseCard(slot);

        fStore.collection("parking")
                .whereEqualTo("slot", "3")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> dsList = queryDocumentSnapshots.getDocuments();

                        for (DocumentSnapshot ds : dsList) {
                            ParkCarModel model = ds.toObject(ParkCarModel.class);
                            if (model.getStatus().equals("EMPTY")) {
//        if (statusreceived == 3) {

                                builder.setTitle("RESERVED")
                                        .setMessage("You have 15 minutes to get to the parking spot!")
                                        .setCancelable(true)
                                        .setIcon(R.drawable.waiting_car_icon)
                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Slot");
                                                reference.child(slot).child("status").setValue(5);
                                                reference.child(slot).child("UID").setValue("113 106 18 28");
                                                chooseColor("RESERVED", cardAll);
//                                                changeStatus("RESERVED", "0");
                                                model.setStatus("RESERVED");
                                                adapter.add(model);

//                                                loaddata4(slot);


                                                Toast.makeText(BookingActivity.this, "You book RESERVED successful", Toast.LENGTH_SHORT).show();
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


                            } else if (model.getStatus().equals("PARKED")) {

                                Toast.makeText(BookingActivity.this, "No Apply Park", Toast.LENGTH_SHORT).show();
                            } else if (model.getStatus().equals("RESERVED")) {

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

//    private void RESERVED1(String slot, Integer data) {
//        statusreceived = data;
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
////                            if (model.getStatus().equals("EMPTY")) {
//                            if (statusreceived == 3) {
//
////                                cardAll.setCardBackgroundColor(getResources().getColor(R.color.green));
//                                builder.setTitle("RESERVED")
//                                        .setMessage("You have 15 minutes to get to the parking spot!")
//                                        .setCancelable(true)
//                                        .setIcon(R.drawable.waiting_car_icon)
//                                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialog, int which) {
////                                                model.setStatus("RESERVED");
////                                                changeStatus("RESERVED", slot);
//                                                chooseColor("RESERVED", cardAll);
//                                                registerPark(slot, "RESERVED");
//                                                adapter.add(model);
//                                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Slot");
//                                                reference.child(slot).child("status").setValue(5);
//
//                                                Toast.makeText(BookingActivity.this, "You book RESERVED successful", Toast.LENGTH_SHORT).show();
////                                                                finish();
//                                            }
//                                        })
//
////                                        .setIcon(R.drawable.negative_button)
//                                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                                            @Override
//                                            public void onClick(DialogInterface dialog, int which) {
//                                                dialog.cancel();
//                                            }
//                                        })
//                                        .show();
//
//
//                                //
//
//
////                            } else if (model.getStatus().equals("PARKED")) {
//                            } else if (statusreceived == 4) {
////                                    cardAll.setCardBackgroundColor(getResources().getColor(R.color.red));
////                                changeStatus("PARKED", slot);
//                                chooseColor("PARKED", cardAll);
//                                registerPark(slot, "PARKED");
//                                adapter.add(model);
//
//                                Toast.makeText(BookingActivity.this, "No Apply Park", Toast.LENGTH_SHORT).show();
////                            } else if (model.getStatus().equals("RESERVED")) {
//                            } else if (statusreceived == 5) {
////                                    cardAll.setCardBackgroundColor(getResources().getColor(R.color.yellow));
////                                changeStatus("RESERVED", slot);
//                                chooseColor("RESERVED", cardAll);
//                                registerPark(slot, "RESERVED");
//                                adapter.add(model);
//
//                                Toast.makeText(BookingActivity.this, "Park is waiting vehicle", Toast.LENGTH_SHORT).show();
//
//                            } else {
//                                Toast.makeText(BookingActivity.this, "Out Range Click", Toast.LENGTH_SHORT).show();
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
//
//    }


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
//                            if (model.getStatus().equals("EMPTY")) {
////                            if(statusFromDB == "EMPTY"){
////                                cardAll.setCardBackgroundColor(getResources().getColor(R.color.green));
//                                changeStatus("EMPTY", slot);
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
    private void loaddata1(String slot) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Slot/0");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String data = snapshot.child("status").getValue().toString();
//                LOADDATA1(slot, data2);
                statusreceived = Integer.valueOf(data);
                chooseCard(slot);
                fStore.collection("parking")
                        .whereEqualTo("slot", slot)
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                List<DocumentSnapshot> dsList = queryDocumentSnapshots.getDocuments();

                                for (DocumentSnapshot ds : dsList) {
                                    ParkCarModel model = ds.toObject(ParkCarModel.class);
//                            if (model.getStatus().equals("EMPTY")) {
                                    if (statusreceived == 3) {
//                                cardAll.setCardBackgroundColor(getResources().getColor(R.color.green));
//                                changeStatus("EMPTY", slot);
                                        chooseColor("EMPTY", cardAll);
                                        changeStatus("EMPTY", "0");
//                                        registerPark(slot, "EMPTY");
//                                        model.setStatus("EMPTY");
//                                        adapter.add(model);
//                                        adapter.add(model);

//                                Toast.makeText(BookingActivity.this, "Slot Empty", Toast.LENGTH_SHORT).show();


                                    }
//                            else if (model.getStatus().equals("PARKED")) {
                                    else if (statusreceived == 4) {
//                                    cardAll.setCardBackgroundColor(getResources().getColor(R.color.red));
//                                changeStatus("PARKED", slot);
                                        chooseColor("PARKED", cardAll);
//                                        registerPark(slot, "PARKED");
                                        changeStatus("PARKED", "0");
//                                        model.setStatus("PARKED");
//                                        adapter.add(model);
//                                        adapter.add(model);

//                                Toast.makeText(BookingActivity.this, "Slot Full", Toast.LENGTH_SHORT).show();
                                    }
//                            else if (model.getStatus().equals("RESERVED")) {
                                    else if (statusreceived == 5) {
//                                    cardAll.setCardBackgroundColor(getResources().getColor(R.color.yellow));

//                                changeStatus("RESERVED", slot);
                                        chooseColor("RESERVED", cardAll);
//                                        registerPark(slot, "RESERVED");
                                        changeStatus("RESERVED", "0");
//                                        model.setStatus("RESERVED");
//                                        adapter.add(model);
//                                        adapter.add(model);

//                                Toast.makeText(BookingActivity.this, "Slot is waiting", Toast.LENGTH_SHORT).show();

                                    } else {
//                                Toast.makeText(BookingActivity.this, "Not received data", Toast.LENGTH_SHORT).show();
                                    }
                                    adapter.add(model);

                                }

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(BookingActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        Query checkUser = reference.orderByChild("1").equalTo(slot);
//
//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
////                    username.setError(null);
////                    username.setErrorEnabled(false);
//                    String statusFromDB = snapshot.child("Slot").child(slot).child("status").getValue(String.class);
//                    statusreceived = Integer.valueOf(statusFromDB);
//                    Toast.makeText(BookingActivity.this,"Receive data", Toast.LENGTH_SHORT).show();
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


    } // xong

    private void loaddata2(String slot) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Slot/1");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String data = snapshot.child("status").getValue().toString();
//                LOADDATA1(slot, data2);
                statusreceived = Integer.valueOf(data);
                chooseCard(slot);
                fStore.collection("parking")
                        .whereEqualTo("slot", slot)
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                List<DocumentSnapshot> dsList = queryDocumentSnapshots.getDocuments();

                                for (DocumentSnapshot ds : dsList) {
                                    ParkCarModel model = ds.toObject(ParkCarModel.class);
//                            if (model.getStatus().equals("EMPTY")) {
                                    if (statusreceived == 3) {
//                                cardAll.setCardBackgroundColor(getResources().getColor(R.color.green));
//                                changeStatus("EMPTY", slot);
                                        chooseColor("EMPTY", cardAll);
                                        changeStatus("EMPTY", "1");
//                                        registerPark(slot, "EMPTY");
//                                        model.setStatus("EMPTY");
//                                        adapter.add(model);
//                                        adapter.add(model);

//                                Toast.makeText(BookingActivity.this, "Slot Empty", Toast.LENGTH_SHORT).show();


                                    }
//                            else if (model.getStatus().equals("PARKED")) {
                                    else if (statusreceived == 4) {
//                                    cardAll.setCardBackgroundColor(getResources().getColor(R.color.red));
//                                changeStatus("PARKED", slot);
                                        chooseColor("PARKED", cardAll);
//                                        registerPark(slot, "PARKED");
                                        changeStatus("PARKED", "1");
//                                        model.setStatus("PARKED");
//                                        adapter.add(model);
//                                        adapter.add(model);

//                                Toast.makeText(BookingActivity.this, "Slot Full", Toast.LENGTH_SHORT).show();
                                    }
//                            else if (model.getStatus().equals("RESERVED")) {
                                    else if (statusreceived == 5) {
//                                    cardAll.setCardBackgroundColor(getResources().getColor(R.color.yellow));

//                                changeStatus("RESERVED", slot);
                                        chooseColor("RESERVED", cardAll);
//                                        registerPark(slot, "RESERVED");
                                        changeStatus("RESERVED", "1");
//                                        model.setStatus("RESERVED");
//                                        adapter.add(model);
//                                        adapter.add(model);

//                                Toast.makeText(BookingActivity.this, "Slot is waiting", Toast.LENGTH_SHORT).show();

                                    } else {
//                                Toast.makeText(BookingActivity.this, "Not received data", Toast.LENGTH_SHORT).show();
                                    }
                                    adapter.add(model);

                                }

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(BookingActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        Query checkUser = reference.orderByChild("1").equalTo(slot);
//
//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
////                    username.setError(null);
////                    username.setErrorEnabled(false);
//                    String statusFromDB = snapshot.child("Slot").child(slot).child("status").getValue(String.class);
//                    statusreceived = Integer.valueOf(statusFromDB);
//                    Toast.makeText(BookingActivity.this,"Receive data", Toast.LENGTH_SHORT).show();
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


    } // xong

    private void loaddata3(String slot) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Slot/2");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String data = snapshot.child("status").getValue().toString();
//                LOADDATA1(slot, data2);
                statusreceived = Integer.valueOf(data);
                chooseCard(slot);
                fStore.collection("parking")
                        .whereEqualTo("slot", slot)
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                List<DocumentSnapshot> dsList = queryDocumentSnapshots.getDocuments();

                                for (DocumentSnapshot ds : dsList) {
                                    ParkCarModel model = ds.toObject(ParkCarModel.class);
//                            if (model.getStatus().equals("EMPTY")) {
                                    if (statusreceived == 3) {
//                                cardAll.setCardBackgroundColor(getResources().getColor(R.color.green));
//                                changeStatus("EMPTY", slot);
                                        chooseColor("EMPTY", cardAll);
                                        changeStatus("EMPTY", "2");
//                                        registerPark(slot, "EMPTY");
//                                        model.setStatus("EMPTY");
//                                        adapter.add(model);
//                                        adapter.add(model);

//                                Toast.makeText(BookingActivity.this, "Slot Empty", Toast.LENGTH_SHORT).show();


                                    }
//                            else if (model.getStatus().equals("PARKED")) {
                                    else if (statusreceived == 4) {
//                                    cardAll.setCardBackgroundColor(getResources().getColor(R.color.red));
//                                changeStatus("PARKED", slot);
                                        chooseColor("PARKED", cardAll);
//                                        registerPark(slot, "PARKED");
                                        changeStatus("PARKED", "2");
//                                        model.setStatus("PARKED");
//                                        adapter.add(model);
//                                        adapter.add(model);

//                                Toast.makeText(BookingActivity.this, "Slot Full", Toast.LENGTH_SHORT).show();
                                    }
//                            else if (model.getStatus().equals("RESERVED")) {
                                    else if (statusreceived == 5) {
//                                    cardAll.setCardBackgroundColor(getResources().getColor(R.color.yellow));

//                                changeStatus("RESERVED", slot);
                                        chooseColor("RESERVED", cardAll);
//                                        registerPark(slot, "RESERVED");
                                        changeStatus("RESERVED", "2");
//                                        model.setStatus("RESERVED");
//                                        adapter.add(model);
//                                        adapter.add(model);

//                                Toast.makeText(BookingActivity.this, "Slot is waiting", Toast.LENGTH_SHORT).show();

                                    } else {
//                                Toast.makeText(BookingActivity.this, "Not received data", Toast.LENGTH_SHORT).show();
                                    }
                                    adapter.add(model);

                                }

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(BookingActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        Query checkUser = reference.orderByChild("1").equalTo(slot);
//
//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
////                    username.setError(null);
////                    username.setErrorEnabled(false);
//                    String statusFromDB = snapshot.child("Slot").child(slot).child("status").getValue(String.class);
//                    statusreceived = Integer.valueOf(statusFromDB);
//                    Toast.makeText(BookingActivity.this,"Receive data", Toast.LENGTH_SHORT).show();
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


    } // xong

    private void loaddata4(String slot) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Slot/3");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String data = snapshot.child("status").getValue().toString();
//                LOADDATA1(slot, data2);
                statusreceived = Integer.valueOf(data);
                chooseCard(slot);
                fStore.collection("parking")
                        .whereEqualTo("slot", slot)
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                List<DocumentSnapshot> dsList = queryDocumentSnapshots.getDocuments();

                                for (DocumentSnapshot ds : dsList) {
                                    ParkCarModel model = ds.toObject(ParkCarModel.class);
//                            if (model.getStatus().equals("EMPTY")) {
                                    if (statusreceived == 3) {
//                                cardAll.setCardBackgroundColor(getResources().getColor(R.color.green));
//                                changeStatus("EMPTY", slot);
                                        chooseColor("EMPTY", cardAll);
                                        changeStatus("EMPTY", "3");
//                                        registerPark(slot, "EMPTY");
//                                        model.setStatus("EMPTY");
//                                        adapter.add(model);
//                                        adapter.add(model);

//                                Toast.makeText(BookingActivity.this, "Slot Empty", Toast.LENGTH_SHORT).show();


                                    }
//                            else if (model.getStatus().equals("PARKED")) {
                                    else if (statusreceived == 4) {
//                                    cardAll.setCardBackgroundColor(getResources().getColor(R.color.red));
//                                changeStatus("PARKED", slot);
                                        chooseColor("PARKED", cardAll);
//                                        registerPark(slot, "PARKED");
                                        changeStatus("PARKED", "3");
//                                        model.setStatus("PARKED");
//                                        adapter.add(model);
//                                        adapter.add(model);

//                                Toast.makeText(BookingActivity.this, "Slot Full", Toast.LENGTH_SHORT).show();
                                    }
//                            else if (model.getStatus().equals("RESERVED")) {
                                    else if (statusreceived == 5) {
//                                    cardAll.setCardBackgroundColor(getResources().getColor(R.color.yellow));

//                                changeStatus("RESERVED", slot);
                                        chooseColor("RESERVED", cardAll);
//                                        registerPark(slot, "RESERVED");
                                        changeStatus("RESERVED", "3");
//                                        model.setStatus("RESERVED");
//                                        adapter.add(model);
//                                        adapter.add(model);

//                                Toast.makeText(BookingActivity.this, "Slot is waiting", Toast.LENGTH_SHORT).show();

                                    } else {
//                                Toast.makeText(BookingActivity.this, "Not received data", Toast.LENGTH_SHORT).show();
                                    }
                                    adapter.add(model);

                                }

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(BookingActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        Query checkUser = reference.orderByChild("1").equalTo(slot);
//
//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
////                    username.setError(null);
////                    username.setErrorEnabled(false);
//                    String statusFromDB = snapshot.child("Slot").child(slot).child("status").getValue(String.class);
//                    statusreceived = Integer.valueOf(statusFromDB);
//                    Toast.makeText(BookingActivity.this,"Receive data", Toast.LENGTH_SHORT).show();
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


    } // xong


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
//                        loaddata(slot);
//                        registerPark(slot, "RESERVED");
//                        chooseColor(status, cardAll);
//                        Toast.makeText(BookingActivity.this, "Status Update", Toast.LENGTH_SHORT).show();
//                        registerPark(slot, status);
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

    //    private void checkdata(String slot) {
//
//        DocumentReference documentReference = fStore.collection("parking").document(slot);
//        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                    @Override
//                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//                        if (!documentSnapshot.exists()) {
//
////                    Date.setText(String.valueOf(documentSnapshot.getString("time")));
//                            registerPark(slot, status_init);
//                            Toast.makeText(getApplicationContext(), "Data register Successful", Toast.LENGTH_LONG).show();
////                            loaddata(slot);
//                        } else {
////                            registerPark(slot);
////                            checkPark(slot);
//                            loaddata(slot);
////                            Toast.makeText(getApplicationContext(), "Data field empty", Toast.LENGTH_LONG).show();
//                        }
//
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(getApplicationContext(), "Data empty", Toast.LENGTH_LONG).show();
//                    }
//                });
//    }
//    private void checkdata(String slot) {
//
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Slot");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                String data = snapshot.child(slot).child("status").getValue().toString();
//                Integer data2 = Integer.valueOf(data);
////                CHECKDATA1(slot, data2);
//                data2 = statusreceived;
//                DocumentReference documentReference = fStore.collection("parking").document(slot);
//                documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                            @Override
//                            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                                if (documentSnapshot.exists()) {
//                                    loaddata(slot);
//
////                    Date.setText(String.valueOf(documentSnapshot.getString("time")));
////                            registerPark(slot, "RESERVED");
//                                    Toast.makeText(getApplicationContext(), "Data register Successful", Toast.LENGTH_LONG).show();
////                            loaddata(slot);
//                                } else {
////                            registerPark(slot);
////                            checkPark(slot);
//
//                                    Toast.makeText(getApplicationContext(), "Data field empty", Toast.LENGTH_LONG).show();
//                                }
//
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(getApplicationContext(), "Data empty", Toast.LENGTH_LONG).show();
//                            }
//                        });
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
////    Query checkUser = reference.orderByChild("1").equalTo(slot);
////
////    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
////        @Override
////        public void onDataChange(@NonNull DataSnapshot snapshot) {
////            if (snapshot.exists()) {
//////                    username.setError(null);
//////                    username.setErrorEnabled(false);
////                String statusFromDB = snapshot.child("Slot").child(slot).child("status").getValue().toString();
////                statusreceived = Integer.valueOf(statusFromDB);
////                Toast.makeText(BookingActivity.this,"Receive data", Toast.LENGTH_SHORT).show();
////
////////                    }
//////                    else{
////////                            ProgressBar.setVisibility(View.GONE);
//////                        password.setError("Wrong Password");
//////                        password.requestFocus();
//////                    }
////            } else {
//////                    username.setError("No such User exist");
//////                    username.requestFocus();
////            }
////        }
////
////        @Override
////        public void onCancelled(@NonNull DatabaseError error) {
//////                Toast.makeText(Login.this, error.getMessage(),Toast.LENGTH_SHORT).show();
////
////        }
////    });
//
//
//    }
//
//    private void CHECKDATA1(String slot, Integer data) {
//        DocumentReference documentReference = fStore.collection("parking").document(slot);
//        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                    @Override
//                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//                        if (!documentSnapshot.exists()) {
//
////                    Date.setText(String.valueOf(documentSnapshot.getString("time")));
////                            registerPark(slot, "RESERVED");
//                            Toast.makeText(getApplicationContext(), "Data register Successful", Toast.LENGTH_LONG).show();
////                            loaddata(slot);
//                        } else {
////                            registerPark(slot);
////                            checkPark(slot);
//                            loaddata(slot);
////                            Toast.makeText(getApplicationContext(), "Data field empty", Toast.LENGTH_LONG).show();
//                        }
//
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(getApplicationContext(), "Data empty", Toast.LENGTH_LONG).show();
//                    }
//                });
//    }

    @Override
    public boolean onLongClick(View v) {
        Handler handler = new Handler();
        switch (v.getId()) {
            case R.id.c1:
                checkRESERVED1("0");
                break;

//                return true;
            case R.id.c2:
                checkRESERVED2("1");
                break;

            case R.id.c3:
                checkRESERVED3("2");

                break;

            case R.id.c4:
                checkRESERVED4("3");
                break;


//                return true;

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