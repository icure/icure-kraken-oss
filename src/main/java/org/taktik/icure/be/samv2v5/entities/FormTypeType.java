/*
 * Copyright (c) 2020. Taktik SA, All rights reserved.
 */

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2020.10.15 at 03:32:18 PM CEST
//


package org.taktik.icure.be.samv2v5.entities;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FormTypeType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="FormTypeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NameId" type="{urn:be:fgov:ehealth:samws:v2:core}Number10Type"/>
 *       &lt;/sequence>
 *       &lt;attribute name="FormTypeId" use="required" type="{urn:be:fgov:ehealth:samws:v2:core}Number3Type" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FormTypeType", namespace = "urn:be:fgov:ehealth:samws:v2:refdata", propOrder = {
    "nameId"
})
public class FormTypeType {

    @XmlElement(name = "NameId", namespace = "urn:be:fgov:ehealth:samws:v2:refdata", required = true)
    protected BigDecimal nameId;
    @XmlAttribute(name = "FormTypeId", required = true)
    protected int formTypeId;

    /**
     * Gets the value of the nameId property.
     *
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *
     */
    public BigDecimal getNameId() {
        return nameId;
    }

    /**
     * Sets the value of the nameId property.
     *
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *
     */
    public void setNameId(BigDecimal value) {
        this.nameId = value;
    }

    /**
     * Gets the value of the formTypeId property.
     *
     */
    public int getFormTypeId() {
        return formTypeId;
    }

    /**
     * Sets the value of the formTypeId property.
     *
     */
    public void setFormTypeId(int value) {
        this.formTypeId = value;
    }

}
