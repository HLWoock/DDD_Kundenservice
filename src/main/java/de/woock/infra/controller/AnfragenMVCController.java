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

import de.woock.domain.Anfrage;
import de.woock.domain.Konvertierer;
import de.woock.domain.Prio;
import de.woock.domain.Status;
import de.woock.domain.fehler.LeeresFeldFehler;
import de.woock.infra.dto.AnfrageDto;
import de.woock.infra.dto.WeiterleitenDto;
import de.woock.infra.metrics.MetricsService;
import de.woock.infra.service.VorgangService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin
@AllArgsConstructor
@Controller
public class AnfragenMVCController {
	
	private VorgangService vorgangService;
	private Konvertierer   konvertierer;
	private MetricsService metricsService;

	@GetMapping({"/anfragen"})
    public ModelAndView anfragen() {
    	ModelAndView model = new ModelAndView("anfragen");
    	model.addObject("anfragen", vorgangService.alleAnfragen());
        return model;
    }
	
	@GetMapping("/anfrage/{anfrageId}/bearbeiten")
	public ModelAndView anfrageBearbeitenForm(@PathVariable Long anfrageId) {
		ModelAndView model   = new ModelAndView("anfrageBearbeiten");
		Anfrage      anfrage = vorgangService.anfrage(anfrageId);
		
		model.addObject("prios"  , Prio.values());
		model.addObject("statuus", Status.values()); 
		model.addObject("anfrage", anfrage);
		log.debug("Anfrage {}/{} wird gerade zur Bearbeitung in die Anzeige gebracht", anfrage.getId(), anfrage.getVersion());
		return model;
	}
	
	@PostMapping("/anfrage/{anfrageId}/bearbeiten")
	public String anfrageBearbeiten(@ModelAttribute("anfrage") AnfrageDto anfrageDto) {
		log.debug("Anfrage {}/{} fertig bearbeitet", anfrageDto.getId(), anfrageDto.getVersion());
		try {
			vorgangService.anfrageAktualisiert(konvertierer.konvertiere(anfrageDto));
			metricsService.increment("stattauto.anfragen");
		} catch (LeeresFeldFehler e) {
			e.printStackTrace();
		} 
		return "redirect:/anfragen";
	}
	
	@GetMapping("/anfrage/{anfrageId}/weiterleiten")
	public ModelAndView anfrageWeiterleitenForm(@PathVariable Long anfrageId) {
		ModelAndView model   = new ModelAndView("anfrageWeiterleiten");
		Anfrage      anfrage = vorgangService.anfrage(anfrageId);
		
		model.addObject("anfrage", new WeiterleitenDto(anfrageId, anfrage.getFrage(), false, false, false));
		log.debug("Anfrage {}/{} wird gerade zur Weiterleitung in die Anzeige gebracht", anfrage.getId(), anfrage.getVersion());
		return model;
	}
	
	@PostMapping("/anfrage/{anfrageId}/weiterleiten")
	public String anfrageWeiterleiten(@ModelAttribute("anfrage") WeiterleitenDto weiterleitenDto) {
		log.debug("Anfrage {} weiterleiten", weiterleitenDto.getId());
		vorgangService.anfrageWeiterleiten(weiterleitenDto.getId(), konvertierer.konvertiere(weiterleitenDto));
 
		return "redirect:/anfragen";
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
			anfrage = konvertierer.konvertiere(anfrageDto);
		} catch (LeeresFeldFehler e) {
			ValidationUtils.rejectIfEmptyOrWhitespace(result, e.feld(), "feld.nicht.leer");
		}
		if (result.hasErrors()) {
			log.error("es {} {} Fehler beim Anlegen einer Anfrage aufgetreten.", result.getErrorCount()==1 ? "ist": "sind", result.getErrorCount());
			return "/neueAnfrage";
		}
		vorgangService.heuteGestellt(anfrage);
		log.debug("neue Anfrage: {}", anfrage);
		return "redirect:/anfragen";
	}
}