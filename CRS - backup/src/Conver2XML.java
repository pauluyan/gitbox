import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

import com.tw.TWCrsXML.AccountHolderType;
import com.tw.TWCrsXML.AcctNumberTypeEnumType;
import com.tw.TWCrsXML.AddressFixType;
import com.tw.TWCrsXML.AddressType;
import com.tw.TWCrsXML.CRSOECD;
import com.tw.TWCrsXML.ControllingPersonType;
import com.tw.TWCrsXML.CorrectableAccountReportType;
import com.tw.TWCrsXML.CorrectableOrganisationPartyType;
import com.tw.TWCrsXML.CountryCodeType;
import com.tw.TWCrsXML.CrsAcctHolderTypeEnumType;
import com.tw.TWCrsXML.CrsBodyType;
import com.tw.TWCrsXML.CrsCtrlgPersonTypeEnumType;
import com.tw.TWCrsXML.CrsMessageTypeIndicEnumType;
import com.tw.TWCrsXML.CrsPaymentTypeEnumType;
import com.tw.TWCrsXML.CurrCodeType;
import com.tw.TWCrsXML.DocSpecType;
import com.tw.TWCrsXML.FIAccountNumberType;
import com.tw.TWCrsXML.MessageSpecType;
import com.tw.TWCrsXML.MessageTypeEnumType;
import com.tw.TWCrsXML.MonAmntType;
import com.tw.TWCrsXML.NameOrganisationType;
import com.tw.TWCrsXML.NamePersonType;
import com.tw.TWCrsXML.OECDDocTypeIndicEnumType;
import com.tw.TWCrsXML.OECDLegalAddressTypeEnumType;
import com.tw.TWCrsXML.OECDNameTypeEnumType;
import com.tw.TWCrsXML.ObjectFactory;
import com.tw.TWCrsXML.OrganisationINType;
import com.tw.TWCrsXML.OrganisationPartyType;
import com.tw.TWCrsXML.PaymentType;
import com.tw.TWCrsXML.PersonPartyType;
import com.tw.TWCrsXML.TINType;
import com.tw.bean.Bean_CRS_XML_ACCTHOLDER;
import com.tw.bean.Bean_CRS_XML_MSGREF;
import com.tw.bean.Bean_CRS_XML_RESCOUNTRYCODE;
import com.tw.jdbc.ConnectionDemo;

public class Conver2XML {
	private static Logger logger = Logger.getLogger(Conver2XML.class);

