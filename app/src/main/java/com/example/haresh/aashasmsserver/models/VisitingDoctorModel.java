package com.example.haresh.aashasmsserver.models;

import com.google.gson.annotations.SerializedName;

public class VisitingDoctorModel {

    @SerializedName("id")
    private int id;

    @SerializedName("doctor_id")
    private int doctorID;

    @SerializedName("hospital_id")
    private int hospitalID;

    @SerializedName("begin")
    private String beginTime;

    @SerializedName("end")
    private String endTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(int doctorID) {
        this.doctorID = doctorID;
    }

    public int getHospitalID() {
        return hospitalID;
    }

    public void setHospitalID(int hospitalID) {
        this.hospitalID = hospitalID;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
