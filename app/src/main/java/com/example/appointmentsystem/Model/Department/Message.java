package com.example.appointmentsystem.Model.Department;
import com.google.gson.annotations.SerializedName;

public class Message {
    public int id;
    @SerializedName("Departmentname")
    public String departmentname;
    @SerializedName("DPid")
    public Object dPid;
    @SerializedName("IsActive")
    public int isActive;

    public Message(int id, String departmentname, Object dPid, int isActive) {
        this.id = id;
        this.departmentname = departmentname;
        this.dPid = dPid;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public Object getdPid() {
        return dPid;
    }

    public void setdPid(Object dPid) {
        this.dPid = dPid;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return departmentname;
    }
}
