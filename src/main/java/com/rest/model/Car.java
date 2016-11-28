package com.rest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by emil on 11/13/16.
 */

@ApiModel
public class Car {

    private int id;
    @ApiModelProperty(required = true, value = "Car manufacturer", example = "Subaru")
    private String manufacturer;
    @ApiModelProperty(required = true, value = "Model", example = "C class")
    private String model;
    //Gasoline, Diesel, Hybrid
    @ApiModelProperty(required = true, value = "Engine Type", example = "Diesel")
    private String engineType;
    @ApiModelProperty(required = true, value = "Year of manufacturing", example = "1998")
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

    @Override
    public String toString() {
        return "Car{" +
                "manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", engineType='" + engineType + '\'' +
                ", year=" + year +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (year != car.year) return false;
        if (!manufacturer.equals(car.manufacturer)) return false;
        if (!model.equals(car.model)) return false;
        return engineType.equals(car.engineType);

    }
}
