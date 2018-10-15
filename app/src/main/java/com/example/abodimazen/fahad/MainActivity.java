package com.example.abodimazen.fahad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button abd = (Button) findViewById(R.id.hospital);
        abd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Hospital.class);
                startActivity(i);
            }
        });

        Button abc = (Button) findViewById(R.id.User);
        abc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent y = new Intent(getApplicationContext(),User.class);
                startActivity(y);
            }
        });
    }
}