	public void ToXml(ObjectFactory objectFactory, ConnectionDemo connectionDemo, String snapYear, String fileName,
			String backupXmlFilePath, int nowPage) {
		int page = 1;
		// 判斷該分為幾份檔案
		page = connectionDemo.selectCountCRS_XML_ACCTHOLDER(snapYear);

		// ------------------------------------------------------------------
		// 工廠

		// 四張表表單
		List<Bean_CRS_XML_MSGREF> crs_XML_MSGREFs = connectionDemo.selectCRS_XML_MSGREF(snapYear);

		// --------------------------------------------------------------------------------------------------------
		CRSOECD crsoecd = objectFactory.createCRSOECD();
		int index = 0;
		logger.debug("CRS START Total Page:" + page);
		logger.debug("CRS START Now Page:" + nowPage);

		for (Bean_CRS_XML_MSGREF msgref : crs_XML_MSGREFs) {

			crsoecd.setVersion("1.0");
			MessageSpecType messageSpecType = objectFactory.createMessageSpecType();
			messageSpecType.setSendingCompanyIN(msgref.getSENDINGCOMPANYIN());
			messageSpecType.setTransmittingCountry(CountryCodeType.fromValue(msgref.getTRANSMITTINGCOUNTRY()));
			messageSpecType.setReceivingCountry(CountryCodeType.fromValue(msgref.getRECEIVINGCOUNTRY()));
			messageSpecType.setMessageType(MessageTypeEnumType.valueOf(msgref.getMESSAGETYPE()));
			messageSpecType.setWarning(msgref.getWARNING());
			messageSpecType.setContact(msgref.getCONTACT());
			messageSpecType.setMessageRefId(msgref.getMESSAGEREFID());
			messageSpecType.setMessageTypeIndic(CrsMessageTypeIndicEnumType.fromValue(msgref.getMESSAGETYPEINDIC()));
			messageSpecType.getCorrMessageRefId().add(msgref.getCORRDOCREFID());
			messageSpecType.setReportingPeriod(dateTurn(msgref.getREPORTINGPERIOD()));
			messageSpecType.setTimestamp(xmlGregorianCalendar());
			crsoecd.setMessageSpec(messageSpecType);
			CrsBodyType crsBodyType = objectFactory.createCrsBodyType();
			// ---------------------------------------------------------------------------------------
			// ReportingFI start
			List<Bean_CRS_XML_ACCTHOLDER> crs_XML_ACCTHOLDERs = connectionDemo.selectCRS_XML_ACCTHOLDER(msgref,
					nowPage);

			CorrectableOrganisationPartyType correctableOrganisationPartyType = objectFactory
					.createCorrectableOrganisationPartyType();

			// --------------------------------------------------------------------------------------
			// ResCountryCode
			correctableOrganisationPartyType.getResCountryCode()
					.add(CountryCodeType.fromValue(msgref.getR_COUNTRYCODE()));
			// --------------------------------------------------------------------------------------
			// IN
			OrganisationINType organisationINType = objectFactory.createOrganisationINType();
			organisationINType.setValue(msgref.getR_TIN());
			// organisationINType.setIssuedBy(CountryCodeType.TW);// Optional
			// organisationINType.setINType("TWIN");// Optional
			correctableOrganisationPartyType.getIN().add(organisationINType);
			// ---------------------------------------------------------------------------------------
			// Name
			NameOrganisationType nameOrganisationType = objectFactory.createNameOrganisationType();
			nameOrganisationType.setValue(msgref.getR_NAME());
			// nameOrganisationType.setNameType(OECDNameTypeEnumType.OECD_202);// Optional
			correctableOrganisationPartyType.getName().add(nameOrganisationType);
			// ---------------------------------------------------------------------------------------
			// Address start
			AddressType addressType = objectFactory.createAddressType();
			// addressType.setLegalAddressType(OECDLegalAddressTypeEnumType.OECD_301);//
			// Optional
			JAXBElement<CountryCodeType> CountryCodeElement = objectFactory
					.createAddressTypeCountryCode(CountryCodeType.fromValue(msgref.getR_COUNTRYCODE()));
			addressType.getContent().add(CountryCodeElement);

			JAXBElement<String> addressFreeElement = objectFactory
					.createAddressTypeAddressFree(msgref.getR_ADDRESSFREE());
			addressType.getContent().add(addressFreeElement);

			// AddressFixType addressFixType = new AddressFixType();
			//
			// addressFixType.setStreet(" ");// 街路號,不接受中文
			// addressFixType.setBuildingIdentifier(" ");// 建物名稱,不接受中文
			// addressFixType.setSuiteIdentifier(" ");// 門房號,不接受中文
			// addressFixType.setFloorIdentifier(" ");// 樓層,不接受中文
			// addressFixType.setDistrictName(" ");// 區名,不接受中文
			// addressFixType.setPOB(" ");// 郵政信箱,不接受中文
			// addressFixType.setPostCode(" ");// 郵遞區號,不接受中文
			// addressFixType.setCity(" ");// 城市,不接受中文
			// addressFixType.setCountrySubentity(" ");// 國家行政區域,不接受中文

			// JAXBElement<AddressFixType> addressJaxbElement =
			// objectFactory.createAddressTypeAddressFix(addressFixType);
			// addressType.getContent().add(addressJaxbElement);

			correctableOrganisationPartyType.getAddress().add(addressType);
			// Address end

			// ---------------------------------------------------------------------------------------

			// DocSpec
			DocSpecType docSpecType = new DocSpecType();
			docSpecType.setDocTypeIndic(OECDDocTypeIndicEnumType.fromValue("OECD1"));
			docSpecType.setDocRefID(msgref.getDOCREFID());
			// docSpecType.setCorrDocRefID(" ");// 更正文檔編號
			correctableOrganisationPartyType.setDocSpec(docSpecType);

			// ---------------------------------------------------------------------------------------
			crsBodyType.setReportingFI(correctableOrganisationPartyType);
			// ReportingFI end

			// ---------------------------------------------------------------------------------------

			// reportingGroup start
			CrsBodyType.ReportingGroup reportingGroup = objectFactory.createCrsBodyTypeReportingGroup();

			// 多個accountReport start
			for (Bean_CRS_XML_ACCTHOLDER acctholder : crs_XML_ACCTHOLDERs) {
				try {
					CorrectableAccountReportType correctableAccountReportType = objectFactory
							.createCorrectableAccountReportType();
					// --------------------------------------------------------------------------------------
					// DocSpec
					docSpecType = objectFactory.createDocSpecType();
					docSpecType.setDocTypeIndic(OECDDocTypeIndicEnumType.fromValue(acctholder.getDOCTYPEINDIC()));
					docSpecType.setDocRefID(acctholder.getDOCREFID());
					// docSpecType.setCorrDocRefID(acctholder.getCORRDOCREFID());//Optional
					correctableAccountReportType.setDocSpec(docSpecType);
					// --------------------------------------------------------------------------------------
					// AccountNumber
					FIAccountNumberType fiAccountNumberType = objectFactory.createFIAccountNumberType();
					fiAccountNumberType.setValue(acctholder.getACCOUNTNUMBER());
					// fiAccountNumberType.setAcctNumberType(AcctNumberTypeEnumType.OECD_601);//
					// Optional
					fiAccountNumberType.setUndocumentedAccount(Boolean.valueOf(acctholder.getUNDOCUMENTEDACCT()));
					fiAccountNumberType.setClosedAccount(Boolean.valueOf(acctholder.getACCOUNTCLOSED()));
					correctableAccountReportType.setAccountNumber(fiAccountNumberType);
					// -------------------------------------------------------------------------------------------------------
					/*
					 * AccountHolder DB判斷TYP1 = I 為 Individual DB判斷TYP1 = O,O+S 為 Organisation
					 * 兩種只會顯示其中一種
					 */

					// -------------------------------------------------------------------------------------------------------
					AccountHolderType accountHolderType = objectFactory.createAccountHolderType();

					// Individual start

					PersonPartyType partyType = objectFactory.createPersonPartyType();
					List<Bean_CRS_XML_RESCOUNTRYCODE> set = null;
					set = connectionDemo.selectAccotholderJoinRESCOUNTRYCODEI(acctholder);
					NamePersonType namePersonType = objectFactory.createNamePersonType();
					if (!set.isEmpty()) {
						for (Iterator iterator = set.iterator(); iterator.hasNext();) {
							Bean_CRS_XML_RESCOUNTRYCODE countryCode = (Bean_CRS_XML_RESCOUNTRYCODE) iterator.next();
							// ResCountryCode
							partyType.getResCountryCode()
									.add(CountryCodeType.fromValue(countryCode.getRESCOUNTRYCODE()));
							// TIN
							TINType tinType = objectFactory.createTINType();
							// TIN為NULL時會報錯 * 原則上為必填，但可視資訊取得性與適用與否提供(如帳戶持有人之生日)
							if (countryCode.getTIN() == null) {////////////////////////////////////// validation
								connectionDemo.insExceptionMessDB(acctholder.getDOCREFID(), "Individual NULL TIN");
//									tinType.setValue("12345");
								logger.error("DOCREFID:" + acctholder.getDOCREFID() + ",Individual NULL TIN");
							}
							tinType.setValue(countryCode.getTIN());

							tinType.setIssuedBy(CountryCodeType.fromValue(countryCode.getRESCOUNTRYCODE()));
							partyType.getTIN().add(tinType);

							// Name
							// namePersonType.setNameType(OECDNameTypeEnumType.OECD_202);//Optional
							// namePersonType.setPrecedingTitle(" ");// 尊稱
							// namePersonType.getTitle().add(" ");// 稱謂
							NamePersonType.FirstName firstName = objectFactory.createNamePersonTypeFirstName();
							if (acctholder.getFIRSTNAME() == null) {////////////////////////////////////// validation
								connectionDemo.insExceptionMessDB(acctholder.getDOCREFID(), "Individual NULL NAME");
								logger.error("DOCREFID:" + acctholder.getDOCREFID() + ",Individual NULL NAME");
//									firstName.setValue("NULL NAME");
							}
							firstName.setValue(acctholder.getFIRSTNAME());

							// firstName.setXnlNameType(" ");
							namePersonType.setFirstName(firstName);
							// NamePersonType.MiddleName middleName =
							// objectFactory.createNamePersonTypeMiddleName();
							// middleName.setValue(" ");// 中間名
							// middleName.setXnlNameType("");
							// namePersonType.getMiddleName().add(middleName);
							NamePersonType.LastName lastName = objectFactory.createNamePersonTypeLastName();
							lastName.setValue(acctholder.getLASTNAME());
							// lastName.setXnlNameType(" ");
							namePersonType.setLastName(lastName);
							// namePersonType.getGenerationIdentifier().add(" ");// 輩份(世代)識別
							// namePersonType.getSuffix().add(" ");// 補充尊稱
							// namePersonType.setGeneralSuffix(" ");// 一般補充尊稱

							// Address
							addressType = objectFactory.createAddressType();
							// addressType.setLegalAddressType(OECDLegalAddressTypeEnumType.OECD_301);//Optional

							CountryCodeElement = null;
							// ACCTHOLDER中 有些countrycode值為NULL 會產生NullPointerException
							if (acctholder.getCOUNTRYCODE() == null) {// -----------------------------------validation
								connectionDemo.insExceptionMessDB(acctholder.getDOCREFID(),"Individual NULL CountryCode");
								logger.error("DOCREFID:" + acctholder.getDOCREFID() + ",Individual NULL CountryCode");
//									CountryCodeElement = objectFactory.createAddressTypeCountryCode(CountryCodeType.fromValue("TW"));
							}
							CountryCodeElement = objectFactory.createAddressTypeCountryCode(CountryCodeType.fromValue(acctholder.getCOUNTRYCODE()));

							addressType.getContent().add(CountryCodeElement);
							// ---------------------------------------------------------------------------------------------------------------------------------------------------
							if (acctholder.getADDRESSFREE() == null) {//// -----------------------------------validation
								connectionDemo.insExceptionMessDB(acctholder.getDOCREFID(),
										"Individual NULL AddressFree");
								logger.error("DOCREFID:" + acctholder.getDOCREFID() + ",Individual NULL AddressFree");
//									addressFreeElement = objectFactory.createAddressTypeAddressFree(" ");
							}
							addressFreeElement = objectFactory.createAddressTypeAddressFree(acctholder.getADDRESSFREE());

							addressType.getContent().add(addressFreeElement);

							// addressFixType = objectFactory.createAddressFixType();
							// addressFixType.setStreet(" ");// 街路號,不接受中文
							// addressFixType.setBuildingIdentifier(" ");// 建物名稱,不接受中文
							// addressFixType.setSuiteIdentifier(" ");// 門房號,不接受中文
							// addressFixType.setFloorIdentifier(" ");// 樓層,不接受中文
							// addressFixType.setDistrictName(" ");// 區名,不接受中文
							// addressFixType.setPOB(" ");// 郵政信箱,不接受中文
							// addressFixType.setPostCode(" ");// 郵遞區號,不接受中文
							// addressFixType.setCity(" ");// 城市,不接受中文
							// addressFixType.setCountrySubentity(" ");// 國家行政區域,不接受中文

							// addressJaxbElement = objectFactory
							// .createAddressTypeAddressFix(addressFixType);
							// addressType.getContent().add(addressJaxbElement);

							// Birth
							PersonPartyType.BirthInfo birthInfo = objectFactory.createPersonPartyTypeBirthInfo();
							if (acctholder.getBIRTHDATE() != null) {
								birthInfo.setBirthDate(dateTurn(acctholder.getBIRTHDATE()));// 之後要回來轉型
								partyType.setBirthInfo(birthInfo);
							}

							// birthInfo.setCity(" ");// 出生城市
							// birthInfo.setCitySubentity(" ");// 出生城市行政區域
							// PersonPartyType.BirthInfo.CountryInfo countryInfo =
							// objectFactory.createPersonPartyTypeBirthInfoCountryInfo();
							// countryInfo.setCountryCode(CountryCodeType.TW);
							// countryInfo.setFormerCountryName(" ");// 原出生國家或地區名稱
							// birthInfo.setCountryInfo(countryInfo);
						}
						// name 及 address為同一人擁有 顯示一次就好
						partyType.getName().add(namePersonType);
						partyType.getAddress().add(addressType);
						accountHolderType.setIndividual(partyType);
					}
					// Individual end

					// ---------------------------------------------------------------------------------------
					// Organisation start
					OrganisationPartyType organisationPartyType = objectFactory.createOrganisationPartyType();

					set = connectionDemo.selectAccotholderJoinRESCOUNTRYCODEO(acctholder);
					if (!set.isEmpty()) {
						for (Iterator iterator = set.iterator(); iterator.hasNext();) {
							Bean_CRS_XML_RESCOUNTRYCODE code = (Bean_CRS_XML_RESCOUNTRYCODE) iterator.next();
							// ResCountryCode

							if (code.getRESCOUNTRYCODE() == null) {//// -----------------------------------validation
								connectionDemo.insExceptionMessDB(acctholder.getDOCREFID(),
										"Organisation NULL ResCountryCode");
								logger.error(
										"DOCREFID:" + acctholder.getDOCREFID() + ",Organisation NULL ResCountryCode");
//									organisationPartyType.getResCountryCode().add(CountryCodeType.fromValue("TW"));
							}
							organisationPartyType.getResCountryCode()
									.add(CountryCodeType.fromValue(code.getRESCOUNTRYCODE()));

							// IN
							organisationINType = objectFactory.createOrganisationINType();

							if (code.getTIN() == null) {//// -----------------------------------validation
								logger.error("DOCREFID:" + acctholder.getDOCREFID() + ",Organisation NULL IN");
								connectionDemo.insExceptionMessDB(acctholder.getDOCREFID(), "Organisation NULL IN");
//									organisationINType.setValue("12345");
							}
							organisationINType.setValue(code.getTIN());

							// organisationINType.setIssuedBy(CountryCodeType.TW);// Optional
							// organisationINType.setINType(" ");// TWIN
							organisationPartyType.getIN().add(organisationINType);
							// name
							nameOrganisationType = objectFactory.createNameOrganisationType();

							if (acctholder.getNAME() == null) {//// -----------------------------------validation
								connectionDemo.insExceptionMessDB(acctholder.getDOCREFID(), "Organisation NULL NAME");
								logger.error("DOCREFID:" + acctholder.getDOCREFID() + ",Organisation NULL NAME");
//									nameOrganisationType.setValue("NULL NAME");
							}
							nameOrganisationType.setValue(acctholder.getNAME());

							// nameOrganisationType.setNameType(OECDNameTypeEnumType.OECD_202);// Optional
							// address
							addressType = objectFactory.createAddressType();
							// addressType.setLegalAddressType(OECDLegalAddressTypeEnumType.OECD_301);//
							// Optional
							// -------------------------------------------------------------------------------------------------------------------------------------------------
							CountryCodeElement = null;
							// ACCTHOLDER中 有些countrycode值為NULL 會產生NullPointerException
							if (acctholder.getCOUNTRYCODE() == null) {//// -----------------------------------validation
								connectionDemo.insExceptionMessDB(acctholder.getDOCREFID(),
										"Organisation NULL CountryCode");
								logger.error("DOCREFID:" + acctholder.getDOCREFID() + ",Organisation NULL CountryCode");
//									CountryCodeElement = objectFactory.createAddressTypeCountryCode(CountryCodeType.fromValue("TW"));
							}
							CountryCodeElement = objectFactory.createAddressTypeCountryCode(
									CountryCodeType.fromValue(acctholder.getCOUNTRYCODE()));

							addressType.getContent().add(CountryCodeElement);
							// ---------------------------------------------------------------------------------------------------------------------------------------------------
							if (acctholder.getADDRESSFREE() == null) {//// -----------------------------------validation
								connectionDemo.insExceptionMessDB(acctholder.getDOCREFID(),
										"Organisation NULL AddressFree");
								logger.error("DOCREFID:" + acctholder.getDOCREFID() + ",Organisation NULL AddressFree");
//									addressFreeElement = objectFactory.createAddressTypeAddressFree(" ");
							}
							addressFreeElement = objectFactory
									.createAddressTypeAddressFree(acctholder.getADDRESSFREE());

							addressType.getContent().add(addressFreeElement);
							// addressFixType = objectFactory.createAddressFixType();

							// addressFixType.setStreet(" ");// 街路號,不接受中文
							// addressFixType.setBuildingIdentifier(" ");// 建物名稱,不接受中文
							// addressFixType.setSuiteIdentifier(" ");// 門房號,不接受中文
							// addressFixType.setFloorIdentifier(" ");// 樓層,不接受中文
							// addressFixType.setDistrictName(" ");// 區名,不接受中文
							// addressFixType.setPOB(" ");// 郵政信箱,不接受中文
							// addressFixType.setPostCode(" ");// 郵遞區號,不接受中文
							// addressFixType.setCity(" ");// 城市,不接受中文
							// addressFixType.setCountrySubentity(" ");// 國家行政區域,不接受中文

							// addressJaxbElement =
							// objectFactory.createAddressTypeAddressFix(addressFixType);
							// addressType.getContent().add(addressJaxbElement);
						}
						// name 及 address為同一人擁有 顯示一次就好
						organisationPartyType.getName().add(nameOrganisationType);
						organisationPartyType.getAddress().add(addressType);
						accountHolderType.setOrganisation(organisationPartyType);
					}
					// Organisation end

					// accountHolderType
					if (acctholder.getACCTHOLDERTYPE() != null) {
						accountHolderType
								.setAcctHolderType(CrsAcctHolderTypeEnumType.fromValue(acctholder.getACCTHOLDERTYPE()));// 有兩筆沒資料
					}
					correctableAccountReportType.setAccountHolder(accountHolderType);
					// ---------------------------------------------------------------------------------------

					/*
					 * controlling person
					 */
					List<Map> subowners = connectionDemo.selectAccotholderJoinSUBSOWNER(acctholder);
					for (Iterator iterator = subowners.iterator(); iterator.hasNext();) {
						Map subOwner = (Map) iterator.next();
						ControllingPersonType controllingPersonType = objectFactory.createControllingPersonType();
						PersonPartyType person = new PersonPartyType();
						if (subOwner.get("rescountrycode") == null) {//// -----------------------------------validation
							connectionDemo.insExceptionMessDB(acctholder.getDOCREFID(),
									"ControllingPerson NULL ResCountryCode");
							logger.error(
									"DOCREFID:" + acctholder.getDOCREFID() + ",ControllingPerson NULL ResCountryCode");
//								person.getResCountryCode().add(CountryCodeType.valueOf("TW"));
						}
						person.getResCountryCode()
								.add(CountryCodeType.valueOf((String) subOwner.get("rescountrycode")));

						// TIN
						TINType tinType = objectFactory.createTINType();
						if (subOwner.get("tin") == null) {//// -----------------------------------validation
							connectionDemo.insExceptionMessDB(acctholder.getDOCREFID(), "ControllingPerson NULL TIN");
							logger.error("DOCREFID:" + acctholder.getDOCREFID() + ",ControllingPerson NULL TIN");
//								tinType.setValue("12345");
						}
						tinType.setValue((String) subOwner.get("tin"));

						person.getTIN().add(tinType);
						// name
						namePersonType = objectFactory.createNamePersonType();
						// namePersonType.setNameType(OECDNameTypeEnumType.OECD_202);//Optional
						// namePersonType.setPrecedingTitle(" ");// 尊稱
						// namePersonType.getTitle().add(" ");// 稱謂
						NamePersonType.FirstName firstName = objectFactory.createNamePersonTypeFirstName();
						if (subOwner.get("firstname") == null) {//// -----------------------------------validation
							connectionDemo.insExceptionMessDB(acctholder.getDOCREFID(), "ControllingPerson NULL NAME");
							logger.error("DOCREFID:" + acctholder.getDOCREFID() + ",ControllingPerson NULL NAME");
//								firstName.setValue("NULL NAME");
						}
						firstName.setValue((String) subOwner.get("firstname"));

						// firstName.setXnlNameType("");
						namePersonType.setFirstName(firstName);
						// NamePersonType.MiddleName middleName =
						// objectFactory.createNamePersonTypeMiddleName();
						// middleName.setValue(" ");// 中間名
						// middleName.setXnlNameType("");
						// namePersonType.getMiddleName().add(middleName);
						NamePersonType.LastName lastName = objectFactory.createNamePersonTypeLastName();
						lastName.setValue((String) subOwner.get("lastname"));
						// lastName.setXnlNameType(" ");
						namePersonType.setLastName(lastName);
						// namePersonType.getGenerationIdentifier().add(" ");// 輩份(世代)識別
						// namePersonType.getSuffix().add(" ");// 補充尊稱
						// namePersonType.setGeneralSuffix(" ");// 一般補充尊稱
						person.getName().add(namePersonType);
						// address
						addressType = objectFactory.createAddressType();
						// NullPointerException
						if (subOwner.get("countryCode") == null) {////////////////////////////////////// validation
							connectionDemo.insExceptionMessDB(acctholder.getDOCREFID(),
									"ControllingPerson NULL CountryCode");
							logger.error(
									"DOCREFID:" + acctholder.getDOCREFID() + ",ControllingPerson NULL CountryCode");
//								CountryCodeElement = objectFactory.createAddressTypeCountryCode(CountryCodeType.TW);
						}
						CountryCodeElement = objectFactory.createAddressTypeCountryCode(
								CountryCodeType.fromValue((String) subOwner.get("countryCode")));

						addressType.getContent().add(CountryCodeElement);
						if (subOwner.get("addressfree") == null) {////////////////////////////////////// validation
							connectionDemo.insExceptionMessDB(acctholder.getDOCREFID(),
									"ControllingPerson NULL AddressFree");
							logger.error(
									"DOCREFID:" + acctholder.getDOCREFID() + ",ControllingPerson NULL AddressFree");
//								addressFreeElement = objectFactory.createAddressTypeAddressFree(" ");
						}
						addressFreeElement = objectFactory
								.createAddressTypeAddressFree((String) subOwner.get("addressfree"));

						addressType.getContent().add(addressFreeElement);
						// -----------------------------------------------------------------------------------------------------------------------------------------

						// addressFixType = objectFactory.createAddressFixType();

						// addressFixType.setStreet(" ");// 街路號,不接受中文
						// addressFixType.setBuildingIdentifier(" ");// 建物名稱,不接受中文
						// addressFixType.setSuiteIdentifier(" ");// 門房號,不接受中文
						// addressFixType.setFloorIdentifier(" ");// 樓層,不接受中文
						// addressFixType.setDistrictName(" ");// 區名,不接受中文
						// addressFixType.setPOB(" ");// 郵政信箱,不接受中文
						// addressFixType.setPostCode(" ");// 郵遞區號,不接受中文
						// addressFixType.setCity(" ");// 城市,不接受中文
						// addressFixType.setCountrySubentity(" ");// 國家行政區域,不接受中文

						// addressJaxbElement =
						// objectFactory.createAddressTypeAddressFix(addressFixType);
						// addressType.getContent().add(addressJaxbElement);
						person.getAddress().add(addressType);
						// birth
						PersonPartyType.BirthInfo birthInfo = objectFactory.createPersonPartyTypeBirthInfo();
						String bitrh = (String) subOwner.get("birthdate");
						if (!(bitrh.equals(" "))) {
							birthInfo.setBirthDate(dateTurn(bitrh));// 資料為null
						}
						person.setBirthInfo(birthInfo);

						controllingPersonType.setIndividual(person);

						// ctrlpersontype
						String ctrlpersonType = (String) subOwner.get("ctrlpersontype");
						if (!(ctrlpersonType.equals(" "))) {
							controllingPersonType
									.setCtrlgPersonType(CrsCtrlgPersonTypeEnumType.fromValue(ctrlpersonType));

						}
						correctableAccountReportType.getControllingPerson().add(controllingPersonType);
					}

					// -------------------------------------------------------------------------------------------------------------------------------------

					// -------------------------------------------------------------------------------------------------------------------------------------

					MonAmntType monAmntType = objectFactory.createMonAmntType();
					BigDecimal bigDecimal = new BigDecimal(String.valueOf(acctholder.getACCOUNTBALANCE()));
					monAmntType.setValue(bigDecimal);
					monAmntType.setCurrCode(CurrCodeType.fromValue(acctholder.getCURRCODE()));
					correctableAccountReportType.setAccountBalance(monAmntType);

					PaymentType paymentType = objectFactory.createPaymentType();

					paymentType.setType(CrsPaymentTypeEnumType.fromValue(acctholder.getTYPE_501()));
					bigDecimal = new BigDecimal(String.valueOf(acctholder.getPAYMENTAMNT_501()));
					monAmntType = objectFactory.createMonAmntType();
					monAmntType.setValue(bigDecimal);
					monAmntType.setCurrCode(CurrCodeType.fromValue(acctholder.getCURRCODE_501()));
					paymentType.setPaymentAmnt(monAmntType);
					correctableAccountReportType.getPayment().add(paymentType);

					paymentType = objectFactory.createPaymentType();
					paymentType.setType(CrsPaymentTypeEnumType.fromValue(acctholder.getTYPE_502()));
					bigDecimal = new BigDecimal(String.valueOf(acctholder.getPAYMENTAMNT_502()));
					monAmntType = objectFactory.createMonAmntType();
					monAmntType.setValue(bigDecimal);
					monAmntType.setCurrCode(CurrCodeType.fromValue(acctholder.getCURRCODE_502()));
					paymentType.setPaymentAmnt(monAmntType);
					correctableAccountReportType.getPayment().add(paymentType);
					String checkYear = msgref.getREPORTINGPERIOD().substring(0, 4);
					if (!checkYear.equals("2019")) {
						paymentType = objectFactory.createPaymentType();
						paymentType.setType(CrsPaymentTypeEnumType.fromValue(acctholder.getTYPE_503()));
						bigDecimal = new BigDecimal(String.valueOf(acctholder.getPAYMENTAMNT_503()));
						monAmntType = objectFactory.createMonAmntType();
						monAmntType.setValue(bigDecimal);
						monAmntType.setCurrCode(CurrCodeType.fromValue(acctholder.getCURRCODE_503()));
						paymentType.setPaymentAmnt(monAmntType);
						correctableAccountReportType.getPayment().add(paymentType);
					}
					paymentType = objectFactory.createPaymentType();
					paymentType.setType(CrsPaymentTypeEnumType.fromValue(acctholder.getTYPE_504()));
					bigDecimal = new BigDecimal(String.valueOf(acctholder.getPAYMENTAMNT_504()));
					monAmntType = objectFactory.createMonAmntType();
					monAmntType.setValue(bigDecimal);
					monAmntType.setCurrCode(CurrCodeType.fromValue(acctholder.getCURRCODE_504()));
					paymentType.setPaymentAmnt(monAmntType);
					correctableAccountReportType.getPayment().add(paymentType);

					reportingGroup.getAccountReport().add(correctableAccountReportType);
					index++;
				} catch (Exception e) {
					logger.error(e);
				}
			}
			crsBodyType.setReportingGroup(reportingGroup);
			crsoecd.getCrsBody().add(crsBodyType);
		}

		// ----------------------------------------------------------------------------------------------------------
		try {
			JAXBContext context = JAXBContext.newInstance(CRSOECD.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			File file = new File(backupXmlFilePath + fileName);
			marshaller.marshal(crsoecd, file);
			logger.debug("TotalRecord:" + index);
		} catch (Exception e) {
			logger.error(e);
		} finally {
//			try {
//				 connectionDemo.connect().close();
//				logger.info("關閉連線");
//			} catch (Exception e2) {
//				logger.error(e2);
//			}

		}
	}

	public static XMLGregorianCalendar dateTurn(String date) {
		XMLGregorianCalendar xmlGregorianCalendar = null;
		SimpleDateFormat simpleDateFormat = null;
		GregorianCalendar gcal = new GregorianCalendar();
		if (date.length() > 8) {
			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		} else {
			simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		}

		Date date2 = null;
		try {
			date2 = simpleDateFormat.parse(date);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		gcal.setTime(date2);
		try {
			xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
			xmlGregorianCalendar.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return xmlGregorianCalendar;
	}

	public static XMLGregorianCalendar xmlGregorianCalendar() {
		XMLGregorianCalendar xmlGregorianCalendar = null;
		// GregorianCalendar gcal = new GregorianCalendar();

		String FORMATER = "yyyy-MM-dd'T'HH:mm:ss";
		SimpleDateFormat format = new SimpleDateFormat(FORMATER);

		try {
			xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(format.format(new Date()));
			xmlGregorianCalendar.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return xmlGregorianCalendar;
	}

	public static boolean timeCheck() {
		java.util.Date date = new java.util.Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
		boolean check;
		String nowTime = simpleDateFormat.format(date).toString();
		if (nowTime.equals("2019")) {
			check = false;
			return check;
		} else {
			check = true;
			return check;
		}

	}
}