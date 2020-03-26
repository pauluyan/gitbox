//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-520 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.12.16 at 05:19:06 �U�� TST 
//


package com.tw.TWCrsXML;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;




/**
 * 
 * This data type is to be used whenever monetary amounts are to be communicated. Such amounts shall be given
 * including two fractional digits of the main currency unit. The code for the currency in which the value is expressed has to be
 * taken from the ISO codelist 4217 and added in attribute currCode.
 * 
 * 
 * <p>Java class for MonAmnt_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MonAmnt_Type">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;urn:oecd:ties:TWcommontypesfatcacrs:v1>TwoDigFract_Type">
 *       &lt;attribute name="currCode" use="required" type="{urn:oecd:ties:isocrstypes:v1}currCode_Type" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MonAmnt_Type", namespace = "urn:oecd:ties:TWcommontypesfatcacrs:v1", propOrder = {
    "value"
})
public class MonAmntType {
public static void main(String[] args) {
	MonAmntType monAmntType = new MonAmntType();
	//----------------------------------------------
	BigDecimal bigDecimal = new BigDecimal("");
	monAmntType.setValue(bigDecimal);
	monAmntType.getValue();
	//----------------------------------------------
	monAmntType.setCurrCode(CurrCodeType.TWD);
	monAmntType.getCurrCode();
}
    @XmlValue
    protected BigDecimal value;
    @XmlAttribute(required = true)
    protected CurrCodeType currCode;

    /**
     * 
     * 				Data type for any kind of numeric data with two decimal fraction digits, especially monetary amounts.
     * 			
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    /**
     * Gets the value of the currCode property.
     * 
     * @return
     *     possible object is
     *     {@link CurrCodeType }
     *     
     */
    public CurrCodeType getCurrCode() {
        return currCode;
    }

    /**
     * Sets the value of the currCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link CurrCodeType }
     *     
     */
    public void setCurrCode(CurrCodeType value) {
        this.currCode = value;
    }

}
