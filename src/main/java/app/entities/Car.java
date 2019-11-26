package app.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import app.DTO.CarDTO;

@Entity
@Table(name = "cars")
public class Car implements java.io.Serializable {

	private static final long serialVersionUID = -3763700688681795327L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "brand", nullable = false)
	@NotNull(message = "Brand cannot be null")
	private String brand;

	@Column(name = "registration")
	private Date registration;

	@Column(name = "country", nullable = false)
	@NotNull(message = "Country cannot be null")
	private String country;

	@Column(name = "createdAt")
	private Date createdAt;

	@Column(name = "lastUpdated")
	private Date lastUpdated;

	public Car() {
	}

	public Car(long id, String brand, String country, Date registration, Date createdAt, Date lastUpdated) {
		this.id = id;
		this.brand = brand;
		this.registration = registration;
		this.country = country;
		this.createdAt = createdAt;
		this.lastUpdated = lastUpdated;
	}

	public Car(long id, String brand, String country) {
		this.id = id;
		this.brand = brand;
		this.country = country;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Date getRegistration() {
		return this.registration;
	}

	public void setRegistration(Date registration) {
		this.registration = registration;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getLastUpdated() {
		return this.lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((lastUpdated == null) ? 0 : lastUpdated.hashCode());
		result = prime * result + ((registration == null) ? 0 : registration.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (createdAt == null) {
			if (other.createdAt != null)
				return false;
		} else if (!createdAt.equals(other.createdAt))
			return false;
		if (id != other.id)
			return false;
		if (lastUpdated == null) {
			if (other.lastUpdated != null)
				return false;
		} else if (!lastUpdated.equals(other.lastUpdated))
			return false;
		if (registration == null) {
			if (other.registration != null)
				return false;
		} else if (!registration.equals(other.registration))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", brand=" + brand + ", registration=" + registration + ", country=" + country
				+ ", createdAt=" + createdAt + ", lastUpdated=" + lastUpdated + "]";
	}

	public static List<Car> mapToEntity(List<CarDTO> carListResponse) throws ParseException {

		Car car;
		List<Car> carList = new ArrayList<>();

		for (CarDTO CarDTO : carListResponse) {

			car = new Car();
			car.setId(CarDTO.getId());
			car.setBrand(CarDTO.getBrand());
			car.setCountry(CarDTO.getCountry());
			car.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd").parse(CarDTO.getCreatedAt()));
			car.setLastUpdated(new SimpleDateFormat("yyyy-MM-dd").parse(CarDTO.getLastUpdated()));
			car.setRegistration(new SimpleDateFormat("yyyy-MM-dd").parse(CarDTO.getRegistration()));

			carList.add(car);
		}

		return carList;
	}
}