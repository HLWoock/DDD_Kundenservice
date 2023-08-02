package de.woock.infra.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import de.woock.domain.Vorgang;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Component
public class Vorgaenge {
	
	private final VorgangRepository vorgangRepository;

	public Vorgang hinzufuegen(Vorgang vorgang) {
		return vorgangRepository.save(vorgang);
	}

	public List<Vorgang> alle() {
		return vorgangRepository.findAll();
	}
	
}
