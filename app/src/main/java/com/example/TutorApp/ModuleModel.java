package com.example.TutorApp;

public class ModuleModel {
    private String Name;
    private String Description;
    private int moduleCode;

    public ModuleModel(String name, String description, int moduleCode) {
        Name = name;
        Description = description;
        this.moduleCode = moduleCode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(int moduleCode) {
        this.moduleCode = moduleCode;
    }
}
