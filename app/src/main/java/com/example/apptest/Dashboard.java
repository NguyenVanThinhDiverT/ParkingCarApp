//package com.example.apptest;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.cardview.widget.CardView;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.app.Activity;
//import android.app.ActivityOptions;
//import android.app.AlertDialog;
//import android.app.ProgressDialog;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Binder;
//import android.os.Bundle;
//import android.renderscript.ScriptGroup;
//import android.util.Pair;
//import android.view.View;
//import android.widget.Button;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//
//import com.example.apptest.databinding.ActivityDashboardBinding;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QuerySnapshot;
//
////import com.example.apptest.databinding.ActivityDashboardBinding;
//import org.w3c.dom.Text;
//
//import java.sql.Time;
//import java.util.List;
//
//public class Dashboard extends AppCompatActivity {
//    Button ButtonSlot1, ButtonSlot2, ButtonSlot3, ButtonSlot4;
//    CardView layout1, layout2, layout3, layout4;
//    TextView Slot1,Slot2,Slot3,Slot4;
//    TextView Status1,Status2,Status3,Status4;
//    @Override
//    protected void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dashboard);
//
//        Status1 = findViewById(R.id.status1);
//        Status2 = findViewById(R.id.status2);
//        Status3 = findViewById(R.id.status3);
//        Status4 = findViewById(R.id.status4);
//
//        Slot1 = findViewById(R.id.slot1);
//        Slot2 = findViewById(R.id.slot2);
//        Slot3 = findViewById(R.id.slot3);
//        Slot4 = findViewById(R.id.slot4);
//
//        ButtonSlot1 = findViewById(R.id.btnslot1);
//        ButtonSlot2 = findViewById(R.id.btnslot2);
//        ButtonSlot3 = findViewById(R.id.btnslot3);
//        ButtonSlot4 = findViewById(R.id.btnslot4);
//
//        ButtonSlot1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                layout1.setBackgroundResource(R.color.red);
//                button1changed();
//            }
//        });
//        ButtonSlot2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                layout2.setBackgroundResource(R.color.yellow);
//                button2changed();
//            }
//        });
//        ButtonSlot3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                layout3.setBackgroundResource(R.color.green);
//                button3changed();
//            }
//        });
//        ButtonSlot4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                layout4.setBackgroundResource(R.color.yellow);
//                button4changed();
//            }
//        });
//
//
//
//
//    }
//
//    public void button1changed() {
//        layout1.setCardBackgroundColor(R.color.red);
//        Intent intent = new Intent(getApplicationContext(), UserProfile.class);
//        startActivity(intent);
//    }
//
//    public void button2changed() {
//        layout2.setBackgroundResource(R.color.yellow);
//    }
//
//    public void button3changed() {
//        layout3.setBackgroundResource(R.color.green);
//    }
//
//    public void button4changed() {
//        layout4.setBackgroundResource(R.color.yellow);
//    }
//
//
//
//}