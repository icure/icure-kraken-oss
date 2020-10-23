//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.10.15 at 03:32:18 PM CEST 
//


package org.taktik.icure.be.samv2v5.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for VirtualIngredientType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VirtualIngredientType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:samws:v2:virtual:common}VirtualIngredientKeyType">
 *       &lt;sequence>
 *         &lt;group ref="{urn:be:fgov:ehealth:samws:v2:virtual:common}VirtualIngredientFields"/>
 *         &lt;group ref="{urn:be:fgov:ehealth:samws:v2:virtual:common}VirtualIngredientReferences"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VirtualIngredientType", namespace = "urn:be:fgov:ehealth:samws:v2:virtual:common", propOrder = {
    "type",
    "strength",
    "substanceCode"
})
@XmlSeeAlso({
    AddVirtualIngredientType.class
})
public class VirtualIngredientType
    extends VirtualIngredientKeyType
{

    @XmlElement(name = "Type", required = true)
    @XmlSchemaType(name = "string")
    protected IngredientTypeType type;
    @XmlElement(name = "Strength")
    protected StrengthRangeType strength;
    @XmlElement(name = "SubstanceCode", required = true)
    protected String substanceCode;

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link IngredientTypeType }
     *     
     */
    public IngredientTypeType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link IngredientTypeType }
     *     
     */
    public void setType(IngredientTypeType value) {
        this.type = value;
    }

    /**
     * Gets the value of the strength property.
     * 
     * @return
     *     possible object is
     *     {@link StrengthRangeType }
     *     
     */
    public StrengthRangeType getStrength() {
        return strength;
    }

    /**
     * Sets the value of the strength property.
     * 
     * @param value
     *     allowed object is
     *     {@link StrengthRangeType }
     *     
     */
    public void setStrength(StrengthRangeType value) {
        this.strength = value;
    }

    /**
     * Gets the value of the substanceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubstanceCode() {
        return substanceCode;
    }

    /**
     * Sets the value of the substanceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubstanceCode(String value) {
        this.substanceCode = value;
    }

}
