
package net.starchl.wetterdemo.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetCitiesByCountryResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getCitiesByCountryResult"
})
@XmlRootElement(name = "GetCitiesByCountryResponse")
public class GetCitiesByCountryResponse {

    @XmlElement(name = "GetCitiesByCountryResult")
    protected String getCitiesByCountryResult;

    /**
     * Ruft den Wert der getCitiesByCountryResult-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetCitiesByCountryResult() {
        return getCitiesByCountryResult;
    }

    /**
     * Legt den Wert der getCitiesByCountryResult-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetCitiesByCountryResult(String value) {
        this.getCitiesByCountryResult = value;
    }

}
