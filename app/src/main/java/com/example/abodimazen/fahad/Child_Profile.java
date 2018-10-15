package com.example.abodimazen.fahad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Child_Profile extends AppCompatActivity {
    private TextView name;
    private TextView gender;
    private TextView birth;
    private TextView bload;
    private TextView Text_Dates;
    private TextView Text_Time;
    private Button Button_record;
    private TextView Text_record;
    private TextView Text_hospial;
    private TextView Text_Plan;
    private TextView Text_Satus;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child__profile);

        name = findViewById(R.id.text_view_Namee);
        gender = findViewById(R.id.text_view_Gender);
        birth = findViewById(R.id.text_view_Birth);
        bload = findViewById(R.id.text_view_Blood);
        Text_Dates = findViewById(R.id.Text_Dates);
        Text_Time = findViewById(R.id.Text_Time);
        Text_record = findViewById(R.id.Text_record);
        Text_hospial = findViewById(R.id.Text_hospial);
        Text_Plan = findViewById(R.id.Text_Plan);
        Text_Satus = findViewById(R.id.Text_Satus);
        Button_record = findViewById(R.id.Button_record);



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
                            String pDates = documentSnapshot.getString("dates");
                            String pTime = documentSnapshot.getString("time");
                            String pHospital = documentSnapshot.getString("hospital");
                            String pPlan = documentSnapshot.getString("typeOfPlan");
                            String pSatus = documentSnapshot.getString("satus");


                            name.setText(pName);
                            gender.setText(pgender);
                            birth.setText(pbirth);
                            bload.setText(pbload);
                            Text_Dates.setText(pDates);
                            Text_Time.setText(pTime);
                            Text_hospial.setText(pHospital);
                            Text_Plan.setText(pPlan);
                            Text_Satus.setText(pSatus);


                        }
                    });

        }



    }
}
