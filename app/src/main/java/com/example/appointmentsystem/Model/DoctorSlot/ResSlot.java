package com.example.appointmentsystem.Model.DoctorSlot;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

public class ResSlot {

    @SerializedName("Message")
    public String message;
    @SerializedName("Slots")
    public ArrayList<Slot> slots;

    public ResSlot(String message, ArrayList<Slot> slots) {
        this.message = message;
        this.slots = slots;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Slot> getSlots() {
        return slots;
    }

    public void setSlots(ArrayList<Slot> slots) {
        this.slots = slots;
    }
}
