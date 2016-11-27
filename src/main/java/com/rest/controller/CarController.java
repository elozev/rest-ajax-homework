package com.rest.controller;

import com.rest.model.Car;
import com.rest.service.CarService;

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
public class CarController {

    private CarService carService = CarService.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCars(@DefaultValue("") @QueryParam("page") String page,
                            @DefaultValue("") @QueryParam("manufacturer") String manufacturer,
                            @DefaultValue("") @QueryParam("model") String model,
                            @DefaultValue("") @QueryParam("year") String year,
                            @DefaultValue("") @QueryParam("engineType") String engineType) {


        //TODO : Save all params in a List
        //TODO : Iritate through the list and check if param is null
        //TODO : method witch accepts param and list

        if (manufacturer.equals("")
                && model.equals("")
                && year.equals("")
                && engineType.equals("")
                && page.equals("")) {
            return Response.ok(carService.getCarList()).build();
//        } else if (!page.equals("")) {
//            if (carService.getByPage(Integer.parseInt(page)) != null) {
//                return Response.ok(carService.getByPage(Integer.parseInt(page))).build();
//            }
//            return Response.status(416).build();
//        }
        } else {
            List<Car> filteredCars = new ArrayList<>(carService.getCarList());
            Map<String, String> filters = new HashMap<>();
            filters.put("manufacturer", manufacturer);
            filters.put("model", model);
            filters.put("year", year);
            filters.put("engineType", engineType);
            filters.put("page", page);


            for (Map.Entry<String, String> entry : filters.entrySet()) {
                if (!entry.getValue().equals("")) {
                    if (!entry.getKey().equals("page"))
                        filteredCars.retainAll(carService.filter(entry.getKey(), entry.getValue()));
                    if (entry.getKey().equals("page"))
                        filteredCars = carService.getByPage(Integer.parseInt(page), filteredCars);
                }
            }
            return Response.ok(filteredCars).build();
        }


        //return null;
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
