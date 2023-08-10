package de.woock.infra.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.woock.domain.Anfrage;
import de.woock.domain.Beschwerde;
import de.woock.domain.Vorgang;
import de.woock.infra.service.VorgangService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class AnfragenRESTController {
	
	private VorgangService vorgangService;
	
	@GetMapping("/anfragen")
	public List<Anfrage> alleAnfragen() {
		return vorgangService.alleAnfragen();
	}
	
	@GetMapping("/beschwerden")
	public List<Beschwerde> alleBeschwerden() {
		return vorgangService.alleBeschwerden();
	}
	
	@GetMapping("/vorgaenge")
	public List<Vorgang> alleVorgaenge() {
		return vorgangService.alleVorgaenge();
	}
}
