package app.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import app.DTO.CarResponse;
import app.entities.Car;
import app.jpa.CarJPA;

@Stateless
public class CarService {

	@EJB
	private CarJPA jpa;

	public CarService() {
	}

	public List<CarResponse> getAll() {
		List<Car> carList = jpa.getAll();
		List<CarResponse> carResponseList = CarResponse.mapToResponse(carList);
		return carResponseList;
	}

	public Car addCar(Car car) throws ParseException {
//		List<CarResponse> carListResponse = new ArrayList<>();
//		
//		List<Car> carList = Car.mapToEntity(carListResponse);
//		
//		return jpa.addEntity(carList.get(0));
		return jpa.addEntity(car);
	}

	public List<CarResponse> getCarByCountry(String country) {
		List<CarResponse> carList = CarResponse.mapToResponse(jpa.getAll());
		List<CarResponse> carListCountry = new ArrayList<>();
		for (CarResponse car : carList) {
			if (country.equalsIgnoreCase(car.getCountry())) {
				carListCountry.add(car);
			}
		}
		return carListCountry;
	}

	public Car getCarById(long id) {
		Car car = jpa.getEntityById(id, Car.class);
		return car;
	}

	public Car putCar(long id, Car car) {
		if (id <= 0) {
			return null;
		}
		return jpa.putEntity(car);
	}

	public Car deleteCar(long id) {
		return jpa.deleteEntity(id);
	}

}
