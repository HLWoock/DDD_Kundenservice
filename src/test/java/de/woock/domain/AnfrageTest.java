package de.woock.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.woock.infra.repository.AnfrageReposity;

@SpringBootTest
public class AnfrageTest {
	
	@Autowired
	AnfrageReposity repo;

	@Test
	public void AnfrageBeantwortenTest() {
		// gegeben
		String frage   = "Wann kommen endlich die versprochenen Jetski?";
		String antwort = "Morgen um 10 Uhr";
		
		// wenn
		new Anfrage().stellen(frage)
                     .weiterleitenAn(Abteilungen.Fuhrpark)
                     .beantworten(antwort);
		
		// dann
		Anfrage anfrage = repo.findByFrage(frage);
		
		assertThat(anfrage.getId())     .isNotEqualTo(0L);
		assertThat(anfrage.getFrage())  .isEqualTo(frage);
		assertThat(anfrage.getAntwort()).isEqualTo(antwort);
	}
}
