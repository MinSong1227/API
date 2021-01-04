package com.weather.test1;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class WeatherTest {
	private String message;
	
	
	
//	@Data
	public String getMessage() {
		return this.message;
	}
	
	public void setMessage(String msg) {
		this.message = msg;
	}
//	 @AllArgsConstructor
	WeatherTest(String message) {
		this.message = message;
	}
}
