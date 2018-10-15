package com.example.abodimazen.fahad;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Plan extends AppCompatActivity {
    private Button standerd;
    private Button plus;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan2);

        standerd = findViewById(R.id.standerdd);
        plus = findViewById(R.id.pluss);

        standerd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent P = getIntent();
                final Bundle a = P.getExtras();

                    String n = (String) a.get("id");

                    String pStandred = standerd.getText().toString();

                    Map<String, Object> Standred = new HashMap<>();
                    Standred.put("typeOfPlan", "Standred");
                    Standred.put("price", "0.00");
                    db.collection("Child Profile").document(n).update(Standred);




                Intent q = new Intent(Plan.this, cart.class);

                q.putExtra("id", n);
                startActivity(q);



    }
});

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent P = getIntent();
                final Bundle a = P.getExtras();

                    String n = (String) a.get("id");

                    String pPlus = plus.getText().toString();
                    Map<String, Object> Plus = new HashMap<>();
                    Plus.put("typeOfPlan", "Plus");
                    Plus.put("price", "13.23");
                    db.collection("Child Profile").document(n).update(Plus);

                    Intent q = new Intent(Plan.this, cart.class);

                    q.putExtra("id", n);
                    startActivity(q);

            }
        });
    }
}
