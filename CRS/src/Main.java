import com.tw.TWCrsXML.ObjectFactory;
import com.tw.jdbc.ConnectionDemo;

public class Main {
	public static void main(String[] args) {
		// String userName = args[0];
		// String password = args[1];
		// String tns = args[2];
		// String backupXmlFilePath = args[3];
		// String xmlName = args[4];
		// String ora = args[5];
		ObjectFactory objectFactory = new ObjectFactory();
		ConnectionDemo connectionDemo;
		try {
			connectionDemo = new ConnectionDemo();
			connectionDemo.setLocal("jdbc:oracle:thin:@localhost:1521:xe");
			connectionDemo.setUser("DM_WMG");
			connectionDemo.setPassword("zaq1xsw2");
			Conver2XML xml  = new Conver2XML();
			xml.ToXml(objectFactory, connectionDemo);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
