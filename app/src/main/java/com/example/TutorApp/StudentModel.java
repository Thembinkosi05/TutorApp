package com.example.TutorApp;

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

    public StudentModel() {
    }

    public StudentModel(int id ,String name, String surname, String email, int password, int ConfirmP) {
        this.name = name;
        this.surname = surname;
        Email = email;
        this.password = password;
        this.ConfirmP = ConfirmP;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getConfirmP() {
        return ConfirmP;
    }

    public void setConfirmP(int confirmP) {
        ConfirmP = confirmP;
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

    @Override
    public String toString() {
        return "StudentModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", Email='" + Email + '\'' +
                ", password=" + password +
                ", ConfirmP=" + ConfirmP +
                '}';
    }
}
