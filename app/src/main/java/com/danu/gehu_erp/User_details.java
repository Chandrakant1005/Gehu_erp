package com.danu.gehu_erp;

public class User_details {
    private String username;
    private String email;
    private String phone;

    // Default constructor required for calls to DataSnapshot.getValue(User.class)
    public User_details() {}

    public User_details(String username, String email, String phone) {
        this.username = username;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
