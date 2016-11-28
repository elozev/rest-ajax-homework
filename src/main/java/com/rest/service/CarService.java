package com.rest.service;

import com.rest.model.Car;

import java.util.*;
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

        for(int i = 0; i < 1000; i++){
            Random random = new Random();
            carList.add(new Car(i + 1,
                    manufactures[random.nextInt(manufactures.length)],
                    models[random.nextInt(models.length)],
                    engines[random.nextInt(engines.length)],
                    random.nextInt(2016 - 1900 + 1) + 1900));
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


    private List<Car> getByManufacturer(String manufacturer, List<Car> carList) {
        return carList.stream().filter(car -> car.getManufacturer().toLowerCase().contains(manufacturer.toLowerCase())).collect(Collectors.toList());
    }

    private List<Car> getByModel(String model, List<Car> carList) {
        return carList.stream().filter(car -> car.getModel().toLowerCase().contains(model.toLowerCase())).collect(Collectors.toList());
    }

    private List<Car> getByEngineType(String engineType, List<Car> carList) {
        return carList.stream().filter(car -> car.getEngineType().equals(engineType)).collect(Collectors.toList());
    }

    private List<Car> getByYear(int year, List<Car> carList) {
        return carList.stream().filter(car -> car.getYear() == year).collect(Collectors.toList());
    }

    public List<Car> getByPage(int pageNumber, List<Car> carList) {
        int start = pageNumber * 10;
        int end = start + 10;
        System.out.println("start: " + start + "\nend: " + end);

        int i = 0;
        for(Car car: carList){
            System.out.println("id: " + i++ + " Car: " + car.toString());
        }

        if(end > carList.size() && start >= carList.size()){
            System.out.println("null");
            return null;
        } else if(end > carList.size() && start < carList.size()){
            System.out.println("not null    ");
            end = carList.size();
        }
        List<Car> returnList = new ArrayList<>(carList.subList(start, end));
        return returnList;
    }

    public List<Car> filter(String key, String value, List<Car> carList) {
        switch (key){
            case "manufacturer" :
                return getByManufacturer(value, carList);
            case "model":
                return getByModel(value, carList);
            case "engineType":
                return getByEngineType(value, carList);
            case "year" :
                return getByYear(Integer.parseInt(value), carList);
        }
        return null;
    }

    public List<String> getCarManufacturers() {
        return carList.stream().map(Car::getManufacturer).distinct().collect(Collectors.toList());
    }
}
