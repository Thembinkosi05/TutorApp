package com.example.finalstudent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    DatabaseHelper db ;
    ImageView maintainModule,modules,profile,maintainTutor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        maintainModule = findViewById(R.id.maitain_module);
        modules = findViewById(R.id.modules);
        modules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, AllModulesActivity.class);
                startActivity(intent);
            }
        });
    }
}
