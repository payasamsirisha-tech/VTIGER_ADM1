package POPpages_GenericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;
/**
 * @author Lakshmi 
 * this class is used to work with DataBase
 */
public class DatabaseUtility {
	
	/**
	 * This method is used to connect with DataBase by passing the url,username and password
	 * @throws SQLException
	 * 
	 */
	Connection con;
	
	public void getconnectWithDB(String url,String un,String pswd) throws SQLException {
		Driver driverobj=new Driver();
		
		DriverManager.registerDriver(driverobj);
		con=DriverManager.getConnection(url,un,pswd);
	
      }
	
	/**
	 * This method is used to connect with DB with credentials
	 * 
	 * @throws SQLException
	 */
	public void getconnectWithDB() throws SQLException {
		Driver driverobj=new Driver();
		DriverManager.registerDriver(driverobj);
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/advanceprj","root","root");
      }
	
	
	/**
	 * This method is used to fetch the data from database
	 * 
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public ResultSet fetchDataFromDataBAse(String query) throws SQLException {
		Statement state=con.createStatement();
		ResultSet data = state.executeQuery(query);
		return data;
    }
	
	/**
	 * This method is used to disconnect with the database
	 * 
	 * @throws SQLException
	 */
	public void discconectWithDB() throws SQLException {
		con.close();
	}
	
	
	/**
	 * This method is used to update the data to database
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public int updateDataTODB(String query) throws SQLException {
		Statement state = con.createStatement();
		int res=state.executeUpdate(query);
		return res;
	}

}
