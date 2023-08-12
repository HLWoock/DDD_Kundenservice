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
			
//			new Anfrage().stellen("Wann kommen endlich die versprochenen Jetski?")
//			             .weiterleitenAn(Fuhrpark)
//			             .beantworten("In 2 Wochen");
//			
//			new Anfrage().stellen("Was kostet die Mitgliedschaft fuer ein Jahr?")
//			             .weiterleitenAn(Verein)
//			             .beantworten("50 Euro");
//			
//			new Beschwerde().einreichen("Mein Auto war schmutzig!")
//            .weiterleitenAn(Verein)
//            .beantworten("Sie bekommen einen 50€ Gutschein");
		};
	}

}
