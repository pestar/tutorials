package net.starchl.springbootjpa.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import net.starchl.springbootjpa.domain.Adresse;
import net.starchl.springbootjpa.domain.Person;
import net.starchl.springbootjpa.service.AdressenService;

public class AdressenContollerTest {
	List<Adresse> adressen;
	AdressenService service = mock(AdressenService.class);
	protected Logger log = Logger.getLogger(getClass());
	protected MockMvc mvc;

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

	@Test
	public void testGetAdresseById() {
		when(service.findById(0)).thenReturn(adressen.get(0));
		AdressenController ac = new AdressenController();
		ac.setService(service);
		Adresse ad = ac.getById(0);
		assertEquals(ad, adressen.get(0));
	}

	@Test
	public void testGetAdresseByOrt() throws Exception {
		String uri = "/adresse/getort/{ort}";
		AdressenController ac = new AdressenController();
		ac.setService(service);
		mvc = MockMvcBuilders.standaloneSetup(ac).build();
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, "Graz").accept(MediaType.APPLICATION_JSON))
				.andReturn();
		int status = result.getResponse().getStatus();
		verify(service, times(1)).findByOrt("Graz");
		assertEquals("Fehler - erwarte HTTP status 200", 200, status);
	}
}
