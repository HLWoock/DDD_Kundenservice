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
public class HomeMVCController {
	
	private VorgangService vorgangService;

	@GetMapping({"/", "/index"})
    public ModelAndView home() {
    	ModelAndView model = new ModelAndView("index");
    	
    	int anfragen    = vorgangService.alleAnfragen().size();
    	int beschwerden = vorgangService.alleBeschwerden().size();
    	
    	model.addObject("anfragen", anfragen);
    	model.addObject("beschwerden", beschwerden);
        return model;
    }
	
	@GetMapping("/about")
	public String about() {
		return "about";
	}
}