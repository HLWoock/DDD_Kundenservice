package de.woock.infra.service;

import java.util.List;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import de.woock.domain.Anfrage;
import de.woock.domain.Beschwerde;
import de.woock.domain.Vorgang;
import de.woock.infra.repository.AnfrageReposity;
import de.woock.infra.repository.VorgangRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
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
	
	public void anfrageAktualisiert(Anfrage anfrage) {
		log.debug("Anfrage {}/{} wird aktualisiert", anfrage.getId(), anfrage.getVersion());

		try {
			anfrage.aktualisiert();
		} catch (ObjectOptimisticLockingFailureException ex) {
			log.error("Anfrage {}/{} wird gerade von jemand anderem bearbeitet", anfrage.getId(), anfrage.getVersion());
		}
	}

	public Anfrage anfrage(Long anfrageId) {
		return anfrageReposity.findById(anfrageId).orElse(new Anfrage());
	}

	public void heuteGestellt(Anfrage anfrage) {
		anfrage.stellen(anfrage.getFrage());
		
	}
}
