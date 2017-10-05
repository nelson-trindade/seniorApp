package nelsonapps.demos.seniorApp.dto;

public class StateNumberCities {

	private String uf;
	private long numberOfCities;
	
	public StateNumberCities(){}
	
	public StateNumberCities(String uf,long numberOfCities){
		this.uf = uf;
		this.numberOfCities = numberOfCities;
	}
	
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public long getNumberOfCities() {
		return numberOfCities;
	}
	public void setNumberOfCities(Long numberOfCities) {
		this.numberOfCities = numberOfCities;
	}
	
	
}
