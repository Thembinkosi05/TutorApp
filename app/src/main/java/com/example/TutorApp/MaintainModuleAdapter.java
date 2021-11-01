package com.example.TutorApp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MaintainModuleAdapter extends RecyclerView.Adapter<MaintainModuleAdapter.ViewHolder> {
    private final ArrayList<ModuleModel> modules;
    private final Context context;
    DatabaseHelper db;
    public static ModuleModel curModule =  null;

    public MaintainModuleAdapter(ArrayList<ModuleModel> modules, Context context) {
        this.modules = modules;
        this.context = context;
        db = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public MaintainModuleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.oneline_maintain_activity,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaintainModuleAdapter.ViewHolder holder, int position) {
        ModuleModel module =modules.get(position);
        holder.moduleName.setText(module.getName());

        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curModule = module;
                Intent intent = new Intent(context,UpdateModule.class);
                context.startActivity(intent);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirmation");
                builder.setMessage("Are you sure you want to delete this module")
                        .setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                db.deleteModule(module.getModuleCode());
                                Intent intent = new Intent(context,MaintainModule.class);
                                context.startActivity(intent);
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, null);
                builder.setIcon(android.R.drawable.ic_dialog_info).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return modules.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        Button btnDelete,btnUpdate;
        TextView moduleName;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            btnUpdate = itemView.findViewById(R.id.btn_Update);
            moduleName = itemView.findViewById(R.id.module_name);
        }
    }
}
