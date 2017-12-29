package net.starchl.adbo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.starchl.adbo.KontaktDaoImpl;
import net.starchl.adbo.model.Kontakt;

@ManagedBean(name = "kontaktctrl", eager = true)
// @RequestScoped
@SessionScoped

public class KontaktController {

	protected static Logger log = Logger.getLogger("net.starchl.adbo.controller.KontaktController");
	//private DbTool db = new DbTool();
	private KontaktDaoImpl db=new KontaktDaoImpl();
	private List<Kontakt> kontakte;
	private int anzahlProSeite = 0, vonIndex, bisIndex;

	public KontaktController() {
		initAnzahlProSeite();
	}

	private void initAnzahlProSeite() {
		log.info("###### INIT INDEXES #######");
		if (anzahlProSeite == 0) {
			anzahlProSeite = 5;
		}
		vonIndex = 0;
		bisIndex = anzahlProSeite;
	}

	public List<Kontakt> getKontakte() {
		kontakte = kontakte();
		return kontakte;
	}

	public void setKontakte(List<Kontakt> kontakte) {
		this.kontakte = kontakte;
	}

	public void incAnzProSeite() {
		log.fine("++++++++++++++");
		setAnzahlProSeite(++anzahlProSeite);
		log.fine("Anzahl/Seite = " + anzahlProSeite + "; vonIndex = " + vonIndex + "; bisIndex = " + bisIndex);
		setBisIndex(vonIndex + anzahlProSeite);
	}

	public void decAnzProSeite() {
		log.fine("--------------");
		if (anzahlProSeite >= 2) {
			setAnzahlProSeite(--anzahlProSeite);
			log.fine("Anzahl/Seite = " + anzahlProSeite + "; vonIndex = " + vonIndex + "; bisIndex = " + bisIndex);
			setBisIndex(vonIndex + anzahlProSeite);
		}
	}

	public void weiter() {
		setVonIndex(vonIndex + anzahlProSeite);
		setBisIndex(bisIndex + anzahlProSeite);
		log.info("vonIndex = " + vonIndex + "; bisIndex = " + bisIndex + "; Anzahl/Seite = " + anzahlProSeite);
	}

	public void vorher() {
		if (vonIndex - anzahlProSeite >= 0) {
			setVonIndex(vonIndex - anzahlProSeite);
			setBisIndex(bisIndex - anzahlProSeite);
		} else {
			setVonIndex(0);
			setBisIndex(anzahlProSeite);
		}
		log.info("vonIndex = " + vonIndex + "; bisIndex = " + bisIndex + "; Anzahl/Seite = " + anzahlProSeite);
	}

	public int getAnzahlProSeite() {
		return anzahlProSeite;
	}

	public void setAnzahlProSeite(int anzahlProSeite) {
		this.anzahlProSeite = anzahlProSeite;
	}

	public int getVonIndex() {
		return vonIndex;
	}

	public void setVonIndex(int vonIndex) {
		this.vonIndex = vonIndex;
	}

	public int getBisIndex() {
		return bisIndex;
	}

	public void setBisIndex(int bisIndex) {
		this.bisIndex = bisIndex;
	}

	private List<Kontakt> kontakte() {
		List<Kontakt> liste = new ArrayList<Kontakt>();
		liste = db.list();
		if (anzahlProSeite > liste.size()) {
			setAnzahlProSeite(liste.size());
		}
		if (bisIndex > liste.size()) {
			setBisIndex(liste.size());
		}
		if (vonIndex + anzahlProSeite > bisIndex) {
			// setVonIndex(bisIndex - anzahlProSeite);
			setBisIndex(liste.size());
			setVonIndex(bisIndex - anzahlProSeite);
		}
		if (vonIndex < 0) {
			setVonIndex(0);
		}
		log.info("Sub-Liste Länge = " + liste.size() + "; Anzahl/Seite = " + anzahlProSeite + "; vonIndex = " + vonIndex
				+ "; bisIndex = " + bisIndex);
		return liste.subList(vonIndex, bisIndex);
	}

}
