package nelsonapps.demos.seniorApp.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import nelsonapps.demos.seniorApp.controller.CityServiceAPI;
import nelsonapps.demos.seniorApp.dto.StateNumberCities;

@RunWith(SpringRunner.class)
@Import(TestApiConfig.class)
@WebMvcTest(value=CityServiceAPI.class,secure=false)
public class ApiTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	@Test
	public void successResultWhenCallingGetStateGreatCityNumber() throws Exception{
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				                       .get("/api/greatCitiesNumber");
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String JsonContent = result.getResponse().getContentAsString();
		
		StateNumberCities retrivedResult = objectMapper.readValue(JsonContent, StateNumberCities.class);
		assertThat(retrivedResult.getUf().equals("MG"));
		
	}
}
