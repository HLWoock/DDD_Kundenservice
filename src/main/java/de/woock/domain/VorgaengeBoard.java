package de.woock.domain;

import org.springframework.stereotype.Service;

import de.woock.infra.message.Ausgang;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class VorgaengeBoard {
	
	private Ausgang ausgang;

	public void neuenVorgangAnheften(Vorgang vorgang, Abteilungen abteilung) {
		ausgang.neuerVorgangFuerAbteilung(vorgang, abteilung);
	}

}
