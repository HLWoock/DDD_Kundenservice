package de.woock.domain;

import java.util.List;

import org.springframework.stereotype.Service;

import de.woock.infra.repository.Vorgaenge;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class VorgaengeOrdner {
	
	private Vorgaenge vorgaenge;

	public Vorgang abheften(Vorgang vorgang) {
		return vorgaenge.hinzufuegen(vorgang);
	}

	public void updaten(Vorgang vorgang) {
		vorgaenge.hinzufuegen(vorgang);		
	}
	
	public List<Vorgang> alleVorgaenge() {
		return vorgaenge.alle();
	}

}
