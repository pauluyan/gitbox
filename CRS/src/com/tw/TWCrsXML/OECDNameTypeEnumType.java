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
 * <p>Java class for OECDNameType_EnumType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="OECDNameType_EnumType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="OECD202"/>
 *     &lt;enumeration value="OECD203"/>
 *     &lt;enumeration value="OECD204"/>
 *     &lt;enumeration value="OECD205"/>
 *     &lt;enumeration value="OECD206"/>
 *     &lt;enumeration value="OECD207"/>
 *     &lt;enumeration value="OECD208"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "OECDNameType_EnumType", namespace = "urn:oecd:ties:stf:v4")
@XmlEnum
public enum OECDNameTypeEnumType {


    /**
     * indiv (individual)
     * 
     */
    @XmlEnumValue("OECD202")
    OECD_202("OECD202"),

    /**
     * alias (alias)
     * 
     */
    @XmlEnumValue("OECD203")
    OECD_203("OECD203"),

    /**
     * nick (nickname)
     * 
     */
    @XmlEnumValue("OECD204")
    OECD_204("OECD204"),

    /**
     * aka (also known as)
     * 
     */
    @XmlEnumValue("OECD205")
    OECD_205("OECD205"),

    /**
     * dba (doing business as)
     * 
     */
    @XmlEnumValue("OECD206")
    OECD_206("OECD206"),

    /**
     * legal (legal name)
     * 
     */
    @XmlEnumValue("OECD207")
    OECD_207("OECD207"),

    /**
     * atbirth (name at birth)
     * 
     */
    @XmlEnumValue("OECD208")
    OECD_208("OECD208");
    private final String value;

    OECDNameTypeEnumType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OECDNameTypeEnumType fromValue(String v) {
        for (OECDNameTypeEnumType c: OECDNameTypeEnumType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
