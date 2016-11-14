package com.rest.model;

import com.rest.service.CarService;

/**
 * Created by emil on 11/13/16.
 */
//@ApiModel
public class Car {

    private int id;
    private String manufacturer;
    private String model;
    private int year;

    public Car(int id, String manufacturer, String model, int year) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
    }

    public Car(String manufacturer, String model, int year) {
        this(CarService.getInstance().getNextId(), manufacturer, model, year);
    }

    public Car() {
        this("", "", 1);
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
