package com.example.psycology_app.Models;

public class SetModel {
    String setName;
    int count;

    public SetModel(String setName) {
        this.setName = setName;
        this.count = 0;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public void incrementCount() {
        count++;
    }

}
