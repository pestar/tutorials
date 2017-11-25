package net.starchl.springbootjpa.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.starchl.springbootjpa.domain.Adresse;
import net.starchl.springbootjpa.service.AdressenService;

@RestController
@RequestMapping(value = "/adresse")
public class AdressenController {

	protected Logger log = Logger.getLogger(getClass());
	@Autowired
	private AdressenService service;

	public AdressenService getService() {
		return service;
	}

	public void setService(AdressenService service) {
		this.service = service;
	}

	@RequestMapping(value = "/")
	public List<Adresse> getAll() {
		return service.findAll();
	}

	@RequestMapping(value = "/getid/{id}")
	public Adresse getById(@PathVariable long id) {
		return service.findById(id);
	}

	@RequestMapping(value = "/getort/{ort}")
	public List<Adresse> getByOrt(@PathVariable String ort) {
		log.info("$$$$$ Abfrage nach Ort: " + ort);
		return service.findByOrt(ort);
	}

	@RequestMapping(value = "/add/{strasse}/{plz}/{ort}/{land}")
	public ResponseEntity<String> addAdresse(@PathVariable String strasse, @PathVariable String plz, @PathVariable String ort,
			@PathVariable String land) {
		Adresse a = new Adresse();
		a.setLand(land);
		a.setOrt(ort);
		a.setStrasse(strasse);
		a.setPlz(plz);
		service.saveAdresse(a);
		return new ResponseEntity<String>("Adresse add OK", HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeAdresse(@PathVariable long id) {
		service.deleteAdresse(id);
		return new ResponseEntity<String>("Adresse delete OK", HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<String> updateAdresse(@RequestBody Adresse adr) {
		service.saveAdresse(adr);
		return new ResponseEntity<String>("Adresse update OK", HttpStatus.OK);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<String> insertAdresse(@RequestBody Adresse adr) {
		service.saveAdresse(adr);
		return new ResponseEntity<String>("Adresse insert OK", HttpStatus.OK);
	}

}
