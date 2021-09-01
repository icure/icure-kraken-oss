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
// Generated on: 2019.06.14 at 03:48:53 PM CEST
//


package org.taktik.icure.services.external.rest.v2.dto.be.ehealth.kmehr.v20100901.be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CD-HCPARTYschemes.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-HCPARTYschemes">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CD-HCPARTY"/>
 *     &lt;enumeration value="CD-APPLICATION"/>
 *     &lt;enumeration value="CD-ENCRYPTION-ACTOR"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-HCPARTYschemes")
@XmlEnum
public enum CDHCPARTYschemes {

    @XmlEnumValue("CD-HCPARTY")
    CD_HCPARTY("CD-HCPARTY"),
    @XmlEnumValue("CD-APPLICATION")
    CD_APPLICATION("CD-APPLICATION"),
    @XmlEnumValue("CD-ENCRYPTION-ACTOR")
    CD_ENCRYPTION_ACTOR("CD-ENCRYPTION-ACTOR");
    private final String value;

    CDHCPARTYschemes(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDHCPARTYschemes fromValue(String v) {
        for (CDHCPARTYschemes c: CDHCPARTYschemes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
