package de.woock.infra.message;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import de.woock.domain.Abteilungen;
import de.woock.domain.Vorgang;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class Ausgang {

	private final JmsTemplate ausgang;

    public void neuerVorgangFuerAbteilung(Vorgang vorgang, Abteilungen abteilung) {
        ausgang.send(abteilung.name(), 
                     session -> session.createObjectMessage(vorgang));
    }
}
