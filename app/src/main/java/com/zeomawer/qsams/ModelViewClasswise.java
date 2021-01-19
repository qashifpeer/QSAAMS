package com.zeomawer.qsams;

public class ModelViewClasswise {String RollNumber,className,name;

    public ModelViewClasswise() {
    }

    public ModelViewClasswise(String rollNumber,String className,String name) {
        RollNumber = rollNumber;
        this.className = className;
        this.name = name;

    }

    public String getRollNumber() {
        return RollNumber;
    }

    public void setRollNumber(String rollNumber) {
        RollNumber = rollNumber;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}