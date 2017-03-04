package com.example.haresh.aashasmsserver.models;

import com.google.gson.annotations.SerializedName;

public class AppointmentModel {

    @SerializedName("id")
    private int id;

    @SerializedName("patient_id")
    private int patientId;

    @SerializedName("hospital_id")
    private int hospitalId;

    @SerializedName("doctor_id")
    private int doctorId;

    private String time;
    private String description;
    private String status;

    public AppointmentModel(int id, int patientId, int hospitalId, int doctorId, String time,
                            String description, String status) {
        this.id = id;
        this.patientId = patientId;
        this.hospitalId = hospitalId;
        this.doctorId = doctorId;
        this.time = time; // to be parsed into java Date later
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
