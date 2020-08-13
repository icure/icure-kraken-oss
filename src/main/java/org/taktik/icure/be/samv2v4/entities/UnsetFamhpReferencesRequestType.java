//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.05.12 at 04:36:37 PM CEST 
//


package org.taktik.icure.be.samv2v4.entities;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UnsetFamhpReferencesRequestType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UnsetFamhpReferencesRequestType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{urn:be:fgov:ehealth:samws:v2:refdata}UnsetFamhpMainEntities"/>
 *         &lt;group ref="{urn:be:fgov:ehealth:samws:v2:refdata}UnsetFamhpAdditionalEntities"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UnsetFamhpReferencesRequestType", namespace = "urn:be:fgov:ehealth:samws:v2:refdata", propOrder = {
    "atcClassification",
    "deliveryModus",
    "deliveryModusSpecification",
    "deviceType",
    "packagingClosure",
    "packagingMaterial",
    "packagingType",
    "pharmaceuticalForm",
    "routeOfAdministration",
    "substance",
    "standardForm",
    "standardRoute",
    "standardSubstance",
    "standardUnit"
})
public class UnsetFamhpReferencesRequestType {

    @XmlElement(name = "AtcClassification")
    protected List<AtcClassificationKeyType> atcClassification;
    @XmlElement(name = "DeliveryModus")
    protected List<DeliveryModusKeyType> deliveryModus;
    @XmlElement(name = "DeliveryModusSpecification")
    protected List<DeliveryModusSpecificationKeyType> deliveryModusSpecification;
    @XmlElement(name = "DeviceType")
    protected List<DeviceTypeKeyType> deviceType;
    @XmlElement(name = "PackagingClosure")
    protected List<PackagingClosureKeyType> packagingClosure;
    @XmlElement(name = "PackagingMaterial")
    protected List<PackagingMaterialKeyType> packagingMaterial;
    @XmlElement(name = "PackagingType")
    protected List<PackagingTypeKeyType> packagingType;
    @XmlElement(name = "PharmaceuticalForm")
    protected List<PharmaceuticalFormKeyType> pharmaceuticalForm;
    @XmlElement(name = "RouteOfAdministration")
    protected List<RouteOfAdministrationKeyType> routeOfAdministration;
    @XmlElement(name = "Substance")
    protected List<SubstanceKeyType> substance;
    @XmlElement(name = "StandardForm")
    protected List<StandardFormKeyFamhpType> standardForm;
    @XmlElement(name = "StandardRoute")
    protected List<StandardRouteKeyFamhpType> standardRoute;
    @XmlElement(name = "StandardSubstance")
    protected List<StandardSubstanceKeyFamhpType> standardSubstance;
    @XmlElement(name = "StandardUnit")
    protected List<StandardUnitKeyFamhpType> standardUnit;

    /**
     * Gets the value of the atcClassification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the atcClassification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAtcClassification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AtcClassificationKeyType }
     * 
     * 
     */
    public List<AtcClassificationKeyType> getAtcClassification() {
        if (atcClassification == null) {
            atcClassification = new ArrayList<AtcClassificationKeyType>();
        }
        return this.atcClassification;
    }

    /**
     * Gets the value of the deliveryModus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deliveryModus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeliveryModus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DeliveryModusKeyType }
     * 
     * 
     */
    public List<DeliveryModusKeyType> getDeliveryModus() {
        if (deliveryModus == null) {
            deliveryModus = new ArrayList<DeliveryModusKeyType>();
        }
        return this.deliveryModus;
    }

    /**
     * Gets the value of the deliveryModusSpecification property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deliveryModusSpecification property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeliveryModusSpecification().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DeliveryModusSpecificationKeyType }
     * 
     * 
     */
    public List<DeliveryModusSpecificationKeyType> getDeliveryModusSpecification() {
        if (deliveryModusSpecification == null) {
            deliveryModusSpecification = new ArrayList<DeliveryModusSpecificationKeyType>();
        }
        return this.deliveryModusSpecification;
    }

