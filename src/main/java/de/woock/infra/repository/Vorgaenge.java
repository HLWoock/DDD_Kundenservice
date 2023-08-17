package de.woock.infra.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import de.woock.domain.Anfrage;
import de.woock.domain.Beschwerde;
import de.woock.domain.Vorgang;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Component
public class Vorgaenge {
	
	private final VorgangRepository  vorgangRepository;
	private final AnfrageReposity    anfrageReposity;
	private final BeschwerdeReposity beschwerdeReposity;

	public Vorgang hinzufuegen(Vorgang vorgang) {
		return vorgangRepository.save(vorgang);
	}

	public List<Vorgang> alleVorgaenge() {
		return vorgangRepository.findAll();
	}
	
	public List<Anfrage> alleAnfragen() {
		return anfrageReposity.findAll();
	}
	
	public List<Beschwerde> alleBeschwerden() {
		return beschwerdeReposity.findAll();
	}
	
}
