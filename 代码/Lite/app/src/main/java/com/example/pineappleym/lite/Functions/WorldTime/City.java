package com.example.pineappleym.lite.Functions.WorldTime;

public class City {
    private String name;
    private String area;
    public City(String name, String area){
        this.name = name;
        this.area = area;
    }

    public String getArea() {
        return area;
    }

    public String getName() {
        return name;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setName(String name) {
        this.name = name;
    }
}
