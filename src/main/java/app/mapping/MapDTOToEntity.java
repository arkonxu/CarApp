package app.mapping;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import app.DTO.BrandDTO;
import app.DTO.CarDTO;
import app.DTO.MotoDTO;
import app.entities.Brand;
import app.entities.Car;
import app.entities.Moto;

public class MapDTOToEntity {

	public static List<Car> mapToEntityList(List<CarDTO> carListResponse) throws ParseException {

		Car car;
		List<Car> carList = new ArrayList<>();

		for (CarDTO carDTO : carListResponse) {

			car = new Car();
			car.setId(carDTO.getId());
			car.setBrand(carDTO.getBrand());
			car.setCountry(carDTO.getCountry());
			LocalDateTime dateCreatedAt = LocalDateTime.parse(carDTO.getCreatedAt());
			car.setCreatedAt((car.getCreatedAt() == null) ? null : dateCreatedAt);
			LocalDateTime dateLastUpdated = LocalDateTime.parse(carDTO.getLastUpdated());
			car.setLastUpdated((car.getLastUpdated() == null) ? null : dateLastUpdated);
			LocalDateTime dateRegistration = LocalDateTime.parse(carDTO.getRegistration());
			car.setRegistration((car.getRegistration() == null) ? null : dateRegistration);
			car.setChecked(car.isChecked());
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
		LocalDateTime dateCreatedAt = (carDTO.getCreatedAt()) == null ? null
				: LocalDateTime.parse(carDTO.getCreatedAt());
		car.setCreatedAt(car.getCreatedAt() == null ? null : dateCreatedAt);
		LocalDateTime dateLastUpdated = (carDTO.getLastUpdated()) == null ? null
				: LocalDateTime.parse(carDTO.getCreatedAt());
		car.setLastUpdated(car.getLastUpdated() == null ? null : dateLastUpdated);
		LocalDateTime dateRegistration = (carDTO.getRegistration()) == null ? null
				: LocalDateTime.parse(carDTO.getCreatedAt());
		car.setRegistration(car.getRegistration() == null ? null : dateRegistration);
		car.setChecked(car.isChecked());

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
			LocalDateTime dateCreatedAt = (motoDTO.getCreatedAt()) == null ? null
					: LocalDateTime.parse(motoDTO.getCreatedAt());
			moto.setCreatedAt(dateCreatedAt);
			LocalDateTime dateLastUpdated = (motoDTO.getLastUpdated()) == null ? null
					: LocalDateTime.parse(motoDTO.getLastUpdated());
			moto.setLastUpdated(dateLastUpdated);
			LocalDateTime dateRegistration = (motoDTO.getRegistration()) == null ? null
					: LocalDateTime.parse(motoDTO.getRegistration());
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

	public static List<Brand> mapBrandToEntityList(List<BrandDTO> brandListResponse) throws ParseException {

		Brand brand;
		List<Brand> brandList = new ArrayList<>();

		for (BrandDTO brandDTO : brandListResponse) {
			brand = new Brand();
			brand.setId(brandDTO.getId());
			brand.setName(brandDTO.getName());
			brand.setCountry(brandDTO.getCountry());
			brandList.add(brand);
		}
		return brandList;
	}

	public static Brand mapBrandToEntity(BrandDTO brandResponse) throws ParseException {

		Brand brand;

		brand = new Brand();
		brand.setId(brandResponse.getId());
		brand.setName(brandResponse.getName());
		brand.setCountry(brandResponse.getCountry());

		return brand;
	}
}
