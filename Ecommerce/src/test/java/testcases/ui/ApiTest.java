package testcases.ui;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.CoreMatchers.equalTo;

/** This is a Api practice class only
 * 
 * @author manish.gaur
 *
 */

public class ApiTest {

	public void getPost() {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		String response = given().log().all().when().get("/posts/1").then().log().all().assertThat().statusCode(200)
				.extract().response().asString();

		JsonPath js = new JsonPath(response);
		String title = js.getString("title");
		int id = js.getInt("id");
		System.out.println(title);
		System.out.println(id);
	}

	public static void getTitleFromId(int id) {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		String response = given().when().get("/posts").then().log().all().assertThat().statusCode(200).extract()
				.response().asString();
		JsonPath js = new JsonPath(response);
		int total_elements = js.getInt("size()");
		System.out.println(total_elements);
		for (int i = 0; i < total_elements; i++) {
			if(js.getInt("["+i+"].id")== id){
				System.out.println(js.getString("["+i+"].title"));
				break;
				
			}
		}

	}
	
	public static String payload(){
		return "{\r\n" + 
				"   \"title\":\"foo\",\r\n" + 
				"   \"body\":\"bar\",\r\n" + 
				"   \"userId\":1\r\n" + 
				"}";
	}
	
	public static void createPost(){
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		Response resp = given().header("Content-Type", "application/json").body(payload())
				.when().post("/posts")
				.then().assertThat().statusCode(201).body("userId", equalTo(1)).extract().response();

		System.out.println(resp.asString());
		
	}

	public static void main(String[] args) {
		createPost();
	}

}
