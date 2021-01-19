package com.zeomawer.qsams;

public class ModelViewStudent {
    String AdNum,RollNumber,admdate,className,dob,fathername,gender,mothername,name,phone,residence,uid;

    public ModelViewStudent() {
    }

    public ModelViewStudent(String adNum, String rollNumber, String admdate, String className, String dob, String fathername, String gender, String mothername, String name, String phone, String residence, String uid) {
        AdNum = adNum;
        RollNumber = rollNumber;
        this.admdate = admdate;
        this.className = className;
        this.dob = dob;
        this.fathername = fathername;
        this.gender = gender;
        this.mothername = mothername;
        this.name = name;
        this.phone = phone;
        this.residence = residence;
        this.uid = uid;
    }

    public String getAdNum() {
        return AdNum;
    }

    public void setAdNum(String adNum) {
        AdNum = adNum;
    }

    public String getRollNumber() {
        return RollNumber;
    }

    public void setRollNumber(String rollNumber) {
        RollNumber = rollNumber;
    }

    public String getAdmdate() {
        return admdate;
    }

    public void setAdmdate(String admdate) {
        this.admdate = admdate;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMothername() {
        return mothername;
    }

    public void setMothername(String mothername) {
        this.mothername = mothername;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
