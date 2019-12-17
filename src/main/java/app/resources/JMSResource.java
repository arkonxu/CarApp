package app.resources;

import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import app.DTO.CarDTO;
import app.jms.JMSSender;

@Path("jms")
public class JMSResource {

	@EJB
	private JMSSender sender;

	@POST
	@Path("/addObject")
	public Response sendObject(@Valid CarDTO car) {
		sender.addCar(car);
		return Response.status(Status.CREATED).entity(car).build();
	}

	@DELETE
	@Path("/deleteObject/{carId}")
	public Response deleteCar(@PathParam("carId") long id) {
		sender.deleteCar(id);
		return Response.status(Status.NO_CONTENT).build();
	}

	@PUT
	@Path("/putObject/{carId}")
	public Response putCars(CarDTO car, @PathParam("carId") long id) {
		sender.putCars(car, id);
		return Response.status(Status.CREATED).entity(car).build();
	}
}
