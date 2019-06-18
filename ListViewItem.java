package com.example.dday;

public class ListViewItem {
    private String EventContents;
    private int Dday;

    public void setEventContents(String contents){
        EventContents = contents;
    }
    public void setD_day(int dday){
        Dday=dday;
    }
    public String getEventContents(){
        return this.EventContents;
    }
    public int getDday(){
        return this.Dday;
    }
}
