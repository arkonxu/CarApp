package app.resources;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import app.entities.Car;
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
	@Path("/hola/{param}")
	public String getMsg(@PathParam("param") String message) {
		return carService.getMsg(message);
	}

	@GET
	public List<Car> getAll(@QueryParam("country") String country) {
		if (country != null) {
			return carService.getCarByCountry(country);
		}
		return carService.getAll();
	}

	@POST
	public String addCar(Car car) {
		try {
			carService.addCar(car);
			return "Se ha añadido correctamente";
		} catch (Exception e) {
			logger.error(e);
			return "Ha habido un error al añadir";
		}
	}

	@GET
	@Path("/{carId}")
	public Car getCarById(@PathParam("carId") long id) {
		try {
			return carService.getCarById(id);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@PUT
	@Path("/{carId}")
	public String putCar(@PathParam("carId") long id, Car car) {
		try {
			car.setId(id);
			carService.putCar(id, car);
			return "Se ha actualizado correctamente";
		} catch (Exception e) {
			logger.error(e);
			return "Ha habido un error al actualizar";
		}
	}

	@DELETE
	@Path("/{carId}")
	public String deleteCar(@PathParam("carId") long id) {
		try {
			carService.deleteCar(id);
			return "Se ha borrado correctamente";
		} catch (Exception e) {
			logger.error(e);
			return "Ha habido un error al eliminar";
		}
	}

}
