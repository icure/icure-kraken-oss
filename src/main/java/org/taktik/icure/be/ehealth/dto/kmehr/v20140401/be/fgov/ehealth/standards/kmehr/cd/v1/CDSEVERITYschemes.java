//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.14 at 03:48:40 PM CEST 
//


package org.taktik.icure.be.ehealth.dto.kmehr.v20140401.be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CD-SEVERITYschemes.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-SEVERITYschemes">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CD-SEVERITY"/>
 *     &lt;enumeration value="CD-NYHA"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CD-SEVERITYschemes")
@XmlEnum
public enum CDSEVERITYschemes {

    @XmlEnumValue("CD-SEVERITY")
    CD_SEVERITY("CD-SEVERITY"),
    @XmlEnumValue("CD-NYHA")
    CD_NYHA("CD-NYHA");
    private final String value;

    CDSEVERITYschemes(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDSEVERITYschemes fromValue(String v) {
        for (CDSEVERITYschemes c: CDSEVERITYschemes.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
