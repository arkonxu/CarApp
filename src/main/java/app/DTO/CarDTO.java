package app.DTO;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import app.entities.Car;
import app.model.Link;

public class CarDTO {

	private long id;

	@NotNull(message = "Brand cannot be null")
	private String brand;

	@NotNull(message = "Country cannot be null")
	private String country;

	private String createdAt;

	private String lastUpdated;

	private String registration;

	private List<Link> links = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public static List<CarDTO> mapToResponseList(List<Car> carList) {

		CarDTO carDTO;
		List<CarDTO> carDTOList = new ArrayList<>();

		for (Car car : carList) {

			carDTO = new CarDTO();
			carDTO.setId(car.getId());
			carDTO.setBrand(car.getBrand());
			carDTO.setCountry(car.getCountry());
			carDTO.setCreatedAt((car.getCreatedAt() == null) ? null
					/* LocalDateTime.now().toString() */ : car.getCreatedAt().toString());
			carDTO.setLastUpdated((car.getLastUpdated() == null) ? null
					/* LocalDateTime.now().toString() */ : car.getLastUpdated().toString());

			carDTO.setRegistration((car.getRegistration() == null) ? null
					/* LocalDateTime.now().toString() */ : car.getRegistration().toString());

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
		carDTO.setCreatedAt((car.getCreatedAt() == null) ? null
				/* LocalDateTime.now().toString() */ : car.getCreatedAt().toString());
		carDTO.setLastUpdated((car.getLastUpdated() == null) ? null
				/* LocalDateTime.now().toString() */ : car.getLastUpdated().toString());

		carDTO.setRegistration((car.getRegistration() == null) ? null
				/* LocalDateTime.now().toString() */ : car.getRegistration().toString());

		return carDTO;
	}

	@Override
	public String toString() {
		return "carDTO [id=" + id + ", brand=" + brand + ", country=" + country + ", createdAt=" + createdAt
				+ ", lastUpdated=" + lastUpdated + ", registration=" + registration + ", links=" + links + "]";
	}

}
