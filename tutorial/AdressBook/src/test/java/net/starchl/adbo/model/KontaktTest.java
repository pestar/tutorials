package net.starchl.adbo.model;

import java.util.Date;

import org.junit.Test;

import junit.framework.TestCase;
import net.starchl.adbo.DbTool;
import net.starchl.adbo.KontaktDaoImpl;

public class KontaktTest extends TestCase {

	//private DbTool db = new DbTool();
	private KontaktDaoImpl db=new KontaktDaoImpl();
	Kontakt k;

	@Test
	public void testKontakt() {
		Date dt = new Date();
		k = new Kontakt("testvn", "testnn", "testti", "testst", "testor", "testem", "testte", dt, (short) 1000);
		assertTrue(k.getVorname() == "testvn");
		assertTrue(k.getNachname() == "testnn");
		assertTrue(k.getTitel() == "testti");
		assertTrue(k.getStrasse() == "testst");
		assertTrue(k.getOrt() == "testor");
		assertTrue(k.getEmail() == "testem");
		assertTrue(k.getTelefon() == "testte");
		assertTrue(k.getGeburtsdatum() == dt);
		assertTrue(k.getPlz() == 1000);
		assertTrue(db.insert(k));
		assertTrue(db.list().size() > 0);
		int id = -1;
		boolean found = false;
		for (Kontakt kl : db.list()) {
			if (kl.getVorname().equals("testvn")) {
				id = kl.getId();
				found = true;
				break;
			}
		}
		if (!found)
			fail("Nach insert Kontakt <" + k.getNachname() + "> nicht in Liste");
		assertTrue(id > 0);
		k.setId(id);
		k.setNachname("testnn1");
		assertTrue(k.update() == null);
		k = new Kontakt(id, "testvn", "testnn1", "testti", "testst", "testor", "testem", "testte", dt, (short) 1000);
		found = false;
		for (Kontakt kl : db.list()) {
			if (kl.getId() == id) {
				assertTrue("Kontakt # " + id + " nicht identisch: " + kl.getNachname() + " - " + k.getNachname(),
						kl.getNachname().equals(k.getNachname()));
				found = true;
				break;
			}
		}
		if (!found)
			fail("Nach Update Kontakt # " + id + " nicht gefunden");
		assertTrue(k.delete(k) == null);
		found = false;
		for (Kontakt kl : db.list()) {
			if (kl.getId() == id) {
				fail("Kontakt # " + id + " immer noch in DB");
			}
		}
	}

}
