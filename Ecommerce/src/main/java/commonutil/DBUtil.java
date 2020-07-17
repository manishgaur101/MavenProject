package commonutil;

import java.util.List;

import org.bson.Document;

import testcases.ui.JsonReader;

public class DBUtil {

	public static String getMongoDbFieldValue(List<Document> documents,String fieldPath){
		String fieldValue = null;
		Document doc = documents.get(0);
		String json = com.mongodb.util.JSON.serialize(doc);
		System.out.println("JSON serialized Document: " + json);
		fieldValue = JsonReader.getJsonDataUsingJsonString(json, fieldPath).toString();
		System.out.println(fieldValue);
		
		return fieldValue;
	}
}
