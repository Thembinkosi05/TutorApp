package com.example.finalstudent;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TutorActivity extends AppCompatActivity {
    DatabaseHelper myDb;

    Button btn_tut_register,btn_Cancel;
    EditText tutName, tutSurname, tutEmail, tutPassword, tutConfirmPass, selectModule;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch switch_to_student;
    ImageView objectImageView;
    static final int PICK_IMAGE_REQUEST =100;
    private Bitmap imageToStore;
    boolean[] selectedModule;
    ArrayList<Integer> moduleList = new ArrayList<>();
    String[] moduleArray = {"Maths","Chemistry","IT","Physical Science"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tutor_activity);
        myDb = new DatabaseHelper(this);
        btn_tut_register = findViewById(R.id.btn_tut_register);
        btn_Cancel =findViewById(R.id.btnCancelTutor);
        tutName = findViewById(R.id.tutName);
        tutSurname = findViewById(R.id.tutSurname);
        tutEmail = findViewById(R.id.tutEmail);
        tutPassword = findViewById(R.id.tutPassword);
        tutConfirmPass = findViewById(R.id.tutConfirmPass);
        switch_to_student = findViewById(R.id.switch_to_student);
        objectImageView = findViewById(R.id.ImageUpload);
        selectModule = findViewById(R.id.selectModule);

        switch_to_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TutorActivity.this, StudentActivity.class);
                startActivity(intent);

            }
        });
        btn_tut_register.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                DatabaseHelper db_Helper = new  DatabaseHelper(TutorActivity.this);
                checkValidity();
                if(tutName.getText().toString().trim().equals("")||tutSurname.getText().toString().trim().equals("")||tutPassword.getText().toString().trim().equals("")||tutConfirmPass.getText().toString().trim().equals("")) {
                    Toast.makeText(TutorActivity.this, "Please fill in all the entries", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Integer.parseInt(tutConfirmPass.getText().toString().trim())!=Integer.parseInt(tutPassword.getText().toString().trim()))
                {
                    tutConfirmPass.setError("Confirmation Password is not the same as Password");
                    return;
                }
                if(!isEmail(tutEmail))
                {
                    tutEmail.setError("Please enter VALID email");
                    return;
                }
                if(objectImageView.getDrawable()!=null && imageToStore!=null) {
                    db_Helper.addTutor(tutName.getText().toString().trim(),
                            tutSurname.getText().toString().trim(), tutEmail.getText().toString().trim(),
                            Integer.parseInt(tutPassword.getText().toString().trim()), imageToStore);
                }else{
                    Toast.makeText(TutorActivity.this, "Please upload image", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TutorActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        selectedModule = new boolean[moduleArray.length];

        selectModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        TutorActivity.this);
                builder.setTitle("Select Module");
                builder.setCancelable(false);
                builder.setMultiChoiceItems(moduleArray, selectedModule, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if(b){
                            moduleList.add(i);
                            Collections.sort(moduleList);
                        }else{
                            moduleList.remove(i);
                        }
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        StringBuilder stringBuilder = new StringBuilder();

                        for (int j = 0; j<moduleList.size();j++){
                            stringBuilder.append(moduleArray[moduleList.get(j)]);
                            if(j!= moduleList.size()-1){
                                stringBuilder.append(", ");
                            }
                        }
                        selectModule.setText(stringBuilder.toString());
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                builder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        for(int j = 0;j<= selectedModule.length;j++){
                            selectedModule[j] = false;
                            moduleList.clear();
                            selectModule.setText("");
                        }
                    }
                });

                builder.show();
            }
        });

    }

    public void chooseImage(View objectView){
        try {
            Intent objectIntent = new Intent();
            objectIntent.setType("image/*");
            objectIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(objectIntent,123);
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==123 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
                Uri imageFilePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(), imageFilePath);
                objectImageView.setImageBitmap(imageToStore);
            }
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
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
        if (isEmpty(tutName)) {
            tutName.setError("Name is required!");
        }
        if (isEmpty(tutSurname)) {
            tutSurname.setError("Surname is required!");
        }
        if (isEmpty(tutEmail)) {
            tutEmail.setError("Email is required!");
        }
        if (isEmpty(tutPassword)) {
            tutPassword.setError("Password is required!");
        }
        if (isEmpty(tutConfirmPass)) {
            tutConfirmPass.setError("Confirm Password required");
        }
    }
}