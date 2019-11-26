package app.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.apache.log4j.Logger;

import app.DTO.CarResponse;
import app.entities.Car;
import app.exceptions.DataNotFoundException;
import app.exceptions.EmptyBodyException;
import app.services.CarService;

@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("cars")
public class CarResource {

	@EJB
	private CarService carService;

	final static Logger logger = Logger.getLogger(CarResource.class);

	@GET
	public Response getAll(@QueryParam("country") String country) {
		List<CarResponse> carResponseList = new ArrayList<>();
		logger.info("COUNTRY : " + country);
		if (country!=null && !country.isEmpty()) {
			carResponseList = carService.getCarByCountry(country);
			if (carResponseList == null | carResponseList.isEmpty()) {
				logger.info("CAR RESPONSE LIST BY COUNTRY: " + carResponseList);
				throw new DataNotFoundException("Not found");
			}
			logger.info("CAR RESPONSE LIST BY COUNTRY: " + carResponseList);
			return Response.status(Status.OK).entity(carResponseList).build();
		} else {
			carResponseList = carService.getAll();
			if (carResponseList == null | carResponseList.isEmpty()) {
				throw new DataNotFoundException("Not found");
			}
			logger.info("CAR RESPONSE LIST: " + carResponseList);
			return Response.status(Status.OK).entity(carResponseList).build();
		}
	}

	@POST
	public Response addCar(@Valid Car car, @Context UriInfo uriInfo) throws URISyntaxException, ParseException {
		Car newCar = carService.addCar(car);
		String uri = uriInfo.getAbsolutePath().toString() + newCar.getId();
		return Response.created(new URI(uri)).status(Status.CREATED).entity(newCar).build();
	}

	@GET
	@Path("/{carId}")
	public Response getCarById(@PathParam("carId") long id) {
		Car car = carService.getCarById(id);
		if (car == null) {
			throw new DataNotFoundException("Not found");
		}
		return Response.status(Status.OK).entity(car).build();
	}

	@PUT
	@Path("/{carId}")
	public Response putCar(@PathParam("carId") long id, Car car, @Context UriInfo uriInfo) throws URISyntaxException {
		car.setId(id);
		if (car.getBrand() == null || car.getCountry() == null || car == null) {
			throw new EmptyBodyException("Body was empty.");
		}
		Car newCar = carService.putCar(id, car);
		String uri = uriInfo.getAbsolutePath().toString() + newCar.getId();
		return Response.created(new URI(uri)).status(Status.CREATED).entity(newCar).build();
	}

	@DELETE
	@Path("/{carId}")
	public Response deleteCar(@PathParam("carId") long id) {
		Car carToDelete = carService.deleteCar(id);
		return Response.status(Status.OK).entity(carToDelete).build();
	}

}
