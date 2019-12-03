package com.example.pineappleym.lite.Functions.ExchangeRate;

import com.google.gson.annotations.SerializedName;

public class Rate {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private RateData rateData;


    public Rate(String status,RateData rateData){
        this.status = status;
        this.rateData = rateData;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public RateData getRateData() {
        return rateData;
    }

    public void setRateData(RateData rateData) {
        this.rateData = rateData;
    }
}
