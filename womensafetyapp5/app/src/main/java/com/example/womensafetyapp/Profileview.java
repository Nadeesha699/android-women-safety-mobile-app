package com.example.womensafetyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profileview extends AppCompatActivity {

    public String gnum;
    public String pnum;

    EditText txtemail, txtusername, txtpasw, txtrepsw, txtaddress ;
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
        setContentView(R.layout.progileupdate);

        //txtusername = findViewById(R.id.txt);
        txtemail = findViewById(R.id.txtemail);
        txtpasw = findViewById(R.id.txtpsw);
        txtaddress = findViewById(R.id.txtaddr);
        btnsingup2 = findViewById(R.id.btn_signup);


        Intent intent = getIntent();
        pnum = intent.getStringExtra("pnum");
        gnum = intent.getStringExtra("gnum");

        databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                final String getPassword = snapshot.child(pnum).child("password").getValue(String.class);
                final String getaddr = snapshot.child(pnum).child("address").getValue(String.class);
                final String getemail = snapshot.child(pnum).child("email").getValue(String.class);
                final String getuname = snapshot.child(pnum).child("username").getValue(String.class);

                TextView add = (TextView) findViewById(R.id.txtaddr);
                add.setText(getaddr);
                TextView psw = (TextView) findViewById(R.id.txtpsw);
                psw.setText(getPassword);
                TextView email = (TextView) findViewById(R.id.txtemail);
                email.setText(getemail);
                TextView uname = (TextView) findViewById(R.id.txtuname);
                uname.setText(getuname);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profileview.this, "Somthing Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });


        btnsingup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SOS.class);
                intent.putExtra("pnum", pnum);
                intent.putExtra("gnum", gnum);
                startActivity(intent);
                finish();
            }
        });
        /*btnsingup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username, email, psw, phno, addr;
                username = String.valueOf(txtusername.getText());
                email = String.valueOf(txtemail.getText());
                psw = String.valueOf(txtpasw.getText());
                addr = String.valueOf(txtaddress.getText());
                phno = pnum;

                if (TextUtils.isEmpty(username)){
                    Toast.makeText(Profileview.this,"Enter Username",Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(email)){
                    Toast.makeText(Profileview.this,"Enter Email",Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(psw)){
                    Toast.makeText(Profileview.this,"Enter Password",Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(addr)){
                    Toast.makeText(Profileview.this,"Enter Address",Toast.LENGTH_SHORT).show();
                }

                else{

                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.hasChild(phno)){
                                databaseReference.child("users").child(phno).child("username").setValue(username);
                                databaseReference.child("users").child(phno).child("email").setValue(email);
                                databaseReference.child("users").child(phno).child("password").setValue(psw);
                                databaseReference.child("users").child(phno).child("address").setValue(addr);


                            }
                            else{
                                Toast.makeText(Profileview.this,"Somthing went wrong please login again", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(Profileview.this,"somthing went wrong!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });*/


    }catch (Exception e){
            Toast.makeText(Profileview.this, "somthing wrong!!", Toast.LENGTH_SHORT).show();
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