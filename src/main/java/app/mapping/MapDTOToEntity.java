package app.mapping;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import app.DTO.CarDTO;
import app.DTO.MotoDTO;
import app.entities.Car;
import app.entities.Moto;

public class MapDTOToEntity {

	public static List<Car> mapToEntityList(List<CarDTO> carListResponse) throws ParseException {

		Car car;
		List<Car> carList = new ArrayList<>();

		for (CarDTO CarDTO : carListResponse) {

			car = new Car();
			car.setId(CarDTO.getId());
			car.setBrand(CarDTO.getBrand());
			car.setCountry(CarDTO.getCountry());
			LocalDateTime dateCreatedAt = LocalDateTime.parse(CarDTO.getCreatedAt());
			car.setCreatedAt(dateCreatedAt);
			LocalDateTime dateLastUpdated = LocalDateTime.parse(CarDTO.getLastUpdated());
			car.setLastUpdated(dateLastUpdated);
			LocalDateTime dateRegistration = LocalDateTime.parse(CarDTO.getRegistration());
			car.setRegistration(dateRegistration);
			carList.add(car);
		}

		return carList;
	}

	public static Car mapToEntity(CarDTO carDTO) throws ParseException {

		Car car;

		car = new Car();
		car.setId(carDTO.getId());
		car.setBrand(carDTO.getBrand());
		car.setCountry(carDTO.getCountry());
		LocalDateTime dateCreatedAt = LocalDateTime.parse(carDTO.getCreatedAt());
		car.setCreatedAt(dateCreatedAt);
		LocalDateTime dateLastUpdated = LocalDateTime.parse(carDTO.getLastUpdated());
		car.setLastUpdated(dateLastUpdated);
		LocalDateTime dateRegistration = LocalDateTime.parse(carDTO.getRegistration());
		car.setRegistration(dateRegistration);

		return car;
	}

	public static List<Moto> mapMotoToEntityList(List<MotoDTO> motoListResponse) throws ParseException {
		Moto moto;
		List<Moto> motoList = new ArrayList<>();

		for (MotoDTO motoDTO : motoListResponse) {

			moto = new Moto();
			moto.setId(motoDTO.getId());
			moto.setBrand(motoDTO.getBrand());
			moto.setCountry(motoDTO.getCountry());
			LocalDateTime dateCreatedAt = LocalDateTime.parse(motoDTO.getCreatedAt());
			moto.setCreatedAt(dateCreatedAt);
			LocalDateTime dateLastUpdated = LocalDateTime.parse(motoDTO.getLastUpdated());
			moto.setLastUpdated(dateLastUpdated);
			LocalDateTime dateRegistration = LocalDateTime.parse(motoDTO.getRegistration());
			moto.setRegistration(dateRegistration);
			motoList.add(moto);
		}

		return motoList;
	}

	public static Moto mapMotoToEntity(MotoDTO motoDTO) throws ParseException {
		Moto moto;

		moto = new Moto();
		moto.setId(motoDTO.getId());
		moto.setBrand(motoDTO.getBrand());
		moto.setCountry(motoDTO.getCountry());
		// if(motoDTO.getCreatedAt() == null){ LocalDateTime.now; } else{
		// LocalDateTime.parse(motoDTO.getCreatedAt()); }
		LocalDateTime dateCreatedAt = (motoDTO.getCreatedAt()) == null ? LocalDateTime.now()
				: LocalDateTime.parse(motoDTO.getCreatedAt());
		moto.setCreatedAt(dateCreatedAt);
		LocalDateTime dateLastUpdated = (motoDTO.getLastUpdated()) == null ? LocalDateTime.now()
				: LocalDateTime.parse(motoDTO.getLastUpdated());
		moto.setLastUpdated(dateLastUpdated);
		LocalDateTime dateRegistration = (motoDTO.getRegistration()) == null ? LocalDateTime.now()
				: LocalDateTime.parse(motoDTO.getRegistration());
		moto.setRegistration(dateRegistration);

		return moto;
	}
}
