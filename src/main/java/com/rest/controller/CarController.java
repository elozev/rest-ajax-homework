package com.rest.controller;

import com.rest.model.Car;
import com.rest.service.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by emil on 11/13/16.
 */

@Path("/cars")
@Api("cars")
public class CarController {

    private CarService carService;

    @GET
    @ApiOperation(value = "Get list of cars", response = Car.class, responseContainer = "List")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCars(){
        carService = CarService.getInstance();
        return Response.ok(carService.getCarList()).build();
    }

    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.TEXT_XML)
    public void addCar(Car car){
        String result = "Added: " + carService.addCar(car);
        //return Response.status(201).entity(result).build();
    }

}
