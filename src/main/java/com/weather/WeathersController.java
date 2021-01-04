package com.weather;

import java.net.URI;
import java.util.List;

//import org.springframework.hateoas.EntityModel;
//import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class WeathersController {
	private WeatherService service;
	
	public WeathersController(WeatherService service) {
		this.service = service;
	}
	
	
	@GetMapping("/weathers")
	public List<Weather> retrieveAllWeathers(){
		return service.findAll();
	}
	
	
	@GetMapping("/weathers/{day}")
	public Weather/*EntityModel<Weather>*/ retrieveWeather(@PathVariable int day) {
		Weather weather = service.findOne(day);
		
		if (weather == null) {
			throw new WeatherNotFoundException(String.format("Day[%s] not found", day));
		}
//		
//		EntityModel<Weather> model = new EntityModel<>(weather);
//		WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllWeathers());
//		model.add(linkTo.withRel("all-weathers"));
//		return model;
//		<dependency>
//		<groupId>org.springframework.boot</groupId>
//		<artifactId>spring-boot-starter-hateoas</artifactId>
//	</dependency>
		return weather;
	}
	
	@PostMapping("/weathers")
	public ResponseEntity<Weather> createWeather(@RequestBody Weather weather) {
		Weather savedWeather = service.save(weather);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{day}")
				.buildAndExpand(savedWeather.getDay())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/weathers/{day}")
	public void deleteWeather(@PathVariable int day) {
		Weather weather = service.deleteByDay(day);
		
		if (weather == null) {
			throw new WeatherNotFoundException(String.format("Day[%s] not found", day));
		}
	}
	
	@PutMapping("/weathers")
	public ResponseEntity<Weather> modifyWeather(@RequestBody Weather weather) {
		Weather savedWeather = service.modiWeather(weather);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{day}")
				.buildAndExpand(savedWeather.getDay())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
}
