package com.rest.service;

import com.rest.model.Car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Created by emil on 11/13/16.
 */
public class CarService {
    private static CarService carServiceInstance;

    public static CarService getInstance() {
        if (carServiceInstance == null) {
            carServiceInstance = new CarService();
        }

        return carServiceInstance;
    }

    private List<Car> carList = new ArrayList<>();

    public CarService() {
        String[] manufactures = {"BMW", "Nissan", "Tesla", "Lada", "Ford", "Mercedes", "Toyota"};
        String[] models = {"Model A", "Model B", "Model C", "Model D", "Model E", "Model G"};
        String[] engines = {"Diesel", "Gasoline", "Electric", "Hybrid", "LPG"};

        for(int i = 0; i < 100; i++){
            Random random = new Random();
            carList.add(new Car(i + 1,
                    manufactures[random.nextInt(manufactures.length)],
                    models[random.nextInt(models.length)],
                    engines[random.nextInt(engines.length)],
                    random.nextInt(2016 - 1900 + 1) + 1900));
            System.out.println("Car List Size: " + carList.size());
        }
    }

    public List<Car> getCarList() {
        return Collections.unmodifiableList(carList);
    }

    public boolean addCar(Car car) {
        if (car.getId() == 0) {
            car.setId(getNextId());
        }
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


    public List<Car> getByManufacturer(String manufacturer) {
        return carList.stream().filter(car -> car.getManufacturer().equals(manufacturer)).collect(Collectors.toList());
    }

    public List<Car> getByModel(String model) {
        return carList.stream().filter(car -> car.getModel().equals(model)).collect(Collectors.toList());
    }

    public List<Car> getByEngineType(String engineType) {
        return carList.stream().filter(car -> car.getEngineType().equals(engineType)).collect(Collectors.toList());
    }

    public List<Car> getByYear(int year) {
        return carList.stream().filter(car -> car.getYear() == year).collect(Collectors.toList());
    }

    public List<Car> getByPage(int pageNumber) {
        int start = pageNumber * 10;
        int end = start + 10;

        if(end > carList.size() && start > carList.size()){
            return null;
        } else if(end > carList.size() && start < carList.size()){
            end = carList.size();
        }
        return carList.subList(start, end);
    }
}
