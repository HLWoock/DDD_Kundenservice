package de.woock.infra.message;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import de.woock.domain.Abteilungen;
import de.woock.domain.Anfrage;
import de.woock.domain.Beschwerde;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Component
public class Ausgang {

	private final JmsTemplate ausgang;

    public void neuerVorgangFuerAbteilung(Anfrage anfrage, Abteilungen abteilung) {
        log.debug("Anfrage {} weitergeleitet", anfrage.getId());
    	ausgang.send(abteilung.name(), 
                     session -> session.createObjectMessage(anfrage));
    }
    
    public void neuerVorgangFuerAbteilung(Beschwerde beschwerde, Abteilungen abteilung) {
    	log.debug("Beschwerde {} weitergeleitet", beschwerde.getId());
    	ausgang.send(abteilung.name(), 
    			session -> session.createObjectMessage(beschwerde));
    }
}
