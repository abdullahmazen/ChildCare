package com.example.abodimazen.ChildCare;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.abodimazen.fahad.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterationAct extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    EditText name,email,password,repeatPassword,phoneNumber,address;
    Spinner typeOfUser;
    private FirebaseAuth mAuth;
    FirebaseUser USER;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        repeatPassword = findViewById(R.id.repeatPassword);
        phoneNumber = findViewById(R.id.phoneNumber);
        address = findViewById(R.id.address);
        typeOfUser = findViewById(R.id.typeOfUser);
        mAuth = FirebaseAuth.getInstance();
        typeOfUser.setVisibility(View.GONE);
        progressBar = findViewById(R.id.progressbar);



        findViewById(R.id.sign_up).setOnClickListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.type_Of_User, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeOfUser.setAdapter(adapter);
        typeOfUser.setOnItemSelectedListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.sign_up:
                registerUser();
                break;

        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void registerUser(){

        final String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();
        final String Name = name.getText().toString().trim();
        final String RepeatPassword = repeatPassword.getText().toString().trim();
        final String PhoneNumber = phoneNumber.getText().toString().trim();
        final String Address = address.getText().toString().trim();
        final String TypeOfUser = typeOfUser.getSelectedItem().toString();

        if(Name.isEmpty()){
            name.setError("Please enter your name!");
            name.requestFocus();
            return;
        }

        if(Email.isEmpty()){
            email.setError("Email is required!");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            email.setError("Please enter a valid email!");
            email.requestFocus();
            return;
        }


        if(Password.isEmpty()){
            password.setError("Password is required!");
            password.requestFocus();
            return;
        }
        if(Password.length()<6){
            password.setError("Minimum length of password should be 6!");
            password.requestFocus();
            return;
        }
        if(!Password.equals(RepeatPassword)){
            password.setError("Password mismatch!");
            password.requestFocus();
            return;
        }

        if(Name.isEmpty()){
            name.setError("You need to fill this!");
            name.requestFocus();
            return;
        }
        if(PhoneNumber.isEmpty()){
            phoneNumber.setError("You need to fill this!");
            phoneNumber.requestFocus();
            return;
        }
        if(PhoneNumber.length() < 10 || PhoneNumber.length() > 10){
            phoneNumber.setError("10 digits phone number!");
            phoneNumber.requestFocus();
            return;
        }
        if(Address.isEmpty()){
            address.setError("Please enter your address!");
            address.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            User_Details user = new User_Details(
                                    Name,
                                    Email,
                                    PhoneNumber,
                                    Address,
                                    TypeOfUser
                            );

                            FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(user)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            USER = mAuth.getCurrentUser();
                                            USER.sendEmailVerification();

                                            Toast.makeText(RegisterationAct.this, "Verification email has been sent! ", Toast.LENGTH_SHORT).show();

                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(RegisterationAct.this, "Error! " + e.getMessage(), Toast.LENGTH_SHORT).show();

                                        }
                                    });


                            Intent i =new Intent(RegisterationAct.this, com.example.abodimazen.ChildCare.Email.class);
                            startActivity(i);

                        } else {

                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(getApplicationContext(), "You are already registered!", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
    }
}
