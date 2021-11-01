package com.example.TutorApp;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PendingTutors extends AppCompatActivity {
    DatabaseHelper db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pending_tutor_activity);
        db = new DatabaseHelper(this);

        RecyclerView rvTutors = findViewById(R.id.rv_pendingTutor);
        rvTutors.setLayoutManager(new LinearLayoutManager(this));
        DatabaseHelper myDb = new DatabaseHelper(PendingTutors.this);
        ArrayList<TutorModel> tutors = new ArrayList<>();

        Cursor cursor = myDb.getPendingTutors();
        try {
            if (cursor.moveToFirst()) {
                do {
                    @SuppressLint("Range") int tutor_id = cursor.getInt(cursor.getColumnIndex("TUTOR_ID"));
                    @SuppressLint("Range") byte[] academics = cursor.getBlob(cursor.getColumnIndex("TUTOR_ACADEMICS"));
                    @SuppressLint("Range") int password = cursor.getInt(cursor.getColumnIndex("TUTOR_PASSWORD"));
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("TUTOR_NAME"));
                    @SuppressLint("Range") String surname = cursor.getString(cursor.getColumnIndex("TUTOR_SURNAME"));
                    @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("TUTOR_EMAIL"));
                    // do what ever you want here
                    TutorModel tutor = new TutorModel(tutor_id,name,surname,email,password,academics);
                    tutors.add(tutor);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        PendingTutorAdapter adapter = new PendingTutorAdapter(tutors,this);
        rvTutors.setAdapter(adapter);
    }
}
