package com.example.abodimazen.ChildCare;


public class addp {

    private String Name;
    private String Birth;
    private String gender;
    private String Bload;
    private String user_id;
    private String hospital;
    private String TypeOfPlan;
    private String appounment;
    private String PlanSatus;
    private String Place_Birth;
    private String Lastvaccination;
    private String PhotoURL;



    public addp() {

    }

    public addp(String name, String birth, String gender, String bload, String user_id, String hospital, String TypeOfPlan, String appounment, String PlanSatus, String Place_Birth, String Lastvaccination, String photoURL ) {
        this.Name = name;
        this.Birth = birth;
        this.gender = gender;
        this.Bload = bload;
        this.user_id = user_id;
        this.hospital = hospital;
        this.TypeOfPlan = TypeOfPlan;
        this.appounment = appounment;
        this.PlanSatus = PlanSatus;
        this.Place_Birth = Place_Birth;
        this.Lastvaccination = Lastvaccination;
        this.PhotoURL = photoURL;


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


    public String getAppounment() {
        return appounment;
    }

    public void setAppounment(String appounment) {
        this.appounment = appounment;
    }

    public String getPlanSatus() {
        return PlanSatus;
    }

    public void setPlanSatus(String planSatus) {
        PlanSatus = planSatus;
    }

    public String getPlace_Birth() {
        return Place_Birth;
    }

    public void setPlace_Birth(String place_Birth) {
        Place_Birth = place_Birth;
    }

    public String getLastvaccination() {
        return Lastvaccination;
    }

    public void setLastvaccination(String lastvaccination) {
        Lastvaccination = lastvaccination;
    }

    public String getPhotoURL() {
        return PhotoURL;
    }

    public void setPhotoURL(String photoURL) {
        PhotoURL = photoURL;
    }
}