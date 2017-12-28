package net.starchl.adbo.model;

import java.util.Date;
import java.util.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.starchl.adbo.DbTool;

@ManagedBean(name = "kontakt", eager = true)
//@RequestScoped
@SessionScoped
// @ViewScoped
public class Kontakt {
	private String vorname, nachname, titel, strasse, ort, email, telefon, meldung;
	private Date geburtsdatum;
	private short plz;
	private int id;
	private boolean edit;
	protected static Logger log = Logger.getLogger("net.starchl.adbo.model.Kontakt");
	private DbTool db;
	private Kontakt kontakt;

	public Kontakt() {
		db = new DbTool();
	}

	public Kontakt(String vorname, String nachname, String titel, String strasse, String ort, String email,
			String telefon, Date geburtsdatum, short plz) {
		this.vorname = vorname;
		this.nachname = nachname;
		this.titel = titel;
		this.strasse = strasse;
		this.ort = ort;
		this.email = email;
		this.telefon = telefon;
		this.geburtsdatum = geburtsdatum;
		this.plz = plz;
		db = new DbTool();
	}

	public Kontakt(int id, String vorname, String nachname, String titel, String strasse, String ort, String email,
			String telefon, Date geburtsdatum, short plz) {
		this.id = id;
		this.vorname = vorname;
		this.nachname = nachname;
		this.titel = titel;
		this.strasse = strasse;
		this.ort = ort;
		this.email = email;
		this.telefon = telefon;
		this.geburtsdatum = geburtsdatum;
		this.plz = plz;
		db = new DbTool();
	}

	public String getVorname() {
		return vorname;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public Date getGeburtsdatum() {
		return geburtsdatum;
	}

	public void setGeburtsdatum(Date geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	public short getPlz() {
		return plz;
	}

	public void setPlz(short plz) {
		this.plz = plz;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMeldung() {
		return meldung;
	}

	public void setMeldung(String meldung) {
		this.meldung = meldung;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	public Kontakt getKontakt() {
		return this.kontakt;
	}

	public String create() {
		edit = false;
		kontakt = new Kontakt(vorname, nachname, titel, strasse, ort, email, telefon, geburtsdatum, plz);
		log.info("Insert Kontakt: " + kontakt.getNachname());
		db.insert(kontakt);
		vorname = null;
		nachname = null;
		titel = null;
		strasse = null;
		ort = null;
		plz = 0;
		geburtsdatum = null;
		telefon = null;
		email = null;
		// return "home";
		return null;
	}

	public void editkontakt(Kontakt k) {
		edit = true;
		id = k.getId();
		vorname = k.getVorname();
		nachname = k.getNachname();
		titel = k.getTitel();
		geburtsdatum = k.getGeburtsdatum();
		strasse = k.getStrasse();
		ort = k.getOrt();
		plz = k.getPlz();
		email = k.getEmail();
		telefon = k.getTelefon();
		log.info("Edit Kontakt Id " + id);
	}

	// public String update(Kontakt kontakt) {
	// this.kontakt = kontakt;
	// edit = true;
	// log.info("Update Kontakt: " + this.kontakt.getNachname());
	// db.update(this.kontakt);
	// return null;
	// }

	public String update() {
		kontakt = new Kontakt(id, vorname, nachname, titel, strasse, ort, email, telefon, geburtsdatum, plz);
		log.info("Update Kontakt: " + this.kontakt.getNachname() + " Id: " + kontakt.getId());
		db.update(this.kontakt);
		vorname = null;
		nachname = null;
		titel = null;
		strasse = null;
		ort = null;
		plz = 0;
		geburtsdatum = null;
		telefon = null;
		email = null;
		edit = false;
		id=-1;
		return null;
	}

	public String delete(Kontakt kontakt) {
		log.info("Delete Kontakt: " + kontakt.getNachname());
		db.delete(kontakt.getId());
		return null;
	}

	public void reset() {
		edit = false;
		vorname = null;
		nachname = null;
		titel = null;
		strasse = null;
		ort = null;
		plz = 0;
		geburtsdatum = null;
		telefon = null;
		email = null;
		edit = false;
	}

}
