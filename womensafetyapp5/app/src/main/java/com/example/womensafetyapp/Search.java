package com.example.womensafetyapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Search extends AppCompatActivity {

    public String gnum;
    public String pnum;

    ImageButton home;
    ImageButton  police;
    ImageButton profile;


    ImageButton guar;
    private EditText searchEditText;
    private TextView resultTextView;
    private ScrollView scrollView;
    private String[] allItems = {    "City:-Colombo\nPolice Station:-Galkissa\nAddress:-122 Main St, Colombo, Sri Lanka\nPhone No:-+94412222222\n\n",
            "City:-Matara\nPolice Station:-Deniyaya\nAddress:-120 Main St, Matara, Sri Lanka\nPhone No:-+94412222277\n\n",
            "City:-Matara\nPolice Station:-Deyyandara\nAddress:-134 Main St, Matara, Sri Lanka\nPhone No:-+94412982222\n\n",
            "City:-Matara\nPolice Station:-Dikwella\nAddress:-145 Main St, Matara, Sri Lanka\nPhone No:-+94412229822\n\n",
            "City:-Matara\nPolice Station:-Ganadara\nAddress:-12 Main St, Matara, Sri Lanka\nPhone No:-+94412224522\n\n",
            "City:-Matara\nPolice Station:-Hakmana\nAddress:-10 Main St, Matara, Sri Lanka\nPhone No:-+94412229522\n\n",
            "City:-Matara\nPolice Station:-Kamburupitiya\nAddress:-20 Main St, Matara, Sri Lanka\nPhone No:-+94413222222\n\n",
            "City:-Matara\nPolice Station:-Kosmodara\nAddress:-11 Main St, Matara, Sri Lanka\nPhone No:-+94412222022\n\n",
            "City:-Matara\nPolice Station:-Kotawila\nAddress:-14 Main St, Matara, Sri Lanka\nPhone No:-+94412221222\n\n",
            "City:-Matara\nPolice Station:-Malimbada\nAddress:-110 Main St, Matara, Sri Lanka\nPhone No:-+94410922222\n\n",
            "City:-Matara\nPolice Station:-Matara\nAddress:-123 Main St, Matara, Sri Lanka\nPhone No:-+94412220122\n\n",
            "City:-Matara\nPolice Station:-Marawila\nAddress:-124 Main St, Matara, Sri Lanka\nPhone No:-+94412442222\n\n",
            "City:-Matara\nPolice Station:-Marawaka\nAddress:-127 Main St, Matara, Sri Lanka\nPhone No:-+94412652222\n\n",
            "City:-Matara\nPolice Station:-Pitabeddara\nAddress:-170 Main St, Matara, Sri Lanka\nPhone No:-+94412222222\n\n",
            "City:-Matara\nPolice Station:-Rotamba\nAddress:-55 Main St, Matara, Sri Lanka\nPhone No:-+94412456222\n\n",
            "City:-Matara\nPolice Station:-Thihagoda\nAddress:-145 Main St, Matara, Sri Lanka\nPhone No:-+94417892222\n\n",
            "City:-Matara\nPolice Station:-Urubokka\nAddress:-45 Main St, Matara, Sri Lanka\nPhone No:-+94412222563\n\n",
            "City:-Matara\nPolice Station:-Weligama\nAddress:-90 Main St, Matara, Sri Lanka\nPhone No:-+94412222785\n\n",
            "City:-Galle\nPolice Station:-Galle\nAddress:-80 Main St, Galle, Sri Lanka\nPhone No:-+94412222456\n\n",
            "City:-Galle\nPolice Station:-Alpitiya\nAddress:-99 Main St, Galle, Sri Lanka\nPhone No:-+94412212522\n\n",
            "City:-Galle\nPolice Station:-Ambalangoda\nAddress:-78 Main St, Galle, Sri Lanka\nPhone No:-+94412224522\n\n",
            "City:-Galle\nPolice Station:-Ahangama\nAddress:-121 Main St, Galle, Sri Lanka\nPhone No:-+94412285222\n\n",
            "City:-Galle\nPolice Station:-Hikkaduwa\nAddress:-122 Main St, Galle, Sri Lanka\nPhone No:-+94412753222\n\n",
            "City:-Galle\nPolice Station:-Karandeniya\nAddress:-123 Main St, Galle, Sri Lanka\nPhone No:-+94412159222\n\n",
            "City:-Galle\nPolice Station:-Yakkalamulla\nAddress:-124 Main St,Galle, Sri Lanka\nPhone No:-+94412896222\n\n",
            "City:-Galle\nPolice Station:-Neluwa\nAddress:-125 Main St, Galle, Sri Lanka\nPhone No:-+94412224592\n\n",
            "City:-Galle\nPolice Station:-Rathgama\nAddress:-126 Main St, Galle, Sri Lanka\nPhone No:-+94412220122\n\n",
            "City:-Galle\nPolice Station:-Baddegama\nAddress:-127 Main St, Galle, Sri Lanka\nPhone No:-+94412242322\n\n",
            "City:-Hambantota\nPolice Station:-Hambantota\nAddress:-2 Main St, Hambantota, Sri Lanka\nPhone No:-+94412223692\n\n",
            "City:-Hambantota\nPolice Station:-Walasmulla\nAddress:-10 Main St, Hambantota, Sri Lanka\nPhone No:-+94412248922\n\n",
            "City:-Hambantota\nPolice Station:-Weeraketiya\nAddress:-15 Main St, Hambantota, Sri Lanka\nPhone No:-+94412749222\n\n",
            "City:-Hambantota\nPolice Station:-Beliatta\nAddress:-19 Main St, Hambantota, Sri Lanka\nPhone No:-+94412221582\n\n",
            "City:-Hambantota\nPolice Station:-Thangalla\nAddress:-22 Main St, Hambantota, Sri Lanka\nPhone No:-+94412224592\n\n",
            "City:-Hambantota\nPolice Station:-Ranna\nAddress:-25 Main St, Hambantota, Sri Lanka\nPhone No:-+94412227592\n\n",
            "City:-Hambantota\nPolice Station:-Ambalatota\nAddress:-37 Main St, Hambantota, Sri Lanka\nPhone No:-+94412227532\n\n",
            "City:-Hambantota\nPolice Station:-Thissamaharamaya\nAddress:-40 Main St, Hambantota, Sri Lanka\nPhone No:-+94418562222\n\n",
            "City:-Hambantota\nPolice Station:-Katharagama\nAddress:-41 Main St, Hambantota, Sri Lanka\nPhone No:-+94412214522\n\n",
            "City:-Hambantota\nPolice Station:-Sooriyawewa\nAddress:-42 Main St, Hambantota, Sri Lanka\nPhone No:-+94412228532\n\n",
            "City:-Colomobo\nPolice Station:-Maradana\nAddress:-44 Main St, Colombo, Sri Lanka\nPhone No:-+94412285622\n\n",
            "City:-Galle\nPolice Station:-Galle\nAddress:-120 Main St, Galle, Sri Lanka\nPhone No:-+94412227852\n\n",
            "City:-Colombo\nPolice Station:-Kirulapana\nAddress:-45 Main St, Colombo, Sri Lanka\nPhone No:-+94412212022\n\n",
            "City:-Colombo\nPolice Station:-Ambalangoda\nAddress:-47 Main St, Colombo, Sri Lanka\nPhone No:-+94412245322\n\n",
            "City:-Colombo\nPolice Station:-Maligawatta\nAddress:-48 Main St, Colombo, Sri Lanka\nPhone No:-+94412285622\n\n",
            "City:-Colombo\nPolice Station:-Grandpass\nAddress:-65 Main St, Colombo, Sri Lanka\nPhone No:-+94412221592\n\n",
            "City:-Colombo\nPolice Station:-Keselwatta\nAddress:-67 Main St, Colombo, Sri Lanka\nPhone No:-+94412224862\n\n",
            "City:-Colombo\nPolice Station:-Fort\nAddress:-128 Main St, Colombo, Sri Lanka\nPhone No:-+94412222222\n\n",
            "City:-Colombo\nPolice Station:-Dam street\nAddress:-131 Main St, Colombo, Sri Lanka\nPhone No:-+94412227852\n\n",
            "City:-Colombo\nPolice Station:-Kotahena\nAddress:-132 Main St, Colombo, Sri Lanka\nPhone No:-+94412222222\n\n",
            "City:-Colombo\nPolice Station:-Armour streat\nAddress:-133 Main St, Colombo, Sri Lanka\nPhone No:-+94412222125\n\n",
            "City:-Colombo\nPolice Station:-Dehiwala\nAddress:-134 Main St, Colombo, Sri Lanka\nPhone No:-+94412222785\n\n",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Intent intent = getIntent();
        pnum = intent.getStringExtra("pnum");
        gnum = intent.getStringExtra("gnum");

        searchEditText = findViewById(R.id.searchE);
        resultTextView = findViewById(R.id.resultT);
        scrollView = findViewById(R.id.scrollV);
        home = findViewById(R.id.home_btn);
        police = findViewById(R.id.police_btn);
        profile = findViewById(R.id.profile_btn);
        guar = findViewById(R.id.gaur_btn);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filterResults(s.toString());
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
            Toast.makeText(Search.this, "somthing wrong!!", Toast.LENGTH_SHORT).show();
        }
    }
        private void filterResults (String query) {

        try {
            StringBuilder filteredItems = new StringBuilder();

            for (String item : allItems) {
                if (item.toLowerCase().contains(query.toLowerCase())) {
                    filteredItems.append(item).append("\n");
                }
            }

            resultTextView.setText(filteredItems.toString());
            resultTextView.setGravity(Gravity.START); // Align text to the start (left)
            scrollView.fullScroll(View.FOCUS_DOWN); // Scroll to the bottom of the ScrollView
        }catch (Exception e){
            Toast.makeText(Search.this, "somthing wrong!!", Toast.LENGTH_SHORT).show();
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

