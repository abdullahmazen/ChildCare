package com.example.abodimazen.ChildCare;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.abodimazen.fahad.R;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ViewPager mviewPager;
    private LinearLayout mslide_linear;
    private TextView [] mDots;
    private SlideAdapter mslideAdapter;
    private Button mPhone;
    private Button mEmail;
    private LinearLayout mslide_linear1;
    private Button mNext;
    private Button mBack;
    private Button mAbout;
    private int mCurrentPage;


    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(this,Profile.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();

        mviewPager = findViewById(R.id.viewPager);
        mslide_linear = findViewById(R.id.slide_linear);
        mPhone = findViewById(R.id.Phone);
        mEmail = findViewById(R.id.Email);
        mslide_linear1 = findViewById(R.id.slide_linear1);
        mNext = findViewById(R.id.Next);
        mBack = findViewById(R.id.back);
        mAbout = findViewById(R.id.about);

        mslideAdapter = new SlideAdapter(this);
        mviewPager.setAdapter(mslideAdapter);


        mPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,User.class);
                startActivity(intent);
            }
        });

        mEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Email.class);
                startActivity(intent);
            }
        });


        mviewPager.addOnPageChangeListener(onPageChangeListener);
addDotsIndiction(0);

    mNext.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        mviewPager.setCurrentItem(mCurrentPage +1);
    }
    });

    mBack.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mviewPager.setCurrentItem(mCurrentPage -1);
        }
    });
    }

    public void addDotsIndiction(int position) {

        mDots = new TextView[3];

        mslide_linear1.removeAllViews();

        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText("\u2022");
            mDots[i].setTextAlignment(mDots[i].TEXT_ALIGNMENT_CENTER);
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(Color.GRAY);
            mslide_linear1.addView(mDots[i]);
        }
        if (mDots.length >0){
            mDots[position].setTextColor(Color.WHITE);
        }

    }

    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {

            mCurrentPage = i;
            addDotsIndiction(i);

            if (i==0){
                mNext.setEnabled(true);
                mAbout.setEnabled(true);
                mPhone.setEnabled(false);
                mEmail.setEnabled(false);
                mBack.setEnabled(false);



                mNext.setVisibility(View.VISIBLE);
                mAbout.setVisibility(View.VISIBLE);
                mPhone.setVisibility(View.INVISIBLE);
                mEmail.setVisibility(View.INVISIBLE);
                mBack.setVisibility(View.INVISIBLE);



                mNext.setText("Next");
                mNext.setTextAlignment(mNext.TEXT_ALIGNMENT_CENTER);
                mNext.setTextColor(Color.WHITE);


                mAbout.setText("About");
                mAbout.setTextAlignment(mAbout.TEXT_ALIGNMENT_CENTER);
                mAbout.setTextColor(Color.WHITE);



            }
            else if (i == 1){
                mNext.setEnabled(true);
                mAbout.setEnabled(false);
                mPhone.setEnabled(true);
                mEmail.setEnabled(false);
                mBack.setEnabled(true);

                mNext.setVisibility(View.VISIBLE);
                mEmail.setVisibility(View.INVISIBLE);
                mAbout.setVisibility(View.INVISIBLE);
                mPhone.setVisibility(View.VISIBLE);
                mBack.setVisibility(View.VISIBLE);



                mPhone.setText("Phone");
                mPhone.setTextAlignment(mPhone.TEXT_ALIGNMENT_CENTER);
                mPhone.setTextColor(Color.WHITE);

                mNext.setText("Next");
                mNext.setTextAlignment(mNext.TEXT_ALIGNMENT_CENTER);
                mNext.setTextColor(Color.WHITE);


                mBack.setText("Back");
                mBack.setTextAlignment(mBack.TEXT_ALIGNMENT_CENTER);
                mBack.setTextColor(Color.WHITE);




            }
            else if (i == 2){
                mNext.setEnabled(true);
                mAbout.setEnabled(false);
                mPhone.setEnabled(false);
                mEmail.setEnabled(true);
                mBack.setEnabled(true);

                mNext.setVisibility(View.VISIBLE);
                mAbout.setVisibility(View.INVISIBLE);
                mPhone.setVisibility(View.INVISIBLE);
                mEmail.setVisibility(View.VISIBLE);
                mBack.setVisibility(View.VISIBLE);



                mEmail.setText("Email");
                mEmail.setTextAlignment(mEmail.TEXT_ALIGNMENT_CENTER);
                mEmail.setTextColor(Color.WHITE);

                mNext.setText("Finish");
                mNext.setTextAlignment(mNext.TEXT_ALIGNMENT_CENTER);
                mNext.setTextColor(Color.WHITE);



                mBack.setText("Back");
                mBack.setTextAlignment(mBack.TEXT_ALIGNMENT_CENTER);
                mBack.setTextColor(Color.WHITE);


            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };


}
