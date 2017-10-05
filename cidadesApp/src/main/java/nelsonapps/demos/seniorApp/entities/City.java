package nelsonapps.demos.seniorApp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;

@Entity
public class City {

	@Value("${seniorApp.definitions.trueKeyWord}")
	private static String trueKeyWord;
	
	@Id
	@Column(name="ibge_id",unique=true)
	private Long ibgeId;
	
	@NotEmpty
	@Column(nullable=false)
	private String uf;
	
	@NotEmpty
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=true)
	private Boolean capital;
	
	@NotNull
	@Column(nullable=false)
	private Double lon;
	
	@NotNull
	@Column(nullable=false)
	private Double lat;
	
	@NotNull
	@Column(name="no_accents",nullable=false)
	private String noAccents;
	
	@NotNull
	@Column(name="alternative_names",nullable=false)
	private String alternativeNames;
	
	@NotEmpty
	@Column(nullable=false)
	private String microregion;
	
	@NotEmpty
	@Column(nullable=false)
	private String mesoregion;

	public Long getIbgeId() {
		return ibgeId;
	}

	public void setIbgeId(Long ibgeId) {
		this.ibgeId = ibgeId;
	}
	
	public void setIbgeId(String ibgeId){
		this.ibgeId = Long.valueOf(ibgeId);
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getCapital() {
		return capital;
	}

	public void setCapital(Boolean capital) {
		this.capital = capital;
	}
	
	public void setCapital(String capital){
		if(capital!=null && !capital.isEmpty()){
			if(capital.equalsIgnoreCase(trueKeyWord)){
				this.capital = true;
			}
		} else {
			this.capital = false;
		}
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}
	
	public void setLon(String lon){
		this.lon = Double.valueOf(lon);
	}

	public Double getLat() {
		return lat;
	}
	
	public void setLat(String lat){
		this.lat = Double.valueOf(lat);
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public String getNoAccents() {
		return noAccents;
	}

	public void setNoAccents(String noAccents) {
		this.noAccents = noAccents;
	}

	public String getAlternativeNames() {
		return alternativeNames;
	}

	public void setAlternativeNames(String alternativeNames) {
		this.alternativeNames = alternativeNames;
	}

	public String getMicroregion() {
		return microregion;
	}

	public void setMicroregion(String microregion) {
		this.microregion = microregion;
	}

	public String getMesoregion() {
		return mesoregion;
	}

	public void setMesoregion(String mesoregion) {
		this.mesoregion = mesoregion;
	}
	
}
