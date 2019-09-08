//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.05.22 at 08:11:32 PM CEST
//


package org.taktik.icure.be.ehealth.samws.v2.company.submit;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.taktik.icure.be.ehealth.samws.v2.consultation.ConsultCompanyType;
import org.taktik.icure.be.samv2.entities.CompanyFullDataType;


/**
 * <p>Java class for CompanyKeyType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="CompanyKeyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="actorNr" use="required" type="{urn:be:fgov:ehealth:samws:v2:core}CompanyActorNrType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompanyKeyType")
@XmlSeeAlso({
    CompanyFullDataType.class,
    RemoveCompanyType.class,
    CompanyType.class,
    ConsultCompanyType.class
})
public class CompanyKeyType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlAttribute(name = "actorNr", required = true)
    protected String actorNr;

    /**
     * Gets the value of the actorNr property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getActorNr() {
        return actorNr;
    }

    /**
     * Sets the value of the actorNr property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setActorNr(String value) {
        this.actorNr = value;
    }

}
