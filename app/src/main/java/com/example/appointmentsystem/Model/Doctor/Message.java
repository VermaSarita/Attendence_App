package com.example.appointmentsystem.Model.Doctor;
import com.google.gson.annotations.SerializedName;

public class Message {

    public int id;
    public String doctor;
    public String departmentID;
    @SerializedName("PDid")
    public Object pDid;
    @SerializedName("IsActive")
    public int isActive;

    public Message(int id, String doctor, String departmentID, Object pDid, int isActive) {
        this.id = id;
        this.doctor = doctor;
        this.departmentID = departmentID;
        this.pDid = pDid;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public Object getpDid() {
        return pDid;
    }

    public void setpDid(Object pDid) {
        this.pDid = pDid;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
}
