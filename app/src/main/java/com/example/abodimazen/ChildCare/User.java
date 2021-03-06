package com.example.abodimazen.ChildCare;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.abodimazen.fahad.R;
import com.google.firebase.auth.FirebaseAuth;


public class User extends AppCompatActivity {

    private Spinner spinner;
    private EditText editText,address, name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        // load the country in the spinner

        spinner = findViewById(R.id.spinnerCountries);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));


        editText = findViewById(R.id.editTextPhone);
        address = findViewById(R.id.address);
        name = findViewById(R.id.name);
        editText.setTextColor(Color.WHITE);
        findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String code = CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];
                String number = editText.getText().toString().trim();
                String Address = address.getText().toString().trim();
                String Name = name.getText().toString().trim();

                if (number.isEmpty() || number.length() < 9) {
                    editText.setError("Valid Number is required!");
                    editText.requestFocus();
                    return;
                }
                String phoneNumber = "+" + code + number;

                Intent intent = new Intent(User.this, VerifyPhoneActivity.class);
                intent.putExtra("Name", Name);
                intent.putExtra("Address", Address);
                intent.putExtra("phoneNumber", phoneNumber);
                startActivity(intent);
            }
        });
    }


}
