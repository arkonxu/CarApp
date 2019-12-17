package app.mapping;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import app.DTO.BrandDTO;
import app.DTO.CarDTO;
import app.DTO.MotoDTO;
import app.entities.Brand;
import app.entities.Car;
import app.entities.Moto;

public class MapEntityToDTO {

	public static List<CarDTO> mapToResponseList(List<Car> carList) {
		return carList.stream().map(MapEntityToDTO::mapToResponse).collect(Collectors.toList());
	}

	public static CarDTO mapToResponse(Car car) {
		CarDTO carDTO = new CarDTO();
		carDTO.setId(car.getId());
		carDTO.setBrand(car.getBrand());
		carDTO.setCountry(car.getCountry());
		carDTO.setCreatedAt((car.getCreatedAt() == null) ? null : car.getCreatedAt().toString());
		carDTO.setLastUpdated((car.getLastUpdated() == null) ? null : car.getLastUpdated().toString());
		carDTO.setRegistration((car.getRegistration() == null) ? null : car.getRegistration().toString());
		carDTO.setChecked(car.isChecked());
		return carDTO;
	}

	public static List<MotoDTO> mapMotoToResponseList(List<Moto> motoList) {
		List<MotoDTO> motoDTOList = new ArrayList<>();
		motoList.stream().map(MapEntityToDTO::mapMotoToResponse).collect(Collectors.toList());
		return motoDTOList;
	}

	public static MotoDTO mapMotoToResponse(Moto moto) {
		MotoDTO motoDTO;
		motoDTO = new MotoDTO();
		motoDTO.setId(moto.getId());
		motoDTO.setBrand(moto.getBrand());
		motoDTO.setCountry(moto.getCountry());
		motoDTO.setCreatedAt((moto.getCreatedAt() == null) ? null : moto.getCreatedAt().toString());
		motoDTO.setLastUpdated((moto.getLastUpdated() == null) ? null : moto.getLastUpdated().toString());
		motoDTO.setRegistration((moto.getRegistration() == null) ? null : moto.getRegistration().toString());
		return motoDTO;
	}

	public static List<BrandDTO> mapBrandToResponseList(List<Brand> brandListResponse) throws ParseException {
		return brandListResponse.stream().map(MapEntityToDTO::mapBrandToResponse).collect(Collectors.toList());
	}

	public static BrandDTO mapBrandToResponse(Brand brandResponse) {
		BrandDTO brandDTO;
		brandDTO = new BrandDTO();
		brandDTO.setId(brandResponse.getId());
		brandDTO.setName(brandResponse.getName());
		brandDTO.setCountry(brandResponse.getCountry());
		return brandDTO;
	}

}
