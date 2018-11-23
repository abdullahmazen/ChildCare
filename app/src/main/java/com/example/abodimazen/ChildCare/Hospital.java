package com.example.abodimazen.ChildCare;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.abodimazen.fahad.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Hospital extends AppCompatActivity implements View.OnClickListener {
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private EditText email,password;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser user;
    private Boolean emailAddressChecker;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        db = FirebaseFirestore.getInstance();
        user = mAuth.getCurrentUser();
        progressBar = findViewById(R.id.progressbar);

        findViewById(R.id.sign_in).setOnClickListener(this);

        Button sign_up = findViewById(R.id.sign_up);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();
                Intent i = new Intent(getApplicationContext(),RegisterationAct.class);
                startActivity(i);
            }
        });
    }

    private  void hospital(){
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();

        mAuth = FirebaseAuth.getInstance();

        if (Email.isEmpty()) {
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }
        if (Password.isEmpty()) {
            password.setError("Password is required!");
            password.requestFocus();
            return;
        }
        if (Password.length() < 6) {
            password.setError("Minimum length of password should be 6!");
            password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //so u can't go back to login screen
                    //finish();
                    // Task completed successfully
                    db.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {

                                user = mAuth.getCurrentUser();
                                emailAddressChecker = user.isEmailVerified();

                                if (emailAddressChecker) {

                                    DocumentSnapshot documentSnapshot = task.getResult();

                                    String typeOfUser = documentSnapshot.getString("typeOfUser");
                                    String id = documentSnapshot.getString("id");

                                    if (typeOfUser.equals("User")) {

                                        Intent i = new Intent(Hospital.this, Profile.class);
                                        startActivity(i);
                                        finish();

                                    } else if (typeOfUser.equals("Hospital Employee") && id.equals("tQBLz8a26df6CNk9J0xZd41i2nG3")) {

                                        Intent i = new Intent(Hospital.this, Pro_hospital.class);
                                        startActivity(i);
                                        finish();
                                    }else if (typeOfUser.equals("Hospital Employee") && id.equals("6ZxdX0wOS7fWSLhQCKb9zt7EQdy2")) {

                                        Intent i = new Intent(Hospital.this, HospitalAct1F.class);
                                        startActivity(i);
                                        finish();
                                    }
                                }else {

                                    Toast.makeText(Hospital.this, "Please verify your Account! ", Toast.LENGTH_SHORT).show();
                                    mAuth.signOut();
                                }
                            }
                        }
                    });

                    //so it clears the stack, so he can't go back to login activity
                    //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                } else {
                    // Task failed with an exception
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onClick(View view) {

                hospital();

        }

    }

