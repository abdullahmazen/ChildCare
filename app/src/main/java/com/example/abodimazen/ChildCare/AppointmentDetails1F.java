package com.example.abodimazen.ChildCare;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abodimazen.fahad.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AppointmentDetails1F extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private TextView parent, address, phone, cname, datebirth, vacc, typeplan, blood,appointment;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_details1_f);


        parent = findViewById(R.id.textView13);
        address = findViewById(R.id.textView14);
        phone = findViewById(R.id.textView15);
        cname = findViewById(R.id.textView16);
        datebirth = findViewById(R.id.textView17);
        vacc = findViewById(R.id.textView18);
        typeplan = findViewById(R.id.textView19);
        blood = findViewById(R.id.textView20);
        appointment = findViewById(R.id.textView21);
        button = findViewById(R.id.button);

        Intent in = getIntent();
        final Bundle b = in.getExtras();

        if(b != null) {

            String n = (String) b.get("id");

            db.collection("Child").document(n).get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            String cName = documentSnapshot.getString("name");
                            String Datebirth = documentSnapshot.getString("dateOfBirth");
                            String Vacc = documentSnapshot.getString("lastVacc");
                            String Typeplan = documentSnapshot.getString("typeOfPlan");
                            String Blood = documentSnapshot.getString("bloodType");
                            String Appointment = documentSnapshot.getString("date");
                            String Id = documentSnapshot.getString("user_id");

                            cname.setText(cName);
                            datebirth.setText(Datebirth);
                            vacc.setText(Vacc);
                            typeplan.setText(Typeplan);
                            blood.setText(Blood);
                            appointment.setText(Appointment);

                            db.collection("Users").document(Id).get()
                                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                        @Override
                                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                                            String Parent = documentSnapshot.getString("name");
                                            String Address = documentSnapshot.getString("address");
                                            String Phone = documentSnapshot.getString("phoneNumber");

                                            parent.setText(Parent);
                                            address.setText(Address);
                                            phone.setText(Phone);

                                        }
                                    });
                        }
                    });
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(b != null) {

                    String n = (String) b.get("id");

                    db.collection("Child").document(n).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {

                            Intent in = getIntent();
                            Bundle b = in.getExtras();
                            String n = (String) b.get("id");

                            String Lastvaccination = documentSnapshot.getString("lastVacc");

                            if (Lastvaccination.equals("At Birth")) {

                                Map<String, Object> lastVacc = new HashMap<>();

                                lastVacc.put("lastVacc", "2 Month");
                                db.collection("Child").document(n).update(lastVacc);

                            }else if(Lastvaccination.equals("2 Month")){

                                Map<String, Object> lastVacc = new HashMap<>();

                                lastVacc.put("lastVacc", "4 Month");
                                db.collection("Child").document(n).update(lastVacc);
                            }else if(Lastvaccination.equals("4 Month")){

                                Map<String, Object> lastVacc = new HashMap<>();

                                lastVacc.put("lastVacc", "6 Month");
                                db.collection("Child").document(n).update(lastVacc);
                            }else if(Lastvaccination.equals("6 Month")){

                                Map<String, Object> lastVacc = new HashMap<>();

                                lastVacc.put("lastVacc", "9 Month");
                                db.collection("Child").document(n).update(lastVacc);
                            }else if(Lastvaccination.equals("9 Month")){

                                Map<String, Object> lastVacc = new HashMap<>();

                                lastVacc.put("lastVacc", "12 Month");
                                db.collection("Child").document(n).update(lastVacc);
                            }else if(Lastvaccination.equals("12 Month")){

                                Map<String, Object> lastVacc = new HashMap<>();

                                lastVacc.put("lastVacc", "18 Month");
                                db.collection("Child").document(n).update(lastVacc);
                            }else if(Lastvaccination.equals("18 Month")){

                                Map<String, Object> lastVacc = new HashMap<>();

                                lastVacc.put("lastVacc", "24 Month");
                                db.collection("Child").document(n).update(lastVacc);
                            }else if(Lastvaccination.equals("24 Month")){

                                Map<String, Object> lastVacc = new HashMap<>();

                                lastVacc.put("lastVacc", "First Primary");
                                db.collection("Child").document(n).update(lastVacc);
                            }

                            Map<String,Object> updates = new HashMap<>();
                            updates.put("date", FieldValue.delete());
                            updates.put("price", FieldValue.delete());
                            updates.put("status", FieldValue.delete());
                            updates.put("typeOfPlan", "");
                            updates.put("dateStatus","no");

                            db.collection("Child").document(n).update(updates).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {

                                        Toast.makeText(AppointmentDetails1F.this, "Done! ", Toast.LENGTH_SHORT).show();

                                    } else {

                                        Toast.makeText(AppointmentDetails1F.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });


                        }
                    });
                }
                Intent i = new Intent(AppointmentDetails1F.this, HospitalAct1F.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
            }
        });
    }
    }

