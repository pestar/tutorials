package net.starchl.springbootjpa.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import net.starchl.springbootjpa.domain.Person;
import net.starchl.springbootjpa.service.PersonService;

@RestController
@RequestMapping(value = "/person")
@Api(value = "Personen Service")
public class PersonController {

	@Autowired
	private PersonService service;

	public PersonService getService() {
		return service;
	}

	public void setService(PersonService service) {
		this.service = service;
	}

	@ApiOperation(value = "Liste aller Personen", response = List.class)
	@ApiResponse(code = 200, message = "Liste erfolgreich übermittelt")
	@RequestMapping(value = "/")
	public List<Person> getAllPersons() {
		return service.findAll();
	}

	@ApiOperation(value = "Person suchen mit ID", response = Person.class)
	@RequestMapping(value = "/getname/{name}")
	public List<Person> getPersonsByName(@PathVariable String name) {
		return service.findByName(name);
	}

	@ApiOperation(value = "Person suchen mit ID", response = Person.class)
	@RequestMapping(value = "/getid/{id}")
	public Person getPersonById(@PathVariable long id) {
		return service.findById(id);
	}

	@ApiOperation(value = "Person einfügen GET - vorname/nachname/adressId")
	@RequestMapping(value = "/add/{vorname}/{nachname}/{adressId}")
	public ResponseEntity<String> addPerson(@PathVariable String vorname, @PathVariable String nachname,
			@PathVariable long adressId) {
		Person p = new Person();
		p.setAdressId(adressId);
		p.setNachname(nachname);
		p.setVorname(vorname);
		service.savePerson(p);
		return new ResponseEntity<String>("Person add OK", HttpStatus.OK);
	}

	@ApiOperation(value = "Person löschen")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removePerson(@PathVariable long id) {
		service.deletePerson(id);
		return new ResponseEntity<String>("Person delete OK", HttpStatus.OK);
	}

	@ApiOperation(value = "Person ändern")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<String> updatePerson(@RequestBody Person p) {
		service.savePerson(p);
		return new ResponseEntity<String>("Person update OK", HttpStatus.OK);
	}

	@ApiOperation(value = "Person einfügen")
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<String> insertPerson(@RequestBody Person p) {
		service.savePerson(p);
		return new ResponseEntity<String>("Person insert OK", HttpStatus.OK);
	}
}
