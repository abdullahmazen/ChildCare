package com.example.abodimazen.ChildCare;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.abodimazen.fahad.R;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference profileRef = db.collection("Child");
    private FirebaseAuth mAuth;
    private padapter adapter;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.action_sign_out)
            signOut();
        return true;
    }

    private void signOut() {
        AuthUI.getInstance().signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        startActivity(new Intent(Profile.this,MainActivity.class));
                        finish();
                    }
                });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        mAuth = FirebaseAuth.getInstance();
        setUpRecyclerView();

        FloatingActionButton aaa = findViewById(R.id.button_add_profile);
        aaa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), NewChild.class);
                startActivity(i);
            }
        });


    }


    private void setUpRecyclerView() {
        Query query = profileRef.whereEqualTo("user_id", FirebaseAuth.getInstance().getCurrentUser().getUid());


        FirestoreRecyclerOptions<addp> options = new FirestoreRecyclerOptions.Builder<addp>()
                .setQuery(query, addp.class)
                .build();

        adapter = new padapter(options);

        RecyclerView recyclerView = findViewById(R.id.recycler_View);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

     adapter.setOnItemClickListener(new padapter.OnItemClickListener()

    {
        @Override
        public void onItemClick (DocumentSnapshot documentSnapshot,int position){
        String id = documentSnapshot.getId();
        Toast.makeText(Profile.this, "Position: " + position + "ID" + id, Toast.LENGTH_SHORT).show();

        Intent i = new Intent(getApplicationContext(), Child_Profile.class);
            i.putExtra("id", id);



            startActivity(i);

    }
    });
}

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}