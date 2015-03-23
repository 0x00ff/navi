package org.fish.navi.model;

import java.util.Date;
import java.util.UUID;

public class Target {
    private UUID id;
    private String name = "";
    private String category = "";
    private String comment = "";
    private double latitude;
    private double longitude;
    private double altitude;
    private float accuracy;
    private Date created;
    private Date modified;


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }
    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public float getAccuracy() {
        return accuracy;
    }
    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public Date getCreated() {
        return created;
    }

    public Date getModified() {
        return modified;
    }
    public void setModified(Date modified) {
        this.modified = modified;
    }


    public Target(){
        id = UUID.randomUUID();
        created = new Date();
    }
}

