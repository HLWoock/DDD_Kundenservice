package de.woock.infra.message;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import de.woock.domain.Anfrage;
import de.woock.infra.repository.AnfrageReposity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Component
public class Eingang {
	
	final AnfrageReposity anfrageReposity;
	
	@JmsListener(destination = "Antwort", containerFactory = "myFactory", subscription = "stattauto")
	public void antwortVerarbeiten(Anfrage antwort) {
		Anfrage anfrage = anfrageReposity.findByFrage(antwort.getFrage());
		
		log.debug("Antwort für Anfrage {} eingegangen: {}", anfrage.getId(), antwort.getAntwort());
		
		anfrage.beantworten(antwort.getAntwort());
	}	
}