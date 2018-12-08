package com.example.abodimazen.ChildCare;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.abodimazen.fahad.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.concurrent.TimeUnit;


public class VerifyPhoneActivity extends AppCompatActivity {

        private String verificationId;
        private FirebaseAuth mAuth;
        private EditText editText;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_phone);

        mAuth = FirebaseAuth.getInstance();

        editText = findViewById(R.id.editTextCode);

        String phonenumber = getIntent().getStringExtra("phoneNumber");
        sendVerificationCode(phonenumber);


        findViewById(R.id.buttonSignIn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String code = editText.getText().toString().trim();

                if (code.isEmpty() || code.length()<6){

                    editText.setError("enter Code...");
                    editText.requestFocus();
                    return;

                }

                verifyCode(code);

            }
        });


    }

    private void verifyCode(String code){

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithCredential(credential);
    }

    private void signInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            //check if user didn't put user and address, because maybe he's already registered!
                            String Name = getIntent().getStringExtra("Name");
                            String Address = getIntent().getStringExtra("Address");

                            if(Name.isEmpty() && Address.isEmpty()){
                                db.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                                        String Name = documentSnapshot.getString("name");
                                        String Address = documentSnapshot.getString("address");

                                        String Email ="";
                                        String TypeOfUser="User";
                                        String PhoneNumber = getIntent().getStringExtra("phoneNumber");

                                        User_Details user = new User_Details(
                                                Name,
                                                Email,
                                                PhoneNumber,
                                                Address,
                                                TypeOfUser,
                                                null
                                        );
                                        db.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(user)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                        Toast.makeText(VerifyPhoneActivity.this, "Code is sent! ", Toast.LENGTH_SHORT).show();

                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(VerifyPhoneActivity.this, "Error! " + e.getMessage(), Toast.LENGTH_SHORT).show();

                                                    }
                                                });

                                    }
                                });

                            }else{

                                Name = getIntent().getStringExtra("Name");
                                Address = getIntent().getStringExtra("Address");
                                String Email ="";
                                String TypeOfUser="User";
                                String PhoneNumber = getIntent().getStringExtra("phoneNumber");

                                User_Details user = new User_Details(
                                        Name,
                                        Email,
                                        PhoneNumber,
                                        Address,
                                        TypeOfUser,
                                        null
                                );
                                db.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(user)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(VerifyPhoneActivity.this, "Code is sent! ", Toast.LENGTH_SHORT).show();

                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(VerifyPhoneActivity.this, "Error! " + e.getMessage(), Toast.LENGTH_SHORT).show();

                                            }
                                        });

                            }

                            Intent intent = new Intent(VerifyPhoneActivity.this, Profile.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                            startActivity(intent);

                        }else{
                            Toast.makeText(VerifyPhoneActivity.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    private void sendVerificationCode(String phonenumber){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phonenumber,
                10,
                TimeUnit.SECONDS,
                this,
                mCallbacks

        );
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null){
                editText.setText(code);
                verifyCode(code);
            }

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

            Toast.makeText(VerifyPhoneActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();

        }
    };

}
