package com.example.abodimazen.ChildCare;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.abodimazen.fahad.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingDates extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_dates);


        spinner = findViewById(R.id.spinner2);

        final Button save = findViewById(R.id.saveDate);

        final List<String> idlist = new ArrayList<>();
        final List<String> list = new ArrayList<>();

        //list.add("Choose a Date");

        db.collection("BookingDates").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {

                        list.add(document.get("Date").toString());
                        idlist.add(document.getId());


                    }


                    final ArrayAdapter<String> adapter = new ArrayAdapter<>(BookingDates.this, android.R.layout.simple_spinner_item
                            ,list);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);

                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, final int i, long l) {
                            save.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String id = idlist.get(i);
                                    String ddate = list.get(i);

                                    Intent b = getIntent();
                                    final Bundle c = b.getExtras();

                                    String n = (String) c.get("id");

                                    Map<String, Object> date = new HashMap<>();

                                    date.put("date", ddate);
                                    date.put("dateStatus","yes");

                                    db.collection("Child").document(n).update(date);

                                    db.collection("BookingDates").document(id).delete();

                                    Intent i = new Intent(BookingDates.this, Plan.class);
                                    i.putExtra("id", n);
                                    startActivity(i);
                                }
                            });

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                } else {
                    Toast.makeText(BookingDates.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
