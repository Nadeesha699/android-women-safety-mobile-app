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

public class login extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://women-safe-app-default-rtdb.asia-southeast1.firebasedatabase.app/");

    EditText txtpsw, txtpnum;
    Button login;
    public static final String tpmum = "tpm";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try{

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        txtpnum = findViewById(R.id.txt_pno);
        txtpsw = findViewById(R.id.txt_psw);
        login = findViewById(R.id.btn_login2);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pnum, psw;
                pnum = String.valueOf(txtpnum.getText());
                psw = String.valueOf(txtpsw.getText());

                if (TextUtils.isEmpty(pnum)) {
                    Toast.makeText(login.this, "Enter phone number", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(psw)) {
                    Toast.makeText(login.this, "Enter Password", Toast.LENGTH_SHORT).show();
                } else {

                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if (snapshot.hasChild(pnum)) {

                                final String getPassword = snapshot.child(pnum).child("password").getValue(String.class);
                                final String getgnum = snapshot.child(pnum).child("guardiannumber").getValue(String.class);

                                if (getPassword.equals(psw)) {
                                    Toast.makeText(login.this, "Successful Login", Toast.LENGTH_SHORT).show();
                                    //startActivity(new Intent(login.this, SOS.class));
                                    Intent intent = new Intent(getApplicationContext(), SOS.class);
                                    intent.putExtra("pnum", pnum);
                                    intent.putExtra("gnum", getgnum);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(login.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(login.this, "You dont have any account create one", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(login.this, "somthing went wrong!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }catch (Exception e){
            Toast.makeText(login.this, "somthing wrong!!", Toast.LENGTH_SHORT).show();
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