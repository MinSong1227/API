package com.weather;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/jpa")
public class WeatherJpaController {
	
	@Autowired(required=true)
	private WeatherRepository weatherRepository;
	
	@Autowired(required=true)
	private PostRepository postRepository;
	
	
	@GetMapping("/weathers")
	public List<Weather> retrieveAllUsers() {
		return weatherRepository.findAll();
		
	}
	
	@GetMapping("/weathers/{id}")
	public Weather retrieveWeather(@PathVariable int id) {
		Optional<Weather> weather = weatherRepository.findById(id);
		
		if(!weather.isPresent()) {
			throw new WeatherNotFoundException(String.format("ID{%s} not Found", id));
		}
		
		return weather.get();
	}
	
	@DeleteMapping("/weathers/{id}")
	public void deleteWeather(@PathVariable int id) {
		weatherRepository.deleteById(id);
	}
	
	
	@PostMapping("/weathers")
	public ResponseEntity<Weather> createWeather(@RequestBody Weather weather){
		Weather savedWeather = weatherRepository.save(weather);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedWeather.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/weathers/{id}")
	public ResponseEntity<Weather> modiWeather(@RequestBody Weather weather) {
		weatherRepository.deleteById(weather.getId());
		
		Weather savedWeather = weatherRepository.save(weather);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedWeather.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@GetMapping("/weathers/{id}/posts")
	public List<Post> retrieveAllPostByWeather(@PathVariable int id){
		Optional<Weather> weather = weatherRepository.findById(id);
		
		if(!weather.isPresent()) {
			throw new WeatherNotFoundException(String.format("ID{%s} not Found", id));
		}
		
		return weather.get().getPosts();
	}
	
	@PostMapping("/weathers/{id}/posts")
	public ResponseEntity<Post> createPost(@PathVariable int id, @RequestBody Post post){
		
		Optional<Weather> weather = weatherRepository.findById(id);
		
		if(!weather.isPresent()) {
			throw new WeatherNotFoundException(String.format("ID{%s} not Found", id));
		}
		post.setWeather(weather.get());
		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedPost.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	@DeleteMapping("/weathers/{id}/posts/{pid}")
	public void deletePost(@PathVariable int pid) {
		postRepository.deleteById(pid);
	}
	
	@PutMapping("/weathers/{id}/posts/{pid}")
	public ResponseEntity<Weather> modifyPost(@PathVariable int id, @PathVariable int pid, @RequestBody Post post) {

		postRepository.deleteById(pid);
		Optional<Weather> weather = weatherRepository.findById(id);
		
		if(!weather.isPresent()) {
			throw new WeatherNotFoundException(String.format("ID{%s} not Found", id));
		}
		post.setWeather(weather.get());
		Post savedPost = postRepository.save(post);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("")
				.buildAndExpand(savedPost.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
}
