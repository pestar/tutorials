package net.starchl.wetterdemo;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.logging.Logger;

import org.junit.Test;

import net.starchl.wetterdemo.service.WetterServiceImpl;

public class WetterSerciveTest {

	protected Logger log = Logger.getLogger(this.getClass().getName());

	private WetterServiceImpl wetterService = new WetterServiceImpl();

	@Test
	public void testGetStadt() {
		List<String> result = wetterService.getStadt("Austria");
		assertTrue("Erwarte Wert > 0", result.size() > 0);
		assertTrue("Erwarte Zeltweg in Liste", result.contains("Zeltweg"));
	}

	@Test
	public void testGetWetterAsString() {
		String result = wetterService.getWetterAsString("Zeltweg", "Austria");
		log.fine("Result getWetterAsString(): " + result);
		assertTrue("Erwarte Data Not Found", result.equalsIgnoreCase("Data Not Found"));
	}

}
