package nelsonapps.demos.seniorApp.repository;

import java.util.Collection;

import nelsonapps.demos.seniorApp.dto.StateNumberCities;
import nelsonapps.demos.seniorApp.entities.City;

public interface CustomCityRepository {

	Collection<City> findByAtributteLike(String searchAttr,String searchValue);
	Long countByAttribute(String attribute);
	StateNumberCities getStateGreatestCitiesNumber();
	StateNumberCities getStateLeastCitiesNumber();
}
