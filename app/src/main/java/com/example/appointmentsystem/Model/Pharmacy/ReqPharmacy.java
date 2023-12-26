package com.example.appointmentsystem.Model.Pharmacy;
import com.google.gson.annotations.SerializedName;

public class ReqPharmacy {

    @SerializedName("Name")
    public String name;
    @SerializedName("ConsultantDr")
    public String consultantDr;
    @SerializedName("MobileNo")
    public String mobileNo;
    @SerializedName("Email")
    public String email;
    @SerializedName("Address")
    public String address;
    @SerializedName("UploadPres")
    public String uploadPres;

    public ReqPharmacy(String name, String consultantDr, String mobileNo, String email, String address, String uploadPres) {
        this.name = name;
        this.consultantDr = consultantDr;
        this.mobileNo = mobileNo;
        this.email = email;
        this.address = address;
        this.uploadPres = uploadPres;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConsultantDr() {
        return consultantDr;
    }

    public void setConsultantDr(String consultantDr) {
        this.consultantDr = consultantDr;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUploadPres() {
        return uploadPres;
    }

    public void setUploadPres(String uploadPres) {
        this.uploadPres = uploadPres;
    }
}
