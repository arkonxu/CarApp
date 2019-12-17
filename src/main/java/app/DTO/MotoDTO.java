package app.DTO;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class MotoDTO implements Serializable{

	private static final long serialVersionUID = 5505484583196488045L;

	private long id;

	@NotNull(message = "Brand cannot be null")
	private String brand;

	@NotNull(message = "Country cannot be null")
	private String country;

	private String createdAt;

	private String lastUpdated;

	private String registration;

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

	@Override
	public String toString() {
		return "motoDTO [id=" + id + ", brand=" + brand + ", country=" + country + ", createdAt=" + createdAt
				+ ", lastUpdated=" + lastUpdated + ", registration=" + registration + "]";
	}

}