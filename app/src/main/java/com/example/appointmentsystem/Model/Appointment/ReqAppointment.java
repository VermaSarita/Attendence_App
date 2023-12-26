package com.example.appointmentsystem.Model.Appointment;
import com.google.gson.annotations.SerializedName;

public class ReqAppointment {

    @SerializedName("Name")
    private String name;

    @SerializedName("MobileNo")
    private String mobileNo;

    @SerializedName("Email")
    private String email;

    @SerializedName("DOB")
    private String dOB;

    @SerializedName("Address")
    private String address;

    @SerializedName("Gender")
    private String gender;

    @SerializedName("payment")
    private String payment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getdOB() {
        return dOB;
    }

    public void setdOB(String dOB) {
        this.dOB = dOB;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public ReqAppointment(String name, String mobileNo, String email, String dOB, String address, String gender, String payment) {
        this.name = name;
        this.mobileNo = mobileNo;
        this.email = email;
        this.dOB = dOB;
        this.address = address;
        this.gender = gender;
        this.payment = payment;
    }
}
