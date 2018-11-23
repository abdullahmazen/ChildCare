package com.example.abodimazen.ChildCare;

import android.app.AlertDialog;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.abodimazen.fahad.R;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import java.util.HashMap;
import java.util.Map;

public class Child_Profile extends AppCompatActivity{
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
    private Button make,upload;
    private StorageTask mUploadTask;


    private StorageReference mStorageRef;

    private static final int GALLERY_INTENT = 1;

    private FirebaseAuth mAuth;


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
        mStorageRef = FirebaseStorage.getInstance().getReference("Photo");
        upload = findViewById(R.id.buttonUpload);



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
                                db.collection("Child").document(n).update(Ahospital);
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
                                db.collection("Child").document(n).update(Shospital);
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

                        Intent in = getIntent();
                        Bundle b = in.getExtras();

                        if(b != null) {

                            String n = (String) b.get("id");
                            Intent intent = new Intent(Child_Profile.this, BookingDates.class);
                            intent.putExtra("id", n);
                            startActivity(intent);
                        }


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

            db.collection("Child").document(n).get()
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
                            String photo = documentSnapshot.getString("photoURL");
                            String Appointment = documentSnapshot.getString("date");



                            if(!photo.isEmpty()){
                                setImageUrl(photo);
                            }

                            name.setText(pName);
                            gender.setText(pgender);
                            birth.setText(pbirth);
                            bload.setText(pbload);
                            Text_Dates.setText(pDates);
                            Text_hospial.setText(pHospital);
                            Text_Plan.setText(pPlan);
                            Text_Satus.setText(pSatus);
                            Text_record.setText(Lastvaccination);
                            Text_Dates.setText(Appointment);



                        }
                    });



        }
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mUploadTask != null && mUploadTask.isInProgress()){
                    Toast.makeText(Child_Profile.this, "Upload in progress",Toast.LENGTH_SHORT).show();

                }else{
                    uploadFile();
                }


            }
        });



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

        }
    }


    public void button_choose_image(View view) {

        openFileChooser();

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

            mUploadTask = fileReference.putFile(mUri);

            Task<Uri> urlTask = mUploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }

                    // Continue with the task to get the download URL
                    return fileReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();

                        Intent in = getIntent();
                        Bundle b = in.getExtras();

                        if(b != null) {



                            String file = downloadUri.toString();

                            String n = (String) b.get("id");

                            Map<String, Object> photo = new HashMap<>();

                            photo.put("photoURL", file);

                            db.collection("Child").document(n).update(photo);

                            Toast.makeText(Child_Profile.this, "Uploaded!", Toast.LENGTH_SHORT).show();

                        }

                    } else {
                        Toast.makeText(Child_Profile.this, "Error!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }else{
            Toast.makeText(this,"No file Selected", Toast.LENGTH_SHORT).show();

        }

    }
    public void setImageUrl(String imageUrl) {
        ImageView_choose_image = findViewById(R.id.ImageView_choose_image);

        Picasso.with(this)
                .load(imageUrl)
                .fit()
                .into(ImageView_choose_image);
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

