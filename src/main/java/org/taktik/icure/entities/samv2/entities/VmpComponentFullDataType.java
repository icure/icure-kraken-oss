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
import javax.xml.bind.annotation.XmlType;
import org.taktik.icure.be.ehealth.samws.v2.core.VmpComponentKeyType;


/**
 * <p>Java class for VmpComponentFullDataType complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="VmpComponentFullDataType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:be:fgov:ehealth:samws:v2:core}VmpComponentKeyType">
 *       &lt;sequence>
 *         &lt;element name="Data" type="{urn:be:fgov:ehealth:samws:v2:export}VmpComponentDataType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="VirtualIngredient" type="{urn:be:fgov:ehealth:samws:v2:export}VirtualIngredientFullDataType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VmpComponentFullDataType", propOrder = {
    "datas",
    "virtualIngredients"
})
public class VmpComponentFullDataType
    extends VmpComponentKeyType
    implements Serializable
{

    private final static long serialVersionUID = 2L;
    @XmlElement(name = "Data")
    protected List<VmpComponentDataType> datas;
    @XmlElement(name = "VirtualIngredient")
    protected List<VirtualIngredientFullDataType> virtualIngredients;

    /**
     * Gets the value of the datas property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the datas property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDatas().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VmpComponentDataType }
     *
     *
     */
    public List<VmpComponentDataType> getDatas() {
        if (datas == null) {
            datas = new ArrayList<VmpComponentDataType>();
        }
        return this.datas;
    }

    /**
     * Gets the value of the virtualIngredients property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the virtualIngredients property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVirtualIngredients().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VirtualIngredientFullDataType }
     *
     *
     */
    public List<VirtualIngredientFullDataType> getVirtualIngredients() {
        if (virtualIngredients == null) {
            virtualIngredients = new ArrayList<VirtualIngredientFullDataType>();
        }
        return this.virtualIngredients;
    }

}
