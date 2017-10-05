package nelsonapps.demos.seniorApp.test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.AssertionFailedError;
import nelsonapps.demos.seniorApp.dto.StateNumberCities;
import nelsonapps.demos.seniorApp.entities.City;
import nelsonapps.demos.seniorApp.repository.CityRepository;
import nelsonapps.demos.seniorApp.service.CityService;
	

@RunWith(SpringRunner.class)
@SpringBootTest(classes=TestApiConfig.class)
public class CityServiceTest {

	@Autowired
	private CityService cityService;

	@Autowired
	private CityRepository cityRepository;
	
	private FileReader csvReader;
	
	@Before
	public void loadDataBase() throws FileNotFoundException, IOException{
		Resource resource = new ClassPathResource("data/cidades.csv");
		csvReader = new FileReader(resource.getFile());

		if (cityRepository.count() == 0) {
			try (BufferedReader reader = new BufferedReader(csvReader)) {
				String lineBuffer;
				final String[] header = reader.readLine().split(",");
				final List<Method> setter = Arrays.asList(City.class.getDeclaredMethods());

				while ((lineBuffer = reader.readLine()) != null) {
					String[] values = lineBuffer.split(",");
					City city = new City();
					for (int i = 0; i < header.length; i++) {
						String columnName = header[i].replaceAll("_", "");
						setter.stream()
								.filter(method -> method.getName().equalsIgnoreCase("set" + columnName)
										&& method.getParameterTypes()[0].isAssignableFrom(String.class))
								.findFirst().get().invoke(city, values[i]);
					}

					cityService.save(city);
				}

			} catch (Exception e) {
				e.printStackTrace();
				throw new AssertionFailedError(e.getMessage());
			}
		}
	}

	@Test
	public void getStateWithGreateNumberOfCities(){
		StateNumberCities result = cityService.findStateGreatNumberOfCities();
		assertEquals("MG",result.getUf());
	}
	
	@Test
	public void getStateWithLeastNumberOfCities(){
		StateNumberCities result = cityService.findStateLeastNumberOfCities();
		assertEquals("DF",result.getUf());
	}
	
	@Test
	public void testSearchByAttributeNegativeTest(){
		Collection<City> result = cityService.findByAttributeLike("agua", "teste");
		assertThat(result==null);
	}
	
	@Test
	public void testSearchByAttributePositiveTest(){
		Collection<City> result = cityService.findByAttributeLike("uf", "DF");
		assertEquals("DF",result.iterator().next().getUf());
	}
}
