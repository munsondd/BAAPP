package com.munson.david.baapp;
import java.util.ArrayList;
import java.util.Date;


public class EventContainer {
    private ArrayList<Event> events;
    private String dateTag;
    private Date date;

    public EventContainer(){
        events = new ArrayList<>();
    }
    public ArrayList<Event> getEvents() {
        return events;
    }

    public String getDateTag() {
        return dateTag;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public void setDateTag(String dateTag) {
        this.dateTag = dateTag;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}
