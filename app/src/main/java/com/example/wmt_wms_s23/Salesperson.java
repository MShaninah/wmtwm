package com.example.wmt_wms_s23;

import androidx.annotation.NonNull;

public class Salesperson {
    private int id; // A unique identifier for the salesperson, e.g., from the database
    private String name;
    private String region;

    public Salesperson(String name, String region) {
        this.name = name;
        this.region = region;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    // You can add more properties and methods as needed

    @NonNull
    @Override
    public String toString() {
        return name; // Return the name for display purposes
    }
}
