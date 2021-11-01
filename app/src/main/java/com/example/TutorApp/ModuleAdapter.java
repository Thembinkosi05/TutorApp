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

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ViewHolder> {

    private final ArrayList<ModuleModel> modules;
    private final Context context;
    public static int code = 0;
    public static String moduleName="";


    public ModuleAdapter(ArrayList<ModuleModel> modules,Context context){
        this.modules = modules;
        this.context = context;
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

        holder.btnModuleName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moduleName =""+module.getName();
                code = module.getModuleCode();
                Intent intent = new Intent(context,SelectedTutor.class);
                context.startActivity(intent);
            }
        });
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
