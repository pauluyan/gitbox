//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-520 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.12.16 at 05:19:06 �U�� TST 
//


package com.tw.TWCrsXML;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CrsAcctHolderType_EnumType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CrsAcctHolderType_EnumType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CRS981"/>
 *     &lt;enumeration value="CRS982"/>
 *     &lt;enumeration value="CRS983"/>
 *     &lt;enumeration value="CRS984"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CrsAcctHolderType_EnumType")
@XmlEnum
public enum CrsAcctHolderTypeEnumType {


    /**
     * Active Non-Financial Entity
     * 
     */
    @XmlEnumValue("CRS981")
    CRS_981("CRS981"),

    /**
     * A Passive NFE is a Reportable Person, with one or more Controlling Persons that is a Reportable Person
     * 
     */
    @XmlEnumValue("CRS982")
    CRS_982("CRS982"),

    /**
     * A Passive NFE is a Reportable Person, without any Controlling Persons that is a Reportable Person
     * 
     */
    @XmlEnumValue("CRS983")
    CRS_983("CRS983"),

    /**
     * A Passive NFE is NOT a Reportable Person, with one or more Controlling Persons that is a Reportable Person
     * 
     */
    @XmlEnumValue("CRS984")
    CRS_984("CRS984");
    private final String value;

    CrsAcctHolderTypeEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CrsAcctHolderTypeEnumType fromValue(String v) {
        for (CrsAcctHolderTypeEnumType c: CrsAcctHolderTypeEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}