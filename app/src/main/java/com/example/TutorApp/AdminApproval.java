package com.example.TutorApp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AdminApproval extends AppCompatActivity {
    DatabaseHelper db;
    TextView approveTut;
    Button btn_approve,btn_decline;
    ImageView academicView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_approval);
        db = new DatabaseHelper(this);

        approveTut=findViewById(R.id.text_pending_tutor);
        btn_approve=findViewById(R.id.btn_approve);
        btn_decline=findViewById(R.id.btn_decline);
        academicView=findViewById(R.id.academic_view);
        TutorModel tutorModel = PendingTutorAdapter.pendingTut;
        approveTut.setText(tutorModel.getName());
        DatabaseHelper myDb = new DatabaseHelper(AdminApproval.this);
        academicView.setImageBitmap(myDb.returnImageAcademic(tutorModel.getName()));
        btn_approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminApproval.this);
                builder.setTitle("Tutor Approval");
                builder.setMessage(tutorModel.getName()+" will be approved as a tutor")
                        .setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                myDb.approveTutor(tutorModel.getId());
                                Intent intent = new Intent(AdminApproval.this,PendingTutors.class);
                                startActivity(intent);
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, null);
                builder.setIcon(android.R.drawable.ic_dialog_alert).show();
            }
        });
        btn_decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminApproval.this);
                builder.setTitle("Tutor Approval");
                builder.setMessage(tutorModel.getName()+" will be declined as a tutor")
                        .setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                myDb.declineTutor(tutorModel.getId());
                               Intent intent = new Intent(AdminApproval.this,PendingTutors.class);
                               startActivity(intent);
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, null);
                builder.setIcon(android.R.drawable.ic_dialog_alert).show();
            }
        });
    }
}
