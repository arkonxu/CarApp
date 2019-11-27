package app.services;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import app.DTO.CarDTO;
import app.entities.Car;
import app.exceptions.DataNotFoundException;
import app.exceptions.EmptyBodyException;
import app.jpa.CarJPA;

@Stateless
public class CarService {

	@EJB
	private CarJPA jpa;

	public CarService() {
	}

	public List<CarDTO> getAll() {
		List<Car> carList = jpa.getAll();
		List<CarDTO> CarDTOList = CarDTO.mapToResponseList(carList);
		if (CarDTOList == null | CarDTOList.isEmpty()) {
			throw new DataNotFoundException("Not found");
		}
		return CarDTOList;
	}

	public Car addCar(Car car) throws ParseException {
		return jpa.addEntity(car);
	}

	public List<CarDTO> getCarByCountry(String country) {
		List<CarDTO> carDTOList = CarDTO.mapToResponseList(jpa.getCarByCountry(country));
		if (carDTOList == null | carDTOList.isEmpty()) {
			throw new DataNotFoundException("Not found");
		}
		return carDTOList;
	}

	public CarDTO getCarById(long id) {
		Car car = jpa.getEntityById(id);
		if (car == null || car.getBrand() == null || car.getCountry() == null) {
			throw new DataNotFoundException("Not found");
		}
		CarDTO carDTO = CarDTO.mapToResponse(car);
		return carDTO;
	}

	public Car putCar(long id, Car car) {
		if (id <= 0) {
			throw new DataNotFoundException("Not found");
		} else {
			LocalDateTime createdAt = LocalDateTime.parse(getCarById(id).getCreatedAt());
			car.setId(id);
			car.setCreatedAt(createdAt);
			if (car == null || car.getBrand() == null || car.getCountry() == null) {
				throw new EmptyBodyException("Body was empty.");
			}
			return jpa.putEntity(car);
		}
	}

	public Car deleteCar(long id) {
		if (id <= 0) {
			throw new DataNotFoundException("Not found");
		} else {
			return jpa.deleteEntity(id);
		}
	}

}
