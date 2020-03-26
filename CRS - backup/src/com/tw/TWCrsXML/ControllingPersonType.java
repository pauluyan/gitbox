//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-520 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.12.16 at 05:19:06 �U�� TST 
//


package com.tw.TWCrsXML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ControllingPerson_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ControllingPerson_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Individual" type="{urn:oecd:ties:crs:v1}PersonParty_Type"/>
 *         &lt;element name="CtrlgPersonType" type="{urn:oecd:ties:crs:v1}CrsCtrlgPersonType_EnumType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ControllingPerson_Type", propOrder = {
    "individual",
    "ctrlgPersonType"
})
public class ControllingPersonType {
	public static void main(String[] args) {
		ControllingPersonType controllingPersonType = new ControllingPersonType();
		//-------------------------------------------------------------------------
		PersonPartyType partyType = new PersonPartyType();
		controllingPersonType.setIndividual(partyType);
		controllingPersonType.getIndividual();
		//-------------------------------------------------------------------------
		controllingPersonType.setCtrlgPersonType(CrsCtrlgPersonTypeEnumType.CRS_801);
		controllingPersonType.getCtrlgPersonType();
	}

    @XmlElement(name = "Individual", required = true)
    protected PersonPartyType individual;
    @XmlElement(name = "CtrlgPersonType")
    protected CrsCtrlgPersonTypeEnumType ctrlgPersonType;

    /**
     * Gets the value of the individual property.
     * 
     * @return
     *     possible object is
     *     {@link PersonPartyType }
     *     
     */
    public PersonPartyType getIndividual() {
        return individual;
    }

    /**
     * Sets the value of the individual property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonPartyType }
     *     
     */
    public void setIndividual(PersonPartyType value) {
        this.individual = value;
    }

    /**
     * Gets the value of the ctrlgPersonType property.
     * 
     * @return
     *     possible object is
     *     {@link CrsCtrlgPersonTypeEnumType }
     *     
     */
    public CrsCtrlgPersonTypeEnumType getCtrlgPersonType() {
        return ctrlgPersonType;
    }

    /**
     * Sets the value of the ctrlgPersonType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrsCtrlgPersonTypeEnumType }
     *     
     */
    public void setCtrlgPersonType(CrsCtrlgPersonTypeEnumType value) {
        this.ctrlgPersonType = value;
    }

}