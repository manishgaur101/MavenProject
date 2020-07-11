package testcases.ui;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.path.json.JsonPath;

public class JsonReader {

	
	
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
	
	
	
	
	static public Object getJsonData(String jsonString, String jsonpath){
		JsonPath js = new JsonPath(jsonString);
		String value = js.getString(jsonpath);
		return value;
	}
	
	public static void main(String[] args) {
		String jsonString = "{\r\n" + 
				"   \"_id\":{\r\n" + 
				"      \"$oid\":\"5f06c8c87a26569e9de2c5c2\"\r\n" + 
				"   },\r\n" + 
				"   \"username\":\"mgaur101gmail.com\",\r\n" + 
				"   \"password\":\"XYZ22\"\r\n" + 
				"}";
		String username_jsonpath  = "username";
		String password_jsonpath = "password";
		Object username = getJsonData(jsonString,username_jsonpath);
		System.out.println(username);
		System.out.println(getJsonData(jsonString,password_jsonpath));

	}

}
