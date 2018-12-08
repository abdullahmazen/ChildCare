package com.example.abodimazen.ChildCare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.abodimazen.fahad.R;
import com.google.firebase.firestore.FirebaseFirestore;

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

                Map<String, Object> Standard = new HashMap<>();
                Standard.put("typeOfPlan", "Standard");
                Standard.put("price", "50.00");
                db.collection("Child").document(n).update(Standard);


                Intent q = new Intent(Plan.this, Payment.class);
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

                Map<String, Object> Plus = new HashMap<>();
                Plus.put("typeOfPlan", "Plus");
                Plus.put("price", "200.00");
                db.collection("Child").document(n).update(Plus);


                Intent q = new Intent(Plan.this, Payment.class);
                q.putExtra("id", n);
                startActivity(q);
            }
        });
    }
}
