package com.tw.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tw.bean.Bean_CRS_XML_ACCTHOLDER;
import com.tw.bean.Bean_CRS_XML_MSGREF;
import com.tw.bean.Bean_CRS_XML_RESCOUNTRYCODE;

public class ConnectionDemo {
	String local;
	String user;
	String password;
	Connection conn = null;
	private static Logger logger=Logger.getLogger(ConnectionDemo.class);
	//檔案上限筆數
	int maxCount = 2000;
	
	public ConnectionDemo() throws ClassNotFoundException {

		Class.forName("oracle.jdbc.driver.OracleDriver");
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean timeCheck() {
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

	public Connection connect() {
		String local = this.local;
		String user = this.user;
		String password = this.password;
		
		try {
			if (conn == null)
				conn = DriverManager.getConnection(local, user, password);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return conn;

	}

	public List selectAccotholderJoinSUBSOWNER(Bean_CRS_XML_ACCTHOLDER accountHolder) {
		Connection connection = connect();
		PreparedStatement st = null;
		ResultSet set = null;
		List result = new ArrayList();
		String sql = "SELECT * FROM DM_WMG.CRS_XML_SUBSOWNER W "
				+ " WHERE W.SNAP_YYYYMMDD = '" + accountHolder.getSNAP_YYYYMMDD()
				+ "' AND   W.MESSAGEREFID = '" + accountHolder.getMESSAGEREFID()
				+ "' AND   W.ID = '" + accountHolder.getID() + "'";
		try {
			st = connection.prepareStatement(sql);
			set = st.executeQuery();
			while (set.next()) {
				Map map = new HashMap();
				map.put("snap_yyyymmdd", set.getString("snap_yyyymmdd"));
				map.put("m_refid", set.getString("m_refid"));
				map.put("messagerefid", set.getString("messagerefid"));
				map.put("id", set.getString("id"));
				map.put("rescountrycode", set.getString("rescountrycode"));
				map.put("tin", set.getString("tin"));
				map.put("countrycode", set.getString("countrycode"));
				map.put("firstname", set.getString("firstname"));
				map.put("lastname", set.getString("lastname"));
				map.put("addressfree", set.getString("addressfree"));
				map.put("birthdate", set.getString("BIRTHDATE"));
				map.put("ctrlpersontype", set.getString("ctrlpersontype"));
				result.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			closeQuietly(connection, st, set);
		}

		return result;
	}



	public List<Bean_CRS_XML_RESCOUNTRYCODE> selectAccotholderJoinRESCOUNTRYCODEI(Bean_CRS_XML_ACCTHOLDER accountHolder) {
		Connection connection = connect();
		PreparedStatement st = null;
		ResultSet set = null;
		List<Bean_CRS_XML_RESCOUNTRYCODE> result = new ArrayList<Bean_CRS_XML_RESCOUNTRYCODE>();
		String sql = "SELECT * FROM DM_WMG.CRS_XML_RESCOUNTRYCODE R inner join DM_WMG.CRS_XML_ACCTHOLDER A "
				+ " on r.snap_yyyymmdd = a.snap_yyyymmdd and r.messagerefid = a.messagerefid and a.id = r.id "
				+ " WHERE A.TYP1 = 'I' and R.snap_yyyymmdd = '" + accountHolder.getSNAP_YYYYMMDD() + "' and"
				+ " R.MESSAGEREFID = '" + accountHolder.getMESSAGEREFID() + "' and" 
				+ " R.ID = '" + accountHolder.getID() + "' and"
				+ " A.DOCREFID = '" + accountHolder.getDOCREFID() + "'";
		try {
			st = connection.prepareStatement(sql);
			set = st.executeQuery();
			while (set.next()) {
				Bean_CRS_XML_RESCOUNTRYCODE resCountryCode = new Bean_CRS_XML_RESCOUNTRYCODE();
				resCountryCode.setID(set.getString("ID"));
				resCountryCode.setSNAP_YYYYMMDD(set.getString("SNAP_YYYYMMDD"));
				resCountryCode.setRESCOUNTRYCODE(set.getString("rescountrycode"));
				resCountryCode.setTIN(set.getString("tin"));
				resCountryCode.setM_REFID(set.getInt("m_refid"));
				result.add(resCountryCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeQuietly(connection, st, set);
		}

		return result;

	}

	public List<Bean_CRS_XML_RESCOUNTRYCODE> selectAccotholderJoinRESCOUNTRYCODEO(Bean_CRS_XML_ACCTHOLDER accountHolder) {
		Connection connection = connect();
		PreparedStatement st = null;
		ResultSet set = null;
		List<Bean_CRS_XML_RESCOUNTRYCODE> result = new ArrayList<Bean_CRS_XML_RESCOUNTRYCODE>();
		String sql = "SELECT * FROM DM_WMG.CRS_XML_RESCOUNTRYCODE R  inner join DM_WMG.CRS_XML_ACCTHOLDER A " 
				+ " on r.snap_yyyymmdd = a.snap_yyyymmdd and r.messagerefid = a.messagerefid and a.id = r.id "
				+ " WHERE TYP1 IN('O','O+S') and R.snap_yyyymmdd = '" + accountHolder.getSNAP_YYYYMMDD() + "' and"
				+ " R.MESSAGEREFID = '" + accountHolder.getMESSAGEREFID() + "' and" 
				+ " R.ID = '" + accountHolder.getID() + "' and"
				+ " A.DOCREFID = '" + accountHolder.getDOCREFID() + "'";
		try {
			st = connection.prepareStatement(sql);
			set = st.executeQuery();
			while (set.next()) {
				Bean_CRS_XML_RESCOUNTRYCODE resCountryCode = new Bean_CRS_XML_RESCOUNTRYCODE();
				resCountryCode.setID(set.getString("ID"));
				resCountryCode.setSNAP_YYYYMMDD(set.getString("SNAP_YYYYMMDD"));
				resCountryCode.setRESCOUNTRYCODE(set.getString("rescountrycode"));
				resCountryCode.setTIN(set.getString("tin"));
				resCountryCode.setM_REFID(set.getInt("m_refid"));
				result.add(resCountryCode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeQuietly(connection, st, set);
		}

		return result;

	}
	
	public void insSuccMessDB(String snapYear)throws ClassNotFoundException, SQLException {
		// 訊息是否寫入DB成功
		boolean status = false;
		// 建立Oracle連線
		Connection connection = connect();
		Statement st = connection.createStatement();
		String getMessagerefidSql = "SELECT MESSAGEREFID FROM DM_WMG.CRS_XML_MSGREF WHERE SNAP_YYYYMMDD='"+ snapYear +"'";
		ResultSet rs = st.executeQuery(getMessagerefidSql);
		try {
			// 取得目前系統時間並寫入DB
			java.util.Date now = new java.util.Date(); // 取得現在時間
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String sGMT = sf.format(now);
			while(rs.next()) {
				String insertSuccessSql = "insert into DM_WMG.CRS_JAVA_LOG(EXEC_TIME, DOCREFID ,LOG) "
						+ " Values(to_date('"
						+ sGMT
						+ "','YYYY/MM/DD HH24:MI:SS'),'"
						+ rs.getString("MESSAGEREFID") + "','FINISH')";
				String commit = "COMMIT";
				st = connection.createStatement();
				st.executeQuery(insertSuccessSql);
				st.executeQuery(commit);
			}
			status = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (status == true) {
				System.out.println("DB writes success!");
			} else {
				System.out.println("DB writes fail!");
			}
			if(st != null) {
				st.close();
			}
			if(connection != null) {
//				connection.close();
			}
		}
	}

	public void insExceptionMessDB(String docRefId,String exception)throws ClassNotFoundException, SQLException {
		// 建立Oracle連線
		Connection connection = connect();
		Statement st = connection.createStatement();
		try {
			// 取得目前系統時間並寫入DB
			java.util.Date now = new java.util.Date(); // 取得現在時間
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String sGMT = sf.format(now);
				String insertSuccessSql = "insert into DM_WMG.CRS_JAVA_LOG(EXEC_TIME, DOCREFID ,LOG) "
						+ " Values(to_date('"
						+ sGMT
						+ "','YYYY/MM/DD HH24:MI:SS'),'"
						+ docRefId + "','"+ exception +"')";
				String commit = "COMMIT";
				st = connection.createStatement();
				st.executeQuery(insertSuccessSql);
				st.executeQuery(commit);
		} catch (Exception e) {
			logger.error(e);
		} finally {
			if(st != null) {
				st.close();
			}
			if(connection != null) {
//				connection.close();
			}
		}
	}

	public List<Bean_CRS_XML_ACCTHOLDER> selectCRS_XML_ACCTHOLDER(Bean_CRS_XML_MSGREF msgref, int nowPage) {
		ResultSet set = null;
		Connection conn = connect();
		PreparedStatement st = null;
		String sql = "SELECT * FROM CRS_XML_ACCTHOLDER where snap_yyyymmdd = '" + msgref.getSNAP_YYYYMMDD() + "' and MESSAGEREFID = '" + msgref.getMESSAGEREFID() +"'";
		
		String sql_page = " select * from ( select rownum as the_row_num2, temp.* from ( ";
		String sql_page2 = " ) temp where rownum <='"+ (nowPage*maxCount) +"')where the_row_num2 >='"+ ((nowPage-1)*maxCount +1) +"'";
		
		List<Bean_CRS_XML_ACCTHOLDER> crs_XML_ACCTHOLDERs = new ArrayList<Bean_CRS_XML_ACCTHOLDER>();
		DecimalFormat df = new DecimalFormat("##########.00");
		try {
			st = conn.prepareStatement(sql_page + sql + sql_page2);
			set = st.executeQuery();
			while (set.next()) {
				Bean_CRS_XML_ACCTHOLDER bean_CRS_XML_ACCTHOLDER = new Bean_CRS_XML_ACCTHOLDER();
				bean_CRS_XML_ACCTHOLDER.setSNAP_YYYYMMDD(set.getString("SNAP_YYYYMMDD"));
				bean_CRS_XML_ACCTHOLDER.setM_REFID(set.getInt("M_REFID"));
				bean_CRS_XML_ACCTHOLDER.setMESSAGEREFID(set.getString("MESSAGEREFID"));
				bean_CRS_XML_ACCTHOLDER.setDOCTYPEINDIC(set.getString("DOCTYPEINDIC"));
				bean_CRS_XML_ACCTHOLDER.setDOCREFID(set.getString("DOCREFID"));
				bean_CRS_XML_ACCTHOLDER.setID(set.getString("ID"));
				bean_CRS_XML_ACCTHOLDER.setACCOUNTNUMBER(set.getString("ACCOUNTNUMBER"));
				bean_CRS_XML_ACCTHOLDER.setACCTTYPE(set.getString("ACCTTYPE"));
				bean_CRS_XML_ACCTHOLDER.setACCTNUMBERTYPE(set.getString("ACCTNUMBERTYPE"));
				if(set.getString("UNDOCUMENTEDACCT").equals("Y")) {
					bean_CRS_XML_ACCTHOLDER.setUNDOCUMENTEDACCT("true");
				}else {
					bean_CRS_XML_ACCTHOLDER.setUNDOCUMENTEDACCT("false");
				}
				bean_CRS_XML_ACCTHOLDER.setCUR_COD(set.getString("CUR_COD"));
				if(set.getString("ACCOUNTCLOSED").equals("Y")) {
					bean_CRS_XML_ACCTHOLDER.setACCOUNTCLOSED("true");
				}else {
					bean_CRS_XML_ACCTHOLDER.setACCOUNTCLOSED("false");
				}
				bean_CRS_XML_ACCTHOLDER.setCTRY_COD(set.getString("CTRY_COD"));
				bean_CRS_XML_ACCTHOLDER.setPRECEDINGTITLE(set.getString("PRECEDINGTITLE"));
				bean_CRS_XML_ACCTHOLDER.setTITLE(set.getString("TITLE"));
				bean_CRS_XML_ACCTHOLDER.setFIRSTNAME(set.getString("FIRSTNAME"));
				bean_CRS_XML_ACCTHOLDER.setMIDDLENAME(set.getString("MIDDLENAME"));
				bean_CRS_XML_ACCTHOLDER.setNAMEPREFIX(set.getString("NAMEPREFIX"));
				bean_CRS_XML_ACCTHOLDER.setLASTNAME(set.getString("LASTNAME"));
				bean_CRS_XML_ACCTHOLDER.setGENERATIONIDENTIFIER(set.getString("GENERATIONIDENTIFIER"));
				bean_CRS_XML_ACCTHOLDER.setSUFFIX(set.getString("SUFFIX"));
				bean_CRS_XML_ACCTHOLDER.setGENERALSUFFIX(set.getString("GENERALSUFFIX"));
				bean_CRS_XML_ACCTHOLDER.setNAME(set.getString("NAME"));
				bean_CRS_XML_ACCTHOLDER.setCOUNTRYCODE(set.getString("COUNTRYCODE"));
				bean_CRS_XML_ACCTHOLDER.setADDRESSFREE(set.getString("ADDRESSFREE"));
				bean_CRS_XML_ACCTHOLDER.setNATIONALITY(set.getString("NATIONALITY"));
				bean_CRS_XML_ACCTHOLDER.setBIRTHDATE(set.getString("BIRTHDATE"));
				bean_CRS_XML_ACCTHOLDER.setCITY(set.getString("CITY"));
				bean_CRS_XML_ACCTHOLDER.setCITYSUBENTITY(set.getString("CITYSUBENTITY"));
				bean_CRS_XML_ACCTHOLDER.setC_COUNTRYCODE(set.getString("C_COUNTRYCODE"));
				bean_CRS_XML_ACCTHOLDER.setACCTHOLDERTYPE(set.getString("ACCTHOLDERTYPE"));
				bean_CRS_XML_ACCTHOLDER.setACCOUNTBALANCE(df.format(set.getDouble("ACCOUNTBALANCE")));
				bean_CRS_XML_ACCTHOLDER.setCURRCODE(set.getString("CURRCODE"));
				bean_CRS_XML_ACCTHOLDER.setTYPE_501(set.getString("TYPE_501"));
				bean_CRS_XML_ACCTHOLDER.setPAYMENTAMNT_501(df.format(set.getDouble("PAYMENTAMNT_501")));
				bean_CRS_XML_ACCTHOLDER.setCURRCODE_501(set.getString("CURRCODE_501"));
				bean_CRS_XML_ACCTHOLDER.setTYPE_502(set.getString("TYPE_502"));
				bean_CRS_XML_ACCTHOLDER.setPAYMENTAMNT_502(df.format(set.getDouble("PAYMENTAMNT_502")));
				bean_CRS_XML_ACCTHOLDER.setCURRCODE_502(set.getString("CURRCODE_502"));
				bean_CRS_XML_ACCTHOLDER.setTYPE_503(set.getString("TYPE_503"));
				bean_CRS_XML_ACCTHOLDER.setPAYMENTAMNT_503(df.format(set.getDouble("PAYMENTAMNT_503")));
				bean_CRS_XML_ACCTHOLDER.setCURRCODE_503(set.getString("CURRCODE_503"));
				bean_CRS_XML_ACCTHOLDER.setTYPE_504(set.getString("TYPE_504"));
				bean_CRS_XML_ACCTHOLDER.setPAYMENTAMNT_504(df.format(set.getDouble("PAYMENTAMNT_504")));
				bean_CRS_XML_ACCTHOLDER.setCURRCODE_504(set.getString("CURRCODE_504"));
				bean_CRS_XML_ACCTHOLDER.setTYP1(set.getString("TYP1"));

				crs_XML_ACCTHOLDERs.add(bean_CRS_XML_ACCTHOLDER);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			closeQuietly(conn, st, set);
		}
		return crs_XML_ACCTHOLDERs;
	}
	
	public int selectCountCRS_XML_ACCTHOLDER(String snapYear) {
		Connection conn = connect();
		ResultSet set = null;
		PreparedStatement st = null;
		String getCountSql = "select count(*) from CRS_XML_ACCTHOLDER where snap_yyyymmdd = '" + snapYear + "'";
		
		int count=1;
		try {
			st = conn.prepareStatement(getCountSql);
			set = st.executeQuery();
			while(set.next()) {
				int cnt = (int) Math.ceil(set.getDouble("count(*)")/maxCount) ;
				count = cnt < 1 ? 1 : cnt;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
		
	}

	public List<Bean_CRS_XML_MSGREF> selectCRS_XML_MSGREF(String snapYear) {

		ResultSet set = null;
		Connection conn = connect();
		String sql = "SELECT * FROM CRS_XML_MSGREF WHERE SNAP_YYYYMMDD = '"+ snapYear +"' ";
		List<Bean_CRS_XML_MSGREF> array = new ArrayList<Bean_CRS_XML_MSGREF>();
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(sql);
			set = st.executeQuery();
			while (set.next()) {
				Bean_CRS_XML_MSGREF bean_CRS_XML_MSGREF = new Bean_CRS_XML_MSGREF();
				bean_CRS_XML_MSGREF.setSNAP_YYYYMMDD(set.getString("SNAP_YYYYMMDD"));
				bean_CRS_XML_MSGREF.setM_REFID(set.getInt("M_REFID"));
				bean_CRS_XML_MSGREF.setMESSAGEREFID(set.getString("MESSAGEREFID"));
				bean_CRS_XML_MSGREF.setCORRMESSAGEREFID(set.getString("CORRMESSAGEREFID"));
				bean_CRS_XML_MSGREF.setDOCTYPEINDIC(set.getString("DOCTYPEINDIC"));
				bean_CRS_XML_MSGREF.setD_REFID(set.getInt("D_REFID"));
				bean_CRS_XML_MSGREF.setDOCREFID(set.getString("DOCREFID"));
				bean_CRS_XML_MSGREF.setCORRDOCREFID(set.getString("CORRDOCREFID"));
				bean_CRS_XML_MSGREF.setYYYYMMDDHHMISS(set.getString("YYYYMMDDHHMISS"));
				bean_CRS_XML_MSGREF.setSYS_DATE(set.getDate("SYS_DATE"));
				bean_CRS_XML_MSGREF.setSENDINGCOMPANYIN(set.getString("SENDINGCOMPANYIN"));
				bean_CRS_XML_MSGREF.setTRANSMITTINGCOUNTRY(set.getString("TRANSMITTINGCOUNTRY"));
				bean_CRS_XML_MSGREF.setRECEIVINGCOUNTRY(set.getString("RECEIVINGCOUNTRY"));
				bean_CRS_XML_MSGREF.setMESSAGETYPE(set.getString("MESSAGETYPE"));
				bean_CRS_XML_MSGREF.setWARNING(set.getString("WARNING"));
				bean_CRS_XML_MSGREF.setCONTACT(set.getString("CONTACT"));
				bean_CRS_XML_MSGREF.setMESSAGETYPEINDIC(set.getString("MESSAGETYPEINDIC"));
				bean_CRS_XML_MSGREF.setREPORTINGPERIOD(set.getString("REPORTINGPERIOD"));
				bean_CRS_XML_MSGREF.setR_RESCOUNTRYCODE(set.getString("R_RESCOUNTRYCODE"));
				bean_CRS_XML_MSGREF.setR_TIN(set.getString("R_TIN"));
				bean_CRS_XML_MSGREF.setR_NAME(set.getString("R_NAME"));
				bean_CRS_XML_MSGREF.setR_COUNTRYCODE(set.getString("R_COUNTRYCODE"));
				bean_CRS_XML_MSGREF.setR_ADDRESSFREE(set.getString("R_ADDRESSFREE"));
				array.add(bean_CRS_XML_MSGREF);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			closeQuietly(conn, st, set);
		}
		return array;
	}

	public void updateCRS_XML_MSGREF(String columeName, String columeValue) {
		Connection conn = connect();
		String sql = "UPDATE CRS_XML_MSGREF SET ? = ?";
		PreparedStatement st = null;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, columeName);
			ps.setString(2, columeValue);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			closeQuietly(conn, st, null);
		}

	}

	public List<Bean_CRS_XML_RESCOUNTRYCODE> selectCRS_XML_RESCOUNTRYCODE() {
		ResultSet set = null;
		Connection conn = connect();
		String sql = "SELECT * FROM CRS_XML_RESCOUNTRYCODE";
		PreparedStatement st = null;
		ArrayList<Bean_CRS_XML_RESCOUNTRYCODE> array = new ArrayList<Bean_CRS_XML_RESCOUNTRYCODE>();
		try {
			st = conn.prepareStatement(sql);
			set = st.executeQuery();
			while (set.next()) {
				Bean_CRS_XML_RESCOUNTRYCODE bean_CRS_XML_RESCOUNTRYCODE = new Bean_CRS_XML_RESCOUNTRYCODE();
				bean_CRS_XML_RESCOUNTRYCODE.setSNAP_YYYYMMDD(set.getString("SNAP_YYYYMMDD"));
				bean_CRS_XML_RESCOUNTRYCODE.setM_REFID(Integer.parseInt(set.getString("M_REFID")));
				bean_CRS_XML_RESCOUNTRYCODE.setMESSAGEREFID(set.getString("MESSAGEREFID"));
				bean_CRS_XML_RESCOUNTRYCODE.setID(set.getString("ID"));
				bean_CRS_XML_RESCOUNTRYCODE.setRESCOUNTRYCODE(set.getString("RESCOUNTRYCODE"));
				bean_CRS_XML_RESCOUNTRYCODE.setTIN(set.getString("TIN"));
				array.add(bean_CRS_XML_RESCOUNTRYCODE);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			closeQuietly(conn, st, set);
		}
		return array;
	}

	public ResultSet selectCRS_XML_SUBSOWNER() {
		ResultSet set = null;
		Connection conn = connect();
		String sql = "SELECT * FROM CRS_XML_SUBSOWNER";
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(sql);
			set = st.executeQuery();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			closeQuietly(conn, st, set);
		}
		return set;
	}
	

	public void close(Connection conn, PreparedStatement stmt, ResultSet rs) throws SQLException {
		try {
			if (rs != null) {
				rs.close();
			}
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} finally {
//				if (conn != null) {
//					System.out.println("connection closed");
//					conn.close();
//				}
			}
		}
	}

	public void closeQuietly(Connection conn, PreparedStatement stmt, ResultSet rs) {
		try {
			close(conn, stmt, rs);
		} catch (SQLException e) {
			
		}
	}
}
