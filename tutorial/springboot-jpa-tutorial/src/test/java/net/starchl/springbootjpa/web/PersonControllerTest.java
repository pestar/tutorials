package net.starchl.springbootjpa.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import net.starchl.springbootjpa.domain.Adresse;
import net.starchl.springbootjpa.domain.Person;
import net.starchl.springbootjpa.service.PersonService;

@SpringBootTest
public class PersonControllerTest {

	private List<Person> personen;
	protected Logger log = Logger.getLogger(getClass());

	private PersonService service = mock(PersonService.class);
	protected MockMvc mvc;

	private TestRestTemplate template = new TestRestTemplate();

	@Before
	public void setup() {
		personen = new ArrayList<Person>();
		Person p1 = new Person();
		Person p2 = new Person();
		Person p3 = new Person();
		Adresse a = new Adresse();
		a.setId(0);
		a.setLand("land");
		a.setOrt("ort");
		a.setPlz("plz");
		a.setStrasse("strasse");
		p1.setAdresse(a);
		p2.setAdresse(a);
		p3.setAdresse(a);
		p1.setAdressId(0);
		p2.setAdressId(0);
		p3.setAdressId(0);
		p1.setId(0);
		p2.setId(1);
		p3.setId(3);
		p1.setNachname("nachname1");
		p2.setNachname("nachname2");
		p3.setNachname("nachname3");
		p1.setVorname("vorname1");
		p2.setVorname("vorname2");
		p3.setVorname("vorname3");
		personen.add(p1);
		personen.add(p2);
		personen.add(p3);
		log.info("TTTTTT setup done");
	}

	@Ignore
	@Test
	public void testRequest() throws Exception {
		HttpHeaders headers = template.getForEntity("http://localhost:8080/person/", String.class).getHeaders();
		assertNotNull(headers);
		log.info("TTTTT Headers: " + headers.toString());
		assertTrue(headers.size() > 0);
	}

	@Test
	public void testGetAllPersons() throws Exception {
		when(service.findAll()).thenReturn(personen);
		PersonController pc = new PersonController();
		pc.setService(service);
		assertEquals(3, pc.getAllPersons().size());
	}

	@Test
	public void testGetPersonsByName() throws Exception {
		String uri = "/person/getname/{name}";
		PersonController pc = new PersonController();
		pc.setService(service);
		mvc = MockMvcBuilders.standaloneSetup(pc).build();
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(uri, "nachname1").accept(MediaType.APPLICATION_JSON))
				.andReturn();
		int status = result.getResponse().getStatus();
		verify(service, times(1)).findByName("nachname1");
		assertEquals("Fehler - erwarte HTTP status 200", 200, status);
	}

	@Test
	public void testGetPersonById() {
		when(service.findById(0)).thenReturn(personen.get(0));
		PersonController pc = new PersonController();
		pc.setService(service);
		Person pt = pc.getPersonById(0);
		assertEquals(pt, personen.get(0));
		log.info("TTTTTT Person -> " + pt.getNachname());
	}

}
