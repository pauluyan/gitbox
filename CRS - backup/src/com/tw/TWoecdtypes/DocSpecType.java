//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-520 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.12.16 at 05:19:50 �U�� TST 
//


package com.tw.TWoecdtypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Document specification: Data identifying and describing the document, where
 * 'document' here means the part of a message that is to transmit the data about a single block of CRS information. 
 * 
 * <p>Java class for DocSpec_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DocSpec_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DocTypeIndic" type="{urn:oecd:ties:stf:v4}OECDDocTypeIndic_EnumType"/>
 *         &lt;element name="DocRefID" type="{urn:oecd:ties:TWcommontypesfatcacrs:v1}Char100_Type"/>
 *         &lt;element name="CorrDocRefID" type="{urn:oecd:ties:TWcommontypesfatcacrs:v1}Char100_Type" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocSpec_Type", namespace = "urn:oecd:ties:stf:v4", propOrder = {
    "docTypeIndic",
    "docRefID",
    "corrDocRefID"
})
public class DocSpecType {

    @XmlElement(name = "DocTypeIndic", required = true)
    protected OECDDocTypeIndicEnumType docTypeIndic;
    @XmlElement(name = "DocRefID", required = true)
    protected String docRefID;
    @XmlElement(name = "CorrDocRefID")
    protected String corrDocRefID;

    /**
     * Gets the value of the docTypeIndic property.
     * 
     * @return
     *     possible object is
     *     {@link OECDDocTypeIndicEnumType }
     *     
     */
    public OECDDocTypeIndicEnumType getDocTypeIndic() {
        return docTypeIndic;
    }

    /**
     * Sets the value of the docTypeIndic property.
     * 
     * @param value
     *     allowed object is
     *     {@link OECDDocTypeIndicEnumType }
     *     
     */
    public void setDocTypeIndic(OECDDocTypeIndicEnumType value) {
        this.docTypeIndic = value;
    }

    /**
     * Gets the value of the docRefID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocRefID() {
        return docRefID;
    }

    /**
     * Sets the value of the docRefID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocRefID(String value) {
        this.docRefID = value;
    }

    /**
     * Gets the value of the corrDocRefID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCorrDocRefID() {
        return corrDocRefID;
    }

    /**
     * Sets the value of the corrDocRefID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCorrDocRefID(String value) {
        this.corrDocRefID = value;
    }

}
