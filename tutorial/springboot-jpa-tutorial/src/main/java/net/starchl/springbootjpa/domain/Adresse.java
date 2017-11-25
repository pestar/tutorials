package net.starchl.springbootjpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Adresse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(notes = "Von der Datenbank generierte Adressen ID")
	@Id
	@GeneratedValue
	private long id;

	@ApiModelProperty(notes = "Feld Strasse Länge 100")
	@Column(length = 100)
	private String strasse;

	@ApiModelProperty(notes = "Feld PLZ Länge 10")
	@Column(length = 10)
	private String plz;
	@ApiModelProperty(notes = "Feld Ort Länge 100")
	@Column(length = 100)
	private String ort;

	@ApiModelProperty(notes = "Feld Land Länge 100")
	@Column(length = 100)
	private String land;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getPlz() {
		return plz;
	}

	public void setPlz(String plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	@Override
	public String toString() {
		return "Adresse [id=" + id + ", strasse=" + strasse + ", plz=" + plz + ", ort=" + ort + ", land=" + land + "]";
	}
}
