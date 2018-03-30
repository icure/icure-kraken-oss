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
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2015.11.10 à 11:53:40 AM CET 
//


package org.taktik.icure.services.external.rest.v1.dto.be.ehealth.kmehr.v20150301.be.fgov.ehealth.standards.kmehr.cd.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CD-FED-COUNTRYvalues.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CD-FED-COUNTRYvalues">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="al"/>
 *     &lt;enumeration value="ad"/>
 *     &lt;enumeration value="de"/>
 *     &lt;enumeration value="at"/>
 *     &lt;enumeration value="bg"/>
 *     &lt;enumeration value="cy"/>
 *     &lt;enumeration value="dk"/>
 *     &lt;enumeration value="es"/>
 *     &lt;enumeration value="fi"/>
 *     &lt;enumeration value="fr"/>
 *     &lt;enumeration value="yt"/>
 *     &lt;enumeration value="re"/>
 *     &lt;enumeration value="pm"/>
 *     &lt;enumeration value="gp"/>
 *     &lt;enumeration value="mq"/>
 *     &lt;enumeration value="gf"/>
 *     &lt;enumeration value="nc"/>
 *     &lt;enumeration value="pf"/>
 *     &lt;enumeration value="wf"/>
 *     &lt;enumeration value="gb"/>
 *     &lt;enumeration value="bm"/>
 *     &lt;enumeration value="vg"/>
 *     &lt;enumeration value="tc"/>
 *     &lt;enumeration value="ai"/>
 *     &lt;enumeration value="ky"/>
 *     &lt;enumeration value="ms"/>
 *     &lt;enumeration value="fk"/>
 *     &lt;enumeration value="pn"/>
 *     &lt;enumeration value="lu"/>
 *     &lt;enumeration value="gr"/>
 *     &lt;enumeration value="hu"/>
 *     &lt;enumeration value="ie"/>
 *     &lt;enumeration value="is"/>
 *     &lt;enumeration value="li"/>
 *     &lt;enumeration value="mt"/>
 *     &lt;enumeration value="mc"/>
 *     &lt;enumeration value="no"/>
 *     &lt;enumeration value="pt"/>
 *     &lt;enumeration value="ro"/>
 *     &lt;enumeration value="sm"/>
 *     &lt;enumeration value="se"/>
 *     &lt;enumeration value="ch"/>
 *     &lt;enumeration value="it"/>
 *     &lt;enumeration value="nl"/>
 *     &lt;enumeration value="an"/>
 *     &lt;enumeration value="cs"/>
 *     &lt;enumeration value="va"/>
 *     &lt;enumeration value="lv"/>
 *     &lt;enumeration value="ee"/>
 *     &lt;enumeration value="lt"/>
 *     &lt;enumeration value="pl"/>
 *     &lt;enumeration value="cz"/>
 *     &lt;enumeration value="sk"/>
 *     &lt;enumeration value="by"/>
 *     &lt;enumeration value="ua"/>
 *     &lt;enumeration value="md"/>
 *     &lt;enumeration value="ru"/>
 *     &lt;enumeration value="hr"/>
 *     &lt;enumeration value="si"/>
 *     &lt;enumeration value="mk"/>
 *     &lt;enumeration value="ba"/>
 *     &lt;enumeration value="be"/>
 *     &lt;enumeration value="me"/>
 *     &lt;enumeration value="rs"/>
 *     &lt;enumeration value="xk"/>
 *     &lt;enumeration value="xi"/>
 *     &lt;enumeration value="xs"/>
 *     &lt;enumeration value="lk"/>
 *     &lt;enumeration value="tw"/>
 *     &lt;enumeration value="sg"/>
 *     &lt;enumeration value="kr"/>
 *     &lt;enumeration value="in"/>
 *     &lt;enumeration value="id"/>
 *     &lt;enumeration value="jp"/>
 *     &lt;enumeration value="la"/>
 *     &lt;enumeration value="kh"/>
 *     &lt;enumeration value="my"/>
 *     &lt;enumeration value="np"/>
 *     &lt;enumeration value="ph"/>
 *     &lt;enumeration value="cn"/>
 *     &lt;enumeration value="kp"/>
 *     &lt;enumeration value="vn"/>
 *     &lt;enumeration value="mn"/>
 *     &lt;enumeration value="mv"/>
 *     &lt;enumeration value="bt"/>
 *     &lt;enumeration value="bn"/>
 *     &lt;enumeration value="kz"/>
 *     &lt;enumeration value="kg"/>
 *     &lt;enumeration value="uz"/>
 *     &lt;enumeration value="tj"/>
 *     &lt;enumeration value="tm"/>
 *     &lt;enumeration value="th"/>
 *     &lt;enumeration value="mm"/>
 *     &lt;enumeration value="bd"/>
 *     &lt;enumeration value="am"/>
 *     &lt;enumeration value="az"/>
 *     &lt;enumeration value="af"/>
 *     &lt;enumeration value="sa"/>
 *     &lt;enumeration value="ge"/>
 *     &lt;enumeration value="iq"/>
 *     &lt;enumeration value="ir"/>
 *     &lt;enumeration value="il"/>
 *     &lt;enumeration value="jo"/>
 *     &lt;enumeration value="lb"/>
 *     &lt;enumeration value="pk"/>
 *     &lt;enumeration value="ae"/>
 *     &lt;enumeration value="sy"/>
 *     &lt;enumeration value="tr"/>
 *     &lt;enumeration value="kw"/>
 *     &lt;enumeration value="om"/>
 *     &lt;enumeration value="qa"/>
 *     &lt;enumeration value="bh"/>
 *     &lt;enumeration value="ye"/>
 *     &lt;enumeration value="tl"/>
 *     &lt;enumeration value="ps"/>
 *     &lt;enumeration value="ls"/>
 *     &lt;enumeration value="bw"/>
 *     &lt;enumeration value="bi"/>
 *     &lt;enumeration value="cm"/>
 *     &lt;enumeration value="cf"/>
 *     &lt;enumeration value="cd"/>
 *     &lt;enumeration value="cg"/>
 *     &lt;enumeration value="bf"/>
 *     &lt;enumeration value="ci"/>
 *     &lt;enumeration value="bj"/>
 *     &lt;enumeration value="et"/>
 *     &lt;enumeration value="ga"/>
 *     &lt;enumeration value="gm"/>
 *     &lt;enumeration value="gh"/>
 *     &lt;enumeration value="gn"/>
 *     &lt;enumeration value="mu"/>
 *     &lt;enumeration value="lr"/>
 *     &lt;enumeration value="ml"/>
 *     &lt;enumeration value="sn"/>
 *     &lt;enumeration value="ne"/>
 *     &lt;enumeration value="ng"/>
 *     &lt;enumeration value="ug"/>
 *     &lt;enumeration value="mg"/>
 *     &lt;enumeration value="za"/>
 *     &lt;enumeration value="rw"/>
 *     &lt;enumeration value="sl"/>
 *     &lt;enumeration value="so"/>
 *     &lt;enumeration value="sz"/>
 *     &lt;enumeration value="tz"/>
 *     &lt;enumeration value="td"/>
 *     &lt;enumeration value="tg"/>
 *     &lt;enumeration value="zm"/>
 *     &lt;enumeration value="ke"/>
 *     &lt;enumeration value="gq"/>
 *     &lt;enumeration value="gw"/>
 *     &lt;enumeration value="cv"/>
 *     &lt;enumeration value="mz"/>
 *     &lt;enumeration value="ao"/>
 *     &lt;enumeration value="sc"/>
 *     &lt;enumeration value="km"/>
 *     &lt;enumeration value="zw"/>
 *     &lt;enumeration value="dj"/>
 *     &lt;enumeration value="st"/>
 *     &lt;enumeration value="er"/>
 *     &lt;enumeration value="dz"/>
 *     &lt;enumeration value="eg"/>
 *     &lt;enumeration value="ly"/>
 *     &lt;enumeration value="ma"/>
 *     &lt;enumeration value="mr"/>
 *     &lt;enumeration value="sd"/>
 *     &lt;enumeration value="tn"/>
 *     &lt;enumeration value="mw"/>
 *     &lt;enumeration value="na"/>
 *     &lt;enumeration value="eh"/>
 *     &lt;enumeration value="sh"/>
 *     &lt;enumeration value="ca"/>
 *     &lt;enumeration value="us"/>
 *     &lt;enumeration value="vi"/>
 *     &lt;enumeration value="pr"/>
 *     &lt;enumeration value="gu"/>
 *     &lt;enumeration value="as"/>
 *     &lt;enumeration value="cr"/>
 *     &lt;enumeration value="cu"/>
 *     &lt;enumeration value="gt"/>
 *     &lt;enumeration value="hn"/>
 *     &lt;enumeration value="jm"/>
 *     &lt;enumeration value="mx"/>
 *     &lt;enumeration value="ni"/>
 *     &lt;enumeration value="pa"/>
 *     &lt;enumeration value="ht"/>
 *     &lt;enumeration value="do"/>
 *     &lt;enumeration value="sv"/>
 *     &lt;enumeration value="tt"/>
 *     &lt;enumeration value="bb"/>
 *     &lt;enumeration value="bs"/>
 *     &lt;enumeration value="gd"/>
 *     &lt;enumeration value="dm"/>
 *     &lt;enumeration value="lc"/>
 *     &lt;enumeration value="vc"/>
 *     &lt;enumeration value="bz"/>
 *     &lt;enumeration value="kn"/>
 *     &lt;enumeration value="ag"/>
 *     &lt;enumeration value="ar"/>
 *     &lt;enumeration value="bo"/>
 *     &lt;enumeration value="br"/>
 *     &lt;enumeration value="cl"/>
 *     &lt;enumeration value="co"/>
 *     &lt;enumeration value="ec"/>
 *     &lt;enumeration value="py"/>
 *     &lt;enumeration value="pe"/>
 *     &lt;enumeration value="uy"/>
 *     &lt;enumeration value="ve"/>
 *     &lt;enumeration value="gy"/>
 *     &lt;enumeration value="sr"/>
 *     &lt;enumeration value="mh"/>
 *     &lt;enumeration value="au"/>
 *     &lt;enumeration value="hm"/>
 *     &lt;enumeration value="nz"/>
 *     &lt;enumeration value="nu"/>
 *     &lt;enumeration value="tk"/>
 *     &lt;enumeration value="ck"/>
 *     &lt;enumeration value="ws"/>
 *     &lt;enumeration value="nr"/>
 *     &lt;enumeration value="to"/>
 *     &lt;enumeration value="fj"/>
 *     &lt;enumeration value="pg"/>
 *     &lt;enumeration value="tv"/>
 *     &lt;enumeration value="ki"/>
 *     &lt;enumeration value="sb"/>
 *     &lt;enumeration value="vu"/>
 *     &lt;enumeration value="pw"/>
 *     &lt;enumeration value="fm"/>
 *     &lt;enumeration value="mp"/>
 *     &lt;enumeration value="xr"/>
 *     &lt;enumeration value="xa"/>
 *     &lt;enumeration value="xe"/>
 *     &lt;enumeration value="gg"/>
 *     &lt;enumeration value="im"/>
 *     &lt;enumeration value="je"/>
 *     &lt;enumeration value="rs"/>
 *     &lt;enumeration value="aq"/>
 *     &lt;enumeration value="aw"/>
 *     &lt;enumeration value="ax"/>
 *     &lt;enumeration value="bq"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CD-FED-COUNTRYvalues")
