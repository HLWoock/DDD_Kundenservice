package de.woock.infra.message;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import de.woock.domain.Abteilung;
import de.woock.domain.Anfrage;
import de.woock.domain.Beschwerde;
import de.woock.domain.Konvertierer;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Component
public class Ausgang {

	private final JmsTemplate  ausgang;
	private final Konvertierer konvertierer;

    public void neuerVorgangFuerAbteilung(Anfrage anfrage, Abteilung abteilung) {
        log.debug("Anfrage {} weitergeleitet: {}", anfrage.getId(), anfrage.getFrage());
    	ausgang.send(abteilung.name(), 
                     session -> session.createObjectMessage(konvertierer.konvertiere(anfrage)));
    }
    
    public void neuerVorgangFuerAbteilung(Beschwerde beschwerde, Abteilung abteilung) {
    	log.debug("Beschwerde {} weitergeleitet: {}", beschwerde.getId(), beschwerde.getBeschwerde());
    	ausgang.send(abteilung.name(), 
    			     session -> session.createObjectMessage(beschwerde));
    }
}
