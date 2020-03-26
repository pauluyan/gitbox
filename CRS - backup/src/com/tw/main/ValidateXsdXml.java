package com.tw.main;
/*************************************************************************************
 * Program name: ValidateXsdXml.java 
 * 
 * Function explanation: 取得xml及xsd的檔案，以便驗證
 **************************************************************************************

 ************************************************************************************** 
 * Development date:2015/01/24  
 * 
 * Developer:Bruce Hsu
 **************************************************************************************

 ************************************************************************************** 
 * Modified date:  Modify staff:
 * 
 * Modify content:
 **************************************************************************************/


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class ValidateXsdXml {
//	private static Logger logger = Logger.getLogger(ValidateXsdXml.class);

	private static boolean status;

	public boolean validate(String saveXmlFilePath , String xmlName) throws SAXException,FileNotFoundException {
		status = false;
		try {
			File Current_Dir = new File ("");
			// 將產生的xml檔存到程式記憶體內
			InputStream xml = new FileInputStream(new File(saveXmlFilePath+xmlName));
			// 宣告一個放多份xsd檔用的List
			List<File> schemas = new ArrayList<File>();
			schemas.add(new File(Current_Dir.getAbsoluteFile().getParent()+"/CRS/CRSxsd/TWoecdtypes_v1.0.xsd"));
			schemas.add(new File(Current_Dir.getAbsoluteFile().getParent()+"/CRS/CRSxsd/TWCrsXML_v1.0.xsd"));
			schemas.add(new File(Current_Dir.getAbsoluteFile().getParent()+"/CRS/CRSxsd/TWCommonTypesFatcaCrs_v1.0.xsd"));
			schemas.add(new File(Current_Dir.getAbsoluteFile().getParent()+"/CRS/CRSxsd/isocrstypes_v1.0.xsd"));
			
			status = validateWithMultiSchemas(xml, schemas);
		} catch (FileNotFoundException ex) {
//			logger.error(ex);
		}
		return status;
	}

	/********************************************************************
	 * Method Name: validateWithSingleSchema
	 * 
	 * Function Explanation: 單一份xml與單一份xsd做比對
	 *********************************************************************/
	public static boolean validateWithSingleSchema(File xml, File xsd) {
		status = false;
		try {
			SchemaFactory sf = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = sf.newSchema(xsd);
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(xml));
			status = true;
		} catch (Exception e) {
			status = false;
			e.getMessage();
		}
		return status;
	}

	/********************************************************************
	 * Method Name: createSchema
	 * 
	 * Function Explanation: 用程式讀取xsd檔存放在記憶體內並回傳物件以便與xml比對
	 *********************************************************************/
	private static Schema createSchema(List<File> schemas)
			throws ParserConfigurationException, SAXException, IOException {
		status = false;
		SchemaFactory sf = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Source[] sources = new Source[schemas.size()];
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		docFactory.setValidating(false);
		docFactory.setNamespaceAware(true);
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		try {
			for (int i = 0; i < schemas.size(); i++) {
				org.w3c.dom.Document doc = docBuilder.parse(schemas.get(i));
				DOMSource stream = new DOMSource(doc, schemas.get(i).getAbsolutePath());
				sources[i] = stream;
			}
			status = true;
		} catch (IOException e) {
//			logger.error(e);
		} finally {
			if (status == true) {
//				logger.info("create xsd schema success");
			} else {
//				logger.info("create xsd schema Fail");
			}
		}
		return sf.newSchema(sources);
	}

	/********************************************************************
	 * Method Name: validateWithMultiSchemas
	 * 
	 * Function Explanation: 一份xml檔與多份xsd檔做比對
	 *********************************************************************/
	public static boolean validateWithMultiSchemas(InputStream xml,
			List<File> schemas) {
		status = false;
		try {
			Schema schema = createSchema(schemas);
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(xml));
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
//			logger.error(e);
		} 	finally {
			if (status == true) {
//				logger.info("XSD and XML mapping success");
			} else {
//				logger.error("XSD and XML mapping Fail");
			}
		}
		return status;
	}
}
