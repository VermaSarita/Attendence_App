package com.example.appointmentsystem;

public class ModelClass {
    int Drimage;
    String textName,textAbout,feeView ,phoneno;
    float ratingBar;

    public ModelClass(int drimage, String textName, String textAbout, String feeView, String phoneno, float ratingBar) {
        Drimage = drimage;
        this.textName = textName;
        this.textAbout = textAbout;
        this.feeView = feeView;
        this.phoneno = phoneno;
        this.ratingBar = ratingBar;
    }

    public int getDrimage() {
        return Drimage;
    }

    public void setDrimage(int drimage) {
        Drimage = drimage;
    }

    public String getTextName() {
        return textName;
    }

    public void setTextName(String textName) {
        this.textName = textName;
    }

    public String getTextAbout() {
        return textAbout;
    }

    public void setTextAbout(String textAbout) {
        this.textAbout = textAbout;
    }

    public String getFeeView() {
        return feeView;
    }

    public void setFeeView(String feeView) {
        this.feeView = feeView;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public float getRatingBar() {
        return ratingBar;
    }

    public void setRatingBar(float ratingBar) {
        this.ratingBar = ratingBar;
    }
}
