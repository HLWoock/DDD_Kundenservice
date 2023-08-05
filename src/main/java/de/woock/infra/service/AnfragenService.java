package de.woock.infra.service;

import java.util.List;

import org.springframework.stereotype.Service;

import de.woock.domain.Anfrage;
import de.woock.domain.Beschwerde;
import de.woock.domain.Vorgang;
import de.woock.infra.repository.AnfrageReposity;
import de.woock.infra.repository.VorgangRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AnfragenService {
	
	private VorgangRepository vorgangRepository;
	private AnfrageReposity   anfrageReposity;

	public List<Anfrage> alleAnfragen() {
		return Anfrage.liste();
	}
	
	public List<Vorgang>alleBeschwerden() {
		return Beschwerde.liste();
	}
	
	public List<Vorgang> alleVorgaenge() {
		List<Vorgang> vorgaenge = vorgangRepository.findAll();
		
		vorgaenge.addAll(vorgaenge);
		
		return vorgaenge;
	}

	public Anfrage anfrage(Long anfrageId) {
		return anfrageReposity.findById(anfrageId).orElse(new Anfrage());
	}
}
