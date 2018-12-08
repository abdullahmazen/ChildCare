package com.example.abodimazen.ChildCare;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class User_Details {
    private String name, email, phoneNumber, address, typeOfUser;
    private @ServerTimestamp Date timestamp;

    public User_Details() {

    }

    public User_Details(String name, String email, String phoneNumber, String address, String typeOfUser, Date timestamp) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.typeOfUser = typeOfUser;
        this.timestamp = timestamp;


    }
    public Date getTimestamp() {
        return timestamp;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getTypeOfUser() {
        return typeOfUser;
    }
}
