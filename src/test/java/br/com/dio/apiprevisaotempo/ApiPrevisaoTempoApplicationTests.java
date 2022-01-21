package br.com.dio.apiprevisaotempo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.dio.apiprevisaotempo.connectors.HGBrasilConnector;
import br.com.dio.apiprevisaotempo.models.Forecast;
import br.com.dio.apiprevisaotempo.resources.PrevisaoTempoResource;
import br.com.dio.apiprevisaotempo.services.PrevisaoTempoService;

@SpringBootTest
class ApiPrevisaoTempoApplicationTests {
	
	@Autowired
	PrevisaoTempoResource resource;

	@Autowired
	HGBrasilConnector connector;

	@Autowired
	PrevisaoTempoService service;
	
	@Test
	void testFetchWeatherForCity() {
		
		var resultadoApi = connector.fetchWeatherForCity("Sao Paulo,SP");
		assertEquals("São Paulo", resultadoApi.getCityName());
	}
	
	@Test
	void testFetchPrevisaoTempo(){
		var resultadoService = service.fetchPrevisaoTempo("Sao Paulo,SP");
		assertEquals("São Paulo", resultadoService.getNomeCidade());
	}

	@Test
	void testGetPrevisaoTempo(){
		var resultadoResource = resource.get("Sao Paulo,SP");
		assertNotEquals(null, resultadoResource);;
	}
	
	
	@Test
	void testForecast(){
		Forecast forecast = Forecast.builder().date("20/12/2021").min(22).max(30).build();
		
		assertEquals(Integer.valueOf(30), forecast.getMax());
	}
	
}
