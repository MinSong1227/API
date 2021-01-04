package com.weather.test1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {
	//GET
	// Weather (endpoint)
	//@RequestMapping(method=RequestMethod.GET, path="weather")
	@GetMapping(path = "/weather")
	public String weather() {
		return "Success";
	}
	
	@GetMapping(path = "/weatherTest")
	public WeatherTest weatherTest() {
		return new WeatherTest("Success");
	}
	
	@GetMapping(path = "/weatherTest/path/{day}")
	public WeatherTest weatherTest(@PathVariable String day) {
		return new WeatherTest(String.format("Have a good day, %s", day));
	}
}
