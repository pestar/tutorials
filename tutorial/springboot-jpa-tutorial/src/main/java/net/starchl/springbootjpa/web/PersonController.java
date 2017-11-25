package net.starchl.springbootjpa.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.starchl.springbootjpa.domain.Person;
import net.starchl.springbootjpa.service.PersonService;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

	@Autowired
	private PersonService service;

	public PersonService getService() {
		return service;
	}

	public void setService(PersonService service) {
		this.service = service;
	}

	@RequestMapping(value = "/")
	public List<Person> getAllPersons() {
		return service.findAll();
	}

	@RequestMapping(value = "/getname/{name}")
	public List<Person> getPersonsByName(@PathVariable String name) {
		return service.findByName(name);
	}

	@RequestMapping(value = "/getid/{id}")
	public Person getPersonById(@PathVariable long id) {
		return service.findById(id);
	}

	@RequestMapping(value = "/add/{vorname}/{nachname}/{adressId}")
	public void addPerson(@PathVariable String vorname, @PathVariable String nachname, @PathVariable long adressId) {
		Person p = new Person();
		p.setAdressId(adressId);
		p.setNachname(nachname);
		p.setVorname(vorname);
		service.savePerson(p);
	}

	@RequestMapping(value = "/delete/{id}")
	public void removePerson(@PathVariable long id) {
		service.deletePerson(id);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public void updatePerson(@RequestBody Person p) {
		service.savePerson(p);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insertPerson(@RequestBody Person p) {
		service.savePerson(p);
	}
}
