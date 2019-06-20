//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.14 at 03:49:15 PM CEST 
//


package org.taktik.icure.be.ehealth.dto.kmehr.v20181201.be.fgov.ehealth.standards.kmehr.schema.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.taktik.icure.be.ehealth.dto.kmehr.v20181201.be.fgov.ehealth.standards.kmehr.cd.v1.CDACKNOWLEDGMENT;


/**
 * to specify the type of acknowledgment that is desired by the sender
 * 
 * <p>Java class for acknowledgmentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="acknowledgmentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cd" type="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}CD-ACKNOWLEDGMENT"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "acknowledgmentType", propOrder = {
    "cd"
})
public class AcknowledgmentType
    implements Serializable
{

    private final static long serialVersionUID = 20181201L;
    @XmlElement(required = true)
    protected CDACKNOWLEDGMENT cd;

    /**
     * Gets the value of the cd property.
     * 
     * @return
     *     possible object is
     *     {@link CDACKNOWLEDGMENT }
     *     
     */
    public CDACKNOWLEDGMENT getCd() {
        return cd;
    }

    /**
     * Sets the value of the cd property.
     * 
     * @param value
     *     allowed object is
     *     {@link CDACKNOWLEDGMENT }
     *     
     */
    public void setCd(CDACKNOWLEDGMENT value) {
        this.cd = value;
    }

}
