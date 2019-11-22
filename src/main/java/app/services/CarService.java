package app.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import app.entities.Car;
import app.exceptions.DataNotFoundException;
import app.exceptions.ErrorMessage;
import app.jpa.CarJPA;

@Stateless
public class CarService {

	@EJB
	private CarJPA jpa;

	public CarService() {
	}

	public List<Car> getAll() {
		return jpa.getAll();
	}

	public String getMsg(String message) {
		return "Hola " + message + " !";
	}

	public Car addCar(Car car) {
		return jpa.addEntity(car);
	}

	public List<Car> getCarByCountry(String country) {
		List<Car> carList = jpa.getAll();
		List<Car> carListCountry = new ArrayList<>();
		for (Car car : carList) {
			if (car.getCountry().equalsIgnoreCase(country)) {
				carListCountry.add(car);
			}
		}
		return carListCountry;
	}

	public Car getCarById(long id) {
		Car car = jpa.getEntityById(id, Car.class);
		if (car == null) {
			throw new DataNotFoundException("Not found");
		}
		return car;
	}

	public Car putCar(long id, Car car) {
		if (id <= 0) {
			return null;
		}
		return jpa.putEntity(car);
	}

	public void deleteCar(long id) {
		jpa.deleteEntity(id);
	}

}
