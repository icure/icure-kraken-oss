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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FormalInterpretationDataType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="FormalInterpretationDataType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:samws:v2:export}DataPeriodType">
 *       &lt;sequence>
 *         &lt;group ref="{urn:be:fgov:ehealth:samws:v2:reimbursementlaw:submit}FormalInterpretationFields"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FormalInterpretationDataType", propOrder = {
    "rule"
})
public class FormalInterpretationDataType
    extends DataPeriodType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(name = "Rule", namespace = "urn:be:fgov:ehealth:samws:v2:reimbursementlaw:submit", required = true)
    protected String rule;

    /**
     * Gets the value of the rule property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getRule() {
        return rule;
    }

    /**
     * Sets the value of the rule property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setRule(String value) {
        this.rule = value;
    }

}
