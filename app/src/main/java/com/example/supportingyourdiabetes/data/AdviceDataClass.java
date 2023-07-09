package com.example.supportingyourdiabetes.data;

public class AdviceDataClass {

    private String dataTitle;
    private int dataDesc;
    private int dataImage;


    public AdviceDataClass(String dataTitle, int dataDesc, int dataImage) {
        this.dataTitle = dataTitle;
        this.dataDesc = dataDesc;
        this.dataImage = dataImage;
    }

    public String getDataTitle() {
        return dataTitle;
    }

    public void setDataTitle(String dataTitle) {
        this.dataTitle = dataTitle;
    }

    public int getDataDesc() {
        return dataDesc;
    }

    public void setDataDesc(int dataDesc) {
        this.dataDesc = dataDesc;
    }

    public int getDataImage() {
        return dataImage;
    }

    public void setDataImage(int dataImage) {
        this.dataImage = dataImage;
    }
}

