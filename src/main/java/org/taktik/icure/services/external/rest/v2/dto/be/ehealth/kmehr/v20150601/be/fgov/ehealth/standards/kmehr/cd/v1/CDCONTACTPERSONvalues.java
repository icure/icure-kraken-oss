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
// Generated on: 2019.06.14 at 03:49:58 PM CEST
//


package org.taktik.icure.services.external.rest.v2.dto.be.ehealth.kmehr.v20150601.be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CD-CONTACT-PERSONvalues.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-CONTACT-PERSONvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="mother"/>
 *     &lt;enumeration value="father"/>
 *     &lt;enumeration value="child"/>
 *     &lt;enumeration value="familymember"/>
 *     &lt;enumeration value="spouse"/>
 *     &lt;enumeration value="husband"/>
 *     &lt;enumeration value="partner"/>
 *     &lt;enumeration value="other"/>
 *     &lt;enumeration value="brother"/>
 *     &lt;enumeration value="sister"/>
 *     &lt;enumeration value="brotherinlaw"/>
 *     &lt;enumeration value="tutor"/>
 *     &lt;enumeration value="notary"/>
 *     &lt;enumeration value="lawyer"/>
 *     &lt;enumeration value="employer"/>
 *     &lt;enumeration value="grandparent"/>
 *     &lt;enumeration value="son"/>
 *     &lt;enumeration value="daughter"/>
 *     &lt;enumeration value="grandchild"/>
 *     &lt;enumeration value="neighbour"/>
 *     &lt;enumeration value="stepson"/>
 *     &lt;enumeration value="stepdaughter"/>
 *     &lt;enumeration value="stepfather"/>
 *     &lt;enumeration value="stepmother"/>
 *     &lt;enumeration value="sisterinlaw"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 *
 */
@XmlType(name = "CD-CONTACT-PERSONvalues")
@XmlEnum
public enum CDCONTACTPERSONvalues {

    @XmlEnumValue("mother")
    MOTHER("mother"),
    @XmlEnumValue("father")
    FATHER("father"),
    @XmlEnumValue("child")
    CHILD("child"),
    @XmlEnumValue("familymember")
    FAMILYMEMBER("familymember"),
    @XmlEnumValue("spouse")
    SPOUSE("spouse"),
    @XmlEnumValue("husband")
    HUSBAND("husband"),
    @XmlEnumValue("partner")
    PARTNER("partner"),
    @XmlEnumValue("other")
    OTHER("other"),
    @XmlEnumValue("brother")
    BROTHER("brother"),
    @XmlEnumValue("sister")
    SISTER("sister"),
    @XmlEnumValue("brotherinlaw")
    BROTHERINLAW("brotherinlaw"),
    @XmlEnumValue("tutor")
    TUTOR("tutor"),
    @XmlEnumValue("notary")
    NOTARY("notary"),
    @XmlEnumValue("lawyer")
    LAWYER("lawyer"),
    @XmlEnumValue("employer")
    EMPLOYER("employer"),
    @XmlEnumValue("grandparent")
    GRANDPARENT("grandparent"),
    @XmlEnumValue("son")
    SON("son"),
    @XmlEnumValue("daughter")
    DAUGHTER("daughter"),
    @XmlEnumValue("grandchild")
    GRANDCHILD("grandchild"),
    @XmlEnumValue("neighbour")
    NEIGHBOUR("neighbour"),
    @XmlEnumValue("stepson")
    STEPSON("stepson"),
    @XmlEnumValue("stepdaughter")
    STEPDAUGHTER("stepdaughter"),
    @XmlEnumValue("stepfather")
    STEPFATHER("stepfather"),
    @XmlEnumValue("stepmother")
    STEPMOTHER("stepmother"),
    @XmlEnumValue("sisterinlaw")
    SISTERINLAW("sisterinlaw");
    private final String value;

    CDCONTACTPERSONvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDCONTACTPERSONvalues fromValue(String v) {
        for (CDCONTACTPERSONvalues c: CDCONTACTPERSONvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
