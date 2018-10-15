package com.example.abodimazen.fahad;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Hospital extends AppCompatActivity implements View.OnClickListener {

     FirebaseAuth mAuth;
    EditText email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);


        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        findViewById(R.id.sign_in).setOnClickListener(this);
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


        mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Hospital.this, "User Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Hospital.this, Pro_hospital.class);

                    startActivity(intent);
                } else {
                    // Task failed with an exception
                    Toast.makeText(Hospital.this,task.getException().getMessage(), Toast.LENGTH_LONG).show();

                }
                }

        });
    }

    @Override
    public void onClick(View view) {

                hospital();

        }

    }

