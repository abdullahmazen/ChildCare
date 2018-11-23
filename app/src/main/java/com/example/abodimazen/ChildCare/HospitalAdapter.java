package com.example.abodimazen.ChildCare;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abodimazen.fahad.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class HospitalAdapter extends FirestoreRecyclerAdapter<Child, HospitalAdapter.ChildHolder> {
    private HospitalAdapter.OnItemClickListener listener;

    public HospitalAdapter(@NonNull FirestoreRecyclerOptions<Child> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull HospitalAdapter.ChildHolder holder, int position, @NonNull Child model) {
        holder.textViewName.setText(model.getName());
        holder.textViewDate.setText(model.getDate());
    }

    @NonNull
    @Override
    public HospitalAdapter.ChildHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.hospital_item,
                parent, false);
        return new HospitalAdapter.ChildHolder(v);
    }

    public void deleteItem(int position) {
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    class ChildHolder extends RecyclerView.ViewHolder {

        TextView textViewName, textViewDate;

        public ChildHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewDate = itemView.findViewById(R.id.text_view_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(HospitalAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}