//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.05.22 at 08:11:32 PM CEST
//


package org.taktik.icure.be.ehealth.samws.v2.core;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for StrengthRangeType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="StrengthRangeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NumeratorRange" type="{urn:be:fgov:ehealth:samws:v2:core}RangeType"/>
 *         &lt;element name="Denominator" type="{urn:be:fgov:ehealth:samws:v2:core}QuantityType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StrengthRangeType", propOrder = {
    "numeratorRange",
    "denominator"
})
public class StrengthRangeType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(name = "NumeratorRange", required = true)
    protected RangeType numeratorRange;
    @XmlElement(name = "Denominator", required = true)
    protected QuantityType denominator;

    /**
     * Gets the value of the numeratorRange property.
     *
     * @return
     *     possible object is
     *     {@link RangeType }
     *
     */
    public RangeType getNumeratorRange() {
        return numeratorRange;
    }

    /**
     * Sets the value of the numeratorRange property.
     *
     * @param value
     *     allowed object is
     *     {@link RangeType }
     *
     */
    public void setNumeratorRange(RangeType value) {
        this.numeratorRange = value;
    }

    /**
     * Gets the value of the denominator property.
     *
     * @return
     *     possible object is
     *     {@link QuantityType }
     *
     */
    public QuantityType getDenominator() {
        return denominator;
    }

    /**
     * Sets the value of the denominator property.
     *
     * @param value
     *     allowed object is
     *     {@link QuantityType }
     *
     */
    public void setDenominator(QuantityType value) {
        this.denominator = value;
    }

}
