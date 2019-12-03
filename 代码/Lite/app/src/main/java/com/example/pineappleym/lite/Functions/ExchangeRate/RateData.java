package com.example.pineappleym.lite.Functions.ExchangeRate;

import com.google.gson.annotations.SerializedName;

public class RateData {
    @SerializedName("sucr")
    private String scur;
    @SerializedName("tcur")
    private String tcur;
    @SerializedName("ratenm")
    private String ratenm;
    @SerializedName("rate")
    private String rate;
    @SerializedName("update")
    private String update;

    public String getRate() {
        return rate;
    }

    public String getRatenm() {
        return ratenm;
    }

    public String getScur() {
        return scur;
    }



    public String getTcur() {
        return tcur;
    }

    public String getUpdate() {
        return update;
    }


    public void setRate(String rate) {
        this.rate = rate;
    }

    public void setRatenm(String ratenm) {
        this.ratenm = ratenm;
    }

    public void setScur(String scur) {
        this.scur = scur;
    }



    public void setTcur(String tcur) {
        this.tcur = tcur;
    }

    public void setUpdate(String update) {
        this.update = update;
    }
}
