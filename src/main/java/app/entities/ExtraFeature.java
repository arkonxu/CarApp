package app.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "EXTRA_FEATURE")
public class ExtraFeature implements java.io.Serializable {

	private static final long serialVersionUID = 4637638845685983629L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private long id;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "CREATE_DATE")
	private LocalDateTime createDate;

	@ManyToMany
	@JoinColumn(name = "CAR_ID", referencedColumnName = "id", nullable = false)
	private List<Car> cars;

	@OneToMany(mappedBy = "feature", cascade = { CascadeType.REMOVE, CascadeType.PERSIST })
	private List<ExtraFeatureLanguage> extraFeatureLanguage;

	@PrePersist
	public void prePersist() {
		createDate = LocalDateTime.now();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public List<Car> getCar() {
		return cars;
	}

	public void setCar(List<Car> cars) {
		this.cars = cars;
	}

}