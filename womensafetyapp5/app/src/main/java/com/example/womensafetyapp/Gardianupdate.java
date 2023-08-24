package com.example.womensafetyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Gardianupdate extends AppCompatActivity {

    public String gnum;
    public String pnum;

    EditText txtgname, txtgnum;
    Button btnsingup2;

    ImageButton home;
    ImageButton  police;
    ImageButton profile;

    ImageButton guar;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://women-safe-app-default-rtdb.asia-southeast1.firebasedatabase.app/");


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.gardian);

        txtgname = findViewById(R.id.txt_gname);
        txtgnum = findViewById(R.id.txt_gnum);
        btnsingup2 = findViewById(R.id.btn_done);

        home = findViewById(R.id.home_btn);
        police = findViewById(R.id.police_btn);
        profile = findViewById(R.id.profile_btn);
        guar = findViewById(R.id.gaur_btn);

        Intent intent = getIntent();
        pnum = intent.getStringExtra("pnum");
        gnum = intent.getStringExtra("gnum");

        btnsingup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phno, gname, gnum;
                gname = String.valueOf(txtgname.getText());
                gnum = String.valueOf(txtgnum.getText());
                phno = pnum;

                if (TextUtils.isEmpty(gname)) {
                    Toast.makeText(Gardianupdate.this, "Enter Guardian name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(gnum)) {
                    Toast.makeText(Gardianupdate.this, "Enter Guardian Phone Number", Toast.LENGTH_SHORT).show();
                } else {

                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (snapshot.hasChild(phno)) {


                                //txtgname.setText(guardianname);
                                //txtgnum.setText(gnum);

                                databaseReference.child("users").child(phno).child("guardianname").setValue(gname);
                                databaseReference.child("users").child(phno).child("guardiannumber").setValue(gnum);

                                Toast.makeText(Gardianupdate.this, "Guardian update successfully.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Gardianupdate.this, "Somthing went wrong please login again", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(Gardianupdate.this, "somthing went wrong!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SOS.class);
                intent.putExtra("pnum", pnum);
                intent.putExtra("gnum", gnum);
                startActivity(intent);
                finish();
            }
        });
        police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Search.class);
                intent.putExtra("pnum", pnum);
                intent.putExtra("gnum", gnum);
                startActivity(intent);
                finish();
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Profileview.class);
                intent.putExtra("pnum", pnum);
                intent.putExtra("gnum", gnum);
                startActivity(intent);
                finish();
            }
        });
        guar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Gardianupdate.class);
                intent.putExtra("pnum", pnum);
                intent.putExtra("gnum", gnum);
                startActivity(intent);
                finish();
            }
        });
    }catch (Exception e){
            Toast.makeText(Gardianupdate.this, "somthing wrong!!", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent =new Intent(getApplicationContext(), SOS.class);
        intent.putExtra("pnum", pnum);
        intent.putExtra("gnum", gnum);
        startActivity(intent);
        finish();
    }
}