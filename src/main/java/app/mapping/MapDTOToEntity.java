package app.mapping;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import app.DTO.BrandDTO;
import app.DTO.CarDTO;
import app.DTO.MotoDTO;
import app.entities.Brand;
import app.entities.Car;
import app.entities.Moto;

public class MapDTOToEntity {

	public static List<Car> mapToEntityList(List<CarDTO> carListResponse) throws ParseException {
		return carListResponse.stream().map(MapDTOToEntity::mapToEntity).collect(Collectors.toList());
	}

	public static Car mapToEntity(CarDTO carDTO) {
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
		return motoListResponse.stream().map(MapDTOToEntity::mapMotoToEntity).collect(Collectors.toList());
	}

	public static Moto mapMotoToEntity(MotoDTO motoDTO){
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
		return brandListResponse.stream().map(MapDTOToEntity::mapBrandToEntity).collect(Collectors.toList());
	}

	public static Brand mapBrandToEntity(BrandDTO brandResponse){
		Brand brand;
		brand = new Brand();
		brand.setId(brandResponse.getId());
		brand.setName(brandResponse.getName());
		brand.setCountry(brandResponse.getCountry());
		return brand;
	}
}
