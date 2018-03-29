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
 * <p>Java class for CD-CERTAINTYvalues.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-CERTAINTYvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="excluded"/>
 *     &lt;enumeration value="probable"/>
 *     &lt;enumeration value="proven"/>
 *     &lt;enumeration value="unprobable"/>
 *     &lt;enumeration value="undefined"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CD-CERTAINTYvalues")
@XmlEnum
public enum CDCERTAINTYvalues {

    @XmlEnumValue("excluded")
    EXCLUDED("excluded"),
    @XmlEnumValue("probable")
    PROBABLE("probable"),
    @XmlEnumValue("proven")
    PROVEN("proven"),
    @XmlEnumValue("unprobable")
    UNPROBABLE("unprobable"),
    @XmlEnumValue("undefined")
    UNDEFINED("undefined");
    private final String value;

    CDCERTAINTYvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDCERTAINTYvalues fromValue(String v) {
        for (CDCERTAINTYvalues c: CDCERTAINTYvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
