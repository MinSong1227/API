package com.weather;

import com.fasterxml.jackson.annotation.JsonFilter;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
//@JsonIgnoreProperties(value= {"writer"})
@NoArgsConstructor
@JsonFilter("WeatherInfoV2")
public class WeatherV2  extends Weather{
	private String dust;
}
