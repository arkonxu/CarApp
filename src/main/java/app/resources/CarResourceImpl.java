package app.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
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
import app.services.CarService;

@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("cars")
@LocalBean
public class CarResourceImpl implements CarResource {

	@EJB
	private CarService carService;

	@GET
	public Response getAll(@QueryParam("country") String country) {
		List<CarDTO> carDTOList = new ArrayList<>();
		if (country != null && !country.isEmpty()) {
			carDTOList = carService.getCarByCountry(country);
			return Response.status(Status.OK).entity(carDTOList).build();
		} else {
			carDTOList = carService.getAll();
			return Response.status(Status.OK).entity(carDTOList).build();
		}
	}

	@POST
	public Response addCar(@Valid CarDTO carDTO, @Context UriInfo uriInfo) throws URISyntaxException, ParseException {
		carService.addCar(carDTO);
		CarDTO newCarDTO = carService.getAll().get(carService.getAll().size() - 1);
		String uri = uriInfo.getAbsolutePath().toString() + newCarDTO.getId();
		return Response.created(new URI(uri)).status(Status.CREATED).entity(newCarDTO).build();
	}

	@GET
	@Path("/{carId}")
	public Response getCarById(@PathParam("carId") long id) {
		CarDTO carDTO = carService.getCarById(id);
		return Response.status(Status.OK).entity(carDTO).build();
	}

	@PUT
	@Path("/{carId}")
	public Response putCar(@PathParam("carId") long id, @Valid CarDTO carDTO, @Context UriInfo uriInfo)
			throws URISyntaxException, ParseException {
		CarDTO newCarDTO = carService.putCar(id, carDTO);
		String uri = uriInfo.getAbsolutePath().toString() + newCarDTO.getId();
		return Response.created(new URI(uri)).status(Status.CREATED).entity(newCarDTO).build();
	}

	@DELETE
	@Path("/{carId}")
	public Response deleteCar(@PathParam("carId") long id) {
		carService.deleteCar(id);
		return Response.status(Status.NO_CONTENT).build();
	}

	@POST
	@Path("/prueba")
	public Response pruebaTransaction(CarDTO carDTO) throws ParseException {
		CarDTO car = carService.pruebaTransaction(carDTO);
		return Response.status(Status.OK).entity(car).build();
	}

}
