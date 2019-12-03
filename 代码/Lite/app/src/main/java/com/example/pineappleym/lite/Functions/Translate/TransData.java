package com.example.pineappleym.lite.Functions.Translate;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TransData {
    @SerializedName("from")
    String from;
    @SerializedName("to")
    String to;
    @SerializedName("trans_result")
    List<TransResult> results;

    public List<TransResult> getResults() {
        return results;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setResults(List<TransResult> results) {
        this.results = results;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
