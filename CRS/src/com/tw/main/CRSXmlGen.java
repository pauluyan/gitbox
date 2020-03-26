package com.tw.main;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.tw.TWCrsXML.ObjectFactory;
import com.tw.jdbc.ConnectionDemo;

public class CRSXmlGen {
	private static Logger logger=Logger.getLogger(CRSXmlGen.class);
	public static void main(String[] args) {
		System.out.println("start.......");
		ConnectionDemo connectionDemo = null;
		try {
			connectionDemo = new ConnectionDemo();
			ObjectFactory objectFactory = new ObjectFactory();
			String userName = args[0];
			String password = args[1];
			String tns = args[2];
			String backupXmlFilePath = args[3];
			String ora = args[4];
			String snapYear = args[5];
			
			System.setProperty("oracle.net.tns_admin", ora);
			connectionDemo.setLocal(tns);
			connectionDemo.setUser(userName);
			connectionDemo.setPassword(password);
			Conver2XML xml  = new Conver2XML();
			ValidateXsdXml validateXsdXml = new ValidateXsdXml();
			int page =1;
			//判斷該分為幾份檔案
			page = connectionDemo.selectCountCRS_XML_ACCTHOLDER(snapYear);
			String fileYear = snapYear.substring(0, 4);
			SimpleDateFormat sdf = new SimpleDateFormat("-yyyy-MM-dd'T'HHmmss");
			String dateString = sdf.format(new Date());
			String uniformNumber = "-03750168";
			for(int nowPage=1; nowPage<=page; nowPage++) {
				String fileName = "TW"+fileYear+"TW"+uniformNumber+dateString+"-0"+nowPage+".xml";//TW申報資料西元年TW-統編-yyyy-MM-DD'T'HHmmss-流水號2位 
				logger.debug("File Name:"+fileName);
				System.out.println("File Name:"+fileName);
				xml.ToXml(objectFactory, connectionDemo, snapYear, fileName, backupXmlFilePath, nowPage);
				boolean status = validateXsdXml.validate(backupXmlFilePath, fileName);//XMLNAME本身帶副檔名
				if(status == true) {
					connectionDemo.insSuccMessDB(snapYear);
				}else {
					logger.error("ValidateXsdXml false");
					System.out.println("ValidateXsdXml false");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		} finally {
//			try {
//				connectionDemo.connect().close();
//				logger.info("關閉連線");
//			} catch (SQLException e) {
//				logger.error(e);
//			}
		}
	}

}
