package de.woock.domain;

import org.springframework.stereotype.Service;

import de.woock.domain.ereignisse.AnfrageGestellt;
import de.woock.domain.fehler.LeeresFeldFehler;
import de.woock.infra.dto.AnfrageDto;
import de.woock.infra.dto.WeiterleitenDto;

@Service
public class Konvertierer {

	public Anfrage konvertiere(AnfrageDto anfrageDto) throws LeeresFeldFehler {
		Anfrage anfrage = new Anfrage(anfrageDto.getFrage());
		anfrage.setAntwort(anfrageDto.getAntwort());
		anfrage.setId     (anfrageDto.getId());
		anfrage.setVersion(anfrageDto.getVersion());
		anfrage.setPrio   (anfrageDto.getPrio());
		return anfrage;
	}
	
	public AnfrageGestellt konvertiere(WeiterleitenDto weiterleitenDto) {
		AnfrageGestellt anfrage = new AnfrageGestellt(weiterleitenDto.getId(), weiterleitenDto.getFrage());
		return anfrage;
	}
	
	public AnfrageGestellt konvertiere(Anfrage anfrage) {
		AnfrageGestellt anfrageGestellt = new AnfrageGestellt(anfrage.getId(), anfrage.getFrage());
		return anfrageGestellt;
	}
}
