package com.example.freshmarket;

public class Product {
    String proNo;
    String proName;
    String proDetails;
    float proPrice;
    float proDiscount;
    String proImage;

    public String getSectionNo() {
        return sectionNo;
    }

    public void setSectionNo(String sectionNo) {
        this.sectionNo = sectionNo;
    }

    String sectionNo;

    public String getProNo() {
        return proNo;
    }

    public void setProNo(String proNo) {
        this.proNo = proNo;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProDetails() {
        return proDetails;
    }

    public void setProDetails(String proDetails) {
        this.proDetails = proDetails;
    }

    public float getProPrice() {
        return proPrice;
    }

    public void setProPrice(float proPrice) {
        this.proPrice = proPrice;
    }

    public float getProDiscount() {
        return proDiscount;
    }

    public void setProDiscount(float proDiscount) {
        this.proDiscount = proDiscount;
    }

    public String getProImage() {
        return proImage;
    }

    public void setProImage(String proImage) {
        this.proImage = proImage;
    }
}
