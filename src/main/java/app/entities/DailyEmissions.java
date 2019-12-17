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
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import app.enums.DailyEmissionsResult;

@Entity
@Table(name = "DAILY_EMISSIONS")
public class DailyEmissions implements java.io.Serializable {

	private static final long serialVersionUID = 2600690606230084055L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private long id;

	@Column(name = "TEST_DATE")
	private LocalDateTime testDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "RESULT", nullable = false)
	private DailyEmissionsResult result;

	@ManyToOne
	@JoinColumn(name = "CAR_ID", referencedColumnName = "id", nullable = false)
	private Car car;
	
	@PrePersist
	public void prePersist() {
		testDate = LocalDateTime.now();
	}

	public DailyEmissions() {
	}

	public DailyEmissions(long id, LocalDateTime testDate) {
		this.id = id;
		this.testDate = testDate;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getTestDate() {
		return this.testDate;
	}

	public void setTestDate(LocalDateTime testDate) {
		this.testDate = testDate;
	}

	public DailyEmissionsResult getResult() {
		return result;
	}

	public void setResult(DailyEmissionsResult result) {
		this.result = result;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
}