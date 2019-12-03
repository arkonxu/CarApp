package app.mapping;

import java.util.ArrayList;
import java.util.List;

import app.DTO.CarDTO;
import app.DTO.MotoDTO;
import app.entities.Car;
import app.entities.Moto;

public class MapEntityToDTO {

	public static List<CarDTO> mapToResponseList(List<Car> carList) {

		CarDTO carDTO;
		List<CarDTO> carDTOList = new ArrayList<>();

		for (Car car : carList) {

			carDTO = new CarDTO();
			carDTO.setId(car.getId());
			carDTO.setBrand(car.getBrand());
			carDTO.setCountry(car.getCountry());
			carDTO.setCreatedAt((car.getCreatedAt() == null) ? null : car.getCreatedAt().toString());
			carDTO.setLastUpdated((car.getLastUpdated() == null) ? null : car.getLastUpdated().toString());

			carDTO.setRegistration((car.getRegistration() == null) ? null : car.getRegistration().toString());

			carDTOList.add(carDTO);
		}

		return carDTOList;
	}

	public static CarDTO mapToResponse(Car car) {

		CarDTO carDTO;

		carDTO = new CarDTO();
		carDTO.setId(car.getId());
		carDTO.setBrand(car.getBrand());
		carDTO.setCountry(car.getCountry());
		carDTO.setCreatedAt((car.getCreatedAt() == null) ? null : car.getCreatedAt().toString());
		carDTO.setLastUpdated((car.getLastUpdated() == null) ? null : car.getLastUpdated().toString());

		carDTO.setRegistration((car.getRegistration() == null) ? null : car.getRegistration().toString());

		return carDTO;
	}

	public static List<MotoDTO> mapMotoToResponseList(List<Moto> motoList) {

		MotoDTO motoDTO;
		List<MotoDTO> motoDTOList = new ArrayList<>();

		for (Moto moto : motoList) {

			motoDTO = new MotoDTO();
			motoDTO.setId(moto.getId());
			motoDTO.setBrand(moto.getBrand());
			motoDTO.setCountry(moto.getCountry());
			motoDTO.setCreatedAt((moto.getCreatedAt() == null) ? null : moto.getCreatedAt().toString());
			motoDTO.setLastUpdated((moto.getLastUpdated() == null) ? null : moto.getLastUpdated().toString());

			motoDTO.setRegistration((moto.getRegistration() == null) ? null : moto.getRegistration().toString());

			motoDTOList.add(motoDTO);
		}

		return motoDTOList;
	}

	public static MotoDTO mapMotoToResponse(Moto car) {

		MotoDTO carDTO;

		carDTO = new MotoDTO();
		carDTO.setId(car.getId());
		carDTO.setBrand(car.getBrand());
		carDTO.setCountry(car.getCountry());
		carDTO.setCreatedAt((car.getCreatedAt() == null) ? null : car.getCreatedAt().toString());
		carDTO.setLastUpdated((car.getLastUpdated() == null) ? null : car.getLastUpdated().toString());

		carDTO.setRegistration((car.getRegistration() == null) ? null : car.getRegistration().toString());

		return carDTO;
	}

}
