package de.woock;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import de.woock.domain.VorgaengeBoard;
import de.woock.domain.VorgaengeOrdner;

@SpringBootApplication
public class Kundenservice {
	
	public static VorgaengeOrdner vorgaengeOrdner;
	public static VorgaengeBoard  vorgaengeBoard;

	public static void main(String[] args) {
		SpringApplication.run(Kundenservice.class, args);
	}
	
	@Bean
	public ApplicationRunner test(VorgaengeOrdner vorgaengeOrdner, VorgaengeBoard vorgaengeBoard) {
		return args -> {
			Kundenservice.vorgaengeOrdner = vorgaengeOrdner;
			Kundenservice.vorgaengeBoard  = vorgaengeBoard;
		};
	}
}
