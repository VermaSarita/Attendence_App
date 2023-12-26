package com.example.appointmentsystem.Model.Doctor;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResDoctor {

    @SerializedName("Message")
    public ArrayList<Message> message;

    public ResDoctor(ArrayList<Message> message) {
        this.message = message;
    }

    public ArrayList<Message> getMessage() {
        return message;
    }

    public void setMessage(ArrayList<Message> message) {
        this.message = message;
    }
}
