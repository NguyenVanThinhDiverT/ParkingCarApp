package com.example.apptest;

import com.google.firebase.database.PropertyName;

public class UserHelperClass {
//    String name, username, email, phoneNo, password, id, userID, status, date;
    private String name;
    private String username;
    private String email;
    private String phoneNo;
    private String password;
    private String id;
    private String userID;
    private String status;
    private long time;
    private String slot;



    public UserHelperClass() {

    }
    public UserHelperClass(String name, String username, String email, String phoneNo, String password,
                           String id, String userID, long time) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.phoneNo = phoneNo;
        this.password = password;
        this.id = id;
        this.userID = userID;
//        this.status = status;
        this.time = time;
//        this.slot = slot;

    }

    @PropertyName("name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @PropertyName("username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @PropertyName("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @PropertyName("phoneNo")
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @PropertyName("password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @PropertyName("id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @PropertyName("userID")
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }

    @PropertyName("time")
    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

//    public String getSlot() {
//        return slot;
//    }
//
//    public void setSlot(String slot) {
//        this.slot = slot;
    //   }
}
