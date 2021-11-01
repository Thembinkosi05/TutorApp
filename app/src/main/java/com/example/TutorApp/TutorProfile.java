package com.example.TutorApp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class TutorProfile  extends AppCompatActivity {
    DatabaseHelper db;
    public static TutorModel curTutor = null;
    Button createSession,pickModule;
    TextView profileName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutor_profile);
        db = new DatabaseHelper(this);
        curTutor = MainActivity.curTutor;

        createSession = findViewById(R.id.btn_createSession);
        pickModule = findViewById(R.id.btn_pickModule);
        profileName = findViewById(R.id.profileNameTutor);

        profileName.setText(curTutor.getName());
    }
}
