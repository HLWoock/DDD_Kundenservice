package de.woock.infra.message;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import de.woock.domain.Abteilungen;
import de.woock.domain.Anfrage;
import de.woock.domain.Beschwerde;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class Ausgang {

	private final JmsTemplate ausgang;

    public void neuerVorgangFuerAbteilung(Anfrage anfrage, Abteilungen abteilung) {
        ausgang.send(abteilung.name(), 
                     session -> session.createObjectMessage(anfrage));
    }
    
    public void neuerVorgangFuerAbteilung(Beschwerde beschwerde, Abteilungen abteilung) {
    	ausgang.send(abteilung.name(), 
    			session -> session.createObjectMessage(beschwerde));
    }
}
