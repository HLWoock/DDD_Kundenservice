package de.woock.infra.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.orm.ObjectOptimisticLockingFailureException;
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
import de.woock.infra.service.VorgangService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin
@AllArgsConstructor
@Controller
public class AnfragenMVCController {
	
	private VorgangService vorgangService;

	@GetMapping({"/", "/index"})
    public ModelAndView home() {
    	ModelAndView model = new ModelAndView("index");
    	model.addObject("anfragen", vorgangService.alleAnfragen());
        return model;
    }
	
	@GetMapping("/about")
	public String about() {
		return "about";
	}

	@GetMapping("/anfrage/{anfrageId}/bearbeiten")
	public ModelAndView anfrageBearbeitenForm(@PathVariable Long anfrageId) {
		ModelAndView model = new ModelAndView("anfrageBearbeiten");
		Anfrage anfrage = vorgangService.anfrage(anfrageId);
		
		model.addObject("prios", Prio.values());
		model.addObject("statuus", Status.values()); 
		model.addObject("anfrage", anfrage);
		log.debug("Anfrage {}/{} wird gerade zur Bearbeitung in die Anzeige gebracht", anfrage.getId(), anfrageId);
		return model;
	}
	
	@PostMapping("/anfrage/{anfrageId}/bearbeiten")
	public String anfrageBearbeiten(@ModelAttribute("anfrage") Anfrage anfrage) {
		log.debug("Anfrage {}/{} bearbeitet", anfrage.getId(), anfrage.getVersion());
		try {
			anfrage.aktualisiert();
		} catch (ObjectOptimisticLockingFailureException ex) {
			log.error("Anfrage {}/{} wird gerade von jemand anderem bearbeitet", anfrage.getId(), anfrage.getVersion());
		}
		return "redirect:/";
	}
	
	@GetMapping("/neueAnfrage")
	public ModelAndView neueAnfrageBearbeitenForm() {
		ModelAndView model = new ModelAndView("neueAnfrage");
		model.addObject("prios", Prio.values());
		model.addObject("statuus", Status.values()); 
		model.addObject("anfrage", new Anfrage());
		return model;
	}
	
	@PostMapping("/neueAnfrage")
	public String neueAnfrageBearbeiten(@ModelAttribute("anfrage") Anfrage anfrage) {
		anfrage.heuteGestellt();
		log.debug("neue Anfrage: {}", anfrage);
		return "redirect:/";
	}
}