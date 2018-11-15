package com.example.abodimazen.ChildCare;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.abodimazen.fahad.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Child_Profile extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private TextView name;
    private TextView gender;
    private TextView birth;
    private TextView bload;
    private TextView Text_Dates;
    private Button Button_record;
    private TextView Text_record;
    private TextView Text_hospial;
    private TextView Text_Plan;
    private TextView Text_Satus;
    private ImageView ImageView_choose_image;
    private  Uri mUri;
    private Button make;
    private DatabaseReference databaseRef;
    private StorageTask mUploadTask;


    private StorageReference mStorageRef;

    private static final int GALLERY_INTENT = 1;

    private FirebaseAuth mAuth;
    int day,month, year, hour,minute;
    int dayFinal, monthFinal, yearFinal, hourFinal, minuteFinal;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();



    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,User.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child__profile);
        mAuth = FirebaseAuth.getInstance();
        make =  findViewById(R.id.make);
        name = findViewById(R.id.text_view_Namee);
        gender = findViewById(R.id.text_view_Gender);
        birth = findViewById(R.id.text_view_Birth);
        bload = findViewById(R.id.text_view_Blood);
        Text_Dates = findViewById(R.id.Text_Dates);
        Text_record = findViewById(R.id.Text_record);
        Text_hospial = findViewById(R.id.Text_hospial);
        Text_Plan = findViewById(R.id.Text_Plan);
        Text_Satus = findViewById(R.id.Text_Satus);
        Button_record = findViewById(R.id.Button_record);
        ImageView_choose_image = findViewById(R.id.ImageView_choose_image);
        mStorageRef = FirebaseStorage.getInstance().getReference("Child Photo");
        databaseRef = FirebaseDatabase.getInstance().getReference("Child Photo");



        Button_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent P = getIntent();
                final Bundle a = P.getExtras();

                String n = (String) a.get("id");
                Intent q = new Intent(Child_Profile.this, Child_record.class);
                q.putExtra("id", n);
                startActivity(q);
            }
        });

        make.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder mBuilder = new AlertDialog.Builder(Child_Profile.this);
                View mview = getLayoutInflater().inflate(R.layout.activity_hos_sp, null);
                mBuilder.setTitle("Chooes a Hospital");
                final Spinner mSpinner = (Spinner) mview.findViewById(R.id.spinner_hos);
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(Child_Profile.this,
                        android.R.layout.simple_spinner_item,
                        getResources().getStringArray(R.array.hospitalList));
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                mSpinner.setAdapter(adapter);
                mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (i == 0) {
                            Text_hospial .setText("King Abdullah hospital");
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
                            Text_hospial .setText("Suliman Fakeh Hospital");

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

                        DatePickerDialog datePickerDialog = new DatePickerDialog(Child_Profile.this, Child_Profile.this,
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
                            String pDates = documentSnapshot.getString("appounment");
                            String pHospital = documentSnapshot.getString("hospital");
                            String pPlan = documentSnapshot.getString("typeOfPlan");
                            String pSatus = documentSnapshot.getString("planSatus");
                            String Lastvaccination = documentSnapshot.getString("lastvaccination");



                            name.setText(pName);
                            gender.setText(pgender);
                            birth.setText(pbirth);
                            bload.setText(pbload);
                            Text_Dates.setText(pDates);
                            Text_hospial.setText(pHospital);
                            Text_Plan.setText(pPlan);
                            Text_Satus.setText(pSatus);
                            Text_record.setText(Lastvaccination);



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

        TimePickerDialog timePickerDialog = new TimePickerDialog(Child_Profile.this, Child_Profile.this,
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

            String appounment = "Your Appounment" + "\n" + "date: " + dayFinal + "/" + monthFinal  +"/" +  yearFinal + "\n" + "Time: " + hourFinal + " : "+ minuteFinal;


            Map<String, Object> Appointments = new HashMap<>();
            Appointments.put("appounment",  appounment );
            db.collection("Child Profile").document(n).update(Appointments);

        }
        Intent b = getIntent();
        final Bundle c = b.getExtras();

        String n = (String) c.get("id");
        Intent q = new Intent(Child_Profile.this, Plan.class);
        q.putExtra("id", n);
        startActivity(q);

    }

    private void openFileChooser() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, GALLERY_INTENT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK && data != null && data.getData()!= null) {

            mUri = data.getData();
            Picasso.with(this).load(mUri).into(ImageView_choose_image);
            //ImageView_choose_image.setImageURI(mUri);



        }
    }


    public void button_choose_image(View view) {
        if (mUploadTask != null && mUploadTask.isInProgress()){
            Toast.makeText(Child_Profile.this, "Upload in progress",Toast.LENGTH_SHORT).show();

        }else{

        }

        openFileChooser();
        uploadFile();


    }

    private String getFileExtenstion(Uri uri){
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));


    }
    private void uploadFile() {
        if (mUri != null){
            final StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
            + "." + getFileExtenstion(mUri));

            mUploadTask = fileReference.putFile(mUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(Child_Profile.this, "Upload successful", Toast.LENGTH_LONG).show();
                            Upload upload = new Upload(fileReference.getDownloadUrl().toString());




                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Child_Profile.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
        }else{
            Toast.makeText(this,"No file Selected", Toast.LENGTH_SHORT).show();

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    private void signOut() {
        AuthUI.getInstance().signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        startActivity(new Intent(Child_Profile.this,MainActivity.class));
                        finish();
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_sign_out:
                signOut();

            case R.id.action_Profile:
                startActivity(new Intent(Child_Profile.this,Profile.class));
                finish();

        }



        return super.onOptionsItemSelected(item);
    }


}

