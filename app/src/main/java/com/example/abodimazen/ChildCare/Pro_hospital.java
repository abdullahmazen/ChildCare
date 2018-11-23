package com.example.abodimazen.ChildCare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.abodimazen.fahad.R;
import com.google.firebase.auth.FirebaseAuth;

public class Pro_hospital extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button appointment, bTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_hospital);

        bTest = findViewById(R.id.bTest);

        appointment = findViewById(R.id.HosApp);

        mAuth = FirebaseAuth.getInstance();

        bTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Pro_hospital.this, HospitalBlood.class);
                startActivity(i);
            }
        });

        appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Pro_hospital.this, HospitalAppointments.class);
                startActivity(i);
            }
        });

    }
    private void Logout(){
        mAuth.signOut();
        finish();
        Intent i =new Intent(Pro_hospital.this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.logoutMenu:{
                Logout();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
