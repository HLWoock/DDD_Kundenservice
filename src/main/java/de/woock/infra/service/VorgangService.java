package de.woock.infra.service;

import java.util.List;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import de.woock.domain.Anfrage;
import de.woock.domain.Beschwerde;
import de.woock.domain.Vorgang;
import de.woock.infra.repository.AnfrageReposity;
import de.woock.infra.repository.BeschwerdeReposity;
import de.woock.infra.repository.VorgangRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class VorgangService {
	
	private VorgangRepository  vorgangRepository;
	private AnfrageReposity    anfrageReposity;
	private BeschwerdeReposity beschwerdeReposity;

	public List<Anfrage> alleAnfragen() {
		return anfrageReposity.findAll();
	}
	
	public List<Beschwerde>alleBeschwerden() {
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

	public Beschwerde beschwerde(Long beschwerdeId) {
		return beschwerdeReposity.findById(beschwerdeId).orElse(new Beschwerde());
	}

	public void heuteGestellt(Anfrage anfrage) {
		anfrage.stellen(anfrage.getFrage());
	}
	
	public void heuteGestellt(Beschwerde beschwerde) {
		beschwerde.einreichen(beschwerde.getBeschwerde());
		
	}

	public void beschwerdeAktualisiert(Beschwerde konvertiere) {
		// TODO Auto-generated method stub
		
	}
}
