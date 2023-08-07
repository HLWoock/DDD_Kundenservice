package de.woock.infra.service;

import java.util.List;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import de.woock.domain.Anfrage;
import de.woock.domain.Beschwerde;
import de.woock.domain.Vorgang;
import de.woock.infra.dto.AnfrageDto;
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
	
	public void anfrageAktualisiert(AnfrageDto anfrageDto) {
		log.debug("Anfrage {}/{} wird aktualisiert", anfrageDto.getId(), anfrageDto.getVersion());

		Anfrage anfrage = konvertiere(anfrageDto);
		try {
			anfrage.aktualisiert();
		} catch (ObjectOptimisticLockingFailureException ex) {
			log.error("Anfrage {}/{} wird gerade von jemand anderem bearbeitet", anfrageDto.getId(), anfrageDto.getVersion());
		}
	}

	public Anfrage anfrage(Long anfrageId) {
		return anfrageReposity.findById(anfrageId).orElse(new Anfrage());
	}

	public void heuteGestellt(AnfrageDto anfrageDto) {
		Anfrage anfrage = konvertiere(anfrageDto);
		anfrage.stellen(anfrageDto.getFrage());
		
	}

	private Anfrage konvertiere(AnfrageDto anfrageDto) {
		Anfrage anfrage = new Anfrage(anfrageDto.getFrage());
		anfrage.setAntwort(anfrageDto.getAntwort());
		anfrage.setId     (anfrageDto.getId());
		anfrage.setVersion(anfrageDto.getVersion());
		anfrage.setPrio   (anfrageDto.getPrio());
		return anfrage;
	}
}
