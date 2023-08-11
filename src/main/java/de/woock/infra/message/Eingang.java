package de.woock.infra.message;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import de.woock.domain.Anfrage;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class Eingang {
	@JmsListener(destination = "Antwort", containerFactory = "myFactory", subscription = "stattauto")
	public void antwortVerarbeiten(Anfrage antwort) {
		log.debug("Antwort eingegangen");
		antwort.beantworten(antwort.getAntwort());
	}	
}