package app.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.log4j.Logger;

import app.DTO.CarDTO;
import app.entities.Car;
import app.event.CarAddedEvent;
import app.exceptions.DataNotFoundException;
import app.exceptions.EmptyBodyException;
import app.jpa.CarJPA;
import app.mapping.MapEntityToDTO;

@Stateless
public class CarService {

	@EJB
	private CarJPA jpa;

	@Inject
	private Event<CarAddedEvent> carAddedEvent;

	Logger logger = Logger.getLogger(CarService.class);

	public CarService() {
	}

	public List<CarDTO> getAll() {
		List<Car> carList = jpa.getAll();
		List<CarDTO> carDTOList = MapEntityToDTO.mapToResponseList(carList);
		if (carDTOList == null | carDTOList.isEmpty()) {
			throw new DataNotFoundException("Not found");
		}
		return carDTOList;
	}

	public Car addCar(Car car) {
		if (car == null || car.getBrand() == null || car.getCountry() == null) {
			throw new EmptyBodyException("Body was empty.");
		}
		carAddedEvent.fire(new CarAddedEvent(car));
		return jpa.addEntity(car);
	}

	public List<CarDTO> getCarByCountry(String country) {
		List<CarDTO> carDTOList = MapEntityToDTO.mapToResponseList(jpa.getCarByCountry(country));
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
		CarDTO carDTO = MapEntityToDTO.mapToResponse(car);
		return carDTO;
	}

	public Car putCar(long id, Car car) {
		if (id <= 0) {
			throw new DataNotFoundException("Not found");
		} else {
			Car oldCar = jpa.getEntityById(id);
			oldCar.setBrand(car.getBrand());
			oldCar.setCountry(car.getCountry());
			if (car == null || car.getBrand() == null || car.getCountry() == null) {
				throw new EmptyBodyException("Body was empty.");
			}
			return jpa.putEntity(oldCar);
		}
	}

	public Car deleteCar(long id) {
		if (id <= 0) {
			throw new DataNotFoundException("Not found");
		} else {
			return jpa.deleteEntity(id);
		}
	}

	@Transactional(TxType.REQUIRES_NEW)
	public Car pruebaTransaction(Car car) {
		Car addedCar = jpa.addEntity(car);
		logger.info("Car " + car + " ADDED.");

		addedCar.setBrand("SEAT");
		addedCar.setCountry("Spain");
		logger.info("CAR " + car + "UPDATED.");

		List<Car> carList = jpa.getAll();
		List<CarDTO> carDTOList = MapEntityToDTO.mapToResponseList(carList);
		logger.info("GET CARLIST " + carDTOList);

		throw new DataNotFoundException("Not found");
	}
}
