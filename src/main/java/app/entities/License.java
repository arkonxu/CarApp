package app.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import app.enums.LicenseState;

@Entity
@Table(name = "LICENSES")
public class License implements java.io.Serializable {

	private static final long serialVersionUID = -2541877085325660094L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private long id;

	@Column(name = "CREATE_DATE")
	private LocalDateTime createDate;

	@Column(name = "EXPIRE_DATE")
	private LocalDateTime expireDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "STATE", nullable = false)
	private LicenseState state;

	@OneToOne
	@JoinColumn(name = "CAR_ID", referencedColumnName = "id", nullable = false)
	private Car car;

	public License() {
	}

	public License(long id, LocalDateTime createDate, LocalDateTime expireDate) {
		this.id = id;
		this.createDate = createDate;
		this.expireDate = expireDate;
	}

	@PrePersist
	public void prePersist() {
		createDate = LocalDateTime.now();
		expireDate = LocalDateTime.now();
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getCreateDate() {
		return this.createDate;
	}

	public void setCreatedDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getExpireDate() {
		return this.expireDate;
	}

	public void setLastUpdated(LocalDateTime expireDate) {
		this.expireDate = expireDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public void setExpireDate(LocalDateTime expireDate) {
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