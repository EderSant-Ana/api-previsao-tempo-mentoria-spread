package br.com.dio.apiprevisaotempo.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.dio.apiprevisaotempo.connectors.HGBrasilConnector;
import br.com.dio.apiprevisaotempo.models.PrevisaoTempoResponseModel;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PrevisaoTempoService {
	
	@Autowired
	HGBrasilConnector connector;
	
	@Cacheable(
			value = "previsaoTempoCidadeCache",
			key = "#city"
			)
	public PrevisaoTempoResponseModel fetchPrevisaoTempo(String city) {
		
		log.info("PrevisaoTempoService -> --------------------------------------------------------------------------");
		log.info("Buscando a previsão do tempo para a cidade {}", city);
		var retornoApi = connector.fetchWeatherForCity(city);
		
		PrevisaoTempoResponseModel ret = null;
		
		if(retornoApi != null) {
			ret =  PrevisaoTempoResponseModel.builder()
					.hora(LocalDateTime.now().getHour())
					.temperatura(retornoApi.getTemp())
					.data(retornoApi.getDate())
					.nomeCidade(retornoApi.getCityName())
					.forecast(retornoApi.getForecast())
					.build();
			
		}
		return ret;
	}

}
