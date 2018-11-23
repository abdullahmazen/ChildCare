package com.example.abodimazen.ChildCare;

public class User_Details {
    private String name, email, phoneNumber, address, typeOfUser;

    public User_Details() {

    }

    public User_Details(String name, String email, String phoneNumber, String address, String typeOfUser) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.typeOfUser = typeOfUser;


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
