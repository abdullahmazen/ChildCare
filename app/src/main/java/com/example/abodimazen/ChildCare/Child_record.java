package com.example.abodimazen.ChildCare;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abodimazen.fahad.R;
import com.firebase.ui.auth.AuthUI;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Child_record extends AppCompatActivity {

    private TextView textView_At_Birth;
    private TextView textView_Two_Month;
    private TextView textView_Four_Month;
    private TextView textView_Six_Month;
    private TextView textView_Nine_Month;
    private TextView textView_Twelve_Month;
    private TextView textViewـEighteen_Month;
    private TextView textView_Twenty_four_Month;
    private TextView textView_First_Primary;
    private static final int GALLERY_INTENT = 1;
    private Uri mUri;
    private ImageView ImageView_choose_image;
    private ImageView ImageView_choose_image1;
    private TextView textView_Dates_At_Birth;
    private TextView textView_Dates_2Month;
    private TextView textView_Dates_4Month;
    private TextView textView_Dates_6Month;
    private TextView textView_Dates_9Month;
    private TextView textView_Dates_12Month;
    private TextView textView_Dates_18Month;
    private TextView textView_Dates_24Month;
    private TextView textView_Dates_First_Primary;
    private String checkedMark = "\u2713";






    private FirebaseAuth mAuth;


    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_record);


        textView_At_Birth = findViewById(R.id.textView_At_Birth);
        textView_Two_Month = findViewById(R.id.textView_Two_Month);
        textView_Four_Month = findViewById(R.id.textView_Four_Month);
        textView_Six_Month = findViewById(R.id.textView_Six_Month);
        textView_Nine_Month = findViewById(R.id.textView_Nine_Month);
        textView_Twelve_Month = findViewById(R.id.textView_Twelve_Month);
        textViewـEighteen_Month = findViewById(R.id.textViewـEighteen_Month);
        textView_Twenty_four_Month = findViewById(R.id.textView_Twenty_four_Month);
        textView_First_Primary = findViewById(R.id.textView_First_Primary);
        ImageView_choose_image = findViewById(R.id.ImageView_choose_image);
        ImageView_choose_image1 = findViewById(R.id.ImageView_choose_image1);
        textView_Dates_At_Birth = findViewById(R.id.textView_Dates_At_Birth);
        textView_Dates_2Month = findViewById(R.id.textView_Dates_2Month);
        textView_Dates_4Month = findViewById(R.id.textView_Dates_4Month);
        textView_Dates_6Month = findViewById(R.id.textView_Dates_6Month);
        textView_Dates_9Month = findViewById(R.id.textView_Dates_9Month);
        textView_Dates_12Month = findViewById(R.id.textView_Dates_12Month);
        textView_Dates_18Month = findViewById(R.id.textView_Dates_18Month);
        textView_Dates_24Month = findViewById(R.id.textView_Dates_24Month);
        textView_Dates_First_Primary = findViewById(R.id.textView_Dates_First_Primary);



        Intent in = getIntent();
        final Bundle b = in.getExtras();
        if (b != null) {

            String n = (String) b.get("id");

            db.collection("Child Profile").document(n).get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                            String Lastvaccination = documentSnapshot.getString("lastvaccination");

                            if (Lastvaccination.equals("At Birth")) {
                                textView_At_Birth.setText(checkedMark);
                                textView_Two_Month.setText("Next vaccination");

                                textView_At_Birth.setTextSize(30);

                                textView_At_Birth.setTextColor(Color.GREEN);
                                textView_Two_Month.setTextColor(Color.GRAY);
                            }

                            if (Lastvaccination.equals("2 Month")) {
                                textView_At_Birth.setText(checkedMark);
                                textView_Two_Month.setText(checkedMark);
                                textView_Four_Month.setText("Next vaccination");

                                textView_At_Birth.setTextSize(30);
                                textView_Two_Month.setTextSize(30);

                                textView_At_Birth.setTextColor(Color.GREEN);
                                textView_Two_Month.setTextColor(Color.GREEN);
                                textView_Four_Month.setTextColor(Color.GRAY);
                            }
                            if (Lastvaccination.equals("4 Month")) {
                                textView_At_Birth.setText(checkedMark);
                                textView_Two_Month.setText(checkedMark);
                                textView_Four_Month.setText(checkedMark);
                                textView_Six_Month.setText("Next vaccination");

                                textView_At_Birth.setTextSize(30);
                                textView_Two_Month.setTextSize(30);
                                textView_Four_Month.setTextSize(30);

                                textView_At_Birth.setTextColor(Color.GREEN);
                                textView_Two_Month.setTextColor(Color.GREEN);
                                textView_Four_Month.setTextColor(Color.GREEN);
                                textView_Six_Month.setTextColor(Color.GRAY);
                            }
                            if (Lastvaccination.equals("6 Month")) {
                                textView_At_Birth.setText(checkedMark);
                                textView_Two_Month.setText(checkedMark);
                                textView_Four_Month.setText(checkedMark);
                                textView_Six_Month.setText(checkedMark);
                                textView_Nine_Month.setText("Next vaccination");

                                textView_At_Birth.setTextSize(30);
                                textView_Two_Month.setTextSize(30);
                                textView_Four_Month.setTextSize(30);
                                textView_Six_Month.setTextSize(30);

                                textView_At_Birth.setTextColor(Color.GREEN);
                                textView_Two_Month.setTextColor(Color.GREEN);
                                textView_Four_Month.setTextColor(Color.GREEN);
                                textView_Six_Month.setTextColor(Color.GREEN);
                                textView_Nine_Month.setTextColor(Color.GRAY);
                            }
                            if (Lastvaccination.equals("9 Month")) {
                                textView_At_Birth.setText(checkedMark);
                                textView_Two_Month.setText(checkedMark);
                                textView_Four_Month.setText(checkedMark);
                                textView_Six_Month.setText(checkedMark);
                                textView_Nine_Month.setText(checkedMark);
                                textView_Twelve_Month.setText("Next vaccination");


                                textView_At_Birth.setTextSize(30);
                                textView_Two_Month.setTextSize(30);
                                textView_Four_Month.setTextSize(30);
                                textView_Six_Month.setTextSize(30);
                                textView_Nine_Month.setTextSize(30);


                                textView_At_Birth.setTextColor(Color.GREEN);
                                textView_Two_Month.setTextColor(Color.GREEN);
                                textView_Four_Month.setTextColor(Color.GREEN);
                                textView_Six_Month.setTextColor(Color.GREEN);
                                textView_Nine_Month.setTextColor(Color.GREEN);
                                textView_Twelve_Month.setTextColor(Color.GRAY);
                            }

                            if (Lastvaccination.equals("12 Month")) {
                                textView_At_Birth.setText(checkedMark);
                                textView_Two_Month.setText(checkedMark);
                                textView_Four_Month.setText(checkedMark);
                                textView_Six_Month.setText(checkedMark);
                                textView_Nine_Month.setText(checkedMark);
                                textView_Twelve_Month.setText(checkedMark);
                                textViewـEighteen_Month.setText("Next vaccination");


                                textView_At_Birth.setTextSize(30);
                                textView_Two_Month.setTextSize(30);
                                textView_Four_Month.setTextSize(30);
                                textView_Six_Month.setTextSize(30);
                                textView_Nine_Month.setTextSize(30);
                                textView_Twelve_Month.setTextSize(30);

                                textView_At_Birth.setTextColor(Color.GREEN);
                                textView_Two_Month.setTextColor(Color.GREEN);
                                textView_Four_Month.setTextColor(Color.GREEN);
                                textView_Six_Month.setTextColor(Color.GREEN);
                                textView_Nine_Month.setTextColor(Color.GREEN);
                                textView_Twelve_Month.setTextColor(Color.GREEN);
                                textViewـEighteen_Month.setTextColor(Color.GRAY);
                            }
                            if (Lastvaccination.equals("18 Month")) {
                                textView_At_Birth.setText(checkedMark);
                                textView_Two_Month.setText(checkedMark);
                                textView_Four_Month.setText(checkedMark);
                                textView_Six_Month.setText(checkedMark);
                                textView_Nine_Month.setText(checkedMark);
                                textView_Twelve_Month.setText(checkedMark);
                                textViewـEighteen_Month.setText(checkedMark);
                                textView_Twenty_four_Month.setText("Next vaccination");

                                textView_At_Birth.setTextSize(30);
                                textView_Two_Month.setTextSize(30);
                                textView_Four_Month.setTextSize(30);
                                textView_Six_Month.setTextSize(30);
                                textView_Nine_Month.setTextSize(30);
                                textView_Twelve_Month.setTextSize(30);
                                textViewـEighteen_Month.setTextSize(30);


                                textView_At_Birth.setTextColor(Color.GREEN);
                                textView_Two_Month.setTextColor(Color.GREEN);
                                textView_Four_Month.setTextColor(Color.GREEN);
                                textView_Six_Month.setTextColor(Color.GREEN);
                                textView_Nine_Month.setTextColor(Color.GREEN);
                                textView_Twelve_Month.setTextColor(Color.GREEN);
                                textViewـEighteen_Month.setTextColor(Color.GREEN);
                                textView_Twenty_four_Month.setTextColor(Color.GRAY);
                            }
                            if (Lastvaccination.equals("24 Month")) {
                                textView_At_Birth.setText(checkedMark);
                                textView_Two_Month.setText(checkedMark);
                                textView_Four_Month.setText(checkedMark);
                                textView_Six_Month.setText(checkedMark);
                                textView_Nine_Month.setText(checkedMark);
                                textView_Twelve_Month.setText(checkedMark);
                                textViewـEighteen_Month.setText(checkedMark);
                                textView_Twenty_four_Month.setText(checkedMark);
                                textView_First_Primary.setText("Next vaccination");

                                textView_At_Birth.setTextSize(30);
                                textView_Two_Month.setTextSize(30);
                                textView_Four_Month.setTextSize(30);
                                textView_Six_Month.setTextSize(30);
                                textView_Nine_Month.setTextSize(30);
                                textView_Twelve_Month.setTextSize(30);
                                textViewـEighteen_Month.setTextSize(30);
                                textView_Twenty_four_Month.setTextSize(30);


                                textView_At_Birth.setTextColor(Color.GREEN);
                                textView_Two_Month.setTextColor(Color.GREEN);
                                textView_Four_Month.setTextColor(Color.GREEN);
                                textView_Six_Month.setTextColor(Color.GREEN);
                                textView_Nine_Month.setTextColor(Color.GREEN);
                                textView_Twelve_Month.setTextColor(Color.GREEN);
                                textViewـEighteen_Month.setTextColor(Color.GREEN);
                                textView_Twenty_four_Month.setTextColor(Color.GREEN);
                                textView_First_Primary.setTextColor(Color.GRAY);
                            }
                            if (Lastvaccination.equals("First Primary")) {
                                textView_At_Birth.setText(checkedMark);
                                textView_Two_Month.setText(checkedMark);
                                textView_Four_Month.setText(checkedMark);
                                textView_Six_Month.setText(checkedMark);
                                textView_Nine_Month.setText(checkedMark);
                                textView_Twelve_Month.setText(checkedMark);
                                textViewـEighteen_Month.setText(checkedMark);
                                textView_Twenty_four_Month.setText(checkedMark);
                                textView_First_Primary.setText(checkedMark);

                                textView_At_Birth.setTextSize(30);
                                textView_Two_Month.setTextSize(30);
                                textView_Four_Month.setTextSize(30);
                                textView_Six_Month.setTextSize(30);
                                textView_Nine_Month.setTextSize(30);
                                textView_Twelve_Month.setTextSize(30);
                                textViewـEighteen_Month.setTextSize(30);
                                textView_Twenty_four_Month.setTextSize(30);
                                textView_First_Primary.setTextSize(30);

                                textView_At_Birth.setTextColor(Color.GREEN);
                                textView_Two_Month.setTextColor(Color.GREEN);
                                textView_Four_Month.setTextColor(Color.GREEN);
                                textView_Six_Month.setTextColor(Color.GREEN);
                                textView_Nine_Month.setTextColor(Color.GREEN);
                                textView_Twelve_Month.setTextColor(Color.GREEN);
                                textViewـEighteen_Month.setTextColor(Color.GREEN);
                                textView_Twenty_four_Month.setTextColor(Color.GREEN);
                                textView_First_Primary.setTextColor(Color.GREEN);

                            }
                            String pbirth = documentSnapshot.getString("birth");


                            textView_Dates_At_Birth.setText(pbirth);

                            String twomonth = pbirth;  // Start date
                            SimpleDateFormat twomonthsdf = new SimpleDateFormat("dd/MM/yyyy");
                            Calendar c = Calendar.getInstance();
                            try {
                                c.setTime(twomonthsdf.parse(twomonth));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }


                            c.add(Calendar.DATE, 60);
                            twomonthsdf = new SimpleDateFormat("dd/MM/yyyy");
                            Date ntwoMonth = new Date(c.getTimeInMillis());
                            twomonth = twomonthsdf.format(ntwoMonth);
                            textView_Dates_2Month.setText(twomonth);




                            String fourmonth = twomonth;  // Start date
                            SimpleDateFormat fourmonthsdf = new SimpleDateFormat("dd/MM/yyyy");
                            Calendar calendar = Calendar.getInstance();
                            try {
                                calendar.setTime(fourmonthsdf.parse(fourmonth));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }


                            calendar.add(Calendar.DATE, 60);
                            fourmonthsdf = new SimpleDateFormat("dd/MM/yyyy");
                            Date nfourmonth = new Date(calendar.getTimeInMillis());
                            fourmonth = fourmonthsdf.format(nfourmonth);
                            textView_Dates_4Month.setText(fourmonth);


                            String sixmonth = fourmonth;  // Start date
                            SimpleDateFormat sixmonthsdf = new SimpleDateFormat("dd/MM/yyyy");
                            Calendar calendar1 = Calendar.getInstance();
                            try {
                                calendar1.setTime(sixmonthsdf.parse(sixmonth));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }


                            calendar1.add(Calendar.DATE, 60);
                            sixmonthsdf = new SimpleDateFormat("dd/MM/yyyy");
                            Date nsixmonth = new Date(calendar1.getTimeInMillis());
                            sixmonth = sixmonthsdf.format(nsixmonth);
                            textView_Dates_6Month.setText(sixmonth);



                            String ninemonth = sixmonth;  // Start date
                            SimpleDateFormat ninemonthsdf = new SimpleDateFormat("dd/MM/yyyy");
                            Calendar calendar2 = Calendar.getInstance();
                            try {
                                calendar2.setTime(ninemonthsdf.parse(ninemonth));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }


                            calendar2.add(Calendar.DATE, 90);
                            ninemonthsdf = new SimpleDateFormat("dd/MM/yyyy");
                            Date nninemonth = new Date(calendar2.getTimeInMillis());
                            ninemonth = ninemonthsdf.format(nninemonth);
                            textView_Dates_9Month.setText(ninemonth);


                            String twuntymonth = ninemonth;  // Start date
                            SimpleDateFormat twuntyemonthsdf = new SimpleDateFormat("dd/MM/yyyy");
                            Calendar calendar3 = Calendar.getInstance();
                            try {
                                calendar3.setTime(twuntyemonthsdf.parse(twuntymonth));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }


                            calendar3.add(Calendar.DATE, 90);
                            twuntyemonthsdf = new SimpleDateFormat("dd/MM/yyyy");
                            Date ntwntymonth = new Date(calendar3.getTimeInMillis());
                            twuntymonth = twuntyemonthsdf.format(ntwntymonth);
                            textView_Dates_12Month.setText(twuntymonth);



                            String eighteenmonth = twuntymonth;  // Start date
                            SimpleDateFormat eighteenmonthmonthsdf = new SimpleDateFormat("dd/MM/yyyy");
                            Calendar calendar4 = Calendar.getInstance();
                            try {
                                calendar4.setTime(eighteenmonthmonthsdf.parse(eighteenmonth));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }


                            calendar4.add(Calendar.DATE, 120);
                            eighteenmonthmonthsdf = new SimpleDateFormat("dd/MM/yyyy");
                            Date neighteenmonth = new Date(calendar4.getTimeInMillis());
                            eighteenmonth = eighteenmonthmonthsdf.format(neighteenmonth);
                            textView_Dates_18Month.setText(eighteenmonth);



                            String twentyfourmonth = eighteenmonth;  // Start date
                            SimpleDateFormat twentyfourmonthmonthsdf = new SimpleDateFormat("dd/MM/yyyy");
                            Calendar calendar5 = Calendar.getInstance();
                            try {
                                calendar5.setTime(twentyfourmonthmonthsdf.parse(twentyfourmonth));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }


                            calendar5.add(Calendar.DATE, 120);
                            twentyfourmonthmonthsdf = new SimpleDateFormat("dd/MM/yyyy");
                            Date ntwentyfournmonth = new Date(calendar5.getTimeInMillis());
                            twentyfourmonth = twentyfourmonthmonthsdf.format(ntwentyfournmonth);
                            textView_Dates_24Month.setText(twentyfourmonth);



                            String FirstPrimary = twentyfourmonth;  // Start date
                            SimpleDateFormat FirstPrimarysdf = new SimpleDateFormat("dd/MM/yyyy");
                            Calendar calendar6 = Calendar.getInstance();
                            try {
                                calendar6.setTime(FirstPrimarysdf.parse(FirstPrimary));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }


                            calendar6.add(Calendar.DATE, 2520);
                            FirstPrimarysdf = new SimpleDateFormat("dd/MM/yyyy");
                            Date nFirstPrimary = new Date(calendar6.getTimeInMillis());
                            FirstPrimary = FirstPrimarysdf.format(nFirstPrimary);
                            textView_Dates_First_Primary.setText(FirstPrimary);


                        }
                    });

        }






        }



    public void button_choose_image(View view) {
        openFileChooser();

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
                        startActivity(new Intent(Child_record.this,MainActivity.class));
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
                startActivity(new Intent(Child_record.this,Profile.class));
                finish();

        }



        return super.onOptionsItemSelected(item);
    }

}
