package com.example.TutorApp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PendingTutorAdapter extends RecyclerView.Adapter<PendingTutorAdapter.ViewHolder> {
    private final ArrayList<TutorModel> tutors;
    private final Context context;
    public static TutorModel pendingTut = null;

    public PendingTutorAdapter(ArrayList<TutorModel> tutors, Context context) {
        this.tutors = tutors;
        this.context = context;
    }

    @NonNull
    @Override
    public PendingTutorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.oneline_all_tutor,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PendingTutorAdapter.ViewHolder holder, int position) {
        TutorModel tutor = tutors.get(position);
        holder.btnTutorName.setText(tutor.getName());

        holder.btnTutorName.setOnClickListener(v -> {
            pendingTut = tutor;
            Intent intent = new Intent(context,AdminApproval.class);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return tutors.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        Button btnTutorName;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            btnTutorName = itemView.findViewById(R.id.btn_tutorName);
        }
    }
}
