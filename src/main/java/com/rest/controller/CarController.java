package com.rest.controller;

import com.rest.model.Car;
import com.rest.service.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.jaxrs.PATCH;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by emil on 11/13/16.
 */

@Path("/cars")
public class CarController {

    private CarService carService = CarService.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Car> getCars(@DefaultValue("")@QueryParam("manufacturer") String manufacturer,
                             @DefaultValue("")@QueryParam("model") String model,
                             @DefaultValue("")@QueryParam("year") String year,
                             @DefaultValue("")@QueryParam("engine") String engineType){


        if(manufacturer.equals("")
                && model.equals("")
                && year.equals("")
                && engineType.equals("")){
            return carService.getCarList();
        }else if(!manufacturer.equals("")){
            return carService.getByManufacturer(manufacturer);
        }else if(!model.equals("")){
            return carService.getByModel(model);
        }else if(!engineType.equals("")){
            return carService.getByEngineType(engineType);
        }else if(!year.equals("")){
            return carService.getByYear(Integer.parseInt(year));
        }
        return null;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Car getCarById(@PathParam("id") String id){
        return carService.getById(Integer.parseInt(id) - 1);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String addCar(Car car){
        carService.addCar(car);
        return "Created car with id: " + car.getId();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String putCar(@PathParam("id") String id,Car car){
        carService.updateCar(Integer.parseInt(id) - 1, car);
        return "Updated car with id: " + id;
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String patchCar(@PathParam("id") String id){
        carService.deleteCar(Integer.parseInt(id) - 1);
        return "Deleted car with id: " + id;
    }

}
