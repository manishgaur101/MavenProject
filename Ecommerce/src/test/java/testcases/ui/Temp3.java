package testcases.ui;

import java.util.List;

import org.bson.Document;

import commonutil.DBUtil;

public class Temp3 {

	public static void main(String[] args) {
		MongoConnect mn = new MongoConnect("ecommerce", "logindetails");
		//Create condition
		
		mn.condition("password", "eq", "ZOOM10");
		mn.condition("username", "EQ", "mgaur101@gmail.com");
		System.out.println(mn.searchCondition(mn.getQuery()));
		mn.clear();
		List<Document> documents = mn.searchCondition(mn.condition("username", "EQ", "mgaur101@gmail.com"));
		
		String fieldValue = DBUtil.getMongoDbFieldValue(documents, "_id");
		
	}

}
