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
// Generated on: 2017.03.09 at 09:24:49 AM CET 
//


package org.taktik.icure.services.external.rest.v1.dto.be.ehealth.kmehr.v20160601.be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CD-INCAPACITYREASONvalues.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-INCAPACITYREASONvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="sickness"/>
 *     &lt;enumeration value="accident"/>
 *     &lt;enumeration value="family"/>
 *     &lt;enumeration value="other"/>
 *     &lt;enumeration value="careencounter"/>
 *     &lt;enumeration value="illness"/>
 *     &lt;enumeration value="hospitalisation"/>
 *     &lt;enumeration value="pregnancy"/>
 *     &lt;enumeration value="workaccident"/>
 *     &lt;enumeration value="occupationaldisease"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CD-INCAPACITYREASONvalues")
@XmlEnum
public enum CDINCAPACITYREASONvalues {

    @XmlEnumValue("sickness")
    SICKNESS("sickness"),
    @XmlEnumValue("accident")
    ACCIDENT("accident"),
    @XmlEnumValue("family")
    FAMILY("family"),
    @XmlEnumValue("other")
    OTHER("other"),
    @XmlEnumValue("careencounter")
    CAREENCOUNTER("careencounter"),
    @XmlEnumValue("illness")
    ILLNESS("illness"),
    @XmlEnumValue("hospitalisation")
    HOSPITALISATION("hospitalisation"),
    @XmlEnumValue("pregnancy")
    PREGNANCY("pregnancy"),
    @XmlEnumValue("workaccident")
    WORKACCIDENT("workaccident"),
    @XmlEnumValue("occupationaldisease")
    OCCUPATIONALDISEASE("occupationaldisease");
    private final String value;

    CDINCAPACITYREASONvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDINCAPACITYREASONvalues fromValue(String v) {
        for (CDINCAPACITYREASONvalues c: CDINCAPACITYREASONvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
