package app.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;

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
	private LocalDateTime registration;

	@Column(name = "country", nullable = false)
	@NotNull(message = "Country cannot be null")
	private String country;

	@Column(name = "createdAt")
	private LocalDateTime createdAt;

	@Column(name = "lastUpdated")
	private LocalDateTime lastUpdated;

	@Column(name = "CHECKED")
	@DefaultValue(value = "false")
	private boolean checked;

	@OneToOne(mappedBy = "car", cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
	private License license;

	@OneToMany(mappedBy = "car", cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
	private List<DailyEmissions> dailyEmissions;

	@ManyToMany(mappedBy = "cars", cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
	private List<ExtraFeature> extraFeature;

	public Car() {
	}

	public Car(long id, String brand, String country, LocalDateTime registration, LocalDateTime createdAt,
			LocalDateTime lastUpdated) {
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

	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now();
		lastUpdated = LocalDateTime.now();
	}

	@PreUpdate
	public void preUpdate() {
		lastUpdated = LocalDateTime.now();
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

	public LocalDateTime getRegistration() {
		return this.registration;
	}

	public void setRegistration(LocalDateTime registration) {
		this.registration = registration;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public LocalDateTime getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getLastUpdated() {
		return this.lastUpdated;
	}

	public void setLastUpdated(LocalDateTime lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + (checked ? 1231 : 1237);
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
		result = prime * result + ((dailyEmissions == null) ? 0 : dailyEmissions.hashCode());
		result = prime * result + ((extraFeature == null) ? 0 : extraFeature.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((lastUpdated == null) ? 0 : lastUpdated.hashCode());
		result = prime * result + ((license == null) ? 0 : license.hashCode());
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
		if (checked != other.checked)
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
		if (dailyEmissions == null) {
			if (other.dailyEmissions != null)
				return false;
		} else if (!dailyEmissions.equals(other.dailyEmissions))
			return false;
		if (extraFeature == null) {
			if (other.extraFeature != null)
				return false;
		} else if (!extraFeature.equals(other.extraFeature))
			return false;
		if (id != other.id)
			return false;
		if (lastUpdated == null) {
			if (other.lastUpdated != null)
				return false;
		} else if (!lastUpdated.equals(other.lastUpdated))
			return false;
		if (license == null) {
			if (other.license != null)
				return false;
		} else if (!license.equals(other.license))
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
				+ ", createdAt=" + createdAt + ", lastUpdated=" + lastUpdated + ", checked=" + checked + ", license="
				+ license + ", dailyEmissions=" + dailyEmissions + ", extraFeature=" + extraFeature + "]";
	}

	public License getLicense() {
		return license;
	}

	public void setLicense(License license) {
		this.license = license;
	}

	public List<DailyEmissions> getDailyEmissions() {
		return dailyEmissions;
	}

	public void setDailyEmissions(List<DailyEmissions> dailyEmissions) {
		this.dailyEmissions = dailyEmissions;
	}

	public List<ExtraFeature> getExtraFeature() {
		return extraFeature;
	}

	public void setExtraFeature(List<ExtraFeature> extraFeature) {
		this.extraFeature = extraFeature;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}