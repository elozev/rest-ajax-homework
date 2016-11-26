package com.rest.controller;

import com.rest.model.Car;
import com.rest.service.CarService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by emil on 11/13/16.
 */

@Path("/cars")
public class CarController {

    private CarService carService = CarService.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCars(@DefaultValue("") @QueryParam("page") String page,
                            @DefaultValue("") @QueryParam("manufacturer") String manufacturer,
                            @DefaultValue("") @QueryParam("model") String model,
                            @DefaultValue("") @QueryParam("year") String year,
                            @DefaultValue("") @QueryParam("engine") String engineType) {

        List<List<Car>> lists = new ArrayList<>();
        List<Car> manufacturers = null;
        List<Car> models = null;
        List<Car> years = null;
        List<Car> engineTypes = null;


        if (manufacturer.equals("")
                && model.equals("")
                && year.equals("")
                && engineType.equals("")
                && page.equals("")) {
            return Response.ok(carService.getCarList()).build();
        } else if (!manufacturer.equals("") && !model.equals("") && !year.equals("") && !engineType.equals("")) {
            List<Car> returnList = new ArrayList<>(carService.getByManufacturer(manufacturer));
            returnList.retainAll(carService.getByModel(model));
            returnList.retainAll(carService.getByYear(Integer.parseInt(year)));
            returnList.retainAll(carService.getByEngineType("Diesel"));
            return Response.ok(returnList).build();
        } else if (!manufacturer.equals("") && !model.equals("") && !engineType.equals("")){
            List<Car> returnList = new ArrayList<>(carService.getByManufacturer(manufacturer));
            returnList.retainAll(carService.getByModel(model));
            returnList.retainAll(carService.getByEngineType(engineType));
            return Response.ok(returnList).build();
        } else if (!manufacturer.equals("") && !model.equals("") && !year.equals("")) {
            List<Car> returnList = new ArrayList<>(carService.getByManufacturer(manufacturer));
            returnList.retainAll(carService.getByModel(model));
            returnList.retainAll(carService.getByYear(Integer.parseInt(year)));
            return Response.ok(returnList).build();
        } else if (!manufacturer.equals("") && !model.equals("")) {
            List<Car> returnList = new ArrayList<>(carService.getByManufacturer(manufacturer));
            returnList.retainAll(carService.getByModel(model));
            return Response.ok(returnList).build();
        } else if (!manufacturer.equals("")) {
            return Response.ok(carService.getByManufacturer(manufacturer)).build();
        } else if (!model.equals("")) {
            return Response.ok(carService.getByModel(model)).build();
        } else if (!engineType.equals("")) {
            return Response.ok(carService.getByEngineType(engineType)).build();
        } else if (!year.equals("")) {
            return Response.ok(carService.getByYear(Integer.parseInt(year))).build();
        } else if (!page.equals("")) {
            if (carService.getByPage(Integer.parseInt(page)) != null) {
                return Response.ok(carService.getByPage(Integer.parseInt(page))).build();
            }
            return Response.status(416).build();
        }

        return null;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Car getCarById(@PathParam("id") String id) {
        return carService.getById(Integer.parseInt(id) - 1);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCar(Car car) {
        carService.addCar(car);
        return Response.ok(car).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String putCar(@PathParam("id") String id, Car car) {
        carService.updateCar(Integer.parseInt(id) - 1, car);
        return "Updated car with id: " + id;
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String patchCar(@PathParam("id") String id) {
        carService.deleteCar(Integer.parseInt(id) - 1);
        return "Deleted car with id: " + id;
    }

}
