package app.services;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import app.DTO.CarDTO;
import app.entities.Car;
import app.jpa.CarJPA;

@Stateless
public class CarService {

	@EJB
	private CarJPA jpa;

	public CarService() {
	}

	public List<CarDTO> getAll() {
		List<Car> carList = jpa.getAll();
		List<CarDTO> CarDTOList = CarDTO.mapToResponse(carList);
		return CarDTOList;
	}

	public Car addCar(Car car) throws ParseException {
//		List<CarDTO> carListResponse = new ArrayList<>();
//		
//		List<Car> carList = Car.mapToEntity(carListResponse);
//		
//		return jpa.addEntity(carList.get(0));
		return jpa.addEntity(car);
	}

	public List<CarDTO> getCarByCountry(String country) {
		List<CarDTO> carList = CarDTO.mapToResponse(jpa.getCarByCountry(country));

		return carList;
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
