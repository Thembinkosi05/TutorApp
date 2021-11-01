package com.example.finalstudent;

import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class SelectedTutorModel
{
    private String selectName;

    private boolean switch_Lock;

    private TutorModel tutObj;

    public SelectedTutorModel(TutorModel tutObj){this.tutObj = tutObj;}



    public SelectedTutorModel(String selectName, boolean switch_Lock) {
        this.selectName = selectName;
        this.switch_Lock = switch_Lock;
    }

    public TutorModel getTutObj() {
        return tutObj;
    }

    public void setTutObj(TutorModel tutObj) {
        this.tutObj = tutObj;
    }

    public String getSelectName() {
        return selectName;
    }

    public void setSelectName(String selectName) {
        this.selectName = selectName;
    }

    public boolean isSwitch_Lock() {
        return switch_Lock;
    }

    public void setSwitch_Lock(boolean switch_Lock) {
        this.switch_Lock = switch_Lock;
    }

    @Override
    public String toString() {
        return "SelectedTutorModel{" +
                "selectName='" + selectName + '\'' +
                ", switch_Lock=" + switch_Lock +
                '}';
    }
}
