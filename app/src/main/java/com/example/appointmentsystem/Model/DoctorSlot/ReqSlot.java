package com.example.appointmentsystem.Model.DoctorSlot;
import com.google.gson.annotations.SerializedName;

public class ReqSlot {

    @SerializedName("Doctor_Id")
    public String doctor_Id;

    public ReqSlot(String doctor_Id) {
        this.doctor_Id = doctor_Id;
    }

    public String getDoctor_Id() {
        return doctor_Id;
    }

    public void setDoctor_Id(String doctor_Id) {
        this.doctor_Id = doctor_Id;
    }
}
