//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.05.22 at 08:11:32 PM CEST
//


package org.taktik.icure.be.ehealth.samws.v2.nonmedicinal.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RemoveNonMedicinalProductRequestType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="RemoveNonMedicinalProductRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NonMedicinalProduct" type="{urn:be:fgov:ehealth:samws:v2:nonmedicinal:common}RemoveNonMedicinalProductType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RemoveNonMedicinalProductRequestType", propOrder = {
    "nonMedicinalProducts"
})
@XmlRootElement(name = "RemoveNonMedicinalProductRequest")
public class RemoveNonMedicinalProductRequest
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(name = "NonMedicinalProduct", required = true)
    protected List<RemoveNonMedicinalProductType> nonMedicinalProducts;

    /**
     * Gets the value of the nonMedicinalProducts property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nonMedicinalProducts property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNonMedicinalProducts().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RemoveNonMedicinalProductType }
     *
     *
     */
    public List<RemoveNonMedicinalProductType> getNonMedicinalProducts() {
        if (nonMedicinalProducts == null) {
            nonMedicinalProducts = new ArrayList<RemoveNonMedicinalProductType>();
        }
        return this.nonMedicinalProducts;
    }

}
