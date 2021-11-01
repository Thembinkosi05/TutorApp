package com.example.finalstudent;

public class TutorModel {
    private final int id;
    private final String name;
    private final String surname;
    private final String Email;
    private final int password;

    public TutorModel(int id, String name, String surname, String email, int password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        Email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return Email;
    }

    public int getPassword() {
        return password;
    }
}
