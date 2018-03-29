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
// Généré le : 2015.11.10 à 11:53:46 AM CET 
//


package org.taktik.icure.services.external.rest.v1.dto.be.ehealth.kmehr.v20150901.be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CD-ACTS-NURSINGvalues.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-ACTS-NURSINGvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NMF001"/>
 *     &lt;enumeration value="NMF002"/>
 *     &lt;enumeration value="NMF011"/>
 *     &lt;enumeration value="NMF012"/>
 *     &lt;enumeration value="NMF013"/>
 *     &lt;enumeration value="NMF021"/>
 *     &lt;enumeration value="NMF022"/>
 *     &lt;enumeration value="NMF031"/>
 *     &lt;enumeration value="NMF040"/>
 *     &lt;enumeration value="NMF041"/>
 *     &lt;enumeration value="NMF042"/>
 *     &lt;enumeration value="NMF043"/>
 *     &lt;enumeration value="NMF044"/>
 *     &lt;enumeration value="NMF045"/>
 *     &lt;enumeration value="NMF046"/>
 *     &lt;enumeration value="NMF047"/>
 *     &lt;enumeration value="NMF051"/>
 *     &lt;enumeration value="NMF052"/>
 *     &lt;enumeration value="NMF061"/>
 *     &lt;enumeration value="NMF062"/>
 *     &lt;enumeration value="NMF071"/>
 *     &lt;enumeration value="NMF072"/>
 *     &lt;enumeration value="NMF073"/>
 *     &lt;enumeration value="NMF074"/>
 *     &lt;enumeration value="NMF081"/>
 *     &lt;enumeration value="NMF082"/>
 *     &lt;enumeration value="NMF091"/>
 *     &lt;enumeration value="NMF092"/>
 *     &lt;enumeration value="NMF093"/>
 *     &lt;enumeration value="NMF101"/>
 *     &lt;enumeration value="NMF111"/>
 *     &lt;enumeration value="NMF112"/>
 *     &lt;enumeration value="NMF113"/>
 *     &lt;enumeration value="NMF114"/>
 *     &lt;enumeration value="NMF115"/>
 *     &lt;enumeration value="NMF116"/>
 *     &lt;enumeration value="NMF121"/>
 *     &lt;enumeration value="NMF999"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CD-ACTS-NURSINGvalues")
@XmlEnum
public enum CDACTSNURSINGvalues {

    @XmlEnumValue("NMF001")
    NMF_001("NMF001"),
    @XmlEnumValue("NMF002")
    NMF_002("NMF002"),
    @XmlEnumValue("NMF011")
    NMF_011("NMF011"),
    @XmlEnumValue("NMF012")
    NMF_012("NMF012"),
    @XmlEnumValue("NMF013")
    NMF_013("NMF013"),
    @XmlEnumValue("NMF021")
    NMF_021("NMF021"),
    @XmlEnumValue("NMF022")
    NMF_022("NMF022"),
    @XmlEnumValue("NMF031")
    NMF_031("NMF031"),
    @XmlEnumValue("NMF040")
    NMF_040("NMF040"),
    @XmlEnumValue("NMF041")
    NMF_041("NMF041"),
    @XmlEnumValue("NMF042")
    NMF_042("NMF042"),
    @XmlEnumValue("NMF043")
    NMF_043("NMF043"),
    @XmlEnumValue("NMF044")
    NMF_044("NMF044"),
    @XmlEnumValue("NMF045")
    NMF_045("NMF045"),
    @XmlEnumValue("NMF046")
    NMF_046("NMF046"),
    @XmlEnumValue("NMF047")
    NMF_047("NMF047"),
    @XmlEnumValue("NMF051")
    NMF_051("NMF051"),
    @XmlEnumValue("NMF052")
    NMF_052("NMF052"),
    @XmlEnumValue("NMF061")
    NMF_061("NMF061"),
    @XmlEnumValue("NMF062")
    NMF_062("NMF062"),
    @XmlEnumValue("NMF071")
    NMF_071("NMF071"),
    @XmlEnumValue("NMF072")
    NMF_072("NMF072"),
    @XmlEnumValue("NMF073")
    NMF_073("NMF073"),
    @XmlEnumValue("NMF074")
    NMF_074("NMF074"),
    @XmlEnumValue("NMF081")
    NMF_081("NMF081"),
    @XmlEnumValue("NMF082")
    NMF_082("NMF082"),
    @XmlEnumValue("NMF091")
    NMF_091("NMF091"),
    @XmlEnumValue("NMF092")
    NMF_092("NMF092"),
    @XmlEnumValue("NMF093")
    NMF_093("NMF093"),
    @XmlEnumValue("NMF101")
    NMF_101("NMF101"),
    @XmlEnumValue("NMF111")
    NMF_111("NMF111"),
    @XmlEnumValue("NMF112")
    NMF_112("NMF112"),
    @XmlEnumValue("NMF113")
    NMF_113("NMF113"),
    @XmlEnumValue("NMF114")
    NMF_114("NMF114"),
    @XmlEnumValue("NMF115")
    NMF_115("NMF115"),
    @XmlEnumValue("NMF116")
    NMF_116("NMF116"),
    @XmlEnumValue("NMF121")
    NMF_121("NMF121"),
    @XmlEnumValue("NMF999")
    NMF_999("NMF999");
    private final String value;

    CDACTSNURSINGvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDACTSNURSINGvalues fromValue(String v) {
        for (CDACTSNURSINGvalues c: CDACTSNURSINGvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
