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

    private Button Button_record,information,bloodResult,upload,make;
    private TextView Text_record;
    private TextView info,textView5;

    private ImageView ImageView_choose_image;
    private  Uri mUri;
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
        make =  findViewById(R.id.imageView);
        name = findViewById(R.id.nameProfile);
        Text_record = findViewById(R.id.textView6);
        info = findViewById(R.id.textView4);
        Button_record = findViewById(R.id.record);
        ImageView_choose_image = findViewById(R.id.imageView4);
        mStorageRef = FirebaseStorage.getInstance().getReference("Photo");
        upload = findViewById(R.id.buttonUpload);
        information = findViewById(R.id.info);
        bloodResult = findViewById(R.id.bloodResults);
        textView5 = findViewById(R.id.textView5);





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
                            Intent P = getIntent();
                            final Bundle a = P.getExtras();
                            if (a != null) {
                                String n = (String) a.get("id");

                                Map<String, Object> Ahospital = new HashMap<>();

                                Ahospital.put("hospitalName", "King Abdullah Hospital");
                                db.collection("Child").document(n).update(Ahospital);
                            }


                        }
                        if (i == 1) {
                            Intent P = getIntent();
                            final Bundle a = P.getExtras();
                            if (a != null) {
                                String n = (String) a.get("id");

                                Map<String, Object> Shospital = new HashMap<>();

                                Shospital.put("hospitalName", "Soliman Fakeeh Hospital");
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
                            String pPlan = documentSnapshot.getString("typeOfPlan");
                            String photo = documentSnapshot.getString("photoURL");



                            if(!photo.isEmpty()){
                                setImageUrl(photo);
                            }

                            name.setText(pName);

                            if(pPlan.equals("Plus")){
                                bloodResult.setVisibility(View.VISIBLE);
                                textView5.setVisibility(View.VISIBLE);
                            }else{
                                bloodResult.setVisibility(View.GONE);
                                textView5.setVisibility(View.GONE);
                            }
                        }
                    });
        }
        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = getIntent();
                Bundle b = in.getExtras();

                if(b != null) {

                    String n = (String) b.get("id");
                    Intent i = new Intent(Child_Profile.this, childInfo.class);
                    i.putExtra("id", n);
                    startActivity(i);
                }
            }
        });



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
        ImageView_choose_image = findViewById(R.id.imageView4);

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

