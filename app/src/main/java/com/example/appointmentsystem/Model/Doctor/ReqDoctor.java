package com.example.appointmentsystem.Model.Doctor;

public class ReqDoctor {

    public String departmentID;

    public ReqDoctor(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }


}
