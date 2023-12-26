package com.example.appointmentsystem.Model.Department;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class ResDepartment {

    @SerializedName("Message")
    public ArrayList<Message> message;

    public ResDepartment(ArrayList<Message> message) {
        this.message = message;
    }

    public ArrayList<Message> getMessage() {
        return message;
    }

    public void setMessage(ArrayList<Message> message) {
        this.message = message;
    }
}
