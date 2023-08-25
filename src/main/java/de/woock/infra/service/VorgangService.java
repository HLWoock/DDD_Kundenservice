package de.woock.infra.service;

import java.util.List;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import de.woock.domain.Abteilung;
import de.woock.domain.Anfrage;
import de.woock.domain.Beschwerde;
import de.woock.domain.Vorgang;
import de.woock.infra.repository.Vorgaenge;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class VorgangService {
	
	private Vorgaenge vorgaenge;

	// Vorgang
	public List<Vorgang> alleVorgaenge() {
		return  vorgaenge.alleVorgaenge();
	}
	
    // Anfrage
	public List<Anfrage> alleAnfragen() {
		return vorgaenge.alleAnfragen();
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
		return vorgaenge.anfrage(anfrageId);
	}

	public void heuteGestellt(Anfrage anfrage) {
		anfrage.stellen(anfrage.getFrage());
	}
	
	public void anfrageWeiterleiten(Long anfrageId, List<Abteilung> abteilungen) {
		Anfrage anfrage = vorgaenge.anfrage(anfrageId);
		abteilungen.forEach(abteilung -> anfrage.weiterleitenAn(abteilung));
	}
	
    // Beschwerde
	public List<Beschwerde>alleBeschwerden() {
		return vorgaenge.alleBeschwerden();
	}
	
	public Beschwerde beschwerde(Long beschwerdeId) {
		return vorgaenge.beschwerde(beschwerdeId);
	}

	public void heuteGestellt(Beschwerde beschwerde) {
		beschwerde.einreichen(beschwerde.getBeschwerde());
		
	}

	public void beschwerdeAktualisiert(Beschwerde konvertiere) {
		
	}
}
