package com.example.finalstudent;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;

    //pixel 4 API 29

    Button btn_reg_login, btn_login;
    EditText userName,login_password;
    boolean passwordVisible;
    FloatingActionButton btn_add_module;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch tutorSwitch;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_reg_login = findViewById(R.id.btn_register_login);
        userName = findViewById(R.id.userName);
        login_password = findViewById(R.id.login_password);
        btn_login = findViewById(R.id.btn_login);
        myDb = new DatabaseHelper(this);

        btn_add_module = findViewById(R.id.btn_add_module);
        btn_add_module.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ModuleActivity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db_Helper = new  DatabaseHelper(MainActivity.this);

                Cursor ex = db_Helper.checkUsernameStudent(userName.getText().toString());
                if(userName.getText().toString().trim().equals("")||login_password.getText().toString().trim().equals("")){
                    Toast.makeText(MainActivity.this, "Please fill in username or password", Toast.LENGTH_SHORT).show();
                    return;
                }
                Cursor login_info = db_Helper.logInFunction(userName.getText().toString(),Integer.parseInt(login_password.getText().toString().trim()));
                if(login_info.getCount()<=0)
                {
                    Toast.makeText(MainActivity.this, "invalid password or username", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);

            }
        });

        login_password.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int Right = 2;
                if(motionEvent.getAction()==MotionEvent.ACTION_UP){
                    if (motionEvent.getRawX() >= login_password.getCompoundDrawables()[Right].getBounds().width()) {
                        int selection =login_password.getSelectionEnd();
                        if(passwordVisible){
                            //set drawable image here
                            login_password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_baseline_visibility_off_24,0);
                            //for hide password
                            login_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible=false;
                        }else {
                            //set drawable image here
                            login_password.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,R.drawable.ic_baseline_visibility_24,0);
                            //for show password
                            login_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible=true;
                        }
                        login_password.setSelection(selection);
                        return true;
                    }
                }

                return false;
            }
        });
        btn_reg_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, StudentActivity.class);
                startActivity(intent);

            }
        });
    }

}