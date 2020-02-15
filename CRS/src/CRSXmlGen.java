



import org.apache.log4j.Logger;

import com.tw.TWCrsXML.ObjectFactory;
import com.tw.jdbc.ConnectionDemo;

public class CRSXmlGen {
	private static Logger logger=Logger.getLogger(CRSXmlGen.class);
	public static void main(String[] args) {
//		 String userName = args[0];
//		 String password = args[1];
//		 String tns = args[2];
//		 String backupXmlFilePath = args[3];
//		 String xmlName = args[4];
//		 String ora = args[5];
		ObjectFactory objectFactory = new ObjectFactory();
		ConnectionDemo connectionDemo;
		try {
			connectionDemo = new ConnectionDemo();
			connectionDemo.setLocal("jdbc:oracle:thin:@localhost:1521:xe");
			connectionDemo.setUser("DM_WMG");
			connectionDemo.setPassword("zaq1xsw2");
			Conver2XML xml  = new Conver2XML();
			xml.ToXml(objectFactory, connectionDemo);
			ValidateXsdXml validateXsdXml = new ValidateXsdXml();
			boolean status = validateXsdXml.validate("D://temp//", "CRS1.xml");
			if(status == true) {
				connectionDemo.insSuccMessDB();
			}else {
				logger.error("ValidateXsdXml false");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
		} 
	}

}
