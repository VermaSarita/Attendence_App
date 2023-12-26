package com.example.appointmentsystem.Model.Appointment;
import com.google.gson.annotations.SerializedName;

public class ResAppointment {

    @SerializedName("Message")
    public String message;
    @SerializedName("Response")
    public String response;

    public ResAppointment(String message, String response) {
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
