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

import app.DTO.CarDTO;
import app.entities.Car;
import app.services.CarService;

@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("cars")
public class CarResource {

	@EJB
	private CarService carService;

	@GET
	public Response getAll(@QueryParam("country") String country) {
		List<CarDTO> CarDTOList = new ArrayList<>();
		if (country != null && !country.isEmpty()) {
			CarDTOList = carService.getCarByCountry(country);
			return Response.status(Status.OK).entity(CarDTOList).build();
		} else {
			CarDTOList = carService.getAll();
			return Response.status(Status.OK).entity(CarDTOList).build();
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
		CarDTO carDTO = carService.getCarById(id);
		return Response.status(Status.OK).entity(carDTO).build();
	}

	@PUT
	@Path("/{carId}")
	public Response putCar(@PathParam("carId") long id, Car car, @Context UriInfo uriInfo) throws URISyntaxException {
		Car newCar = carService.putCar(id, car);
		String uri = uriInfo.getAbsolutePath().toString() + newCar.getId();
		return Response.created(new URI(uri)).status(Status.CREATED).entity(carService.putCar(id, car)).build();
	}

	@DELETE
	@Path("/{carId}")
	public Response deleteCar(@PathParam("carId") long id) {
		Car carToDelete = carService.deleteCar(id);
		return Response.status(Status.NO_CONTENT).entity(carToDelete).build();
	}

}
