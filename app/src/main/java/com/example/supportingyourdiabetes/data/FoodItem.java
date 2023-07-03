package com.example.supportingyourdiabetes.data;

public class FoodItem {

    private String img;
    private String label;
    private double ENERC_KCAL;
    private double PROCNT;
    private double FAT;
    private double CHOCDF;
    private double FIBTG;

    public FoodItem(String img, String label, double ENERC_KCAL,
                    double PROCNT, double FAT, double CHOCDF, double FIBTG) {
        this.img = img;
        this.label = label;
        this.ENERC_KCAL = ENERC_KCAL;
        this.PROCNT = PROCNT;
        this.FAT = FAT;
        this.CHOCDF = CHOCDF;
        this.FIBTG = FIBTG;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getENERC_KCAL() {
        return ENERC_KCAL;
    }

    public void setENERC_KCAL(double ENERC_KCAL) {
        this.ENERC_KCAL = ENERC_KCAL;
    }

    public double getPROCNT() {
        return PROCNT;
    }

    public void setPROCNT(double PROCNT) {
        this.PROCNT = PROCNT;
    }

    public double getFAT() {
        return FAT;
    }

    public void setFAT(double FAT) {
        this.FAT = FAT;
    }

    public double getCHOCDF() {
        return CHOCDF;
    }

    public void setCHOCDF(double CHOCDF) {
        this.CHOCDF = CHOCDF;
    }

    public double getFIBTG() {
        return FIBTG;
    }

    public void setFIBTG(double FIBTG) {
        this.FIBTG = FIBTG;
    }

}
