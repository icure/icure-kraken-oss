//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.14 at 03:50:09 PM CEST 
//


package org.taktik.icure.be.ehealth.dto.kmehr.v20170601.be.fgov.ehealth.standards.kmehr.schema.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.taktik.icure.be.ehealth.dto.kmehr.v20170601.be.fgov.ehealth.standards.kmehr.cd.v1.CDTIMEUNIT;


/**
 * to specify the time unit
 * 
 * <p>Java class for timeunitType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="timeunitType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cd" type="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}CD-TIMEUNIT"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "timeunitType", propOrder = {
    "cd"
})
public class TimeunitType
    implements Serializable
{

    private final static long serialVersionUID = 20170601L;
    @XmlElement(required = true)
    protected CDTIMEUNIT cd;

    /**
     * Gets the value of the cd property.
     * 
     * @return
     *     possible object is
     *     {@link CDTIMEUNIT }
     *     
     */
    public CDTIMEUNIT getCd() {
        return cd;
    }

    /**
     * Sets the value of the cd property.
     * 
     * @param value
     *     allowed object is
     *     {@link CDTIMEUNIT }
     *     
     */
    public void setCd(CDTIMEUNIT value) {
        this.cd = value;
    }

}
