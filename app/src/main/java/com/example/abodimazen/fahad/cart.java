package com.example.abodimazen.fahad;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;



public class cart extends AppCompatActivity {

    private TextView plan;
    private TextView price;

    private Button CheckOut;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        CheckOut = (Button) findViewById(R.id.CheckOut);

        plan = findViewById(R.id.View_Plan_cart);
        price = findViewById(R.id.View_Plan_price);
        CheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent P = getIntent();
                final Bundle a = P.getExtras();

                String n = (String) a.get("id");
                Intent q = new Intent(cart.this, Payment.class);
                q.putExtra("id", n);
                startActivity(q);
            }

        });

        Intent in = getIntent();
        final Bundle b = in.getExtras();
        if(b != null) {

            String n = (String) b.get("id");

            db.collection("Child Profile").document(n).get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            String pPlan = documentSnapshot.getString("typeOfPlan");
                            String pPrice = documentSnapshot.getString("price");

                            plan.setText(pPlan);
                            price.setText(pPrice);

                        }
                    });
        }
        }
    }




