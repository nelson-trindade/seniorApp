package nelsonapps.demos.seniorApp.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nelsonapps.demos.seniorApp.constants.ColumnMapping;
import nelsonapps.demos.seniorApp.dto.StateNumberCities;
import nelsonapps.demos.seniorApp.entities.City;
import nelsonapps.demos.seniorApp.repository.CityRepository;
import nelsonapps.demos.seniorApp.repository.CustomCityRepository;

@Service("cityService")
public class CityService {

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CustomCityRepository customCityRepository;
	
	@Transactional
	public void save(City city){
		cityRepository.save(city);
	}
	
	public Collection<City> findCapitalsOnly(){
		return cityRepository.findByCapital(true, new Sort("name"));
	}
	
	public StateNumberCities findStateGreatNumberOfCities(){
		return customCityRepository.getStateGreatestCitiesNumber();
	}
	
	public StateNumberCities findStateLeastNumberOfCities(){
		return customCityRepository.getStateLeastCitiesNumber();
	}
	
	public Slice<StateNumberCities> getNumberOfCitiesPerState(){
		return cityRepository.getMumberCitiesPerState();
	}
	
	public City getCityByIbgeId(Long ibgeId){
		return cityRepository.findByIbgeId(ibgeId);
	}
	
	public Slice<City> getCitiesInState(String uf,Pageable pageable){
		return cityRepository.findByUf(uf,pageable);
	}
	
	public Collection<City> findByAttributeLike(String attribute,String value){
		
		Optional<ColumnMapping> attributeMapping = ColumnMapping.findByColumnNameFile(attribute);
		
		if(attributeMapping.isPresent()){
			return customCityRepository
					.findByAtributteLike(attributeMapping.get().getColumnNameDB(), value);
		} else {
			return null;
		}
		             
	}
}
