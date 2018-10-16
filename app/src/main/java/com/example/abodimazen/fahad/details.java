package com.example.abodimazen.fahad;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

import android.text.format.DateFormat;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;


public class details extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{


    private TextView name;
    private TextView gender;
    private TextView birth;
    private TextView bload;
    private Button make;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    int day,month, year, hour,minute;
    int dayFinal, monthFinal, yearFinal, hourFinal, minuteFinal;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        name = findViewById(R.id.text_view_Namee);
        gender = findViewById(R.id.text_view_Gender);
        birth = findViewById(R.id.text_view_Birth);
        bload = findViewById(R.id.text_view_Blood);
        make =  findViewById(R.id.make);




        make.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(details.this);
                View mview = getLayoutInflater().inflate(R.layout.activity_hos_sp, null);
                mBuilder.setTitle("Chooes a Hospital");
                final Spinner mSpinner = (Spinner) mview.findViewById(R.id.spinner_hos);
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(details.this,
                        android.R.layout.simple_spinner_item,
                        getResources().getStringArray(R.array.hospitalList));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner.setAdapter(adapter);
                mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (i == 0) {
                            Intent P = getIntent();
                            final Bundle a = P.getExtras();
                            if (a != null) {
                                String n = (String) a.get("id");


                                Map<String, Object> Ahospital = new HashMap<>();

                                Ahospital.put("hospital", "King Abdullah hospital");
                                db.collection("Child Profile").document(n).update(Ahospital);
                            }


                        }
                        if (i == 1) {
                            Intent P = getIntent();
                            final Bundle a = P.getExtras();
                            if (a != null) {
                                String n = (String) a.get("id");


                                Map<String, Object> Shospital = new HashMap<>();
                                Shospital.put("hospital", "Suliman Fakeh Hospital");
                                db.collection("Child Profile").document(n).update(Shospital);
                            }
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });


                mBuilder.setPositiveButton("Next", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                                Calendar calendar = Calendar.getInstance();
                                year = calendar.get(Calendar.YEAR);
                                month = calendar.get(Calendar.MONTH);
                                day = calendar.get(Calendar.DAY_OF_MONTH);

                                DatePickerDialog datePickerDialog = new DatePickerDialog(details.this, details.this,
                                        year, month, day);

                                datePickerDialog.show();


                            }


                        });










                mBuilder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();

                    }
                });

                mBuilder.setView(mview);
                AlertDialog alertDialog = mBuilder.create();
                alertDialog.show();


            }
        });


        Intent in = getIntent();
        final Bundle b = in.getExtras();
        if (b != null) {

            String n = (String) b.get("id");

            db.collection("Child Profile").document(n).get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            String pName = documentSnapshot.getString("name");
                            String pgender = documentSnapshot.getString("gender");
                            String pbirth = documentSnapshot.getString("birth");
                            String pbload = documentSnapshot.getString("bload");


                            name.setText(pName);
                            gender.setText(pgender);
                            birth.setText(pbirth);
                            bload.setText(pbload);

                        }
                    });

        }




    }




    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        yearFinal = i;
        monthFinal = i1 + 1;
        dayFinal = i2;

        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(details.this, details.this,
                hour, minute, DateFormat.is24HourFormat(this));

        timePickerDialog.show();

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {

        hourFinal = i;
        minuteFinal = i1;


        Intent P = getIntent();
        final Bundle a = P.getExtras();
        if (a != null) {
            String n = (String) a.get("id");


            Map<String, Object> Appointments = new HashMap<>();
            Appointments.put("dates",  "day: " + dayFinal + "month: " + monthFinal + "\n" +  "year: " + yearFinal + "\n");
            Appointments.put("time", "hour: " + hourFinal + "\n" +
                    "minute: " + minuteFinal);
            db.collection("Child Profile").document(n).update(Appointments);

        }
        Intent b = getIntent();
        final Bundle c = b.getExtras();

        String n = (String) c.get("id");
        Intent q = new Intent(details.this, Plan.class);
        q.putExtra("id", n);
        startActivity(q);

    }


}
