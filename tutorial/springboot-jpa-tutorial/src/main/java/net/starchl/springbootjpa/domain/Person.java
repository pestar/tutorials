package net.starchl.springbootjpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private long id;

	private long adressId;

	public long getAdressId() {
		return adressId;
	}

	public void setAdressId(long adressId) {
		this.adressId = adressId;
	}

	@Column(length = 100)
	private String vorname;
	@Column(length = 100)
	private String nachname;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "adressId", insertable = false, updatable = false)
	private Adresse adresse;

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
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

	@Override
	public String toString() {
		return "Person [id=" + id + ", adressId=" + adressId + ", vorname=" + vorname + ", nachname=" + nachname
				+ ", adresse=" + adresse + "]";
	}
}
