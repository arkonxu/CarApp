package app.DTO;

import javax.validation.constraints.NotNull;

import app.entities.Car;
import app.enums.LicenseState;

public class LicenseDTO implements java.io.Serializable {

	private static final long serialVersionUID = -2541877085325660094L;

	private long id;

	@NotNull(message = "createDate cannot be null")
	private String createDate;

	@NotNull(message = "expireDate cannot be null")
	private String expireDate;

	@NotNull(message = "State cannot be null")
	private LicenseState state;

	@NotNull
	private Car car;

	public LicenseDTO() {
	}

	public LicenseDTO(long id, String createDate, String expireDate) {
		this.id = id;
		this.createDate = createDate;
		this.expireDate = expireDate;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreatedDate(String createDate) {
		this.createDate = createDate;
	}

	public String getExpireDate() {
		return this.expireDate;
	}

	public void setLastUpdated(String expireDate) {
		this.expireDate = expireDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	public LicenseState getState() {
		return state;
	}

	public void setState(LicenseState state) {
		this.state = state;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
}