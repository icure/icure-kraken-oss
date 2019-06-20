//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.14 at 03:49:57 PM CEST 
//


package org.taktik.icure.be.ehealth.dto.kmehr.v20150601.be.fgov.ehealth.standards.kmehr.schema.v1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import org.taktik.icure.be.ehealth.dto.kmehr.v20150601.be.fgov.ehealth.standards.kmehr.cd.v1.CDTRANSACTION;
import org.taktik.icure.be.ehealth.dto.kmehr.v20150601.be.fgov.ehealth.standards.kmehr.cd.v1.LnkType;
import org.taktik.icure.be.ehealth.dto.kmehr.v20150601.be.fgov.ehealth.standards.kmehr.dt.v1.TextType;
import org.taktik.icure.be.ehealth.dto.kmehr.v20150601.be.fgov.ehealth.standards.kmehr.id.v1.IDKMEHR;


/**
 * a transaction is a set of medical information validated by one healthcare professional at one given moment.
 * 
 * <p>Java class for transactionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="transactionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="confidentiality" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}confidentialityType" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.ehealth.fgov.be/standards/kmehr/id/v1}ID-KMEHR" maxOccurs="unbounded"/>
 *         &lt;element name="cd" type="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}CD-TRANSACTION" maxOccurs="unbounded"/>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}time"/>
 *         &lt;element name="author" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}authorType"/>
 *         &lt;element name="redactor" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}authorType" minOccurs="0"/>
 *         &lt;element name="iscomplete" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isvalidated" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="expirationdate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;choice maxOccurs="unbounded">
 *           &lt;element name="heading" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}headingType"/>
 *           &lt;element name="item" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}itemType"/>
 *           &lt;element name="text" type="{http://www.ehealth.fgov.be/standards/kmehr/dt/v1}textType"/>
 *           &lt;element name="text-with-layout" type="{http://www.ehealth.fgov.be/standards/kmehr/schema/v1}textWithLayoutType" maxOccurs="unbounded" minOccurs="0"/>
 *           &lt;element name="lnk" type="{http://www.ehealth.fgov.be/standards/kmehr/cd/v1}lnkType"/>
 *         &lt;/choice>
 *         &lt;element name="recorddatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transactionType", propOrder = {
    "confidentiality",
    "ids",
    "cds",
    "date",
    "time",
    "author",
    "redactor",
    "iscomplete",
    "isvalidated",
    "expirationdate",
    "headingsAndItemsAndTexts",
    "recorddatetime"
})
public class TransactionType
    implements Serializable
{

    private final static long serialVersionUID = 20150601L;
    protected ConfidentialityType confidentiality;
    @XmlElement(name = "id", required = true)
    protected List<IDKMEHR> ids;
    @XmlElement(name = "cd", required = true)
    protected List<CDTRANSACTION> cds;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;
    @XmlElement(required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar time;
    @XmlElement(required = true)
    protected AuthorType author;
    protected AuthorType redactor;
    protected boolean iscomplete;
    protected boolean isvalidated;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar expirationdate;
    @XmlElements({
        @XmlElement(name = "heading", type = HeadingType.class),
        @XmlElement(name = "item", type = ItemType.class),
        @XmlElement(name = "text", type = TextType.class),
        @XmlElement(name = "text-with-layout", type = TextWithLayoutType.class, nillable = true),
        @XmlElement(name = "lnk", type = LnkType.class)
    })
    protected List<Serializable> headingsAndItemsAndTexts;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar recorddatetime;

    /**
     * Gets the value of the confidentiality property.
     * 
     * @return
     *     possible object is
     *     {@link ConfidentialityType }
     *     
     */
    public ConfidentialityType getConfidentiality() {
        return confidentiality;
    }

    /**
     * Sets the value of the confidentiality property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfidentialityType }
     *     
     */
    public void setConfidentiality(ConfidentialityType value) {
        this.confidentiality = value;
    }

    /**
     * Gets the value of the ids property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ids property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IDKMEHR }
     * 
     * 
     */
    public List<IDKMEHR> getIds() {
        if (ids == null) {
            ids = new ArrayList<IDKMEHR>();
        }
        return this.ids;
    }

    /**
     * Gets the value of the cds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CDTRANSACTION }
     * 
     * 
     */
    public List<CDTRANSACTION> getCds() {
        if (cds == null) {
            cds = new ArrayList<CDTRANSACTION>();
        }
        return this.cds;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Gets the value of the time property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTime(XMLGregorianCalendar value) {
        this.time = value;
    }

    /**
     * Gets the value of the author property.
     * 
     * @return
     *     possible object is
     *     {@link AuthorType }
     *     
     */
    public AuthorType getAuthor() {
        return author;
    }

    /**
     * Sets the value of the author property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthorType }
     *     
     */
    public void setAuthor(AuthorType value) {
        this.author = value;
    }

    /**
     * Gets the value of the redactor property.
     * 
     * @return
     *     possible object is
     *     {@link AuthorType }
     *     
     */
    public AuthorType getRedactor() {
        return redactor;
    }

    /**
     * Sets the value of the redactor property.
     * 
     * @param value
     *     allowed object is
     *     {@link AuthorType }
     *     
     */
    public void setRedactor(AuthorType value) {
        this.redactor = value;
    }

    /**
     * Gets the value of the iscomplete property.
     * 
     */
    public boolean isIscomplete() {
        return iscomplete;
    }

    /**
     * Sets the value of the iscomplete property.
     * 
     */
    public void setIscomplete(boolean value) {
        this.iscomplete = value;
    }

    /**
     * Gets the value of the isvalidated property.
     * 
     */
    public boolean isIsvalidated() {
        return isvalidated;
    }

    /**
     * Sets the value of the isvalidated property.
     * 
     */
    public void setIsvalidated(boolean value) {
        this.isvalidated = value;
    }

    /**
     * Gets the value of the expirationdate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpirationdate() {
        return expirationdate;
    }

    /**
     * Sets the value of the expirationdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpirationdate(XMLGregorianCalendar value) {
        this.expirationdate = value;
    }

    /**
     * Gets the value of the headingsAndItemsAndTexts property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the headingsAndItemsAndTexts property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHeadingsAndItemsAndTexts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HeadingType }
     * {@link ItemType }
     * {@link TextType }
     * {@link TextWithLayoutType }
     * {@link LnkType }
     * 
     * 
     */
    public List<Serializable> getHeadingsAndItemsAndTexts() {
        if (headingsAndItemsAndTexts == null) {
            headingsAndItemsAndTexts = new ArrayList<Serializable>();
        }
        return this.headingsAndItemsAndTexts;
    }

    /**
     * Gets the value of the recorddatetime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRecorddatetime() {
        return recorddatetime;
    }

    /**
     * Sets the value of the recorddatetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRecorddatetime(XMLGregorianCalendar value) {
        this.recorddatetime = value;
    }

}
