package com.example.abodimazen.ChildCare;


public class addp {

    private String Name;
    private String dateOfBirth;
    private String gender;
    private String bloodType;
    private String user_id;
    private String hospital;
    private String TypeOfPlan;
    private String appounment;
    private String PlanSatus;
    private String Place_Birth;
    private String lastVacc;
    private String PhotoURL;



    public addp() {

    }

    public addp(String name, String dateOfBirth, String gender, String bloodType, String user_id, String hospital, String TypeOfPlan, String appounment, String PlanSatus, String Place_Birth, String lastVacc, String photoURL ) {
        this.Name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.bloodType = bloodType;
        this.user_id = user_id;
        this.hospital = hospital;
        this.TypeOfPlan = TypeOfPlan;
        this.appounment = appounment;
        this.PlanSatus = PlanSatus;
        this.Place_Birth = Place_Birth;
        this.lastVacc = lastVacc;
        this.PhotoURL = photoURL;


    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getLastVacc() {
        return lastVacc;
    }

    public void setLastVacc(String lastVacc) {
        this.lastVacc = lastVacc;
    }

    public String getPhotoURL() {
        return PhotoURL;
    }

    public void setPhotoURL(String photoURL) {
        PhotoURL = photoURL;
    }
}