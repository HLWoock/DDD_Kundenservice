package de.woock.infra.message;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import de.woock.domain.Anfrage;

@Component
public class Eingang {
	@JmsListener(destination = "Antwort", containerFactory = "myFactory", subscription = "stattauto")
	public void antwortVerarbeiten(Anfrage antwort) {
		antwort.beantworten(antwort.getAntwort());
	}	
}