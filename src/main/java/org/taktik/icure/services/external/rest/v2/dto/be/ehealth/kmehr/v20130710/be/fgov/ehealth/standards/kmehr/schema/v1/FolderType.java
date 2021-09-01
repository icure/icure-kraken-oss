/*
 *  iCure Data Stack. Copyright (c) 2020 Taktik SA
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Affero General Public License as
 *     published by the Free Software Foundation, either version 3 of the
 *     License, or (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful, but
 *     WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *     Affero General Public License for more details.
 *
 *     You should have received a copy of the GNU Affero General Public
 *     License along with this program.  If not, see
 *     <https://www.gnu.org/licenses/>.
 */

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.06.14 at 03:50:02 PM CEST
//


package org.taktik.icure.services.external.rest.v2.dto.be.ehealth.kmehr.v20130710.be.fgov.ehealth.standards.kmehr.schema.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.taktik.icure.services.external.rest.v2.dto.be.ehealth.kmehr.v20130710.be.fgov.ehealth.standards.kmehr.cd.v1.LnkType;
import org.taktik.icure.services.external.rest.v2.dto.be.ehealth.kmehr.v20130710.be.fgov.ehealth.standards.kmehr.dt.v1.TextType;
import org.taktik.icure.services.external.rest.v2.dto.be.ehealth.kmehr.v20130710.be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR;


/**
 * contains the clinical information related to one patient
 *
 * <p>Java class for folderType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="folderType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="confidentiality" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}confidentialityType" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.ehealth.fgov.be/standards/kmehr/id/v1}ID-KMEHR" maxOccurs="unbounded"/>
 *         &lt;element name="patient" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}personType"/>
 *         &lt;element name="transaction" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}transactionType" maxOccurs="unbounded"/>
 *         &lt;element name="text" type="{http://www.ehealth.fgov.be/standards/kmehr/dt/v1}textType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="lnk" type="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}lnkType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "folderType", propOrder = {
    "confidentiality",
    "ids",
    "patient",
    "transactions",
    "texts",
    "lnks"
})
public class FolderType
    implements Serializable
{

    private final static long serialVersionUID = 20130710L;
    protected ConfidentialityType confidentiality;
    @XmlElement(name = "id", required = true)
    protected List<IDKMEHR> ids;
    @XmlElement(required = true)
    protected PersonType patient;
    @XmlElement(name = "transaction", required = true)
    protected List<TransactionType> transactions;
    @XmlElement(name = "text")
    protected List<TextType> texts;
    @XmlElement(name = "lnk")
    protected List<LnkType> lnks;

    /**
     * Gets the value of the confidentiality property.
     *
     * @return
     *     possible object is
     *     {@link ConfidentialityType }
     *
     */
    public ConfidentialityType getConfidentiality() {
        return confidentiality;
    }

    /**
     * Sets the value of the confidentiality property.
     *
     * @param value
     *     allowed object is
     *     {@link ConfidentialityType }
     *
     */
    public void setConfidentiality(ConfidentialityType value) {
        this.confidentiality = value;
    }

    /**
     * Gets the value of the ids property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ids property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIds().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IDKMEHR }
     *
     *
     */
    public List<IDKMEHR> getIds() {
        if (ids == null) {
            ids = new ArrayList<IDKMEHR>();
        }
        return this.ids;
    }

    /**
     * Gets the value of the patient property.
     *
     * @return
     *     possible object is
     *     {@link PersonType }
     *
     */
    public PersonType getPatient() {
        return patient;
    }

    /**
     * Sets the value of the patient property.
     *
     * @param value
     *     allowed object is
     *     {@link PersonType }
     *
     */
    public void setPatient(PersonType value) {
        this.patient = value;
    }

    /**
     * Gets the value of the transactions property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transactions property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransactions().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TransactionType }
     *
     *
     */
    public List<TransactionType> getTransactions() {
        if (transactions == null) {
            transactions = new ArrayList<TransactionType>();
        }
        return this.transactions;
    }

    /**
     * Gets the value of the texts property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the texts property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTexts().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TextType }
     *
     *
     */
    public List<TextType> getTexts() {
        if (texts == null) {
            texts = new ArrayList<TextType>();
        }
        return this.texts;
    }

    /**
     * Gets the value of the lnks property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lnks property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLnks().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LnkType }
     *
     *
     */
    public List<LnkType> getLnks() {
        if (lnks == null) {
            lnks = new ArrayList<LnkType>();
        }
        return this.lnks;
    }

}
