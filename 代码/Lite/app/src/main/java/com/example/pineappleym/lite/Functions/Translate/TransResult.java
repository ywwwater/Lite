package com.example.pineappleym.lite.Functions.Translate;

import com.google.gson.annotations.SerializedName;

public class TransResult {
    @SerializedName("src")
    private String src;
    @SerializedName("dst")
    private String des;

    public TransResult(String src,String des){
        this.src = src;
        this.des = des;
    }

    public String getDes() {
        return des;
    }

    public String getSrc() {
        return src;
    }

    public void setDes(String des) {
        this.des = des;
    };

    public void setSrc(String src) {
        this.src = src;
    }
}
