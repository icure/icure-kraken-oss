//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.05.22 at 08:11:32 PM CEST
//


package org.taktik.icure.entities.samv2.entities;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.taktik.icure.be.ehealth.samws.v2.company.submit.CompanyLanguageType;
import org.taktik.icure.be.ehealth.samws.v2.company.submit.VatNrPerCountryType;


/**
 * <p>Java class for CompanyDataType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="CompanyDataType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:samws:v2:export}DataPeriodType">
 *       &lt;sequence>
 *         &lt;group ref="{urn:be:fgov:ehealth:samws:v2:company:submit}CompanyFields"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompanyDataType", propOrder = {
    "authorisationNr",
    "vatNr",
    "europeanNr",
    "denomination",
    "legalForm",
    "building",
    "streetName",
    "streetNum",
    "postbox",
    "postcode",
    "city",
    "countryCode",
    "phone",
    "language",
    "website"
})
public class CompanyDataType
    extends DataPeriodType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(name = "AuthorisationNr", namespace = "urn:be:fgov:ehealth:samws:v2:company:submit")
    protected String authorisationNr;
    @XmlElement(name = "VatNr", namespace = "urn:be:fgov:ehealth:samws:v2:company:submit")
    protected VatNrPerCountryType vatNr;
    @XmlElement(name = "EuropeanNr", namespace = "urn:be:fgov:ehealth:samws:v2:company:submit")
    protected String europeanNr;
    @XmlElement(name = "Denomination", namespace = "urn:be:fgov:ehealth:samws:v2:company:submit", required = true)
    protected String denomination;
    @XmlElement(name = "LegalForm", namespace = "urn:be:fgov:ehealth:samws:v2:company:submit")
    protected String legalForm;
    @XmlElement(name = "Building", namespace = "urn:be:fgov:ehealth:samws:v2:company:submit")
    protected String building;
    @XmlElement(name = "StreetName", namespace = "urn:be:fgov:ehealth:samws:v2:company:submit")
    protected String streetName;
    @XmlElement(name = "StreetNum", namespace = "urn:be:fgov:ehealth:samws:v2:company:submit")
    protected String streetNum;
    @XmlElement(name = "Postbox", namespace = "urn:be:fgov:ehealth:samws:v2:company:submit")
    protected String postbox;
    @XmlElement(name = "Postcode", namespace = "urn:be:fgov:ehealth:samws:v2:company:submit")
    protected String postcode;
    @XmlElement(name = "City", namespace = "urn:be:fgov:ehealth:samws:v2:company:submit")
    protected String city;
    @XmlElement(name = "CountryCode", namespace = "urn:be:fgov:ehealth:samws:v2:company:submit")
    protected String countryCode;
    @XmlElement(name = "Phone", namespace = "urn:be:fgov:ehealth:samws:v2:company:submit")
    protected String phone;
    @XmlElement(name = "Language", namespace = "urn:be:fgov:ehealth:samws:v2:company:submit", required = true)
    @XmlSchemaType(name = "string")
    protected CompanyLanguageType language;
    @XmlElement(name = "Website", namespace = "urn:be:fgov:ehealth:samws:v2:company:submit")
    protected String website;

    /**
     * Gets the value of the authorisationNr property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAuthorisationNr() {
        return authorisationNr;
    }

    /**
     * Sets the value of the authorisationNr property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAuthorisationNr(String value) {
        this.authorisationNr = value;
    }

    /**
     * Gets the value of the vatNr property.
     *
     * @return
     *     possible object is
     *     {@link VatNrPerCountryType }
     *
     */
    public VatNrPerCountryType getVatNr() {
        return vatNr;
    }

    /**
     * Sets the value of the vatNr property.
     *
     * @param value
     *     allowed object is
     *     {@link VatNrPerCountryType }
     *
     */
    public void setVatNr(VatNrPerCountryType value) {
        this.vatNr = value;
    }

    /**
     * Gets the value of the europeanNr property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getEuropeanNr() {
        return europeanNr;
    }

    /**
     * Sets the value of the europeanNr property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setEuropeanNr(String value) {
        this.europeanNr = value;
    }

    /**
     * Gets the value of the denomination property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDenomination() {
        return denomination;
    }

    /**
     * Sets the value of the denomination property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDenomination(String value) {
        this.denomination = value;
    }

    /**
     * Gets the value of the legalForm property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getLegalForm() {
        return legalForm;
    }

    /**
     * Sets the value of the legalForm property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setLegalForm(String value) {
        this.legalForm = value;
    }

    /**
     * Gets the value of the building property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBuilding() {
        return building;
    }

    /**
     * Sets the value of the building property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBuilding(String value) {
        this.building = value;
    }

    /**
     * Gets the value of the streetName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * Sets the value of the streetName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setStreetName(String value) {
        this.streetName = value;
    }

    /**
     * Gets the value of the streetNum property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getStreetNum() {
        return streetNum;
    }

    /**
     * Sets the value of the streetNum property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setStreetNum(String value) {
        this.streetNum = value;
    }

    /**
     * Gets the value of the postbox property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPostbox() {
        return postbox;
    }

    /**
     * Sets the value of the postbox property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPostbox(String value) {
        this.postbox = value;
    }

    /**
     * Gets the value of the postcode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Sets the value of the postcode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPostcode(String value) {
        this.postcode = value;
    }

    /**
     * Gets the value of the city property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the countryCode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the value of the countryCode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCountryCode(String value) {
        this.countryCode = value;
    }

    /**
     * Gets the value of the phone property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the value of the phone property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPhone(String value) {
        this.phone = value;
    }

    /**
     * Gets the value of the language property.
     *
     * @return
     *     possible object is
     *     {@link CompanyLanguageType }
     *
     */
    public CompanyLanguageType getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     *
     * @param value
     *     allowed object is
     *     {@link CompanyLanguageType }
     *
     */
    public void setLanguage(CompanyLanguageType value) {
        this.language = value;
    }

    /**
     * Gets the value of the website property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Sets the value of the website property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setWebsite(String value) {
        this.website = value;
    }

}
