package com.rest.service;

import com.rest.model.Car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by emil on 11/13/16.
 */
public class CarService {
    private static CarService carServiceInstance;

    public static CarService getInstance(){
        if(carServiceInstance == null){
            carServiceInstance = new CarService();
        }

        return carServiceInstance;
    }

    private List<Car> carList = new ArrayList<>();

    public CarService(){
        carList.add(new Car(1, "BMW", "530d", 2004));
        carList.add(new Car(2, "Nissan", "Silvia S15", 1999));
        carList.add(new Car(3, "Ford", "Mustang", 2011));
    }

    public List<Car> getCarList() {
        return Collections.unmodifiableList(carList);
    }

    public boolean addCar(Car car){
        return carList.add(car);
    }

    public int getNextId() {
        return carList.size() + 1;
    }

    public Car getById(int id) {
        return carList.get(id);
    }

    public void updateCar(int i, Car car) {
        Car updateCar = carList.get(i);
        updateCar.setManufacturer(car.getManufacturer());
        updateCar.setModel(car.getModel());
        updateCar.setYear(car.getYear());
        carList.remove(i);
        carList.add(i, car);
    }

    public void deleteCar(int i) {
        carList.remove(i);
    }
}
