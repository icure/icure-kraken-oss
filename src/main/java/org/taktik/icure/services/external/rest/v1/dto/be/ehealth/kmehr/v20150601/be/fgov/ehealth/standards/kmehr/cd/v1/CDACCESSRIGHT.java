/*
 * Copyright (C) 2018 Taktik SA
 *
 * This file is part of iCureBackend.
 *
 * Foobar is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with iCureBackend.  If not, see <http://www.gnu.org/licenses/>.
 */

//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.11.10 à 11:53:43 AM CET 
//


package org.taktik.icure.services.external.rest.v1.dto.be.ehealth.kmehr.v20150601.be.fgov.ehealth.standards.kmehr.cd.v1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java pour CD-ACCESSRIGHT complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CD-ACCESSRIGHT">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.ehealth.fgov.be/standards/kmehr/cd/v1>CD-ACCESSRIGHTvalues">
 *       &lt;attribute name="S" use="required" type="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}CD-ACCESSRIGHTschemes" />
 *       &lt;attribute name="SV" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="SL" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="DN" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="L" type="{http://www.w3.org/2001/XMLSchema}language" default="en" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CD-ACCESSRIGHT", propOrder = {
    "value"
})
public class CDACCESSRIGHT
    implements Serializable
{

    private final static long serialVersionUID = 20150601L;
    @XmlValue
    protected CDACCESSRIGHTvalues value;
    @XmlAttribute(name = "S", required = true)
    protected CDACCESSRIGHTschemes s;
    @XmlAttribute(name = "SV", required = true)
    protected String sv;
    @XmlAttribute(name = "SL")
    protected String sl;
    @XmlAttribute(name = "DN")
    protected String dn;
    @XmlAttribute(name = "L")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "language")
    protected String l;

    /**
     * Obtient la valeur de la propriété value.
     * 
     * @return
     *     possible object is
     *     {@link CDACCESSRIGHTvalues }
     *     
     */
    public CDACCESSRIGHTvalues getValue() {
        return value;
    }

    /**
     * Définit la valeur de la propriété value.
     * 
     * @param value
     *     allowed object is
     *     {@link CDACCESSRIGHTvalues }
     *     
     */
    public void setValue(CDACCESSRIGHTvalues value) {
        this.value = value;
    }

    /**
     * Obtient la valeur de la propriété s.
     * 
     * @return
     *     possible object is
     *     {@link CDACCESSRIGHTschemes }
     *     
     */
    public CDACCESSRIGHTschemes getS() {
        return s;
    }

    /**
     * Définit la valeur de la propriété s.
     * 
     * @param value
     *     allowed object is
     *     {@link CDACCESSRIGHTschemes }
     *     
     */
    public void setS(CDACCESSRIGHTschemes value) {
        this.s = value;
    }

    /**
     * Obtient la valeur de la propriété sv.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSV() {
        return sv;
    }

    /**
     * Définit la valeur de la propriété sv.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSV(String value) {
        this.sv = value;
    }

    /**
     * Obtient la valeur de la propriété sl.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSL() {
        return sl;
    }

    /**
     * Définit la valeur de la propriété sl.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSL(String value) {
        this.sl = value;
    }

    /**
     * Obtient la valeur de la propriété dn.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDN() {
        return dn;
    }

    /**
     * Définit la valeur de la propriété dn.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDN(String value) {
        this.dn = value;
    }

    /**
     * Obtient la valeur de la propriété l.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getL() {
        if (l == null) {
            return "en";
        } else {
            return l;
        }
    }

    /**
     * Définit la valeur de la propriété l.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setL(String value) {
        this.l = value;
    }

}
