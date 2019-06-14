//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.14 at 03:49:15 PM CEST 
//


package org.taktik.icure.be.ehealth.dto.kmehr.v20181201.be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CD-SEVERITYvalues.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-SEVERITYvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="abnormal"/>
 *     &lt;enumeration value="high"/>
 *     &lt;enumeration value="low"/>
 *     &lt;enumeration value="normal"/>
 *     &lt;enumeration value="resistent"/>
 *     &lt;enumeration value="susceptible"/>
 *     &lt;enumeration value="susceptibleintermediate"/>
 *     &lt;enumeration value="veryabnormal"/>
 *     &lt;enumeration value="veryhigh"/>
 *     &lt;enumeration value="verylow"/>
 *     &lt;enumeration value="extremelyhigh"/>
 *     &lt;enumeration value="extremelylow"/>
 *     &lt;enumeration value="verysusceptible"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CD-SEVERITYvalues")
@XmlEnum
public enum CDSEVERITYvalues {

    @XmlEnumValue("abnormal")
    ABNORMAL("abnormal"),
    @XmlEnumValue("high")
    HIGH("high"),
    @XmlEnumValue("low")
    LOW("low"),
    @XmlEnumValue("normal")
    NORMAL("normal"),
    @XmlEnumValue("resistent")
    RESISTENT("resistent"),
    @XmlEnumValue("susceptible")
    SUSCEPTIBLE("susceptible"),
    @XmlEnumValue("susceptibleintermediate")
    SUSCEPTIBLEINTERMEDIATE("susceptibleintermediate"),
    @XmlEnumValue("veryabnormal")
    VERYABNORMAL("veryabnormal"),
    @XmlEnumValue("veryhigh")
    VERYHIGH("veryhigh"),
    @XmlEnumValue("verylow")
    VERYLOW("verylow"),
    @XmlEnumValue("extremelyhigh")
    EXTREMELYHIGH("extremelyhigh"),
    @XmlEnumValue("extremelylow")
    EXTREMELYLOW("extremelylow"),
    @XmlEnumValue("verysusceptible")
    VERYSUSCEPTIBLE("verysusceptible");
    private final String value;

    CDSEVERITYvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDSEVERITYvalues fromValue(String v) {
        for (CDSEVERITYvalues c: CDSEVERITYvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
