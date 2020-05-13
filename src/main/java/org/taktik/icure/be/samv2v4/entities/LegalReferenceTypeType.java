//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.05.12 at 04:36:37 PM CEST 
//


package org.taktik.icure.be.samv2v4.entities;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LegalReferenceTypeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LegalReferenceTypeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ARTICLE"/>
 *     &lt;enumeration value="CHAPTER"/>
 *     &lt;enumeration value="PARAGRAPH"/>
 *     &lt;enumeration value="SECTION"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LegalReferenceTypeType", namespace = "urn:be:fgov:ehealth:samws:v2:reimbursementlaw:submit")
@XmlEnum
public enum LegalReferenceTypeType {

    ARTICLE,
    CHAPTER,
    PARAGRAPH,
    SECTION;

    public String value() {
        return name();
    }

    public static LegalReferenceTypeType fromValue(String v) {
        return valueOf(v);
    }

}
