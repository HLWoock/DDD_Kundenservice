package de.woock.domain;

import org.springframework.stereotype.Service;

import de.woock.infra.message.Ausgang;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class VorgaengeBoard {
	
	private Ausgang ausgang;

	public void neueAnfrageAnheften(Anfrage anfrage, Abteilungen abteilung) {
		ausgang.neuerVorgangFuerAbteilung(anfrage, abteilung);
	}
	
	public void neueBeschwerdeAnheften(Beschwerde beschwerde, Abteilungen abteilung) {
		ausgang.neuerVorgangFuerAbteilung(beschwerde, abteilung);
	}

}
