package app.DTO;

import java.time.ZoneId;
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

	public static List<CarDTO> mapToResponse(List<Car> carList) {

		CarDTO CarDTO;
		List<CarDTO> CarDTOList = new ArrayList<>();

		for (Car car : carList) {

			CarDTO = new CarDTO();
			CarDTO.setId(car.getId());
			CarDTO.setBrand(car.getBrand());
			CarDTO.setCountry(car.getCountry());
			CarDTO.setCreatedAt(
					car.getCreatedAt().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString());
			CarDTO.setLastUpdated(
					car.getLastUpdated().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString());

			CarDTO.setRegistration((car.getRegistration() == null) ? null
					: car.getRegistration().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString());

			CarDTOList.add(CarDTO);
		}

		return CarDTOList;
	}

	@Override
	public String toString() {
		return "CarDTO [id=" + id + ", brand=" + brand + ", country=" + country + ", createdAt=" + createdAt
				+ ", lastUpdated=" + lastUpdated + ", registration=" + registration + ", links=" + links + "]";
	}

}
