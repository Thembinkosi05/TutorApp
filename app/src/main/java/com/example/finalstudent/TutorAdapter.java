package com.example.finalstudent;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TutorAdapter extends RecyclerView.Adapter<TutorAdapter.ViewHolder>{
    private final ArrayList<TutorModel> tutors;
    private final Context context;
    public static TutorModel T = null;

    public TutorAdapter(ArrayList<TutorModel> tutors,Context context) {
        this.tutors = tutors;
        this.context = context;
    }

    @NonNull
    @Override
    public TutorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.oneline_all_tutor,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TutorAdapter.ViewHolder holder, int position) {
        TutorModel tutor = tutors.get(position);


        holder.btnTutorName.setText(tutor.getName());

        holder.btnTutorName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                T = tutors.get(position);
                Intent intent = new Intent(context,SelectedTutorCreate.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tutors.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        Button btnTutorName;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            btnTutorName = itemView.findViewById(R.id.btn_tutorName);
        }
    }
}
