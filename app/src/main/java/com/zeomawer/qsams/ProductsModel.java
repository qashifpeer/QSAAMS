package com.zeomawer.qsams;

public class ProductsModel {



    private String School;
    private String Udise;
    private String isUser;

    private ProductsModel(){}

    public ProductsModel(String school, String udise, String isUser) {
        School = school;
        Udise = udise;
        this.isUser = isUser;
    }

    public String getSchool() {
        return School;
    }

    public void setSchool(String school) {
        School = school;
    }

    public String getUdise() {
        return Udise;
    }

    public void setUdise(String udise) {
        Udise = udise;
    }

    public String getIsUser() {
        return isUser;
    }

    public void setIsUser(String isUser) {
        this.isUser = isUser;
    }
}


