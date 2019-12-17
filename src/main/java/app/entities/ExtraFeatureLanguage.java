package app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FEATURE_LANGUAGE")
public class ExtraFeatureLanguage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private long id;

	@Column(name = "ES")
	private String es;

	@Column(name = "EN")
	private String en;

	@Column(name = "DE")
	private String de;

	@ManyToOne
	@JoinColumn(name = "FEATURE_ID", referencedColumnName = "ID", nullable = false)
	private ExtraFeature feature;

	public String getEs() {
		return es;
	}

	public void setEs(String es) {
		this.es = es;
	}

	public String getEn() {
		return en;
	}

	public void setEn(String en) {
		this.en = en;
	}

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}

}