@XmlEnum
public enum CDFEDCOUNTRYvalues {

    @XmlEnumValue("al")
    AL("al"),
    @XmlEnumValue("ad")
    AD("ad"),
    @XmlEnumValue("de")
    DE("de"),
    @XmlEnumValue("at")
    AT("at"),
    @XmlEnumValue("bg")
    BG("bg"),
    @XmlEnumValue("cy")
    CY("cy"),
    @XmlEnumValue("dk")
    DK("dk"),
    @XmlEnumValue("es")
    ES("es"),
    @XmlEnumValue("fi")
    FI("fi"),
    @XmlEnumValue("fr")
    FR("fr"),
    @XmlEnumValue("yt")
    YT("yt"),
    @XmlEnumValue("re")
    RE("re"),
    @XmlEnumValue("pm")
    PM("pm"),
    @XmlEnumValue("gp")
    GP("gp"),
    @XmlEnumValue("mq")
    MQ("mq"),
    @XmlEnumValue("gf")
    GF("gf"),
    @XmlEnumValue("nc")
    NC("nc"),
    @XmlEnumValue("pf")
    PF("pf"),
    @XmlEnumValue("wf")
    WF("wf"),
    @XmlEnumValue("gb")
    GB("gb"),
    @XmlEnumValue("bm")
    BM("bm"),
    @XmlEnumValue("vg")
    VG("vg"),
    @XmlEnumValue("tc")
    TC("tc"),
    @XmlEnumValue("ai")
    AI("ai"),
    @XmlEnumValue("ky")
    KY("ky"),
    @XmlEnumValue("ms")
    MS("ms"),
    @XmlEnumValue("fk")
    FK("fk"),
    @XmlEnumValue("pn")
    PN("pn"),
    @XmlEnumValue("lu")
    LU("lu"),
    @XmlEnumValue("gr")
    GR("gr"),
    @XmlEnumValue("hu")
    HU("hu"),
    @XmlEnumValue("ie")
    IE("ie"),
    @XmlEnumValue("is")
    IS("is"),
    @XmlEnumValue("li")
    LI("li"),
    @XmlEnumValue("mt")
    MT("mt"),
    @XmlEnumValue("mc")
    MC("mc"),
    @XmlEnumValue("no")
    NO("no"),
    @XmlEnumValue("pt")
    PT("pt"),
    @XmlEnumValue("ro")
    RO("ro"),
    @XmlEnumValue("sm")
    SM("sm"),
    @XmlEnumValue("se")
    SE("se"),
    @XmlEnumValue("ch")
    CH("ch"),
    @XmlEnumValue("it")
    IT("it"),
    @XmlEnumValue("nl")
    NL("nl"),
    @XmlEnumValue("an")
    AN("an"),
    @XmlEnumValue("cs")
    CS("cs"),
    @XmlEnumValue("va")
    VA("va"),
    @XmlEnumValue("lv")
    LV("lv"),
    @XmlEnumValue("ee")
    EE("ee"),
    @XmlEnumValue("lt")
    LT("lt"),
    @XmlEnumValue("pl")
    PL("pl"),
    @XmlEnumValue("cz")
    CZ("cz"),
    @XmlEnumValue("sk")
    SK("sk"),
    @XmlEnumValue("by")
    BY("by"),
    @XmlEnumValue("ua")
    UA("ua"),
    @XmlEnumValue("md")
    MD("md"),
    @XmlEnumValue("ru")
    RU("ru"),
    @XmlEnumValue("hr")
    HR("hr"),
    @XmlEnumValue("si")
    SI("si"),
    @XmlEnumValue("mk")
    MK("mk"),
    @XmlEnumValue("ba")
    BA("ba"),
    @XmlEnumValue("be")
    BE("be"),
    @XmlEnumValue("me")
    ME("me"),
    @XmlEnumValue("rs")
    RS("rs"),
    @XmlEnumValue("xk")
    XK("xk"),
    @XmlEnumValue("xi")
    XI("xi"),
    @XmlEnumValue("xs")
    XS("xs"),
    @XmlEnumValue("lk")
    LK("lk"),
    @XmlEnumValue("tw")
    TW("tw"),
    @XmlEnumValue("sg")
    SG("sg"),
    @XmlEnumValue("kr")
    KR("kr"),
    @XmlEnumValue("in")
    IN("in"),
    @XmlEnumValue("id")
    ID("id"),
    @XmlEnumValue("jp")
    JP("jp"),
    @XmlEnumValue("la")
    LA("la"),
    @XmlEnumValue("kh")
    KH("kh"),
    @XmlEnumValue("my")
    MY("my"),
    @XmlEnumValue("np")
    NP("np"),
    @XmlEnumValue("ph")
    PH("ph"),
    @XmlEnumValue("cn")
    CN("cn"),
    @XmlEnumValue("kp")
    KP("kp"),
    @XmlEnumValue("vn")
    VN("vn"),
    @XmlEnumValue("mn")
    MN("mn"),
    @XmlEnumValue("mv")
    MV("mv"),
    @XmlEnumValue("bt")
    BT("bt"),
    @XmlEnumValue("bn")
    BN("bn"),
    @XmlEnumValue("kz")
    KZ("kz"),
    @XmlEnumValue("kg")
    KG("kg"),
    @XmlEnumValue("uz")
    UZ("uz"),
    @XmlEnumValue("tj")
    TJ("tj"),
    @XmlEnumValue("tm")
    TM("tm"),
    @XmlEnumValue("th")
    TH("th"),
    @XmlEnumValue("mm")
    MM("mm"),
    @XmlEnumValue("bd")
    BD("bd"),
    @XmlEnumValue("am")
    AM("am"),
    @XmlEnumValue("az")
    AZ("az"),
    @XmlEnumValue("af")
    AF("af"),
    @XmlEnumValue("sa")
    SA("sa"),
    @XmlEnumValue("ge")
    GE("ge"),
    @XmlEnumValue("iq")
    IQ("iq"),
    @XmlEnumValue("ir")
    IR("ir"),
    @XmlEnumValue("il")
    IL("il"),
    @XmlEnumValue("jo")
    JO("jo"),
    @XmlEnumValue("lb")
    LB("lb"),
    @XmlEnumValue("pk")
    PK("pk"),
    @XmlEnumValue("ae")
    AE("ae"),
    @XmlEnumValue("sy")
    SY("sy"),
    @XmlEnumValue("tr")
    TR("tr"),
    @XmlEnumValue("kw")
    KW("kw"),
    @XmlEnumValue("om")
    OM("om"),
    @XmlEnumValue("qa")
    QA("qa"),
    @XmlEnumValue("bh")
    BH("bh"),
    @XmlEnumValue("ye")
    YE("ye"),
    @XmlEnumValue("tl")
    TL("tl"),
    @XmlEnumValue("ps")
    PS("ps"),
    @XmlEnumValue("ls")
    LS("ls"),
    @XmlEnumValue("bw")
    BW("bw"),
    @XmlEnumValue("bi")
    BI("bi"),
    @XmlEnumValue("cm")
    CM("cm"),
    @XmlEnumValue("cf")
    CF("cf"),
    @XmlEnumValue("cd")
    CD("cd"),
    @XmlEnumValue("cg")
    CG("cg"),
    @XmlEnumValue("bf")
    BF("bf"),
    @XmlEnumValue("ci")
    CI("ci"),
    @XmlEnumValue("bj")
    BJ("bj"),
    @XmlEnumValue("et")
    ET("et"),
    @XmlEnumValue("ga")
    GA("ga"),
    @XmlEnumValue("gm")
    GM("gm"),
    @XmlEnumValue("gh")
    GH("gh"),
    @XmlEnumValue("gn")
    GN("gn"),
    @XmlEnumValue("mu")
    MU("mu"),
    @XmlEnumValue("lr")
    LR("lr"),
    @XmlEnumValue("ml")
    ML("ml"),
    @XmlEnumValue("sn")
    SN("sn"),
    @XmlEnumValue("ne")
    NE("ne"),
    @XmlEnumValue("ng")
    NG("ng"),
    @XmlEnumValue("ug")
    UG("ug"),
    @XmlEnumValue("mg")
    MG("mg"),
    @XmlEnumValue("za")
    ZA("za"),
    @XmlEnumValue("rw")
    RW("rw"),
    @XmlEnumValue("sl")
    SL("sl"),
    @XmlEnumValue("so")
    SO("so"),
    @XmlEnumValue("sz")
    SZ("sz"),
    @XmlEnumValue("tz")
    TZ("tz"),
    @XmlEnumValue("td")
    TD("td"),
    @XmlEnumValue("tg")
    TG("tg"),
    @XmlEnumValue("zm")
    ZM("zm"),
    @XmlEnumValue("ke")
    KE("ke"),
    @XmlEnumValue("gq")
    GQ("gq"),
    @XmlEnumValue("gw")
    GW("gw"),
    @XmlEnumValue("cv")
    CV("cv"),
    @XmlEnumValue("mz")
    MZ("mz"),
    @XmlEnumValue("ao")
    AO("ao"),
    @XmlEnumValue("sc")
    SC("sc"),
    @XmlEnumValue("km")
    KM("km"),
    @XmlEnumValue("zw")
    ZW("zw"),
    @XmlEnumValue("dj")
    DJ("dj"),
    @XmlEnumValue("st")
    ST("st"),
    @XmlEnumValue("er")
    ER("er"),
    @XmlEnumValue("dz")
    DZ("dz"),
    @XmlEnumValue("eg")
    EG("eg"),
    @XmlEnumValue("ly")
    LY("ly"),
    @XmlEnumValue("ma")
    MA("ma"),
    @XmlEnumValue("mr")
    MR("mr"),
    @XmlEnumValue("sd")
    SD("sd"),
    @XmlEnumValue("tn")
    TN("tn"),
    @XmlEnumValue("mw")
    MW("mw"),
    @XmlEnumValue("na")
    NA("na"),
    @XmlEnumValue("eh")
    EH("eh"),
    @XmlEnumValue("sh")
    SH("sh"),
    @XmlEnumValue("ca")
    CA("ca"),
    @XmlEnumValue("us")
    US("us"),
    @XmlEnumValue("vi")
    VI("vi"),
    @XmlEnumValue("pr")
    PR("pr"),
    @XmlEnumValue("gu")
    GU("gu"),
    @XmlEnumValue("as")
    AS("as"),
    @XmlEnumValue("cr")
    CR("cr"),
    @XmlEnumValue("cu")
    CU("cu"),
    @XmlEnumValue("gt")
    GT("gt"),
    @XmlEnumValue("hn")
    HN("hn"),
    @XmlEnumValue("jm")
    JM("jm"),
    @XmlEnumValue("mx")
    MX("mx"),
    @XmlEnumValue("ni")
    NI("ni"),
    @XmlEnumValue("pa")
    PA("pa"),
    @XmlEnumValue("ht")
    HT("ht"),
    @XmlEnumValue("do")
    DO("do"),
    @XmlEnumValue("sv")
    SV("sv"),
    @XmlEnumValue("tt")
    TT("tt"),
    @XmlEnumValue("bb")
    BB("bb"),
    @XmlEnumValue("bs")
    BS("bs"),
    @XmlEnumValue("gd")
    GD("gd"),
    @XmlEnumValue("dm")
    DM("dm"),
    @XmlEnumValue("lc")
    LC("lc"),
    @XmlEnumValue("vc")
    VC("vc"),
    @XmlEnumValue("bz")
    BZ("bz"),
    @XmlEnumValue("kn")
    KN("kn"),
    @XmlEnumValue("ag")
    AG("ag"),
    @XmlEnumValue("ar")
    AR("ar"),
    @XmlEnumValue("bo")
    BO("bo"),
    @XmlEnumValue("br")
    BR("br"),
    @XmlEnumValue("cl")
    CL("cl"),
    @XmlEnumValue("co")
    CO("co"),
    @XmlEnumValue("ec")
    EC("ec"),
    @XmlEnumValue("py")
    PY("py"),
    @XmlEnumValue("pe")
    PE("pe"),
    @XmlEnumValue("uy")
    UY("uy"),
    @XmlEnumValue("ve")
    VE("ve"),
    @XmlEnumValue("gy")
    GY("gy"),
    @XmlEnumValue("sr")
    SR("sr"),
    @XmlEnumValue("mh")
    MH("mh"),
    @XmlEnumValue("au")
    AU("au"),
    @XmlEnumValue("hm")
    HM("hm"),
    @XmlEnumValue("nz")
    NZ("nz"),
    @XmlEnumValue("nu")
    NU("nu"),
    @XmlEnumValue("tk")
    TK("tk"),
    @XmlEnumValue("ck")
    CK("ck"),
    @XmlEnumValue("ws")
    WS("ws"),
    @XmlEnumValue("nr")
    NR("nr"),
    @XmlEnumValue("to")
    TO("to"),
    @XmlEnumValue("fj")
    FJ("fj"),
    @XmlEnumValue("pg")
    PG("pg"),
    @XmlEnumValue("tv")
    TV("tv"),
    @XmlEnumValue("ki")
    KI("ki"),
    @XmlEnumValue("sb")
    SB("sb"),
    @XmlEnumValue("vu")
    VU("vu"),
    @XmlEnumValue("pw")
    PW("pw"),
    @XmlEnumValue("fm")
    FM("fm"),
    @XmlEnumValue("mp")
    MP("mp"),
    @XmlEnumValue("xr")
    XR("xr"),
    @XmlEnumValue("xa")
    XA("xa"),
    @XmlEnumValue("xe")
    XE("xe"),
    @XmlEnumValue("gg")
    GG("gg"),
    @XmlEnumValue("im")
    IM("im"),
    @XmlEnumValue("je")
    JE("je"),
    @XmlEnumValue("aq")
    AQ("aq"),
    @XmlEnumValue("aw")
    AW("aw"),
    @XmlEnumValue("ax")
    AX("ax"),
    @XmlEnumValue("bq")
    BQ("bq");
    private final String value;

    CDFEDCOUNTRYvalues(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CDFEDCOUNTRYvalues fromValue(String v) {
        for (CDFEDCOUNTRYvalues c: CDFEDCOUNTRYvalues.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
