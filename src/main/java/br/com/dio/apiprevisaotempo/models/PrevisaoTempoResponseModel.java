package br.com.dio.apiprevisaotempo.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PrevisaoTempoResponseModel {
	
	private Integer hora;
	private Integer temperatura;
	private String data;
	private String nomeCidade;
	private List<Forecast> forecast; 

}