    /**
     * Gets the value of the deviceType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deviceType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeviceType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DeviceTypeKeyType }
     * 
     * 
     */
    public List<DeviceTypeKeyType> getDeviceType() {
        if (deviceType == null) {
            deviceType = new ArrayList<DeviceTypeKeyType>();
        }
        return this.deviceType;
    }

    /**
     * Gets the value of the packagingClosure property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the packagingClosure property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPackagingClosure().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PackagingClosureKeyType }
     * 
     * 
     */
    public List<PackagingClosureKeyType> getPackagingClosure() {
        if (packagingClosure == null) {
            packagingClosure = new ArrayList<PackagingClosureKeyType>();
        }
        return this.packagingClosure;
    }

    /**
     * Gets the value of the packagingMaterial property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the packagingMaterial property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPackagingMaterial().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PackagingMaterialKeyType }
     * 
     * 
     */
    public List<PackagingMaterialKeyType> getPackagingMaterial() {
        if (packagingMaterial == null) {
            packagingMaterial = new ArrayList<PackagingMaterialKeyType>();
        }
        return this.packagingMaterial;
    }

    /**
     * Gets the value of the packagingType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the packagingType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPackagingType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PackagingTypeKeyType }
     * 
     * 
     */
    public List<PackagingTypeKeyType> getPackagingType() {
        if (packagingType == null) {
            packagingType = new ArrayList<PackagingTypeKeyType>();
        }
        return this.packagingType;
    }

    /**
     * Gets the value of the pharmaceuticalForm property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pharmaceuticalForm property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPharmaceuticalForm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PharmaceuticalFormKeyType }
     * 
     * 
     */
    public List<PharmaceuticalFormKeyType> getPharmaceuticalForm() {
        if (pharmaceuticalForm == null) {
            pharmaceuticalForm = new ArrayList<PharmaceuticalFormKeyType>();
        }
        return this.pharmaceuticalForm;
    }

    /**
     * Gets the value of the routeOfAdministration property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the routeOfAdministration property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRouteOfAdministration().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RouteOfAdministrationKeyType }
     * 
     * 
     */
    public List<RouteOfAdministrationKeyType> getRouteOfAdministration() {
        if (routeOfAdministration == null) {
            routeOfAdministration = new ArrayList<RouteOfAdministrationKeyType>();
        }
        return this.routeOfAdministration;
    }

    /**
     * Gets the value of the substance property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the substance property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubstance().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubstanceKeyType }
     * 
     * 
     */
    public List<SubstanceKeyType> getSubstance() {
        if (substance == null) {
            substance = new ArrayList<SubstanceKeyType>();
        }
        return this.substance;
    }

    /**
     * Gets the value of the standardForm property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the standardForm property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStandardForm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StandardFormKeyFamhpType }
     * 
     * 
     */
    public List<StandardFormKeyFamhpType> getStandardForm() {
        if (standardForm == null) {
            standardForm = new ArrayList<StandardFormKeyFamhpType>();
        }
        return this.standardForm;
    }

    /**
     * Gets the value of the standardRoute property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the standardRoute property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStandardRoute().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StandardRouteKeyFamhpType }
     * 
     * 
     */
    public List<StandardRouteKeyFamhpType> getStandardRoute() {
        if (standardRoute == null) {
            standardRoute = new ArrayList<StandardRouteKeyFamhpType>();
        }
        return this.standardRoute;
    }

    /**
     * Gets the value of the standardSubstance property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the standardSubstance property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStandardSubstance().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StandardSubstanceKeyFamhpType }
     * 
     * 
     */
    public List<StandardSubstanceKeyFamhpType> getStandardSubstance() {
        if (standardSubstance == null) {
            standardSubstance = new ArrayList<StandardSubstanceKeyFamhpType>();
        }
        return this.standardSubstance;
    }

    /**
     * Gets the value of the standardUnit property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the standardUnit property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStandardUnit().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StandardUnitKeyFamhpType }
     * 
     * 
     */
    public List<StandardUnitKeyFamhpType> getStandardUnit() {
        if (standardUnit == null) {
            standardUnit = new ArrayList<StandardUnitKeyFamhpType>();
        }
        return this.standardUnit;
    }

}
