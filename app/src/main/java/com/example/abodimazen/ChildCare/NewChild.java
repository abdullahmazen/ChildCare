package com.example.abodimazen.ChildCare;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.abodimazen.fahad.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class NewChild extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener  {
    private static final String TAG = "NewChild";
    private static final String Full_Name = "Full Name";
    private static final String date_of_birth = "Date of Birth";
    private static final String sex = "Sex";
    private static final String TypeB = "Type of Bload";
    int day,month, year;
    int dayFinal, monthFinal, yearFinal;



    private Spinner spinner;
    private Spinner spinner1;
    private EditText editTextName;
    private TextView TextViewBirth;
    private TextView TextViewSex;
    private TextView TextViewBload;
    private EditText editTextPlace_Birth;
    private Spinner Spinner_Vaccination;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_child);


        editTextName = findViewById(R.id.Full_Name);
        TextViewBirth = findViewById(R.id.TextViewBirth);
        TextViewSex = findViewById(R.id.gender);
        TextViewBload = findViewById(R.id.TypeB);
        editTextPlace_Birth = findViewById(R.id.Place_Birth);

        spinner = findViewById(R.id.spinnerGender);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Sex.Sex));


        spinner1 = findViewById(R.id.spinnerBlod);
        spinner1.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Bloods.Boolds));




        Spinner_Vaccination = findViewById(R.id.Spinner_Vaccination);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.vaccinationlist, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner_Vaccination.setAdapter(adapter);
            }










    public void add(View v) {

        String Name = editTextName.getText().toString();
        String DateOfBirth =  dayFinal + "/" + monthFinal  +"/" +  yearFinal ;
        String PlaceOfBirth = editTextPlace_Birth.getText().toString();
        String gender = Sex.Sex[spinner.getSelectedItemPosition()];
        String BloodType = Bloods.Boolds[spinner1.getSelectedItemPosition()];
        String lastVacc = Spinner_Vaccination.getSelectedItem().toString();
        String user_id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String hospitalName = "";
        String typeOfPlan ="";
        String date = "";
        String PlanSatus = "";
        String PhotoURL = "";





        if(Name.isEmpty()){
            editTextName.setError("Name is required!");
            editTextName.requestFocus();
            return;
        }
        if(BloodType.isEmpty()){
            TextViewBload.requestFocus();
            return;
        }
        if(DateOfBirth.isEmpty()){
            TextViewBirth.setError("Date of birth is required!");
            TextViewBirth.requestFocus();
            return;
        }
        if(gender.isEmpty()){
            TextViewSex.requestFocus();
            return;
        }
        if(PlaceOfBirth.isEmpty()){
            editTextPlace_Birth.setError("Place Of Birth is required!");
            editTextPlace_Birth.requestFocus();
            return;
        }





        addp addd = new addp( Name,  DateOfBirth,  gender,  BloodType, user_id, hospitalName, typeOfPlan,date, PlanSatus, PlaceOfBirth, lastVacc, PhotoURL);

        addd.setName(Name);
        addd.setDateOfBirth(DateOfBirth);
        addd.setGender(gender);
        addd.setBloodType(BloodType);
        addd.setUser_id(user_id);
        addd.setHospital(hospitalName);
        addd.setTypeOfPlan(typeOfPlan);
        addd.setAppounment(date);
        addd.setPlanSatus(PlanSatus);
        addd.setPlace_Birth(PlaceOfBirth);
        addd.setLastVacc(lastVacc);
        addd.setPhotoURL(PhotoURL);



        TextViewBirth.setText(DateOfBirth);







        db.collection("Child").document().set(addd)
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


    public void date(View view) {

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(NewChild.this, NewChild.this,
                year, month, day);
        String a =  dayFinal + "/" + monthFinal  +"/" +  yearFinal ;

        TextViewBirth.setText(a);

        datePickerDialog.show();

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        yearFinal = i;
        monthFinal = i1 + 1;
        dayFinal = i2;

        String b =  dayFinal + "/" + monthFinal  +"/" +  yearFinal ;

        TextViewBirth.setText(b);




        }



    }

