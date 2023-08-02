package de.woock.infra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import de.woock.infra.service.VorgangService;
import lombok.AllArgsConstructor;

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
}