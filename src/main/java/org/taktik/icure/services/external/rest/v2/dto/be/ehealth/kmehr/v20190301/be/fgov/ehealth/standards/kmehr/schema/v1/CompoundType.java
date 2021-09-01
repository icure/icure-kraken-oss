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
// Generated on: 2019.06.14 at 03:49:43 PM CEST
//


package org.taktik.icure.services.external.rest.v2.dto.be.ehealth.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.schema.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.taktik.icure.services.external.rest.v2.dto.be.ehealth.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1.CDQUANTITYPREFIX;
import org.taktik.icure.services.external.rest.v2.dto.be.ehealth.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR;


/**
 * <p>Java class for compoundType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="compoundType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.ehealth.fgov.be/standards/kmehr/id/v1}ID-KMEHR" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="medicinalproduct" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}medicinalProductType"/>
 *           &lt;element name="substance" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}substanceType"/>
 *         &lt;/choice>
 *         &lt;element name="quantityprefix" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="cd" type="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}CD-QUANTITYPREFIX"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="quantity" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}quantityType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "compoundType", propOrder = {
    "ids",
    "substance",
    "medicinalproduct",
    "quantityprefix",
    "quantity"
})
public class CompoundType
    implements Serializable
{

    private final static long serialVersionUID = 20190301L;
    @XmlElement(name = "id")
    protected List<IDKMEHR> ids;
    protected SubstanceType substance;
    protected MedicinalProductType medicinalproduct;
    protected CompoundType.Quantityprefix quantityprefix;
    protected QuantityType quantity;

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
     * Gets the value of the substance property.
     *
     * @return
     *     possible object is
     *     {@link SubstanceType }
     *
     */
    public SubstanceType getSubstance() {
        return substance;
    }

    /**
     * Sets the value of the substance property.
     *
     * @param value
     *     allowed object is
     *     {@link SubstanceType }
     *
     */
    public void setSubstance(SubstanceType value) {
        this.substance = value;
    }

    /**
     * Gets the value of the medicinalproduct property.
     *
     * @return
     *     possible object is
     *     {@link MedicinalProductType }
     *
     */
    public MedicinalProductType getMedicinalproduct() {
        return medicinalproduct;
    }

    /**
     * Sets the value of the medicinalproduct property.
     *
     * @param value
     *     allowed object is
     *     {@link MedicinalProductType }
     *
     */
    public void setMedicinalproduct(MedicinalProductType value) {
        this.medicinalproduct = value;
    }

    /**
     * Gets the value of the quantityprefix property.
     *
     * @return
     *     possible object is
     *     {@link CompoundType.Quantityprefix }
     *
     */
    public CompoundType.Quantityprefix getQuantityprefix() {
        return quantityprefix;
    }

    /**
     * Sets the value of the quantityprefix property.
     *
     * @param value
     *     allowed object is
     *     {@link CompoundType.Quantityprefix }
     *
     */
    public void setQuantityprefix(CompoundType.Quantityprefix value) {
        this.quantityprefix = value;
    }

    /**
     * Gets the value of the quantity property.
     *
     * @return
     *     possible object is
     *     {@link QuantityType }
     *
     */
    public QuantityType getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     *
     * @param value
     *     allowed object is
     *     {@link QuantityType }
     *
     */
    public void setQuantity(QuantityType value) {
        this.quantity = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     *
     * <p>The following schema fragment specifies the expected content contained within this class.
     *
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="cd" type="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}CD-QUANTITYPREFIX"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "cd"
    })
    public static class Quantityprefix
        implements Serializable
    {

        private final static long serialVersionUID = 20190301L;
        @XmlElement(required = true)
        protected CDQUANTITYPREFIX cd;

        /**
         * Gets the value of the cd property.
         *
         * @return
         *     possible object is
         *     {@link CDQUANTITYPREFIX }
         *
         */
        public CDQUANTITYPREFIX getCd() {
            return cd;
        }

        /**
         * Sets the value of the cd property.
         *
         * @param value
         *     allowed object is
         *     {@link CDQUANTITYPREFIX }
         *
         */
        public void setCd(CDQUANTITYPREFIX value) {
            this.cd = value;
        }

    }

}
