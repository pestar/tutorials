package net.starchl.wetterdemo.service;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import net.starchl.wetterdemo.ws.GlobalWeather;
import net.starchl.wetterdemo.ws.GlobalWeatherSoap;

@Service
public class WetterServiceImpl implements WetterService {
	Logger log = Logger.getLogger(this.getClass().getName());

	@Override
	public List<String> getStadt(String land) {
		GlobalWeather gw = new GlobalWeather();
		GlobalWeatherSoap gws = gw.getGlobalWeatherSoap();
		String payload = gws.getCitiesByCountry(land);
		log.fine(payload);
		try {
			return parsePayload(payload);
		} catch (Exception e) {
			log.severe(e.getLocalizedMessage());
			List<String> errorlist = new ArrayList<>();
			errorlist.add(e.getLocalizedMessage());
			return errorlist;
		}
	}

	@Override
	public String getWetterAsString(String stadt, String land) {
		GlobalWeather gw = new GlobalWeather();
		GlobalWeatherSoap gws = gw.getGlobalWeatherSoap();
		return gws.getWeather(stadt, land);
	}

	private List<String> parsePayload(String payload) throws ParserConfigurationException, SAXException, IOException {
		List<String> liste = new ArrayList<>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(payload));
		Document doc = db.parse(is);
		NodeList nodes = doc.getElementsByTagName("Table");
		for (int i = 0; i < nodes.getLength(); i++) {
			Element element = (Element) nodes.item(i);
			NodeList cityNl = element.getElementsByTagName("City");
			Element stadtEl = (Element) cityNl.item(0);
			liste.add(stadtEl.getTextContent());
		}
		return liste;
	}

}
