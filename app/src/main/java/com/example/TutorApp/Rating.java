package com.example.TutorApp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Rating  extends AppCompatActivity {

    DatabaseHelper db;
    int numStars;
    RatingBar ratingBar;
    TextView tutName;
    EditText comment;
    Button rate;
    TutorModel tutorModel;
    StudentModel studentModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_activity);
        db = new DatabaseHelper(this);
        comment = findViewById(R.id.comment);
        ratingBar = findViewById(R.id.ratingBar);
        tutName = findViewById(R.id.RatingTutName);
        rate = findViewById(R.id.btn_rate);

        tutorModel = TutorAdapter.T;
        tutName.setText(tutorModel.getName());
        studentModel = MainActivity.curStudent;

        DatabaseHelper db_helper = new DatabaseHelper(Rating.this);
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numStars = (int)ratingBar.getRating();

                AlertDialog.Builder builder = new AlertDialog.Builder(Rating.this);
                builder.setTitle("Rating");
                builder.setMessage("Thank you for rating ❤")
                        .setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                db_helper.addRatings(numStars,comment.getText().toString(),studentModel.getId(),tutorModel.getId());
                                Intent intent = new Intent(Rating.this,SelectedTutor.class);
                                startActivity(intent);
                            }
                        });
                builder.setIcon(android.R.drawable.ic_dialog_info).show();
            }
        });

    }
}
