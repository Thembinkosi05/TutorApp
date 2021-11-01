package com.example.TutorApp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SelectedTutor extends AppCompatActivity {
    DatabaseHelper db;
    StudentModel studentModel;
    TutorModel tutorModel;
    ModuleModel moduleModel;

    Button nameSelected;


    int modCode;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutor_for_specific_module);

        db = new DatabaseHelper(this);


        RecyclerView rvTutors = findViewById(R.id.view_tutor);
        rvTutors.setLayoutManager(new LinearLayoutManager(this));

        TextView selectedModule = findViewById(R.id.text_selectedModule);

        DatabaseHelper myDb = new DatabaseHelper(SelectedTutor.this);
        ArrayList<TutorModel> tutors = new ArrayList<>();
        modCode = ModuleAdapter.code;
        selectedModule.setText("Tutors Available for "+ModuleAdapter.moduleName);
        Cursor cursor = myDb.getAllModuleTut(modCode);

        try {
            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") int tutor_id = cursor.getInt(cursor.getColumnIndex("TUTOR_ID"));
                    @SuppressLint("Range") int moduleCode = cursor.getInt(cursor.getColumnIndex("MODULE_CODE"));
                    @SuppressLint("Range") int password = cursor.getInt(cursor.getColumnIndex("TUTOR_PASSWORD"));
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("TUTOR_NAME"));
                    @SuppressLint("Range") String surname = cursor.getString(cursor.getColumnIndex("TUTOR_SURNAME"));
                    @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("TUTOR_EMAIL"));
                    // do what ever you want here
                    TutorModel tutor = new TutorModel(tutor_id,name,surname,email,password,null);
                    modCode = moduleCode;
                    tutors.add(tutor);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        TutorAdapter adapter = new TutorAdapter(tutors,this);
        rvTutors.setAdapter(adapter);


    }

    public void setModuleModel(ModuleModel moduleModel) {
        this.moduleModel = moduleModel;
    }

    public int codeM()
    {
        return modCode;
    }


}
