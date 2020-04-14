package org.launchcode.water_garden_tour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController  // added @RestController to verify the hello page will load
public class WaterGardenTourApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaterGardenTourApplication.class, args);
	}

//	added below to verify a hello page will load; may be removed later
@GetMapping("/hello")
public String hello(@RequestParam(value = "name", defaultValue = "Team Water Garden") String name) {
	return String.format("Hello %s!", name);
}
// added above to verify a hello page would load; may be removed later
}
