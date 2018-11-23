package com.example.abodimazen.ChildCare;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.abodimazen.fahad.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;


public class padapter extends FirestoreRecyclerAdapter<addp, padapter.profileHolder> {




    private  OnItemClickListener listener;


    public padapter(@NonNull FirestoreRecyclerOptions<addp> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull profileHolder holder, int position, @NonNull addp model) {
        holder.textViewName.setText(model.getName());



    }


    @NonNull
    @Override
    public profileHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_item, parent, false);


        return new profileHolder(v);

    }


    class profileHolder extends RecyclerView.ViewHolder {
        TextView textViewName;




        public profileHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.Text_view_name);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && listener != null){
                        listener.onItemClick(getSnapshots().getSnapshot(position),position);
                    }
                }
            });

        }
    }

    public interface  OnItemClickListener{
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;

    }

}