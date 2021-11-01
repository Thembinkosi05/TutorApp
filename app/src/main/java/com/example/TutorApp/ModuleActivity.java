package com.example.TutorApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ModuleActivity extends AppCompatActivity {
    DatabaseHelper db ;
    Button btn_add,btn_cancel_main;
    EditText ModuleName, ModuleDescr;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_activity);
        db = new DatabaseHelper(this);

        btn_add = findViewById(R.id.btn_add);
        ModuleName =findViewById(R.id.ModuleName);
        ModuleDescr = findViewById(R.id.ModuleDescr);

        btn_cancel_main = findViewById(R.id.btn_cancel_main);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db_Helper = new  DatabaseHelper(ModuleActivity.this);
                db_Helper.addModule(ModuleName.getText().toString().trim(),ModuleDescr.getText().toString().trim());
            }
        });

        btn_cancel_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ModuleActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
