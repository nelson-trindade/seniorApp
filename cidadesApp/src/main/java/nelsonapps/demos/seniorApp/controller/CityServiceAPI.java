package nelsonapps.demos.seniorApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nelsonapps.demos.seniorApp.dto.StateNumberCities;
import nelsonapps.demos.seniorApp.service.CityService;

@RestController("cityServiceAPI")
@RequestMapping(path="/api",produces=MediaType.APPLICATION_JSON_VALUE)
public class CityServiceAPI {

	@Autowired
	private CityService cityService;
	
	@GetMapping("/greatCitiesNumber")
	public StateNumberCities getStateGreatCityNumber(){
		return cityService.findStateGreatNumberOfCities();
	}
}
