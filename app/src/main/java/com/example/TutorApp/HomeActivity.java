package com.example.TutorApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class HomeActivity extends AppCompatActivity {
    DatabaseHelper db ;
    ImageView maintainModule,modules,profile,maintainTutor;
    static TutorModel curTutor = null;
    static StudentModel curStudent = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        maintainModule = findViewById(R.id.maitain_module);
        modules = findViewById(R.id.modules);
        maintainTutor = findViewById(R.id.maintainTuor);
        profile = findViewById(R.id.profile);

        DatabaseHelper db_Helper = new DatabaseHelper(HomeActivity.this);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db_Helper.checkUsername(MainActivity.CurrentEmailLoggedIn).getCount()>0) {
                    curTutor = db_Helper.returnTutor(MainActivity.CurrentEmailLoggedIn);
                    Intent intent = new Intent(HomeActivity.this,TutorProfile.class);
                    startActivity(intent);
                }
                else
                    curStudent = db_Helper.returnStudent(MainActivity.CurrentEmailLoggedIn);
            }
        });

        modules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AllModulesActivity.class);
                startActivity(intent);
            }
        });


        maintainTutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.CurrentEmailLoggedIn.equals("admin@gmail.com")){
                Intent intent = new Intent(HomeActivity.this, PendingTutors.class);
                startActivity(intent);}
                else {
                    Snackbar.make(v, " That section is only for admin ", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        maintainModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.CurrentEmailLoggedIn.equals("admin@gmail.com")){
                    Intent intent = new Intent(HomeActivity.this, MaintainModule.class);
                    startActivity(intent);}
                else {
                    Snackbar.make(v, " That section is only for admin ", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }
}
