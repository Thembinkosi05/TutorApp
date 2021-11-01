package com.example.finalstudent;

public class StudentModel
{
   // private String DATABASE_NAME = "Student.sqLiteDatabase";
  //  private String TABLE_NAME = "Student_Table";
    private int id;
    private String name;
    private String surname;
    private String Email;
    private int password;
    private int ConfirmP;
    private boolean booked;

    public StudentModel(int id, String name, String surname, String email, int password, int confirmP, boolean booked) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        Email = email;
        this.password = password;
        ConfirmP = confirmP;
        this.booked = booked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public int getConfirmP() {
        return ConfirmP;
    }

    public void setConfirmP(int confirmP) {
        ConfirmP = confirmP;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", Email='" + Email + '\'' +
                ", password=" + password +
                ", ConfirmP=" + ConfirmP +
                ", booked=" + booked +
                '}';
    }
}
