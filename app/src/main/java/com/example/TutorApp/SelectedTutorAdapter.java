package com.example.TutorApp.;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class SelectedTutorAdapter extends RecyclerView.Adapter<SelectedTutorAdapter.ViewHolder>
{
    private ArrayList<SelectedTutorModel> tutor;


    public SelectedTutorAdapter(ArrayList<SelectedTutorModel> tutor)
    {
        this.tutor = tutor;

    }

    @NonNull
    @Override
    public SelectedTutorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_tutor,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        String name = tutor.get(position).getSelectName();
        boolean isBooked = tutor.get(position).isSwitch_Lock();

        holder.setData(name,isBooked);

    }

    @Override
    public int getItemCount() {
        return tutor.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView selectName;
        private Button bookSession;
        private Button viewPortfolio;
        private Switch switch_Lock;


        public ViewHolder(@NonNull View itemView)
         {
            super(itemView);

            selectName = itemView.findViewById(R.id.text_selectName);
            bookSession = itemView.findViewById(R.id.btn_bookSession);
            viewPortfolio = itemView.findViewById(R.id.btn_ViewPortfolio);
            switch_Lock = itemView.findViewById(R.id.switch_lock);
        }

        public void setData(String name, boolean booked)
        {
            selectName.setText(name);
            switch_Lock.setChecked(booked);
        }
    }
}
