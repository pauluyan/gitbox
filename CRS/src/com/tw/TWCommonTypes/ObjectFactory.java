//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-520 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.12.16 at 05:18:18 �U�� TST 
//


package com.tw.TWCommonTypes;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.tw.TWCommonTypes package. 
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

    private final static QName _AddressTypeAddressFix_QNAME = new QName("urn:oecd:ties:TWcommontypesfatcacrs:v1", "AddressFix");
    private final static QName _AddressTypeAddressFree_QNAME = new QName("urn:oecd:ties:TWcommontypesfatcacrs:v1", "AddressFree");
    private final static QName _AddressTypeCountryCode_QNAME = new QName("urn:oecd:ties:TWcommontypesfatcacrs:v1", "CountryCode");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.tw.TWCommonTypes
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MonAmntType }
     * 
     */
    public MonAmntType createMonAmntType() {
        return new MonAmntType();
    }

    /**
     * Create an instance of {@link AddressType }
     * 
     */
    public AddressType createAddressType() {
        return new AddressType();
    }

    /**
     * Create an instance of {@link DocSpecType }
     * 
     */
    public DocSpecType createDocSpecType() {
        return new DocSpecType();
    }

    /**
     * Create an instance of {@link TINType }
     * 
     */
    public TINType createTINType() {
        return new TINType();
    }

    /**
     * Create an instance of {@link NameOrganisationType }
     * 
     */
    public NameOrganisationType createNameOrganisationType() {
        return new NameOrganisationType();
    }

    /**
     * Create an instance of {@link AddressFixType }
     * 
     */
    public AddressFixType createAddressFixType() {
        return new AddressFixType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddressFixType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oecd:ties:TWcommontypesfatcacrs:v1", name = "AddressFix", scope = AddressType.class)
    public JAXBElement<AddressFixType> createAddressTypeAddressFix(AddressFixType value) {
        return new JAXBElement<AddressFixType>(_AddressTypeAddressFix_QNAME, AddressFixType.class, AddressType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oecd:ties:TWcommontypesfatcacrs:v1", name = "AddressFree", scope = AddressType.class)
    public JAXBElement<String> createAddressTypeAddressFree(String value) {
        return new JAXBElement<String>(_AddressTypeAddressFree_QNAME, String.class, AddressType.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CountryCodeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oecd:ties:TWcommontypesfatcacrs:v1", name = "CountryCode", scope = AddressType.class)
    public JAXBElement<CountryCodeType> createAddressTypeCountryCode(CountryCodeType value) {
        return new JAXBElement<CountryCodeType>(_AddressTypeCountryCode_QNAME, CountryCodeType.class, AddressType.class, value);
    }

}
