package com.example.abodimazen.fahad;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.AppCompatButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Payment extends AppCompatActivity {

    PayPalConfiguration m_PayPalConfiguration;
    String m_paypalClientId = "ARVtklU-3_Tw9ZfRVsx7oOIgylTih73lGx8YHrU06ACDCrfYrdZb8OJKH2hUKb_af_FOHtPlcr1DyPUm";
    Intent m_service;
    int m_paypalRequestCode = 999;


    private Button paypal;
    private Button inPerson;
    private TextView ppt;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, PayPalService.class));

        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        m_PayPalConfiguration = new PayPalConfiguration()
                .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
                .clientId(m_paypalClientId);

        m_service = new Intent(this, PayPalService.class);
        m_service.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, m_PayPalConfiguration);
        startService(m_service);


        inPerson = (Button) findViewById(R.id.inPerson);
        ppt = (TextView) findViewById(R.id.ppt);

        Intent in = getIntent();
        final Bundle b = in.getExtras();
        if (b != null) {

            String n = (String) b.get("id");

            db.collection("Child Profile").document(n).get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            String Pprice = documentSnapshot.getString("price");


                            ppt.setText(Pprice);

                        }
                    });
        }


    }
    public void paypal(View view){
        PayPalPayment payment = new PayPalPayment(new BigDecimal(13.23), "USD", "Toal payment",
                PayPalPayment.PAYMENT_INTENT_SALE);


        Intent intent = new Intent(this,PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,m_PayPalConfiguration);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payment);
        startActivityForResult(intent,m_paypalRequestCode);


    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == m_paypalRequestCode){

            if (resultCode == Activity.RESULT_OK) {
                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);

                if (confirmation != null) {
                    String state = confirmation.getProofOfPayment().getState();


                    if (state.equals("approved"))
                        ppt.setText("payment apptoved");
                    Intent P = getIntent();
                    final Bundle a = P.getExtras();
                    if (a != null) {
                        String n = (String) a.get("id");


                        Map<String, Object> ASatus = new HashMap<>();

                        ASatus.put("satus", "apptoved");
                        db.collection("Child Profile").document(n).update(ASatus);
                    } else
                        ppt.setText("error in the payment");

                    if (a != null) {
                        String n = (String) a.get("id");


                        Map<String, Object> ESatus = new HashMap<>();

                        ESatus.put("satus", "not apptoved");
                        db.collection("Child Profile").document(n).update(ESatus);
                    }

                }
                Intent b = getIntent();
                final Bundle c = b.getExtras();

                String n = (String) c.get("id");
                Intent q = new Intent(Payment.this, Child_Profile.class);
                q.putExtra("id", n);
                startActivity(q);
            }
                    else
                        ppt.setText("confirmation is null");
                }
            }


    }






