//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.05.22 at 08:11:32 PM CEST
//


package org.taktik.icure.be.ehealth.samws.v2.reimbursementlaw.submit;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import org.taktik.icure.be.ehealth.samws.v2.consultation.ConsultReimbursementConditionType;
import org.taktik.icure.entities.samv2.entities.ReimbursementConditionFullDataType;


/**
 * <p>Java class for ReimbursementConditionKeyType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ReimbursementConditionKeyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="legalTextRelativePath" use="required" type="{urn:be:fgov:ehealth:samws:v2:reimbursementlaw:submit}LegalTextPathType" />
 *       &lt;attribute name="key" use="required" type="{urn:be:fgov:ehealth:samws:v2:core}String15Type" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReimbursementConditionKeyType")
@XmlSeeAlso({
    ReimbursementConditionFullDataType.class,
    ReimbursementConditionType.class,
    ConsultReimbursementConditionType.class
})
public class ReimbursementConditionKeyType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlAttribute(name = "legalTextRelativePath", required = true)
    protected String legalTextRelativePath;
    @XmlAttribute(name = "key", required = true)
    protected String key;

    /**
     * Gets the value of the legalTextRelativePath property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getLegalTextRelativePath() {
        return legalTextRelativePath;
    }

    /**
     * Sets the value of the legalTextRelativePath property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setLegalTextRelativePath(String value) {
        this.legalTextRelativePath = value;
    }

    /**
     * Gets the value of the key property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setKey(String value) {
        this.key = value;
    }

}
