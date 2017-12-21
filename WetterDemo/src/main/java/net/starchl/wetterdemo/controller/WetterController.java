package net.starchl.wetterdemo.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.starchl.wetterdemo.model.StringModel;
import net.starchl.wetterdemo.service.WetterService;

@Controller
public class WetterController {

	private Logger log = Logger.getLogger(this.getClass().getName());
	@Autowired
	private WetterService service;
	private String aktLand = "Iceland";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(Model model) {
		log.info("%%%%%%%%%% Aufruf index()");
		model.addAttribute("welcomemessage", "Willkommen");
		model.addAttribute("welcomedate", new java.util.Date());
		model.addAttribute("searchland", new StringModel());
		ModelAndView mv = new ModelAndView("/wetter");
		List<String> stadtListe = service.getStadt("Iceland");
		mv.addObject("list", stadtListe);
		return mv;
	}

	@RequestMapping(value = "/staedte", method = RequestMethod.POST)
	public ModelAndView staedteByLand(@ModelAttribute("searchland") StringModel land) {
		log.info("%%%%%%%%%% Aufruf staedteByLand() für " + land.getString1());
		aktLand = land.getString1();
		ModelAndView mv = new ModelAndView("/wetter");
		List<String> stadtListe = service.getStadt(land.getString1());
		mv.addObject("welcomemessage", " Suche Städte für das <b>Land</b> " + land.getString1());
		mv.addObject("welcomedate", new java.util.Date());
		mv.addObject("list", stadtListe);
		return mv;
	}

	@RequestMapping(value = "/wetter/{searchstadt}", method = RequestMethod.GET)
	public ModelAndView getWetter(@PathVariable("searchstadt") String stadt) {
		log.info("%%%%%%%%%% Aufruf getWetter() für " + stadt + " in " + aktLand);
		StringModel land = new StringModel();
		land.setString1(aktLand);
		ModelAndView mv = new ModelAndView("/wetter");
		mv.addObject("welcomemessage",
				" Suche Wetter für das Land <b>" + land.getString1() + "</b> und Stadt <b>" + stadt + "</b>");
		mv.addObject("welcomedate", new java.util.Date());
		mv.addObject("searchland", land);
		String wetter = service.getWetterAsString(stadt, aktLand);
		log.info("%%%%%%%%%% Wettermeldung: " + wetter);
		mv.addObject("wetter", wetter);
		return mv;
	}

}
