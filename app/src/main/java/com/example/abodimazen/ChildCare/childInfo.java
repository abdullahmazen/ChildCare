package com.example.abodimazen.ChildCare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.abodimazen.fahad.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class childInfo extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private TextView parent, address, phone, cname, datebirth, vacc, typeplan, blood,appointment, hospitalname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_info);


        parent = findViewById(R.id.textView13);
        address = findViewById(R.id.textView14);
        phone = findViewById(R.id.textView15);
        cname = findViewById(R.id.textView16);
        datebirth = findViewById(R.id.textView17);
        vacc = findViewById(R.id.textView18);
        typeplan = findViewById(R.id.textView19);
        blood = findViewById(R.id.textView20);
        appointment = findViewById(R.id.textView21);
        hospitalname = findViewById(R.id.textView22);

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
                            String Hospitalname = documentSnapshot.getString("hospitalName");
                            String Id = documentSnapshot.getString("user_id");

                            cname.setText(cName);
                            datebirth.setText(Datebirth);
                            vacc.setText(Vacc);
                            typeplan.setText(Typeplan);
                            blood.setText(Blood);
                            appointment.setText(Appointment);
                            hospitalname.setText(Hospitalname);

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
    }
    }

