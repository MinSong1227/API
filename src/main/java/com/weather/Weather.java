package com.weather;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

//import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFilter;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@AllArgsConstructor
//@JsonIgnoreProperties(value= {"writer"})
@NoArgsConstructor
//@JsonFilter("WeatherInfo")
@ApiModel(description = "날씨 상세 정보를 위한 도메인 객체")
@Entity
public class Weather {
	
	public Weather(int id,int r_id, int temp, String wed, int day, String writer, String region) {
		this.id = id;
		this.r_id = r_id;
		this.temper = temp;
		this.wead = wed;
		this.day = day;
		this.writer = writer;
		this.region = region;
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue
	private Integer id;
	private Integer r_id;
	@ApiModelProperty(notes = "기온")
	private Integer temper;
//	@Size(min=2, message = "2글자 이상으로 써주세요")
	@ApiModelProperty(notes = "날씨")
	private String wead;
	@ApiModelProperty(notes = "날짜")
//	@Id
	private Integer day;
	private String writer;
	private String region;
	
	@OneToMany(mappedBy = "weather")
	private List<Post> posts;
	
}
