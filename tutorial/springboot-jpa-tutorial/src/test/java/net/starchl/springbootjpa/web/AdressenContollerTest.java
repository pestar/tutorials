package net.starchl.springbootjpa.web;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import net.starchl.springbootjpa.domain.Adresse;
import net.starchl.springbootjpa.service.AdressenService;

public class AdressenContollerTest {
	List<Adresse> adressen;
	AdressenService service = mock(AdressenService.class);
	protected Logger log = Logger.getLogger(getClass());

	@Before
	public void setUp() throws Exception {
		adressen = new ArrayList<>();
		Adresse adr = new Adresse();
		for (int i = 0; i < 5; i++) {
			adr.setId(i);
			adr.setLand("Land" + i);
			adr.setOrt("ort" + i);
			adr.setPlz("800" + i);
			adr.setStrasse("strasse" + i);
			adressen.add(adr);
			adr = new Adresse();
		}
		log.info("IIIII Setup done IIIII");
	}

	@Test
	public void testGetAll() {
		when(service.findAll()).thenReturn(adressen);
		AdressenController ac = new AdressenController();
		ac.setService(service);
		assertTrue(ac.getAll().size() == 5);
	}
}
