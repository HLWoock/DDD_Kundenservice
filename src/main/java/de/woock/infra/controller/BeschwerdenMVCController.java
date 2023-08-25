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

import de.woock.domain.Beschwerde;
import de.woock.domain.Konvertierer;
import de.woock.domain.Prio;
import de.woock.domain.Status;
import de.woock.domain.fehler.LeeresFeldFehler;
import de.woock.infra.dto.BeschwerdeDto;
import de.woock.infra.service.VorgangService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin
@AllArgsConstructor
@Controller
public class BeschwerdenMVCController {
	
	private VorgangService vorgangService;
	private Konvertierer   konvertierer;

		@GetMapping({"/beschwerden"})
    public ModelAndView beschwerden() {
    	ModelAndView model = new ModelAndView("beschwerden");
    	model.addObject("beschwerden", vorgangService.alleBeschwerden());
        return model;
    }
	
	@GetMapping("/beschwerde/{beschwerdeId}/bearbeiten")
	public ModelAndView beschwerdeBearbeitenForm(@PathVariable Long beschwerdeId) {
		ModelAndView model = new ModelAndView("beschwerdeBearbeiten");
		Beschwerde beschwerde = vorgangService.beschwerde(beschwerdeId);
		
		model.addObject("prios", Prio.values());
		model.addObject("statuus", Status.values()); 
		model.addObject("beschwerde", beschwerde);
		log.debug("Beschwerde {}/{} wird gerade zur Bearbeitung in die Anzeige gebracht", beschwerde.getId(), beschwerde.getVersion());
		return model;
	}
	
	@PostMapping("/beschwerde/{beschwerdeId}/bearbeiten")
	public String beschwerdeBearbeiten(@ModelAttribute("beschwerde") BeschwerdeDto beschwerdeDto) {
		log.debug("Beschwerde {}/{} fertig bearbeitet", beschwerdeDto.getId(), beschwerdeDto.getVersion());
		try {
			vorgangService.beschwerdeAktualisiert(konvertierer.konvertiere(beschwerdeDto));
		} catch (LeeresFeldFehler e) {
			e.printStackTrace();
		} 
		return "redirect:/";
	}
	
	@GetMapping("/neueBeschwerde")
	public ModelAndView neueBeschwerdeForm() {
		ModelAndView model = new ModelAndView("neueBeschwerde");
		model.addObject("beschwerde", new BeschwerdeDto());
		return model;
	}
	
	@PostMapping("/neueBeschwerde")
	public String neueBeschwerde(@ModelAttribute("beschwerde") BeschwerdeDto beschwerdeDto, BindingResult result, Model model) {
		log.debug("neue Beschwerde: '{}' mit Prio {} wird gestellt", beschwerdeDto.getBeschwerde(), beschwerdeDto.getPrio());
		Beschwerde beschwerde = null;
		try {
			beschwerde = konvertierer.konvertiere(beschwerdeDto);
		} catch (LeeresFeldFehler e) {
			ValidationUtils.rejectIfEmptyOrWhitespace(result, e.feld(), "feld.nicht.leer");
		}
		if (result.hasErrors()) {
			log.error("es {} {} Fehler beim Anlegen einer Beschwerde aufgetreten.", result.getErrorCount()==1 ? "ist": "sind", result.getErrorCount());
			return "/neueBeschwerde";
		}
		vorgangService.heuteGestellt(beschwerde);
		log.debug("neue Beschwerde: {}", beschwerde);
		return "redirect:/";
	}
	
	
}