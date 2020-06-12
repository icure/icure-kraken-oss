//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.05.12 at 04:36:37 PM CEST 
//


package org.taktik.icure.be.samv2v4.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ChangeAmpComponentBcpiType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChangeAmpComponentBcpiType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:samws:v2:actual:common}AmpComponentKeyType">
 *       &lt;sequence>
 *         &lt;group ref="{urn:be:fgov:ehealth:samws:v2:actual:common}AmpComponentBcpiFields"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{urn:be:fgov:ehealth:samws:v2:core}changeMetadata"/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChangeAmpComponentBcpiType", namespace = "urn:be:fgov:ehealth:samws:v2:actual:common", propOrder = {
    "dividable",
    "scored",
    "crushable",
    "containsAlcohol",
    "sugarFree",
    "modifiedReleaseType",
    "specificDrugDevice",
    "dimensions",
    "name",
    "note"
})
public class ChangeAmpComponentBcpiType
    extends AmpComponentKeyType
{

    @XmlElement(name = "Dividable")
    protected String dividable;
    @XmlElement(name = "Scored")
    protected String scored;
    @XmlElement(name = "Crushable")
    @XmlSchemaType(name = "string")
    protected CrushableType crushable;
    @XmlElement(name = "ContainsAlcohol")
    @XmlSchemaType(name = "string")
    protected ContainsAlcoholType containsAlcohol;
    @XmlElement(name = "SugarFree")
    protected Boolean sugarFree;
    @XmlElement(name = "ModifiedReleaseType")
    @XmlSchemaType(name = "integer")
    protected Integer modifiedReleaseType;
    @XmlElement(name = "SpecificDrugDevice")
    @XmlSchemaType(name = "integer")
    protected Integer specificDrugDevice;
    @XmlElement(name = "Dimensions")
    protected String dimensions;
    @XmlElement(name = "Name", required = true)
    protected Text255Type name;
    @XmlElement(name = "Note")
    protected TextType note;
    @XmlAttribute(name = "action", required = true)
    protected ChangeActionType action;
    @XmlAttribute(name = "from", required = true)
    protected XMLGregorianCalendar from;
    @XmlAttribute(name = "to")
    protected XMLGregorianCalendar to;

    /**
     * Gets the value of the dividable property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDividable() {
        return dividable;
    }

    /**
     * Sets the value of the dividable property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDividable(String value) {
        this.dividable = value;
    }

    /**
     * Gets the value of the scored property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScored() {
        return scored;
    }

    /**
     * Sets the value of the scored property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScored(String value) {
        this.scored = value;
    }

    /**
     * Gets the value of the crushable property.
     * 
     * @return
     *     possible object is
     *     {@link CrushableType }
     *     
     */
    public CrushableType getCrushable() {
        return crushable;
    }

    /**
     * Sets the value of the crushable property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrushableType }
     *     
     */
    public void setCrushable(CrushableType value) {
        this.crushable = value;
    }

    /**
     * Gets the value of the containsAlcohol property.
     * 
     * @return
     *     possible object is
     *     {@link ContainsAlcoholType }
     *     
     */
    public ContainsAlcoholType getContainsAlcohol() {
        return containsAlcohol;
    }

    /**
     * Sets the value of the containsAlcohol property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContainsAlcoholType }
     *     
     */
    public void setContainsAlcohol(ContainsAlcoholType value) {
        this.containsAlcohol = value;
    }

    /**
     * Gets the value of the sugarFree property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSugarFree() {
        return sugarFree;
    }

    /**
     * Sets the value of the sugarFree property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSugarFree(Boolean value) {
        this.sugarFree = value;
    }

    /**
     * Gets the value of the modifiedReleaseType property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getModifiedReleaseType() {
        return modifiedReleaseType;
    }

    /**
     * Sets the value of the modifiedReleaseType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setModifiedReleaseType(Integer value) {
        this.modifiedReleaseType = value;
    }

    /**
     * Gets the value of the specificDrugDevice property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSpecificDrugDevice() {
        return specificDrugDevice;
    }

    /**
     * Sets the value of the specificDrugDevice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSpecificDrugDevice(Integer value) {
        this.specificDrugDevice = value;
    }

    /**
     * Gets the value of the dimensions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDimensions() {
        return dimensions;
    }

    /**
     * Sets the value of the dimensions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDimensions(String value) {
        this.dimensions = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link Text255Type }
     *     
     */
    public Text255Type getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link Text255Type }
     *     
     */
    public void setName(Text255Type value) {
        this.name = value;
    }

    /**
     * Gets the value of the note property.
     * 
     * @return
     *     possible object is
     *     {@link TextType }
     *     
     */
    public TextType getNote() {
        return note;
    }

    /**
     * Sets the value of the note property.
     * 
     * @param value
     *     allowed object is
     *     {@link TextType }
     *     
     */
    public void setNote(TextType value) {
        this.note = value;
    }

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link ChangeActionType }
     *     
     */
    public ChangeActionType getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChangeActionType }
     *     
     */
    public void setAction(ChangeActionType value) {
        this.action = value;
    }

    /**
     * Gets the value of the from property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFrom() {
        return from;
    }

    /**
     * Sets the value of the from property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFrom(XMLGregorianCalendar value) {
        this.from = value;
    }

    /**
     * Gets the value of the to property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTo() {
        return to;
    }

    /**
     * Sets the value of the to property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTo(XMLGregorianCalendar value) {
        this.to = value;
    }

}
