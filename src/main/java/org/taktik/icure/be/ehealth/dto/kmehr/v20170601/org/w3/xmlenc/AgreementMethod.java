//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.14 at 03:50:09 PM CEST 
//


package org.taktik.icure.be.ehealth.dto.kmehr.v20170601.org.w3.xmlenc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import org.taktik.icure.be.ehealth.dto.kmehr.v20170601.org.w3.xmldsig.KeyInfo;


/**
 * <p>Java class for AgreementMethodType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AgreementMethodType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="KA-Nonce" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;any namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="OriginatorKeyInfo" type="{http://www.w3.org/2000/09/xmldsig#}KeyInfoType" minOccurs="0"/>
 *         &lt;element name="RecipientKeyInfo" type="{http://www.w3.org/2000/09/xmldsig#}KeyInfoType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Algorithm" use="required" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AgreementMethodType", propOrder = {
    "content"
})
@XmlRootElement(name = "AgreementMethod")
public class AgreementMethod
    implements Serializable
{

    private final static long serialVersionUID = 20170601L;
    @XmlElementRefs({
        @XmlElementRef(name = "KA-Nonce", namespace = "http://www.w3.org/2001/04/xmlenc#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "OriginatorKeyInfo", namespace = "http://www.w3.org/2001/04/xmlenc#", type = JAXBElement.class, required = false),
        @XmlElementRef(name = "RecipientKeyInfo", namespace = "http://www.w3.org/2001/04/xmlenc#", type = JAXBElement.class, required = false)
    })
    @XmlMixed
    @XmlAnyElement(lax = true)
    protected List<Object> content;
    @XmlAttribute(name = "Algorithm", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String algorithm;

    /**
     * Gets the value of the content property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the content property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     * {@link JAXBElement }{@code <}{@link KeyInfo }{@code >}
     * {@link JAXBElement }{@code <}{@link KeyInfo }{@code >}
     * {@link Object }
     * {@link String }
     * 
     * 
     */
    public List<Object> getContent() {
        if (content == null) {
            content = new ArrayList<Object>();
        }
        return this.content;
    }

    /**
     * Gets the value of the algorithm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlgorithm() {
        return algorithm;
    }

    /**
     * Sets the value of the algorithm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlgorithm(String value) {
        this.algorithm = value;
    }

}
