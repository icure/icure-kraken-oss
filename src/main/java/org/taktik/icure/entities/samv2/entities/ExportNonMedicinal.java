//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2019.05.22 at 08:11:32 PM CEST
//


package org.taktik.icure.entities.samv2.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ExportNonMedicinalType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ExportNonMedicinalType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:samws:v2:export}VersionedExportType">
 *       &lt;sequence>
 *         &lt;element name="NonMedicinalProduct" type="{urn:be:fgov:ehealth:samws:v2:export}NonMedicinalProductFullDataType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExportNonMedicinalType", propOrder = {
    "nonMedicinalProducts"
})
@XmlRootElement(name = "ExportNonMedicinal")
public class ExportNonMedicinal
    extends VersionedExportType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(name = "NonMedicinalProduct")
    protected List<NonMedicinalProductFullDataType> nonMedicinalProducts;

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
     * {@link NonMedicinalProductFullDataType }
     *
     *
     */
    public List<NonMedicinalProductFullDataType> getNonMedicinalProducts() {
        if (nonMedicinalProducts == null) {
            nonMedicinalProducts = new ArrayList<NonMedicinalProductFullDataType>();
        }
        return this.nonMedicinalProducts;
    }

}
