package com.example.TutorApp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MaintainModule  extends AppCompatActivity {
    Button cancel,addNewModule;
    DatabaseHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maintain_module_activity);
        db = new DatabaseHelper(this);

        RecyclerView rvModules = findViewById(R.id.rvMaintainModule);
        cancel = findViewById(R.id.btn_cancel_module);
        addNewModule = findViewById(R.id.btn_newModule);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaintainModule.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        addNewModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MaintainModule.this,ModuleActivity.class);
                startActivity(intent);
            }
        });

        rvModules.setLayoutManager(new LinearLayoutManager(this));

        DatabaseHelper myDb = new DatabaseHelper(MaintainModule.this);
        ArrayList<ModuleModel> modules = new ArrayList<>();
        Cursor cursor = myDb.AllModules();
        try {
            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("MODULE_NAME"));
                    @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("MODULE_DESCRIPTION"));
                    @SuppressLint("Range") int Code = cursor.getInt(cursor.getColumnIndex("MODULE_CODE"));
                    ModuleModel module = new ModuleModel(name, description, Code);
                    modules.add(module);
                    // do what ever you want here
                } while (cursor.moveToNext());
            }
            cursor.close();
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        MaintainModuleAdapter adapter = new MaintainModuleAdapter(modules,this);
        rvModules.setAdapter(adapter);
    }

}
