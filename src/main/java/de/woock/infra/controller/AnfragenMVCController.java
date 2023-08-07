package de.woock.infra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import de.woock.domain.Anfrage;
import de.woock.domain.Prio;
import de.woock.domain.Status;
import de.woock.infra.dto.AnfrageDto;
import de.woock.infra.service.AnfragenService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin
@AllArgsConstructor
@Controller
public class AnfragenMVCController {
	
	private AnfragenService anfragenService;

	@GetMapping({"/", "/index"})
    public ModelAndView home() {
    	ModelAndView model = new ModelAndView("index");
    	model.addObject("anfragen", anfragenService.alleAnfragen());
        return model;
    }
	
	@GetMapping("/about")
	public String about() {
		return "about";
	}

	@GetMapping("/anfrage/{anfrageId}/bearbeiten")
	public ModelAndView anfrageBearbeitenForm(@PathVariable Long anfrageId) {
		ModelAndView model = new ModelAndView("anfrageBearbeiten");
		Anfrage anfrage = anfragenService.anfrage(anfrageId);
		
		model.addObject("prios", Prio.values());
		model.addObject("statuus", Status.values()); 
		model.addObject("anfrage", anfrage);
		log.debug("Anfrage {}/{} wird gerade zur Bearbeitung in die Anzeige gebracht", anfrage.getId(), anfrageId);
		return model;
	}
	
	@PostMapping("/anfrage/{anfrageId}/bearbeiten")
	public String anfrageBearbeiten(@ModelAttribute("anfrage") AnfrageDto anfrageDto) {
		log.debug("Anfrage {}/{} fertig bearbeitet", anfrageDto.getId(), anfrageDto.getVersion());
		anfragenService.anfrageAktualisiert(konvertiere(anfrageDto));
		return "redirect:/";
	}
	
	@GetMapping("/neueAnfrage")
	public ModelAndView neueAnfrageBearbeitenForm() {
		ModelAndView model = new ModelAndView("neueAnfrage");
		model.addObject("anfrage", new AnfrageDto(Prio.values()));
		return model;
	}
	
	@PostMapping("/neueAnfrage")
	public String neueAnfrageBearbeiten(@ModelAttribute("anfrage") AnfrageDto anfrageDto) {
		anfragenService.heuteGestellt(konvertiere(anfrageDto));
		log.debug("neue Anfrage: {}", anfrageDto);
		return "redirect:/";
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