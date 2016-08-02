package com.munson.david.baapp;
import java.util.Date;

public class Event {
    private String title;
    private String description;
    private Date startTime;
    private Date endTime;
    private String location;
    private boolean privateEvent;
    private String[] tags;
    private long id;
    private static long nextId;


    public Event() {
        this.id = nextId;
        nextId++;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPrivateEvent(boolean privateEvent) {
        this.privateEvent = privateEvent;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String getTitle() {

        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getLocation() {
        return location;
    }

    public boolean isPrivateEvent() {
        return privateEvent;
    }

    public String[] getTags() {
        return tags;
    }

    public long getId() {
        return id;
    }

    public static long getNextId() {
        return nextId;
    }


}
