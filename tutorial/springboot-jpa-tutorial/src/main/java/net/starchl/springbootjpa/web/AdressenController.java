package net.starchl.springbootjpa.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
	public void addAdresse(@PathVariable String strasse, @PathVariable String plz, @PathVariable String ort,
			@PathVariable String land) {
		Adresse a = new Adresse();
		a.setLand(land);
		a.setOrt(ort);
		a.setStrasse(strasse);
		a.setPlz(plz);
		service.saveAdresse(a);
	}

	@RequestMapping(value = "/delete/{id}")
	public void removeAdresse(@PathVariable long id) {
		service.deleteAdresse(id);
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public void updateAdresse(@RequestBody Adresse adr) {
		service.saveAdresse(adr);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public void insertAdresse(@RequestBody Adresse adr) {
		service.saveAdresse(adr);
	}

}
