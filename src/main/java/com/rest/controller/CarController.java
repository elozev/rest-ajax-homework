package com.rest.controller;

import com.rest.model.Car;
import com.rest.service.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by emil on 11/13/16.
 */

@Path("/cars")
@Api("Cars")
public class CarController {

    private CarService carService = CarService.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns all cars or filtered ones",
            response = Car.class,
            responseContainer = "List")
    public Response getCars(@DefaultValue("") @QueryParam("page") String page,
                            @DefaultValue("") @QueryParam("manufacturer") String manufacturer,
                            @DefaultValue("") @QueryParam("model") String model,
                            @DefaultValue("") @QueryParam("year") String year,
                            @DefaultValue("") @QueryParam("engineType") String engineType) {


        if (manufacturer.equals("")
                && model.equals("")
                && year.equals("")
                && engineType.equals("")
                && page.equals("")) {
            return Response.ok(carService.getCarList()).build();
        } else {
            List<Car> filteredCars = new ArrayList<>(carService.getCarList());
            Map<String, String> filters = new HashMap<>();
            filters.put("manufacturer", manufacturer);
            filters.put("model", model);
            filters.put("year", year);
            filters.put("engineType", engineType);
            //filters.put("page", page);


            for (Map.Entry<String, String> entry : filters.entrySet()) {
                if (!entry.getValue().equals("")) {
                    //      if (!entry.getKey().equals("page"))
                    filteredCars = carService.filter(entry.getKey(), entry.getValue(), filteredCars);
                    //        filteredCars = carService.getByPage(Integer.parseInt(page), filteredCars);
                }
            }
            if (!page.equals(""))
                filteredCars = carService.getByPage(Integer.parseInt(page), filteredCars);

            return Response.ok(filteredCars).build();
        }
    }

    @GET
    @Path("/names")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Find manufacturers names",
            response = Car.class,
            responseContainer = "List")
    public Response getCarManufacturers() {
        return Response.ok(carService.getCarManufacturers()).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Find specific car by id",
            response = Car.class)
    public Response getCarById(@PathParam("id") String id) {
        return Response.ok(carService.getById(Integer.parseInt(id) - 1)).build();
    }

    @POST
    @ApiOperation(value = "Add Car",
            response = Car.class)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCar(Car car) {
        carService.addCar(car);
        return Response.ok(car).build();
    }

    @PUT
    @ApiOperation(value = "Update car",
            response = Car.class)
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String putCar(@PathParam("id") String id, Car car) {
        carService.updateCar(Integer.parseInt(id) - 1, car);
        return "Updated car with id: " + id;
    }

    @DELETE
    @ApiOperation(value = "Delete car",
            response = Car.class)
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String patchCar(@PathParam("id") String id) {
        carService.deleteCar(Integer.parseInt(id) - 1);
        return "Deleted car with id: " + id;
    }

}
