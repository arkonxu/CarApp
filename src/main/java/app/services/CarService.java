package app.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import app.entities.Car;
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
		for (Car car : carList) {
			if (car.getCountry().equalsIgnoreCase(country)) {
				carList.add(car);
			}
		}
		return carList;
	}

	public Car getCarById(long id) {
		Car car;
		car = jpa.getEntityById(id, Car.class);
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
