package com.example.TutorApp;

public class RatingModel {
    int ratingID,TutorID,StudentID,numStars;
    String comment;

    public int getRatingID() {
        return ratingID;
    }

    public int getTutorID() {
        return TutorID;
    }

    public int getStudentID() {
        return StudentID;
    }

    public int getNumStars() {
        return numStars;
    }

    public String getComment() {
        return comment;
    }

    public RatingModel(int ratingID, int tutorID, int studentID, int numStars, String comment) {
        this.ratingID = ratingID;
        TutorID = tutorID;
        StudentID = studentID;
        this.numStars = numStars;
        this.comment = comment;
    }
}
