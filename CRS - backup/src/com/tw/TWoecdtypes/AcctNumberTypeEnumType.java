//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-520 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.12.16 at 05:19:50 �U�� TST 
//


package com.tw.TWoecdtypes;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AcctNumberType_EnumType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AcctNumberType_EnumType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="OECD601"/>
 *     &lt;enumeration value="OECD602"/>
 *     &lt;enumeration value="OECD603"/>
 *     &lt;enumeration value="OECD604"/>
 *     &lt;enumeration value="OECD605"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AcctNumberType_EnumType")
@XmlEnum
public enum AcctNumberTypeEnumType {


    /**
     * IBAN
     * 
     */
    @XmlEnumValue("OECD601")
    OECD_601("OECD601"),

    /**
     * OBAN
     * 
     */
    @XmlEnumValue("OECD602")
    OECD_602("OECD602"),

    /**
     * ISIN
     * 
     */
    @XmlEnumValue("OECD603")
    OECD_603("OECD603"),

    /**
     * OSIN
     * 
     */
    @XmlEnumValue("OECD604")
    OECD_604("OECD604"),

    /**
     * Other
     * 
     */
    @XmlEnumValue("OECD605")
    OECD_605("OECD605");
    private final String value;

    AcctNumberTypeEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static AcctNumberTypeEnumType fromValue(String v) {
        for (AcctNumberTypeEnumType c: AcctNumberTypeEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
