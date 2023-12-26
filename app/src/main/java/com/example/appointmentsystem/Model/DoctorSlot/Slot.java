package com.example.appointmentsystem.Model.DoctorSlot;
import com.google.gson.annotations.SerializedName;

public class Slot {

    @SerializedName("SlotId")
    public int slotId;
    @SerializedName("Timing")
    public String timing;
    @SerializedName("Slot")
    public String slot;
    @SerializedName("Doctor_Id")
    public String doctor_Id;

    public Slot(int slotId, String timing, String slot, String doctor_Id) {
        this.slotId = slotId;
        this.timing = timing;
        this.slot = slot;
        this.doctor_Id = doctor_Id;

    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getDoctor_Id() {
        return doctor_Id;
    }

    public void setDoctor_Id(String doctor_Id) {
        this.doctor_Id = doctor_Id;
    }
}
