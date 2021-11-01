package com.example.finalstudent;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllModulesActivity extends AppCompatActivity {
    DatabaseHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_module_activity);
        db = new DatabaseHelper(this);

        RecyclerView rvModules = findViewById(R.id.view_modules);

        rvModules.setLayoutManager(new LinearLayoutManager(this));

        DatabaseHelper myDb = new DatabaseHelper(AllModulesActivity.this);
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
        ModuleAdapter adapter = new ModuleAdapter(modules,this);
        rvModules.setAdapter(adapter);
    }
}
