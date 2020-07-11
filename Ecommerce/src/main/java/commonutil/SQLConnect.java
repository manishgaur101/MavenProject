package commonutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import constants.FilePath;

public class SQLConnect {
	PropertyReader prop = new PropertyReader();
	String file_path = FilePath.ENV_PROPERTIES;
	private Connection con;
	private Statement stmt;
	private MongoClient mongoClient;
	private MongoDatabase db;
	private MongoCollection<Document> dbCollection;
	
	
	
	/**This is a parameterized constructor of DBConnect class
	 * 
	 * 
	 * @param 
	 * 		dbName - which database to connect - i.e. MySql, mongo
	 * 
	 * @author manish.gaur
	 */
	public SQLConnect(String dbName){
		if(dbName.equalsIgnoreCase("MySQL")){
		try {
			con = DriverManager.getConnection(prop.getValue(file_path, "url"),prop.getValue(file_path, "username"),prop.getValue(file_path, "password"));
			stmt=con.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else if(dbName.equalsIgnoreCase("mongo")){
		}
		
	}
	
	public SQLConnect(){
		
	}
	
	
	/**This method will print all the documents present in an collection
	 * 
	 * @param - 
	 *  	database - Name of the mongoDB
	 * @param - 
	 * 		collectionName - Name of the collection
	 * 
	 * @author manish.gaur
	 */
	public void getMongoData(String database, String collectionName){
		mongoClient = new MongoClient( "localhost" , 27017);
		 db = mongoClient.getDatabase(database);
		  dbCollection = db.getCollection(collectionName);
		  FindIterable<Document> iterDoc  = dbCollection.find();
		  int i = 1; 
	      // Getting the iterator 
	      Iterator it = iterDoc.iterator(); 
	      while (it.hasNext()) {  
	         System.out.println(it.next());  
	         i++; 
	      }     
		 
		}

	
	public void closeSqlConnection(){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public static void getSqlData(){
		try{
		PropertyReader prop = new PropertyReader();
		String file_path = FilePath.ENV_PROPERTIES;
		//Class.forName(prop.getValue(file_path, "sql_driver"));
		Connection con=DriverManager.getConnection(prop.getValue(file_path, "url"),prop.getValue(file_path, "username"),prop.getValue(file_path, "password"));
		Statement stmt=con.createStatement();

		ResultSet rs=stmt.executeQuery("select * from logindetails");

		while(rs.next())
		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));

		con.close();
		}
		catch(Exception e){
			
		}
		
		
		}
	
	
	/**This method returns row count of the query
	 * 
	 * @param = It takes tableName as parameter in String format
	 * 
	 * @return = row count of the result
	 * 
	 * @author manish.gaur
	 */
	public int getRowCountFromTable(String tableName) {
		int row_count = 0;
		try {
			ResultSet rs = stmt.executeQuery("select * from " + tableName + ";");
			while (rs.next()) {
				row_count++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return row_count;
	}
	
	
	/**This method returns row count from ResultSet
	 * 
	 * @param = It takes ResultSet as parameter
	 * 
	 * @return = row count int the ResultSet
	 * 
	 * @author manish.gaur
	 */
	public int getRowCountFromResultSet(ResultSet rs) {
		/*int rowCount = 0;
		try {
			rs.last();
			rowCount =  rs.getRow();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowCount;*/
		int rowCount = 0;
		try{
		
		while(rs.next()){
			rowCount++;
			
		}}
		catch(Exception e){
			e.getMessage();
		}
		return rowCount;
	}
	
	/**This method returns column count of the ResultSet
	 * 
	 * @param = It takes RestulSet as parameter
	 * 
	 * @return = Column count of the result
	 * 
	 * @author manish.gaur
	 */
		public int getColumnCount(ResultSet rs){
			ResultSetMetaData rsMeta;
			int total_col = 0;
			try {
				rsMeta = rs.getMetaData();
				total_col =  rsMeta.getColumnCount();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return total_col;
			
		}
		
		
		/**This method reads the sql query's result and return 
		 * as Map Type elements in Object[][] 
		 * 
		 * 
		 * @return
		 * 		Object[][] - Returns Every row's data as Map Type
		 * 
		 * @author manish.gaur
		 */
		public Object[][] getMapDataFromMySql(){
			Object[][] data = null;
			try{
				
				 Map<String, String> datamap;

			ResultSet rs=stmt.executeQuery("select * from logindetails");
			int colCount = getColumnCount(rs);
			//int rowCount = getRowCountFromResultSet(rs);
			int rowCount = getRowCountFromTable("logindetails");
			data = new Object[rowCount][1];
			for (int row = 0; row < rowCount; row++) {
	            datamap = new HashMap<>();
	            for (int col = 0; col < colCount; col++) {
	                datamap.put(rs.getString(2),rs.getString(3));
	            }
	            data[row][0] = datamap;
	        }
			
			}
			catch(Exception e){
				e.getMessage();
			}
			return data;
		
	}
	
	
	public static void main(String[] args) {
		//getSqlData();
		SQLConnect db = new SQLConnect();
		db.getMongoData("ecommerce","logindetails");
		
	}

}
