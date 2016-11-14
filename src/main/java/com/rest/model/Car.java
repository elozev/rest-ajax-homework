package com.rest.model;

/**
 * Created by emil on 11/13/16.
 */

public class Car {

    private int id;
    private String manufacturer;
    private String model;
    //Gasoline, Diesel, Hybrid
    private String engineType;
    private int year;

    public Car(int id, String manufacturer, String model, String engineType, int year) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.engineType = engineType;
        this.year = year;
    }


    public Car() {}

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
