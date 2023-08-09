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
import de.woock.domain.Prio;
import de.woock.domain.Status;
import de.woock.domain.ausnahmen.LeeresFeldException;
import de.woock.infra.dto.AnfrageDto;
import de.woock.infra.service.AnfragenService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@CrossOrigin
@AllArgsConstructor
@Controller
public class HomeMVCController {
	
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
}