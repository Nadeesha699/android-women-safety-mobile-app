package com.example.womensafetyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {


    EditText txtemail, txtusername, txtpasw, txtrepsw, txtpnum, txtaddress, txtgname, txtgnum;
    Button btnsingup2;

    
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://women-safe-app-default-rtdb.asia-southeast1.firebasedatabase.app/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        txtusername = findViewById(R.id.txt_pno);
        txtemail = findViewById(R.id.txt_email);
        txtpasw = findViewById(R.id.txt_psw);
        txtrepsw = findViewById(R.id.txt_repsw);
        txtpnum = findViewById(R.id.txt_phno);
        txtaddress = findViewById(R.id.txt_addr);
        txtgname = findViewById(R.id.txt_gname);
        txtgnum = findViewById(R.id.txt_gnum);
        btnsingup2 = findViewById(R.id.btn_done);

        btnsingup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username, email, psw, repsw, phno, addr, gname, gnum;
                username = String.valueOf(txtusername.getText());
                email = String.valueOf(txtemail.getText());
                psw = String.valueOf(txtpasw.getText());
                repsw = String.valueOf(txtrepsw.getText());
                phno = String.valueOf(txtpnum.getText());
                addr = String.valueOf(txtaddress.getText());
                gname = String.valueOf(txtgname.getText());
                gnum = String.valueOf(txtgnum.getText());

                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(Register.this, "Enter Username", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Register.this, "Enter Email", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(psw)) {
                    Toast.makeText(Register.this, "Enter Password", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(repsw)) {
                    Toast.makeText(Register.this, "Enter Confirm Password", Toast.LENGTH_SHORT).show();
                } else if (psw == repsw) {
                    Toast.makeText(Register.this, "Password & Confirm Password Need to be Same", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(addr)) {
                    Toast.makeText(Register.this, "Enter Address", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(phno)) {
                    Toast.makeText(Register.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(gname)) {
                    Toast.makeText(Register.this, "Enter Guardian name", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(gnum)) {
                    Toast.makeText(Register.this, "Enter Guardian Phone Number", Toast.LENGTH_SHORT).show();
                } else {

                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (snapshot.hasChild(phno)) {
                                Toast.makeText(Register.this, "This user is already registered", Toast.LENGTH_SHORT).show();
                            } else {
                                databaseReference.child("users").child(phno).child("username").setValue(username);
                                databaseReference.child("users").child(phno).child("email").setValue(email);
                                databaseReference.child("users").child(phno).child("password").setValue(psw);
                                databaseReference.child("users").child(phno).child("address").setValue(addr);
                                databaseReference.child("users").child(phno).child("guardianname").setValue(gname);
                                databaseReference.child("users").child(phno).child("guardiannumber").setValue(gnum);

                                Toast.makeText(Register.this, "User registered successfully.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this, login.class));
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(Register.this, "somthing went wrong!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

        });
    }catch (Exception e){
            Toast.makeText(Register.this, "somthing wrong!!", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent =new Intent(getApplicationContext(), Intro.class);
        startActivity(intent);
        finish();
    }
}