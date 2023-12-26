package com.example.appointmentsystem.Model.Pharmacy;
import com.google.gson.annotations.SerializedName;

public class ResPharmacy {

    @SerializedName("Message")
    public String message;
    @SerializedName("Response")
    public String response;

    public ResPharmacy(String message, String response) {
        this.message = message;
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
