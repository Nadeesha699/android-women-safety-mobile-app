package com.example.womensafetyapp;

import static com.example.womensafetyapp.R.id.home_btn;
import static com.example.womensafetyapp.R.id.police_btn;
import static com.example.womensafetyapp.R.id.profile_btn;
import static com.example.womensafetyapp.R.id.textView3;
import static com.example.womensafetyapp.R.id.txt_gnum;
import static com.example.womensafetyapp.R.id.txt_phno;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.List;
import java.util.Locale;
import android.os.Handler;


public class SOS extends AppCompatActivity implements LocationListener{


    ImageButton button_location;

    TextView textView_location;
    LocationManager locationManager;

    public String gnum;
    public String pnum;
    ImageButton home;
    ImageButton  police;
    ImageButton profile;

    ImageButton guar;
    ImageButton call;

    public String googlemaplink="link";
    private RequestQueue mQueue;
    final Handler handler = new Handler();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);

        textView_location = findViewById(R.id.textView3);
        button_location = findViewById(R.id.sosButton);
        home = findViewById(R.id.home_btn);
        police = findViewById(R.id.police_btn);
        profile = findViewById(R.id.profile_btn);
        guar = findViewById(R.id.gaur_btn);
        call = findViewById(R.id.call);

        mQueue = Volley.newRequestQueue(this);


        Intent intent = getIntent();
        pnum = intent.getStringExtra("pnum");
        gnum = intent.getStringExtra("gnum");
        //textView_location.setText(gnum);

        //Runtime permissions
        if (ContextCompat.checkSelfPermission(SOS.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(SOS.this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            }, 100);
        }

        button_location.setOnClickListener(new View.OnClickListener() {
                                               @Override
                                               public void onClick(View v) {
                                                   //create method
                                                   getLocation();

                                                   handler.postDelayed(new Runnable() {
                                                       @Override
                                                       public void run() {
                                                           // Do something after 5s = 5000ms
                                                           jsonParse();
                                                       }
                                                   }, 30000);


                                               }
                                           }

        );

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
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:0711735332"));
                startActivity(callIntent);
            }

        });
    }catch (Exception e){
            Toast.makeText(SOS.this, "somthing went wrong!!", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {

        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,SOS.this);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(this, ""+location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show();
         googlemaplink = "https://maps.google.com/?q="+location.getLatitude()+","+location.getLongitude();

        try {
            Geocoder geocoder = new Geocoder(SOS.this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            String address = addresses.get(0).getAddressLine(0);

            Log.i("LINK",""+googlemaplink+"");

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
    private void jsonParse() {

        try {

        Log.i("FAIL", "" + googlemaplink + "");


        String url = "https://app.notify.lk/api/v1/send?user_id=24970&api_key=mCdiGUX0FREzyD2VNpUQ&sender_id=NotifyDEMO&to=" + gnum + "&message=" + googlemaplink + " Im in trouble Please Help Me "+ "";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("FAIL", response.toString());

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);
    }catch (Exception e){
            Toast.makeText(SOS.this, "somthing wrong!!", Toast.LENGTH_SHORT).show();
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