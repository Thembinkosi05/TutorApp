package com.example.finalstudent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ViewHolder> {

    private final ArrayList<ModuleModel> modules;
    public ModuleAdapter(ArrayList<ModuleModel> modules){
        this.modules = modules;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.oneline_all_module_activity,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModuleModel module =modules.get(position);

        holder.btnModuleName.setText(module.getName());
    }

    @Override
    public int getItemCount() {
        return modules.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        Button btnModuleName;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            btnModuleName = itemView.findViewById(R.id.btn_moduleName);
        }
    }
}
