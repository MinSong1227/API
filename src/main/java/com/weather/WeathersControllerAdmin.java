package com.weather;

import java.net.URI;
import java.util.List;

import org.springframework.beans.BeanUtils;

//import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
@RequestMapping("/admin")
public class WeathersControllerAdmin {
	private WeatherService service;
	
	public WeathersControllerAdmin(WeatherService service) {
		this.service = service;
	}
	
	
	@GetMapping("/v1/weathers")
	public MappingJacksonValue retrieveAllWeathers(){
		List<Weather> weathers = service.findAll();
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("temper", "wead", "day", "writer", "region");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("WeatherInfo", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(weathers);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
	
//	@GetMapping("/weathers/{day}")
	@GetMapping(value = "/weathers/{day}", params = "version=1")
//	@GetMapping(value = "/weathers/{day}", headers="API-VERSION=1")
//	@GetMapping(value = "/weathers/{day}", produces = "application/vnd.company.appv1+json")
	public MappingJacksonValue retrieveWeather(@PathVariable int day) {
		Weather weather = service.findOne(day);
		
		if (weather == null) {
			throw new WeatherNotFoundException(String.format("Day[%s] not found", day));
		}
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("temper", "wead", "day", "writer", "region");
		
		FilterProvider filters = new SimpleFilterProvider().addFilter("WeatherInfo", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(weather);
		mapping.setFilters(filters);
		
		return mapping;
	}
	
//	@GetMapping("/v2/weathers/{day}")
	@GetMapping(value = "/v2/weathers/{day}", params = "version=2")
//	@GetMapping(value = "/weathers/{day}", headers="API-VERSION=2	")
//	@GetMapping(value = "/weathers/{day}", produces = "application/vnd.company.appv2+json")
	public MappingJacksonValue retrieveWeatherV2(@PathVariable int day) {
		Weather weather = service.findOne(day);
		
		if (weather == null) {
			throw new WeatherNotFoundException(String.format("Day[%s] not found", day));
		}
		WeatherV2 weatherV2 = new WeatherV2();
		BeanUtils.copyProperties(weather, weatherV2);
		weatherV2.setDust("Good");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
				.filterOutAllExcept("temper", "wead", "day", "region");

		FilterProvider filters = new SimpleFilterProvider().addFilter("WeatherInfoV2", filter);
		
		MappingJacksonValue mapping = new MappingJacksonValue(weatherV2);
		mapping.setFilters(filters);
		
		return mapping;
	}

}
