package com.example.finalstudent;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SelectedTutorCreate extends AppCompatActivity
{
    DatabaseHelper db;
    StudentModel studentModel;
    TutorModel tutorModel;
    ModuleModel moduleModel;
    SelectedTutorModel selectedModel;

    RecyclerView recyclerView;

    LinearLayoutManager layoutManager;

    ArrayList<SelectedTutorModel> userList;

    SelectedTutorAdapter adapter;
    TutorModel tut = TutorAdapter.T;

    TextView textSelectName;
    Switch switchLock;

    Button btn_BookSession;

    Button btn_ViewPortfolio;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_tutor);

        textSelectName = findViewById(R.id.text_selectName);

        switchLock = findViewById(R.id.switch_lock);

        btn_BookSession = findViewById(R.id.btn_bookSession);

        btn_ViewPortfolio = findViewById(R.id.btn_ViewPortfolio);

        switchLock.setChecked(true);

        textSelectName.setText(tut.getName());


    }


}
