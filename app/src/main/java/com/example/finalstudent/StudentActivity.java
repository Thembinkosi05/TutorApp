package com.example.finalstudent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StudentActivity extends AppCompatActivity {
    DatabaseHelper myDb;

    Button btn_reg;
    EditText std_name, std_surname, std_email, std_password, std_confirm_password;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch tutorSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_activity);
        myDb = new DatabaseHelper(this);

        tutorSwitch = findViewById(R.id.switch_to_tutor);
        btn_reg = findViewById(R.id.btn_register);
        std_name = findViewById(R.id.std_name);
        std_surname = findViewById(R.id.std_surname);
        std_email = findViewById(R.id.std_email);
        std_password = findViewById(R.id.std_password);
        std_confirm_password = findViewById(R.id.std_confirm_password);

        tutorSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentActivity.this, TutorActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidity();

                StudentModel curS;
                try {

                    if(std_name.getText().toString().trim().equals("")||std_surname.getText().toString().trim().equals("")||std_email.getText().toString().trim().equals("")||std_confirm_password.getText().toString().trim().equals("")) {
                        Toast.makeText(StudentActivity.this, "Please fill in all the entries", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if(Integer.parseInt(std_confirm_password.getText().toString().trim())!=Integer.parseInt(std_password.getText().toString().trim()))
                    {
                        std_confirm_password.setError("Confirmation Password is not the same as Password");
                        return;
                    }
                    if(!isEmail(std_email))
                    {
                        std_email.setError("Please enter VALID email");
                        return;
                    }

                    curS = new StudentModel(-1,std_name.getText().toString(),std_surname.getText().toString(),std_email.getText().toString(),Integer.parseInt( std_password.getText().toString()), Integer.parseInt( std_confirm_password.getText().toString()),false);
                    if(curS.getConfirmP() != curS.getPassword())
                    {
                        curS = null;
                    }
                    Toast.makeText(StudentActivity.this, curS.toString(),Toast.LENGTH_SHORT).show();
                    DatabaseHelper db_Helper = new  DatabaseHelper(StudentActivity.this);
                    boolean success = db_Helper.addOne(curS);
                    Toast.makeText(StudentActivity.this, "Success" + success, Toast.LENGTH_SHORT).show();

                }
                catch (Exception e)
                {
                    Toast.makeText(StudentActivity.this,"Error creating student",Toast.LENGTH_SHORT).show();
                    curS = new StudentModel(-1,"error","","",0,0,false);

                }

            }
        });
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    public void checkValidity() {
        if (isEmpty(std_name)) {
            std_name.setError("Name is required!");
        }
        if (isEmpty(std_surname)) {
            std_surname.setError("Surname is required!");
        }
        if (isEmpty(std_email)) {
            std_email.setError("Email is required!");
        }
        if (isEmpty(std_password)) {
            std_password.setError("Password is required!");
        }
        if (isEmpty(std_confirm_password)) {
            std_confirm_password.setError("Confirm Password required");
        }
    }
}
