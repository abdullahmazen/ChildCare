package com.example.abodimazen.fahad;

public class addp {

    private String Name ;
    private String Birth ;
    private String gender ;
    private String Bload ;
    private String user_id;
    private String hospital;
    private String TypeOfPlan;
    private String Price;
    private String Time;
    private String Dates;
    private String Satus;








    public addp() {

    }

    public addp(String name, String birth, String gender, String bload, String user_id, String hospital, String TypeOfPlan, String Price, String Time, String Dates, String Satus) {
        this.Name = name;
        this.Birth = birth;
        this.gender = gender;
        this.Bload = bload;
        this.user_id = user_id;
        this.hospital = hospital;
        this.TypeOfPlan = TypeOfPlan;
        this.Price = Price;
        this.Time =Time;
        this.Dates = Dates;
        this.Satus = Satus;

        }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBirth() {
        return Birth;
    }

    public void setBirth(String birth) {
        Birth = birth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBload() {
        return Bload;
    }

    public void setBload(String bload) {
        Bload = bload;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getTypeOfPlan() {
        return TypeOfPlan;
    }

    public void setTypeOfPlan(String typeOfPlan) {
        TypeOfPlan = typeOfPlan;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDates() {
        return Dates;
    }

    public void setDates(String dates) {
        Dates = dates;
    }

    public String getSatus() {
        return Satus;
    }

    public void setSatus(String satus) {
        Satus = satus;
    }
}