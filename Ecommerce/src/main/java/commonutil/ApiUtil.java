package commonutil;

import static io.restassured.RestAssured.given;

import java.util.Map;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

/** 
 * Utility to handle the RESTful API.
 * 
 * @author Sujyonta.Kumar */


public final class ApiUtil {
    
    /** private constructor. */
    private ApiUtil() {
        
    }

    /**
     * Perform a POST request to the <code>url</code> with the <code>headers</code>.
     * 
     * @param url - the REST api that you want to access
     * @param headers - headers object that you want to send it across
     * @param inputJson - Input JSON you want to post
     * @return The {@link Response} of the request.
     * 
     * @author Sujyonta.Kumar
     */
    public static Response postRequest(final String url, final Map<String, String> headers, final String inputJson) {
        return given().headers(headers).body(inputJson).post(url).then().extract().response();
    }

    
    /**
     * Perform a GET request to the <code>url</code> with the <code>headers</code>.
     * 
     * @param url - the REST api that you want to access
     * @param headers - headers object that you want to send it across
     * @param inputJson - Input JSON you want to get
     * @return The {@link Response} of the request.
     * 
     * @author Sujyonta.Kumar
     */
    public static Response getRequest(final String url, final Map<String, String> headers, final String inputJson) {
        return given().headers(headers).body(inputJson).get(url).then().extract().response();
    }

    /**
     * Perform a DELETE request to the <code>url</code> with the <code>headers.</code>
     * 
     * @param url - the REST api that you want to access
     * @param headers - headers object that you want to send it across
     * @param inputJson - Input JSON you want to delete
     * @return The {@link Response} of the request.
     * 
     * @author Sujyonta.Kumar
     */
    public static Response deleteRequest(final String url, final Map<String, String> headers, final String inputJson) {
        return given().headers(headers).body(inputJson).delete(url).then().extract().response();
    }

    /**
     * Perform a PUT request to the <code>url</code> with the <code>headers.</code>
     * 
     * @param url - the REST api that you want to access
     * @param headers - headers object that you want to send it across
     * @param inputJson - Input JSON you want to put
     * @return The {@link Response} of the request.
     * 
     * @author Sujyonta.Kumar
     */
    public static Response putRequest(final String url, final Map<String, String> headers, final String inputJson) {
        return given().headers(headers).body(inputJson).put(url).then().extract().response();
    }
    
    /**
    * Get the status code of the response.
    *
    * @param response the Response body
    * @return {@link int} The status code of the response.
    */
    public static int getStatusCode(final Response response) {
        return response.getStatusCode();
    }
    
    /**
    * Returns the response body.
    * 
    * @param response the Response body
    *
     * @return The {@link Response} response body.
    */
    public static ResponseBody<?> getResponseBody(final Response response) {
        return response.getBody();
    }
    
    /**
    * Get the status line of the response.
    * 
    * @param response the Response body
    * @return {@link String} status line of the response
    */
    public static String getStatusLine(final Response response) {
        return response.getStatusLine();
    }
    
    /**
     * Perform a POST request to the <code>url</code> with the <code>headers</code> and with <code>cookies</code>.
     * 
     * @param url - the REST api that you want to access
     * @param headers - headers object that you want to send it across
     * @param cookies - cookies object that you want to send it across to keep session live
     * @param inputJson - Input JSON you want to post
     * @return The response of the request.
     * 
     * @author Sujyonta.Kumar
     */
    public static Response postRequest(final String url, final Map<String, String> headers, final Map<String, String> cookies, final String inputJson) {
        return given().cookies(cookies).headers(headers).body(inputJson).post(url).then().extract().response();
    }
    
    /**
     * Perform a GET request to the <code>url</code> with the <code>headers</code> and <code>cookies</code>.
     * 
     * @param url - the REST api that you want to access
     * @param headers - headers object that you want to send it across
     * @param cookies - cookies object that you want to send it across to keep session live
     * @return The response of the request.
     * 
     * @author Sujyonta.Kumar
     */
    public static Response getRequest(final String url, final Map<String, String> headers, final Map<String, String> cookies) {
        return given().cookies(cookies).headers(headers).get(url).then().extract().response();
    }
    
    /**
     * Perform a PUT request to the <code>url</code> with the <code>headers</code> and <code>cookies</code>.
     * 
     * @param url - the REST api that you want to access
     * @param headers - headers object that you want to send it across
     * @param cookies - cookies object that you want to send it across
     * @param inputJson - Input JSON you want to put
     * @return The response of the request.
     * 
     * @author Sujyonta.Kumar
     */
    public static Response putRequest(final String url, final Map<String, String> headers, final Map<String, String> cookies, final String inputJson) {
        return given().cookies(cookies).headers(headers).body(inputJson).put(url).then().extract().response();
    }
}