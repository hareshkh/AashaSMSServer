package com.example.haresh.aashasmsserver.models;

public class HospitalModel {

    private int id;
    private String name;
    private String address;
    private String phone;
    private double latitude;
    private double longitude;

    public HospitalModel(int id, String name, String address, String phoneNumber, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phoneNumber;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
