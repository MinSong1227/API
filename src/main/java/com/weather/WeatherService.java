package com.weather;

import java.util.ArrayList;
import java.util.Iterator;
//import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;



@Service
public class WeatherService {
	private static List<Weather> weathers = new ArrayList<>();
	
	private static int contentsCount = 3;
	
	static {
		weathers.add(new Weather(1, 200, -14,"cloud", 201227,"Minsong","Seoul"));
		weathers.add(new Weather(2, 200, -12,"Windy", 201228,"Minsong","Seoul"));
		weathers.add(new Weather(3, 1000, 2, "Sunny", 201229, "GilDong", "Jeju"));
		
	}
	public List<Weather> findAll() {
		return weathers;
	}
	
	public Weather save(Weather weather) {
		weather.setId(++contentsCount);
		weathers.add(weather);
		return weather;
	}
	
	
	public Weather findOne(int day) {
		for (Weather weather : weathers) {
			if (weather.getDay() == day) {
				return weather;
			}
		}		return null;

	}
	
	public Weather deleteByDay(int day) {
		Iterator<Weather> iterator = weathers.iterator();
		
		while (iterator.hasNext()) {
			Weather weather = iterator.next();
			
			if(weather.getDay() == day) {
				iterator.remove();
				return weather;
			}
		}
		return null;
	}
	
	public Weather modiWeather(Weather weather) {
		deleteByDay(weather.getDay());
		save(weather);
		return weather;
	}
}
