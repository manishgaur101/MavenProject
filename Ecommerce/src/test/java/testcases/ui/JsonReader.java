package testcases.ui;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import commonutil.FileInteraction;
import commonutil.PropertyReader;
import constants.FilePath;
import io.restassured.path.json.JsonPath;


public class JsonReader {

	/**
	 * Method to return Map from Json String using Jackson API
	 * 
	 * @param jsonString
	 * @return
	 * 		Map<String,String> - Json String converted into Map and returned
	 */
	
	static public Map<String,String> getJsonObjects(String jsonString){
		Map<String, String> map  = new HashMap<String,String>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			 map = mapper.readValue(jsonString, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return map;
		
		
	}
	
	/**
	 * Method to read Json data using jayway JsonPath class
	 * 
	 * @param jsonString
	 * @param jsonpath
	 * @return
	 * 		Object - value from the jsonString based on jsonPath
	 */
	
	static public Object getJsonDataUsingJayway(String filePath, String jsonpath){
		String jsonString = FileInteraction.readFileToString(filePath);
		String value = com.jayway.jsonpath.JsonPath.read(jsonString, jsonpath);
		return value;
	}
	
	
	/**
	 * Method to read Json data using RestAssured's JsonPath class
	 * 
	 * @param filePath is complete path of the file in which json is present
	 * @param jsonpath is the path of the element whose value needs to be extracted
	 * @return 
	 * 		Object - value of an element from the jsonString
	 * 		
	 */
	
	static public Object getJsonDataUsingJsonFilePath(String filePath, String jsonpath){
		String jsonString = FileInteraction.readFileToString(filePath);
		JsonPath js = new JsonPath(jsonString);
		String value = js.getString(jsonpath);
		return value;
	}
	
	
	static public Object getJsonDataUsingJsonString(String jsonString, String jsonpath){
		JsonPath js = new JsonPath(jsonString);
		String value = js.getString(jsonpath);
		return value;
	}
	
	/**
	 * Method to read Json data using Google's gson
	 * 
	 * @param filePath is complete path of the file in which json is present
	 * @param jsonpath is the path of the element whose value needs to be extracted
	 * @return 
	 * 		Object - value of an element from the jsonString
	 * 		
	 */
	
	static public void getTotalObjectCountUsingGson(String filePath){
		String jsonString = FileInteraction.readFileToString(filePath);
		JsonObject jsonObj = new JsonParser().parse(jsonString).getAsJsonObject();
		int total_elements = jsonObj.getAsJsonObject("address").size();
		System.out.println(total_elements);
	}
	
	public static void main(String[] args) {
		PropertyReader prop = new PropertyReader();
		System.out.println(getJsonDataUsingJsonFilePath(FilePath.json_file,prop.getValue(FilePath.jsonPath_file, "type2")));
		System.out.println(getJsonDataUsingJayway(FilePath.json_file,prop.getValue(FilePath.jsonPath_file, "lastName")));
		getTotalObjectCountUsingGson(FilePath.json_file);
		/*String jsonString = "{\r\n" + 
				"   \"_id\":{\r\n" + 
				"      \"$oid\":\"5f06c8c87a26569e9de2c5c2\"\r\n" + 
				"   },\r\n" + 
				"   \"username\":\"mgaur101gmail.com\",\r\n" + 
				"   \"password\":\"XYZ22\"\r\n" + 
				"}";*/
		//String jsonString = FilePath.json_file;
		
		//String username_jsonpath  = "username";
		//String password_jsonpath = "password";
		//Object username = getJsonData(jsonString,username_jsonpath);
		//System.out.println(username);
		//System.out.println(getJsonData(jsonString,password_jsonpath));

		//System.out.println(getJsonData(FilePath.json_file,prop.getValue(FilePath.jsonPath_file, "firstName")));
		
	}

}
