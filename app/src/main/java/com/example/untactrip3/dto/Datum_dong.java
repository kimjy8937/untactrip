package com.example.untactrip3.dto;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Datum_dong {

    @SerializedName("hh")
    @Expose
    private String hh;
    @SerializedName("ldongCd")
    @Expose
    private String ldongCd;
    @SerializedName("flowDensityPercentile")
    @Expose
    private Double flowDensityPercentile;

    public String getHh() {
        return hh;
    }

    public void setHh(String hh) {
        this.hh = hh;
    }

    public String getLdongCd() {
        return ldongCd;
    }

    public void setLdongCd(String ldongCd) {
        this.ldongCd = ldongCd;
    }

    public double getFlowDensityPercentile() {
        return flowDensityPercentile;
    }

    public void setFlowDensityPercentile(Double flowDensityPercentile) {
        this.flowDensityPercentile = flowDensityPercentile;
    }
}