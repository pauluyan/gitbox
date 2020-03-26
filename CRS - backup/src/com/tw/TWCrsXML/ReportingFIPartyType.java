//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-520 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.12.16 at 05:19:06 �U�� TST 
//


package com.tw.TWCrsXML;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import com.tw.jdbc.ConnectionDemo;


/**
 * (Modified OrganisationParty_Type) At least one IN should be added.
 * 
 * <p>Java class for ReportingFIParty_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReportingFIParty_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ResCountryCode" type="{urn:oecd:ties:isocrstypes:v1}CountryCode_Type" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="IN" type="{urn:oecd:ties:crs:v1}OrganisationIN_Type" maxOccurs="unbounded"/>
 *         &lt;element name="Name" type="{urn:oecd:ties:TWcommontypesfatcacrs:v1}NameOrganisation_Type" maxOccurs="unbounded"/>
 *         &lt;element name="Address" type="{urn:oecd:ties:TWcommontypesfatcacrs:v1}Address_Type" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReportingFIParty_Type", propOrder = {
    "resCountryCode",
    "in",
    "name",
    "address"
})
@XmlSeeAlso({
    CorrectableOrganisationPartyType.class
})
public class ReportingFIPartyType {
	public static void main(String[] args) {
	ReportingFIPartyType reportingFIPartyType = new ReportingFIPartyType();
	//---------------------------------------------------------------------
	reportingFIPartyType.getResCountryCode();
	//---------------------------------------------------------------------
	reportingFIPartyType.getIN();
	//--------------------------------------------------------------------
	reportingFIPartyType.getName();
	//--------------------------------------------------------------------
	reportingFIPartyType.getAddress();
		
	}

    @XmlElement(name = "ResCountryCode")
    protected List<CountryCodeType> resCountryCode;
    @XmlElement(name = "IN", required = true)
    protected List<OrganisationINType> in;
    @XmlElement(name = "Name", required = true)
    protected List<NameOrganisationType> name;
    @XmlElement(name = "Address", required = true)
    protected List<AddressType> address;

    /**
     * Gets the value of the resCountryCode property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the resCountryCode property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResCountryCode().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CountryCodeType }
     * 
     * 
     */
    public List<CountryCodeType> getResCountryCode() {
//    	ResultSet set = ConnectionDemo.selectCRS_XML_MSGREF();
//        if (resCountryCode == null) {
//            resCountryCode = new ArrayList<CountryCodeType>();
//            try {
//                while (set.next()) {
//    			resCountryCode.add(CountryCodeType.fromValue(set.getString("R_RESCOUNTRYCODE")));
//    			}
//			} catch (Exception e) {
//				System.out.println(e.getMessage());
//			}
//        
//        }
    	if (resCountryCode == null) {
          resCountryCode = new ArrayList<CountryCodeType>();
          }
        return this.resCountryCode;
    }

    /**
     * Gets the value of the in property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the in property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIN().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrganisationINType }
     * 
     * 
     */
    public List<OrganisationINType> getIN() {
        if (in == null) {
            in = new ArrayList<OrganisationINType>();
        }
        return this.in;
    }

    /**
     * Gets the value of the name property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the name property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getName().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link NameOrganisationType }
     * 
     * 
     */
    public List<NameOrganisationType> getName() {
        if (name == null) {
            name = new ArrayList<NameOrganisationType>();
        }
        return this.name;
    }

    /**
     * Gets the value of the address property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the address property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddress().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AddressType }
     * 
     * 
     */
    public List<AddressType> getAddress() {
        if (address == null) {
            address = new ArrayList<AddressType>();
        }
        return this.address;
    }

}
