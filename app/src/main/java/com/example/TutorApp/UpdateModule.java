package com.example.TutorApp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class UpdateModule extends AppCompatActivity {
    DatabaseHelper db;
    Button update;
    EditText newName,newDescription;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_module);
        db = new DatabaseHelper(this);

        update = findViewById(R.id.btn_update_2);
        newName = findViewById(R.id.newName);
        newDescription = findViewById(R.id.newDescr);
        TextView curdescr = findViewById(R.id.curDescr);
        TextView curName = findViewById(R.id.curName);


        ModuleModel cur = MaintainModuleAdapter.curModule;
        curdescr.setText(cur.getDescription());
        curName.setText(cur.getName());
        DatabaseHelper myDb = new DatabaseHelper(UpdateModule.this);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!newDescription.getText().toString().equals("") || !newName.getText().toString().equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UpdateModule.this);
                    builder.setTitle("Updating Module");
                    builder.setMessage("Module will be updated")
                            .setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    myDb.updateModule(cur.getModuleCode(), newName.getText().toString(), newDescription.getText().toString());
                                    Intent intent = new Intent(UpdateModule.this,SelectedTutor.class);
                                    startActivity(intent);
                                }
                            });
                    builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Snackbar.make(v, " Module was not updated ", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    });
                    builder.setIcon(android.R.drawable.ic_dialog_alert).show();
                }
                else {
                    Toast.makeText(UpdateModule.this,"Please Fill in Name or Description",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
