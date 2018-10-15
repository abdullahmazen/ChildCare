package com.example.abodimazen.fahad;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class NewChild extends AppCompatActivity {
    private static final String TAG = "NewChild";
    private static final String Full_Name = "Full Name";
    private static final String date_of_birth = "Date of Birth";
    private static final String sex = "Sex";
    private static final String TypeB = "Type of Bload";



    private Spinner spinner;
    private Spinner spinner1;
    private EditText editTextName;
    private EditText editTextBirth;
    private TextView TextViewSex;
    private TextView TextViewBload;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_child);


        editTextName = findViewById(R.id.Full_Name);
        editTextBirth = findViewById(R.id.Date);
        TextViewSex = findViewById(R.id.gender);
        TextViewBload = findViewById(R.id.TypeB);



        spinner = findViewById(R.id.spinnerGender);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Sex.Sex));


        spinner1 = findViewById(R.id.spinnerBlod);
        spinner1.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Bloods.Boolds));



    }


    public void add(View v) {

        String Name = editTextName.getText().toString();
        String Birth = editTextBirth.getText().toString();
        String gender = Sex.Sex[spinner.getSelectedItemPosition()];
        String Bload = Bloods.Boolds[spinner1.getSelectedItemPosition()];
        String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String hospital = "";
        String TypeOfPlan ="";
        String Price = "";
        String Time = "";
        String Dates = "";
        String Satus = "";






        addp addd = new addp( Name,  Birth,  gender,  Bload, user_id, hospital, TypeOfPlan,Price,Time,Dates, Satus);

        addd.setName(Name);
        addd.setBirth(Birth);
        addd.setGender(gender);
        addd.setBload(Bload);
        addd.setUser_id(user_id);
        addd.setHospital(hospital);
        addd.setTypeOfPlan(TypeOfPlan);
        addd.setPrice(Price);
        addd.setTime(Time);
        addd.setDates(Dates);
        addd.setSatus(Satus);


        db.collection("Child Profile").document().set(addd)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(NewChild.this,"Chld Information Saved",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(NewChild.this, Profile.class);

                        startActivity(intent);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(NewChild.this,"Error",Toast.LENGTH_SHORT).show();
                        Log.d(TAG,  e.toString());

                    }
                });


    }

}
