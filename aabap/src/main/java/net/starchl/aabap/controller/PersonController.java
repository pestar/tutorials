package net.starchl.aabap.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.starchl.aabap.model.Person;
import net.starchl.aabap.service.PersonService;

@Controller
@RequestMapping("/personen")
public class PersonController {
	@Autowired
	PersonService service;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("personen/list");
		List<Person> lp = service.findAllPersons();
		mv.addObject("list", lp);
		return mv;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add() {
		ModelAndView mv = new ModelAndView("personen/form");
		Person p = new Person();
		mv.addObject("personenForm", p);
		return mv;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("personenForm") Person p) {
		// service.insertPerson(p);
		service.insertOrUpdatePserson(p);
		return new ModelAndView("redirect:/personen/list");
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public ModelAndView update(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("personen/form");
		Person p = service.findPersonById(id);
		mv.addObject("personenForm", p);
		return mv;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable("id") int id) {
		Person p = service.findPersonById(id);
		service.deletePerson(p);
		return new ModelAndView("redirect:/personen/list");
	}
}
