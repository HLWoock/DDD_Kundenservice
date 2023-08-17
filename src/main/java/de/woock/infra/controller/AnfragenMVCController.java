package de.woock.infra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import de.woock.domain.Abteilung;
import de.woock.domain.Anfrage;
import de.woock.domain.Prio;
import de.woock.domain.Status;
import de.woock.domain.fehler.LeeresFeldFehler;
import de.woock.infra.dto.AnfrageDto;
import de.woock.infra.dto.WeiterleitenDto;
import de.woock.infra.service.VorgangService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin
@AllArgsConstructor
@Controller
public class AnfragenMVCController {
	
	private VorgangService anfragenService;

		@GetMapping({"/anfragen"})
    public ModelAndView anfragen() {
    	ModelAndView model = new ModelAndView("anfragen");
    	model.addObject("anfragen", anfragenService.alleAnfragen());
        return model;
    }
	
	@GetMapping("/anfrage/{anfrageId}/bearbeiten")
	public ModelAndView anfrageBearbeitenForm(@PathVariable Long anfrageId) {
		ModelAndView model = new ModelAndView("anfrageBearbeiten");
		Anfrage anfrage = anfragenService.anfrage(anfrageId);
		
		model.addObject("prios", Prio.values());
		model.addObject("statuus", Status.values()); 
		model.addObject("anfrage", anfrage);
		log.debug("Anfrage {}/{} wird gerade zur Bearbeitung in die Anzeige gebracht", anfrage.getId(), anfrage.getVersion());
		return model;
	}
	
	@PostMapping("/anfrage/{anfrageId}/bearbeiten")
	public String anfrageBearbeiten(@ModelAttribute("anfrage") AnfrageDto anfrageDto) {
		log.debug("Anfrage {}/{} fertig bearbeitet", anfrageDto.getId(), anfrageDto.getVersion());
		try {
			anfragenService.anfrageAktualisiert(konvertiere(anfrageDto));
		} catch (LeeresFeldFehler e) {
			e.printStackTrace();
		} 
		return "redirect:/anfragen";
	}
	
	@GetMapping("/anfrage/{anfrageId}/weiterleiten")
	public ModelAndView anfrageWeiterleitenForm(@PathVariable Long anfrageId) {
		ModelAndView model = new ModelAndView("anfrageWeiterleiten");
		Anfrage anfrage = anfragenService.anfrage(anfrageId);
		
		
		model.addObject("anfrage", new WeiterleitenDto(anfrageId, anfrage.getFrage(), false));
		log.debug("Anfrage {}/{} wird gerade zur Weiterleitung in die Anzeige gebracht", anfrage.getId(), anfrage.getVersion());
		return model;
	}
	
	@PostMapping("/anfrage/{anfrageId}/weiterleiten")
	public String anfrageWeiterleiten(@ModelAttribute("anfrage") WeiterleitenDto weiterleitenDto) {
		log.debug("Anfrage {} weiterleiten", weiterleitenDto.getId());
 
		return "redirect:/";
	}
	
	@GetMapping("/neueAnfrage")
	public ModelAndView neueAnfrageForm() {
		ModelAndView model = new ModelAndView("neueAnfrage");
		model.addObject("anfrage", new AnfrageDto());
		return model;
	}
	
	@PostMapping("/neueAnfrage")
	public String neueAnfrage(@ModelAttribute("anfrage") AnfrageDto anfrageDto, BindingResult result, Model model) {
		log.debug("neue Anfrage: '{}' mit Prio {} wird gestellt", anfrageDto.getFrage(), anfrageDto.getPrio());
		Anfrage anfrage = null;
		try {
			anfrage = konvertiere(anfrageDto);
		} catch (LeeresFeldFehler e) {
			ValidationUtils.rejectIfEmptyOrWhitespace(result, e.feld(), "feld.nicht.leer");
		}
		if (result.hasErrors()) {
			log.error("es {} {} Fehler beim Anlegen einer Anfrage aufgetreten.", result.getErrorCount()==1 ? "ist": "sind", result.getErrorCount());
			return "/neueAnfrage";
		}
		anfragenService.heuteGestellt(anfrage);
		log.debug("neue Anfrage: {}", anfrage);
		return "redirect:/anfragen";
	}
	
	private Anfrage konvertiere(AnfrageDto anfrageDto) throws LeeresFeldFehler {
		Anfrage anfrage = new Anfrage(anfrageDto.getFrage());
		anfrage.setAntwort(anfrageDto.getAntwort());
		anfrage.setId     (anfrageDto.getId());
		anfrage.setVersion(anfrageDto.getVersion());
		anfrage.setPrio   (anfrageDto.getPrio());
		return anfrage;
	}
}