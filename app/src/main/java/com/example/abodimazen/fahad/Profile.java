package com.example.abodimazen.fahad;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Profile extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference profileRef = db.collection("Child Profile");
    private FirebaseAuth mAuth;
    private padapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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

        Intent i = new Intent(getApplicationContext(), details.class);
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