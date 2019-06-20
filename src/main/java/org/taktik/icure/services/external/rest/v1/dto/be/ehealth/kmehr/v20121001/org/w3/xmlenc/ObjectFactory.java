//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.14 at 03:48:45 PM CEST 
//


package org.taktik.icure.services.external.rest.v1.dto.be.ehealth.kmehr.v20121001.org.w3.xmlenc;

import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import org.taktik.icure.services.external.rest.v1.dto.be.ehealth.kmehr.v20121001.org.w3.xmldsig.KeyInfo;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.taktik.icure.services.external.rest.v1.dto.be.ehealth.kmehr.v20121001.org.w3.xmlenc package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ReferenceListDataReference_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "DataReference");
    private final static QName _ReferenceListKeyReference_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "KeyReference");
    private final static QName _EncryptionMethodTypeKeySize_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "KeySize");
    private final static QName _EncryptionMethodTypeOAEPparams_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "OAEPparams");
    private final static QName _AgreementMethodKANonce_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "KA-Nonce");
    private final static QName _AgreementMethodOriginatorKeyInfo_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "OriginatorKeyInfo");
    private final static QName _AgreementMethodRecipientKeyInfo_QNAME = new QName("http://www.w3.org/2001/04/xmlenc#", "RecipientKeyInfo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.taktik.icure.services.external.rest.v1.dto.be.ehealth.kmehr.v20121001.org.w3.xmlenc
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EncryptionProperty }
     * 
     */
    public EncryptionProperty createEncryptionProperty() {
        return new EncryptionProperty();
    }

    /**
     * Create an instance of {@link CipherData }
     * 
     */
    public CipherData createCipherData() {
        return new CipherData();
    }

    /**
     * Create an instance of {@link CipherReference }
     * 
     */
    public CipherReference createCipherReference() {
        return new CipherReference();
    }

    /**
     * Create an instance of {@link TransformsType }
     * 
     */
    public TransformsType createTransformsType() {
        return new TransformsType();
    }

    /**
     * Create an instance of {@link AgreementMethod }
     * 
     */
    public AgreementMethod createAgreementMethod() {
        return new AgreementMethod();
    }

    /**
     * Create an instance of {@link EncryptedKey }
     * 
     */
    public EncryptedKey createEncryptedKey() {
        return new EncryptedKey();
    }

    /**
     * Create an instance of {@link EncryptionMethodType }
     * 
     */
    public EncryptionMethodType createEncryptionMethodType() {
        return new EncryptionMethodType();
    }

    /**
     * Create an instance of {@link EncryptionProperties }
     * 
     */
    public EncryptionProperties createEncryptionProperties() {
        return new EncryptionProperties();
    }

    /**
     * Create an instance of {@link ReferenceList }
     * 
     */
    public ReferenceList createReferenceList() {
        return new ReferenceList();
    }

    /**
     * Create an instance of {@link ReferenceType }
     * 
     */
    public ReferenceType createReferenceType() {
        return new ReferenceType();
    }

    /**
     * Create an instance of {@link EncryptedData }
     * 
     */
    public EncryptedData createEncryptedData() {
        return new EncryptedData();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReferenceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2001/04/xmlenc#", name = "DataReference", scope = ReferenceList.class)
    public JAXBElement<ReferenceType> createReferenceListDataReference(ReferenceType value) {
        return new JAXBElement<ReferenceType>(_ReferenceListDataReference_QNAME, ReferenceType.class, ReferenceList.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReferenceType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2001/04/xmlenc#", name = "KeyReference", scope = ReferenceList.class)
    public JAXBElement<ReferenceType> createReferenceListKeyReference(ReferenceType value) {
        return new JAXBElement<ReferenceType>(_ReferenceListKeyReference_QNAME, ReferenceType.class, ReferenceList.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigInteger }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2001/04/xmlenc#", name = "KeySize", scope = EncryptionMethodType.class)
    public JAXBElement<BigInteger> createEncryptionMethodTypeKeySize(BigInteger value) {
        return new JAXBElement<BigInteger>(_EncryptionMethodTypeKeySize_QNAME, BigInteger.class, EncryptionMethodType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2001/04/xmlenc#", name = "OAEPparams", scope = EncryptionMethodType.class)
    public JAXBElement<byte[]> createEncryptionMethodTypeOAEPparams(byte[] value) {
        return new JAXBElement<byte[]>(_EncryptionMethodTypeOAEPparams_QNAME, byte[].class, EncryptionMethodType.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2001/04/xmlenc#", name = "KA-Nonce", scope = AgreementMethod.class)
    public JAXBElement<byte[]> createAgreementMethodKANonce(byte[] value) {
        return new JAXBElement<byte[]>(_AgreementMethodKANonce_QNAME, byte[].class, AgreementMethod.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KeyInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2001/04/xmlenc#", name = "OriginatorKeyInfo", scope = AgreementMethod.class)
    public JAXBElement<KeyInfo> createAgreementMethodOriginatorKeyInfo(KeyInfo value) {
        return new JAXBElement<KeyInfo>(_AgreementMethodOriginatorKeyInfo_QNAME, KeyInfo.class, AgreementMethod.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link KeyInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2001/04/xmlenc#", name = "RecipientKeyInfo", scope = AgreementMethod.class)
    public JAXBElement<KeyInfo> createAgreementMethodRecipientKeyInfo(KeyInfo value) {
        return new JAXBElement<KeyInfo>(_AgreementMethodRecipientKeyInfo_QNAME, KeyInfo.class, AgreementMethod.class, value);
    }

}
