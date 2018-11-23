package com.example.abodimazen.ChildCare;

public class Child {

    private String name;
    private String bloodType;
    private String dateOfBirth;
    private String sex;
    private String nationality;
    private String user_id;
    private String date;
    private String typeOfPlan;
    private String price;
    private String status;
    private String hospitalName;
    private String lastVacc;
    private String ASatus;
    private String placeOfBirth;
    private String dateStatus;
    private String photoURL;

    public Child() {

    }


    public Child(String name, String bloodType, String dateOfBirth, String sex, String nationality, String user_id, String date, String typeOfPlan,
                 String price, String status, String hospitalName, String lastVacc, String ASatus, String placeOfBirth, String dateStatus, String photoURL) {
        this.name = name;
        this.bloodType = bloodType;
        this.dateOfBirth = dateOfBirth;
        this.sex = sex;
        this.nationality = nationality;
        this.user_id = user_id;
        this.date = date;
        this.typeOfPlan = typeOfPlan;
        this.price = price;
        this.status = status;
        this.hospitalName = hospitalName;
        this.lastVacc = lastVacc;
        this.ASatus = ASatus;
        this.placeOfBirth = placeOfBirth;
        this.dateStatus = dateStatus;
        this.photoURL = photoURL;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTypeOfPlan() {
        return typeOfPlan;
    }

    public void setTypeOfPlan(String typeOfPlan) {
        this.typeOfPlan = typeOfPlan;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getLastVacc() {
        return lastVacc;
    }

    public void setLastVacc(String lastVacc) {
        this.lastVacc = lastVacc;
    }

    public String getASatus() {
        return ASatus;
    }

    public void setASatus(String ASatus) {
        this.ASatus = ASatus;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getDateStatus() {
        return dateStatus;
    }

    public void setDateStatus(String dateStatus) {
        this.dateStatus = dateStatus;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }
}
