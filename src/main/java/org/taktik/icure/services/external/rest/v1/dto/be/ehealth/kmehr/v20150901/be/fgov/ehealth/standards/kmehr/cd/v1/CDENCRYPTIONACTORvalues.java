//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.14 at 03:49:26 PM CEST 
//


package org.taktik.icure.services.external.rest.v1.dto.be.ehealth.kmehr.v20150901.be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CD-ENCRYPTION-ACTORvalues.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-ENCRYPTION-ACTORvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NIHII"/>
 *     &lt;enumeration value="NIHII-HOSPITAL"/>
 *     &lt;enumeration value="NIHII-PHARMACY"/>
 *     &lt;enumeration value="CBE"/>
 *     &lt;enumeration value="INSS"/>
 *     &lt;enumeration value="EHP"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CD-ENCRYPTION-ACTORvalues")
@XmlEnum
public enum CDENCRYPTIONACTORvalues {

    NIHII("NIHII"),
    @XmlEnumValue("NIHII-HOSPITAL")
    NIHII_HOSPITAL("NIHII-HOSPITAL"),
    @XmlEnumValue("NIHII-PHARMACY")
    NIHII_PHARMACY("NIHII-PHARMACY"),
    CBE("CBE"),
    INSS("INSS"),
    EHP("EHP");
    private final String value;

    CDENCRYPTIONACTORvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDENCRYPTIONACTORvalues fromValue(String v) {
        for (CDENCRYPTIONACTORvalues c: CDENCRYPTIONACTORvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
