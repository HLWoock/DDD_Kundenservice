package de.woock.infra.message;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import de.woock.domain.Anfrage;
import de.woock.domain.ereignisse.AnfrageGestellt;
import de.woock.infra.repository.AnfrageReposity;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Component
public class Eingang {
	
	final AnfrageReposity anfrageReposity;
	
	@JmsListener(destination      = "Antwort", 
			     containerFactory = "myFactory", 
			     subscription     = "stattauto")
	public void antwortVerarbeiten(Message<AnfrageGestellt> antwort, @Header("status") String status) {
		Anfrage anfrage = anfrageReposity.findByFrage(antwort.getPayload().getFrage());
		
		log.debug("Antwort für Anfrage {} eingegangen: {}", anfrage.getId(), antwort.getPayload().getAntwort());
		log.debug("...mit Sttatus {} ", status);
		
		anfrage.beantworten(antwort.getPayload().getAntwort());
	}	
}