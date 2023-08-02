package de.woock.infra.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import de.woock.domain.Anfrage;
import de.woock.domain.Beschwerde;
import de.woock.domain.Vorgang;

@Service
public class VorgangService {

	public List<Vorgang> alleAnfragen() {
		return Anfrage.liste();
	}
	
	public List<Vorgang>alleBeschwerden() {
		return Beschwerde.liste();
	}
	
	public List<Vorgang> alleVorgaenge() {
		List <Vorgang> vorgaenge = new ArrayList<>();
		List<Vorgang> anfragen    = Anfrage.liste();
		List<Vorgang> beschwerden = Beschwerde.liste();
		
		vorgaenge.addAll(beschwerden);
		vorgaenge.addAll(anfragen);
		
		return vorgaenge;
	}
}
