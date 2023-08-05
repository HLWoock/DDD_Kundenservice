package de.woock.infra.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@GetMapping("/anfrage/{anfrageId}/bearbeiten")
	public ModelAndView anfrageBearbeitenForm(@PathVariable Long anfrageId) {
		ModelAndView model = new ModelAndView("anfrageBearbeiten");
		List<String> statuus = Stream.of(Status.values())
                                     .map(Enum::name)
                                     .collect(Collectors.toList());
		model.addObject("prios", Prio.values());
		model.addObject("statuus", statuus); 
		model.addObject("anfrage", vorgangService.anfrage(anfrageId));
		return model;
	}
	
	@PostMapping("/anfrage/{anfrageId}/bearbeiten")
	public String anfrageBearbeiten(@ModelAttribute("anfrage") Anfrage anfrage, Model model) {
		log.debug("anfrage: {}, model {}", anfrage, model.getAttribute("anfrage"));
		return "redirect:/";
	}
	
	@GetMapping("/about")
	public String about() {
		return "about";
	}
}