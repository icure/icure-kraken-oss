/*
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of iCureBackend.
 *
 * iCureBackend is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * iCureBackend is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with iCureBackend.  If not, see <http://www.gnu.org/licenses/>.
 */

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.03.09 at 09:24:53 AM CET 
//


package org.taktik.icure.services.external.rest.v1.dto.be.ehealth.kmehr.v20161201.org.w3.xmlenc;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.taktik.icure.services.external.rest.v1.dto.be.ehealth.kmehr.v20161201.org.w3.xmldsig.KeyInfo;


/**
 * <p>Java class for EncryptedType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EncryptedType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EncryptionMethod" type="{http://www.w3.org/2001/04/xmlenc#}EncryptionMethodType" minOccurs="0"/>
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}KeyInfo" minOccurs="0"/>
 *         &lt;element ref="{http://www.w3.org/2001/04/xmlenc#}CipherData"/>
 *         &lt;element ref="{http://www.w3.org/2001/04/xmlenc#}EncryptionProperties" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Id" type="{http://www.w3.org/2001/XMLSchema}ID" />
 *       &lt;attribute name="Type" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *       &lt;attribute name="MimeType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Encoding" type="{http://www.w3.org/2001/XMLSchema}anyURI" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EncryptedType", propOrder = {
    "encryptionMethod",
    "keyInfo",
    "cipherData",
    "encryptionProperties"
})
@XmlSeeAlso({
    EncryptedData.class,
    EncryptedKey.class
})
public abstract class EncryptedType
    implements Serializable
{

    private final static long serialVersionUID = 20161201L;
    @XmlElement(name = "EncryptionMethod")
    protected EncryptionMethodType encryptionMethod;
    @XmlElement(name = "KeyInfo", namespace = "http://www.w3.org/2000/09/xmldsig#")
    protected KeyInfo keyInfo;
    @XmlElement(name = "CipherData", required = true)
    protected CipherData cipherData;
    @XmlElement(name = "EncryptionProperties")
    protected EncryptionProperties encryptionProperties;
    @XmlAttribute(name = "Id")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;
    @XmlAttribute(name = "Type")
    @XmlSchemaType(name = "anyURI")
    protected String type;
    @XmlAttribute(name = "MimeType")
    protected String mimeType;
    @XmlAttribute(name = "Encoding")
    @XmlSchemaType(name = "anyURI")
    protected String encoding;

    /**
     * Gets the value of the encryptionMethod property.
     * 
     * @return
     *     possible object is
     *     {@link EncryptionMethodType }
     *     
     */
    public EncryptionMethodType getEncryptionMethod() {
        return encryptionMethod;
    }

    /**
     * Sets the value of the encryptionMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link EncryptionMethodType }
     *     
     */
    public void setEncryptionMethod(EncryptionMethodType value) {
        this.encryptionMethod = value;
    }

    /**
     * Gets the value of the keyInfo property.
     * 
     * @return
     *     possible object is
     *     {@link KeyInfo }
     *     
     */
    public KeyInfo getKeyInfo() {
        return keyInfo;
    }

    /**
     * Sets the value of the keyInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link KeyInfo }
     *     
     */
    public void setKeyInfo(KeyInfo value) {
        this.keyInfo = value;
    }

    /**
     * Gets the value of the cipherData property.
     * 
     * @return
     *     possible object is
     *     {@link CipherData }
     *     
     */
    public CipherData getCipherData() {
        return cipherData;
    }

    /**
     * Sets the value of the cipherData property.
     * 
     * @param value
     *     allowed object is
     *     {@link CipherData }
     *     
     */
    public void setCipherData(CipherData value) {
        this.cipherData = value;
    }

    /**
     * Gets the value of the encryptionProperties property.
     * 
     * @return
     *     possible object is
     *     {@link EncryptionProperties }
     *     
     */
    public EncryptionProperties getEncryptionProperties() {
        return encryptionProperties;
    }

    /**
     * Sets the value of the encryptionProperties property.
     * 
     * @param value
     *     allowed object is
     *     {@link EncryptionProperties }
     *     
     */
    public void setEncryptionProperties(EncryptionProperties value) {
        this.encryptionProperties = value;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the mimeType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * Sets the value of the mimeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMimeType(String value) {
        this.mimeType = value;
    }

    /**
     * Gets the value of the encoding property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncoding() {
        return encoding;
    }

    /**
     * Sets the value of the encoding property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncoding(String value) {
        this.encoding = value;
    }

}
