package app.services;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

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
import app.mapping.MapDTOToEntity;
import app.mapping.MapEntityToDTO;

@Stateless
public class CarService {

	@EJB
	private CarJPA jpa;

	@Inject
	private Event<CarAddedEvent> carAddedEvent;

	Logger logger = Logger.getLogger(CarService.class);

	public List<CarDTO> getAll() {
		List<CarDTO> carDTOList = jpa.getAll().stream().map(MapEntityToDTO::mapToResponse).collect(Collectors.toList());
		if (carDTOList == null | carDTOList.isEmpty()) {
			throw new DataNotFoundException("Not found");
		}
		return carDTOList;
	}

	public Car addCar(CarDTO carDTO) throws ParseException {
		if (carDTO == null || carDTO.getBrand() == null || carDTO.getCountry() == null) {
			throw new EmptyBodyException("Body was empty.");
		}
		Car car = MapDTOToEntity.mapToEntity(carDTO);
		carAddedEvent.fire(new CarAddedEvent(car));
		return jpa.addEntity(car);
	}

	public List<CarDTO> getCarByCountry(String country) {
		List<CarDTO> carDTOList = MapEntityToDTO.mapToResponseList(jpa.getEntityByCountry(country));
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
		return MapEntityToDTO.mapToResponse(car);
	}

	public CarDTO putCar(long id, CarDTO carDTO) {
		if (id <= 0) {
			throw new DataNotFoundException("Not found");
		} else {
			Car oldCar = jpa.getEntityById(id);
			oldCar.setBrand(carDTO.getBrand());
			oldCar.setCountry(carDTO.getCountry());
			if (carDTO == null || carDTO.getBrand() == null || carDTO.getCountry() == null) {
				throw new EmptyBodyException("Body was empty.");
			}
			return MapEntityToDTO.mapToResponse(jpa.putEntity(oldCar));
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
	public CarDTO pruebaTransaction(CarDTO carDTO) throws ParseException {
		Car car = MapDTOToEntity.mapToEntity(carDTO);
		Car addedCar = jpa.addEntity(car);
		logger.info("Car " + car + " ADDED.");

		addedCar.setBrand("SEAT");
		addedCar.setCountry("Spain");
		logger.info("CAR " + car + "UPDATED.");

		List<CarDTO> carDTOList = jpa.getAll().stream().map(MapEntityToDTO::mapToResponse).collect(Collectors.toList());
		logger.info("GET CARLIST " + carDTOList);

		throw new DataNotFoundException("Not found");
	}
}
