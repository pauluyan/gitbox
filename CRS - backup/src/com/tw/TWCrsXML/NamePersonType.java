//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-520 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.12.16 at 05:19:06 �U�� TST 
//


package com.tw.TWCrsXML;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;




/**
 * The user must spread the data about the name of a party over up to six elements. The container element for this will be 'NameFix'. 
 * 
 * <p>Java class for NamePerson_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NamePerson_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PrecedingTitle" type="{urn:oecd:ties:TWcommontypesfatcacrs:v1}Char100_Type" minOccurs="0"/>
 *         &lt;element name="Title" type="{urn:oecd:ties:TWcommontypesfatcacrs:v1}Char100_Type" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="FirstName">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="xnlNameType" type="{urn:oecd:ties:TWcommontypesfatcacrs:v1}Char100_Type" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="MiddleName" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="xnlNameType" type="{urn:oecd:ties:TWcommontypesfatcacrs:v1}Char100_Type" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="NamePrefix" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="xnlNameType" type="{urn:oecd:ties:TWcommontypesfatcacrs:v1}Char100_Type" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="LastName">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="xnlNameType" type="{urn:oecd:ties:TWcommontypesfatcacrs:v1}Char100_Type" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="GenerationIdentifier" type="{urn:oecd:ties:TWcommontypesfatcacrs:v1}Char100_Type" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Suffix" type="{urn:oecd:ties:TWcommontypesfatcacrs:v1}Char100_Type" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="GeneralSuffix" type="{urn:oecd:ties:TWcommontypesfatcacrs:v1}Char100_Type" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="nameType" type="{urn:oecd:ties:stf:v4}OECDNameType_EnumType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NamePerson_Type", propOrder = {
    "precedingTitle",
    "title",
    "firstName",
    "middleName",
    "namePrefix",
    "lastName",
    "generationIdentifier",
    "suffix",
    "generalSuffix"
})
public class NamePersonType {
	public static void main(String[] args) {
		NamePersonType namePersonType = new NamePersonType();
		//---------------------------------------------------------------------------
		namePersonType.setPrecedingTitle("�L��");
		namePersonType.getPrecedingTitle();
		//---------------------------------------------------------------------------
		namePersonType.getTitle().add("�ٿ�");
		//---------------------------------------------------------------------------
		NamePersonType.FirstName firstName = new NamePersonType.FirstName();
		namePersonType.setFirstName(firstName);
		namePersonType.getFirstName();
		//----------------------------------------------------------------------------
		NamePersonType.MiddleName middleName=new NamePersonType.MiddleName();
		namePersonType.getMiddleName().add(middleName);
		//----------------------------------------------------------------------------
		NamePersonType.NamePrefix namePrefix = new NamePersonType.NamePrefix();
		namePersonType.setNamePrefix(namePrefix);
		namePersonType.getNamePrefix();
		//----------------------------------------------------------------------------
		NamePersonType.LastName lastName = new NamePersonType.LastName();
		namePersonType.setLastName(lastName);
		namePersonType.getLastName();
		//----------------------------------------------------------------------------
		namePersonType.getGenerationIdentifier().add("����(�@�N)�ѧO");
		//----------------------------------------------------------------------------
		namePersonType.getSuffix().add("�ɥR�L��");
		//----------------------------------------------------------------------------
		namePersonType.setGeneralSuffix("�@��ɥR�L��");
		namePersonType.getGeneralSuffix();
		//-----------------------------------------------------------------------------
		namePersonType.setNameType(OECDNameTypeEnumType.OECD_202);
		namePersonType.getNameType();
	}

    @XmlElement(name = "PrecedingTitle")
    protected String precedingTitle;
    @XmlElement(name = "Title")
    protected List<String> title;
    @XmlElement(name = "FirstName", required = true)
    protected NamePersonType.FirstName firstName;
    @XmlElement(name = "MiddleName")
    protected List<NamePersonType.MiddleName> middleName;
    @XmlElement(name = "NamePrefix")
    protected NamePersonType.NamePrefix namePrefix;
    @XmlElement(name = "LastName", required = true)
    protected NamePersonType.LastName lastName;
    @XmlElement(name = "GenerationIdentifier")
    protected List<String> generationIdentifier;
    @XmlElement(name = "Suffix")
    protected List<String> suffix;
    @XmlElement(name = "GeneralSuffix")
    protected String generalSuffix;
    @XmlAttribute
    protected OECDNameTypeEnumType nameType;

    /**
     * Gets the value of the precedingTitle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrecedingTitle() {
        return precedingTitle;
    }

    /**
     * Sets the value of the precedingTitle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrecedingTitle(String value) {
        this.precedingTitle = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the title property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTitle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getTitle() {
        if (title == null) {
            title = new ArrayList<String>();
        }
        return this.title;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link NamePersonType.FirstName }
     *     
     */
    public NamePersonType.FirstName getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link NamePersonType.FirstName }
     *     
     */
    public void setFirstName(NamePersonType.FirstName value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the middleName property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the middleName property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMiddleName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NamePersonType.MiddleName }
     * 
     * 
     */
    public List<NamePersonType.MiddleName> getMiddleName() {
        if (middleName == null) {
            middleName = new ArrayList<NamePersonType.MiddleName>();
        }
        return this.middleName;
    }

    /**
     * Gets the value of the namePrefix property.
     * 
     * @return
     *     possible object is
     *     {@link NamePersonType.NamePrefix }
     *     
     */
    public NamePersonType.NamePrefix getNamePrefix() {
        return namePrefix;
    }

    /**
     * Sets the value of the namePrefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link NamePersonType.NamePrefix }
     *     
     */
    public void setNamePrefix(NamePersonType.NamePrefix value) {
        this.namePrefix = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link NamePersonType.LastName }
     *     
     */
    public NamePersonType.LastName getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link NamePersonType.LastName }
     *     
     */
    public void setLastName(NamePersonType.LastName value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the generationIdentifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the generationIdentifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGenerationIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getGenerationIdentifier() {
        if (generationIdentifier == null) {
            generationIdentifier = new ArrayList<String>();
        }
        return this.generationIdentifier;
    }

    /**
     * Gets the value of the suffix property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the suffix property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSuffix().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSuffix() {
        if (suffix == null) {
            suffix = new ArrayList<String>();
        }
        return this.suffix;
    }

    /**
     * Gets the value of the generalSuffix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeneralSuffix() {
        return generalSuffix;
    }

    /**
     * Sets the value of the generalSuffix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeneralSuffix(String value) {
        this.generalSuffix = value;
    }

    /**
     * Gets the value of the nameType property.
     * 
     * @return
     *     possible object is
     *     {@link OECDNameTypeEnumType }
     *     
     */
    public OECDNameTypeEnumType getNameType() {
        return nameType;
    }

    /**
     * Sets the value of the nameType property.
     * 
     * @param value
     *     allowed object is
     *     {@link OECDNameTypeEnumType }
     *     
     */
    public void setNameType(OECDNameTypeEnumType value) {
        this.nameType = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attribute name="xnlNameType" type="{urn:oecd:ties:TWcommontypesfatcacrs:v1}Char100_Type" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class FirstName {
    	public static void main(String[] args) {
			FirstName firstName = new FirstName();
			//---------------------------------------
			firstName.setValue("�W�r");
			firstName.getValue();
			//---------------------------------------
			firstName.setXnlNameType("");
			firstName.getXnlNameType();
		}

        @XmlValue
        protected String value;
        @XmlAttribute
        protected String xnlNameType;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the xnlNameType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getXnlNameType() {
            return xnlNameType;
        }

        /**
         * Sets the value of the xnlNameType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setXnlNameType(String value) {
            this.xnlNameType = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attribute name="xnlNameType" type="{urn:oecd:ties:TWcommontypesfatcacrs:v1}Char100_Type" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class LastName {
    	public static void main(String[] args) {
			LastName lastName = new LastName();
			//------------------------------------
			lastName.setValue("�m��");
			lastName.getValue();
			//------------------------------------
			lastName.setXnlNameType("");
			lastName.getXnlNameType();
		}

        @XmlValue
        protected String value;
        @XmlAttribute
        protected String xnlNameType;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the xnlNameType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getXnlNameType() {
            return xnlNameType;
        }

        /**
         * Sets the value of the xnlNameType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setXnlNameType(String value) {
            this.xnlNameType = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attribute name="xnlNameType" type="{urn:oecd:ties:TWcommontypesfatcacrs:v1}Char100_Type" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class MiddleName {
    	public static void main(String[] args) {
			MiddleName middleName = new MiddleName();
			//-----------------------------------------------
			middleName.setValue("�����W�r");
			middleName.getValue();
			//-----------------------------------------------
			middleName.setXnlNameType("");
			middleName.getXnlNameType();
		}

        @XmlValue
        protected String value;
        @XmlAttribute
        protected String xnlNameType;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the xnlNameType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getXnlNameType() {
            return xnlNameType;
        }

        /**
         * Sets the value of the xnlNameType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setXnlNameType(String value) {
            this.xnlNameType = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attribute name="xnlNameType" type="{urn:oecd:ties:TWcommontypesfatcacrs:v1}Char100_Type" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class NamePrefix {
    	public static void main(String[] args) {
			NamePrefix namePrefix = new NamePrefix();
			//------------------------------------------
			namePrefix.setValue("�W�٫e��(�Q�ڴL��)");
			namePrefix.getValue();
			//------------------------------------------
			namePrefix.setXnlNameType("");
			namePrefix.getXnlNameType();
		}

        @XmlValue
        protected String value;
        @XmlAttribute
        protected String xnlNameType;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * Gets the value of the xnlNameType property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getXnlNameType() {
            return xnlNameType;
        }

        /**
         * Sets the value of the xnlNameType property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setXnlNameType(String value) {
            this.xnlNameType = value;
        }

    }

}
