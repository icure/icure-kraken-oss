//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.14 at 03:49:41 PM CEST 
//


package org.taktik.icure.be.ehealth.dto.kmehr.v20190301.be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CD-BVT-STATUSvalues.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-BVT-STATUSvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="saved"/>
 *     &lt;enumeration value="submitted"/>
 *     &lt;enumeration value="rejected"/>
 *     &lt;enumeration value="inactive"/>
 *     &lt;enumeration value="published"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CD-BVT-STATUSvalues")
@XmlEnum
public enum CDBVTSTATUSvalues {

    @XmlEnumValue("saved")
    SAVED("saved"),
    @XmlEnumValue("submitted")
    SUBMITTED("submitted"),
    @XmlEnumValue("rejected")
    REJECTED("rejected"),
    @XmlEnumValue("inactive")
    INACTIVE("inactive"),
    @XmlEnumValue("published")
    PUBLISHED("published");
    private final String value;

    CDBVTSTATUSvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDBVTSTATUSvalues fromValue(String v) {
        for (CDBVTSTATUSvalues c: CDBVTSTATUSvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
