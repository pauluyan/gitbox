



import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.tw.TWCrsXML.ObjectFactory;
import com.tw.jdbc.ConnectionDemo;

public class CRSXmlGen {
	private static Logger logger=Logger.getLogger(CRSXmlGen.class);
	public static void main(String[] args) {
		ConnectionDemo connectionDemo = null;
		try {
			connectionDemo = new ConnectionDemo();
	//		String userName = args[0];
	//		String password = args[1];
	//		String tns = args[2];
	//		String backupXmlFilePath = args[3];
	//		String ora = args[4];
//			String crsHome = args[5];
	//		String snapYear = args[6];
			ObjectFactory objectFactory = new ObjectFactory();
			
			
			connectionDemo = new ConnectionDemo();
			connectionDemo.setLocal("jdbc:oracle:thin:@localhost:1521:xe");
			connectionDemo.setUser("DM_WMG");
			connectionDemo.setPassword("zaq1xsw2");
			Conver2XML xml  = new Conver2XML();
			ValidateXsdXml validateXsdXml = new ValidateXsdXml();
			int page =1;
			//判斷該分為幾份檔案
			page = connectionDemo.selectCountCRS_XML_ACCTHOLDER("20191231");
			String fileYear = "20191231".substring(0, 4);
			String backupXmlFilePath = "D://temp//";
			SimpleDateFormat sdf = new SimpleDateFormat("-yyyy-MM-dd'T'HHmmss");
			String dateString = sdf.format(new Date());
			String uniformNumber = "-03750168";
			for(int nowPage=1; nowPage<=page; nowPage++) {
				String fileName = "TW"+fileYear+"TW"+uniformNumber+dateString+"-0"+nowPage+".xml";//TW申報資料西元年TW-統編-yyyy-MM-DD'T'HHmmss-流水號2位 
				logger.debug("File Name:"+fileName);
				xml.ToXml(objectFactory, connectionDemo, "20191231", fileName, backupXmlFilePath, nowPage);
				boolean status = validateXsdXml.validate(backupXmlFilePath, fileName);//XMLNAME本身帶副檔名
				if(status == true) {
					connectionDemo.insSuccMessDB("20191231");
				}else {
					logger.error("ValidateXsdXml false");
				}
			}
		} catch (Exception ex) {
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
