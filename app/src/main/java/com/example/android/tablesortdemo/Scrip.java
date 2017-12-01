package com.example.android.tablesortdemo;

/**
 * Created by Kalpesh B Kundanani on 30-Nov-17.
 */

public class Scrip {
    private String scrip;
    private int close,change,volume;

    public Scrip(String scrip, int close, int change, int volume) {
        this.scrip = scrip;
        this.close = close;
        this.change = change;
        this.volume = volume;
    }

    public String getScrip() {
        return scrip;
    }

    public void setScrip(String scrip) {
        this.scrip = scrip;
    }

    public int getClose() {
        return close;
    }

    public void setClose(int close) {
        this.close = close;
    }

    public int getChange() {
        return change;
    }

    public void setChange(int change) {
        this.change = change;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
