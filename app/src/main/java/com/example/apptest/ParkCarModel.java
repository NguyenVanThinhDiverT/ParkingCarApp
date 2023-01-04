package com.example.apptest;

import com.google.firebase.database.PropertyName;

public class ParkCarModel {
    private String id;
    private String driverName;
    private String driverNumber;
    private String numberPlate;
//    private String vehicleType;
//    private String fee;
    private long time;
    private String userId;
    private String status;
    private String slot;

    public ParkCarModel() {

    }

    public ParkCarModel(String id,
//                        String driverName, String driverNumber, String numberPlate,
                        long time, String userId, String status, String slot) {
        this.id = id;
//        this.driverName = driverName;
//        this.driverNumber = driverNumber;
//        this.numberPlate = numberPlate;
        this.time = time;
        this.userId = userId;
        this.status = status;
        this.slot = slot + "1";
    }

    @PropertyName("status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @PropertyName("id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public String getDriverName() {
//        return driverName;
//    }
//
//    public void setDriverName(String driverName) {
//        this.driverName = driverName;
//    }
//
//    public String getDriverNumber() {
//        return driverNumber;
//    }
//
//    public void setDriverNumber(String driverNumber) {
//        this.driverNumber = driverNumber;
//    }
//
//    public String getNumberPlate() {
//        return numberPlate;
//    }
//
//    public void setNumberPlate(String numberPlate) {
//        this.numberPlate = numberPlate;
//    }


//    public String getVehicleType() {
//        return vehicleType;
//    }
//
//    public void setVehicleType(String vehicleType) {
//        this.vehicleType = vehicleType;
//    }
//
//    public String getFee() {
//        return fee;
//    }
//
//    public void setFee(String fee) {
//        this.fee = fee;
//    }
    @PropertyName("time")
    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @PropertyName("userID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @PropertyName("slot")
    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }
}
