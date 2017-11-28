package net.starchl.springbootactivemq.demoactivemq.web;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.starchl.springbootactivemq.demoactivemq.jms.Sender;

@RestController
@RequestMapping("/input")
public class InputController {

	@Autowired
	private Sender sender;

	protected Logger log = Logger.getLogger(this.getClass().getName());

	@GetMapping("/{message}")
	private ResponseEntity<String> getInput(@PathVariable("message") final String input) {
		try {
			sender.send(input, false);
		} catch (Exception e) {
			log.severe("EEEEE Error! " + e);
			return new ResponseEntity<String>("Fehler!\n" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("Nachricht in Queue gesendet", HttpStatus.OK);
	}

}
