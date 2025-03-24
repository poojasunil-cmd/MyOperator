package Tests;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class PutPatchDelete {
    @Test
	public void testPut() {
	     // Create a JSONObject and add data to it
	     JSONObject req = new JSONObject();
	     req.put("name", "Pooja");
	     req.put("job", "QA - Intern");

	   
	     System.out.println(req);

	    
	     baseURI = "https://reqres.in/api";

	     // Send a POST request with the JSON data
	     given().
	         contentType("application/json"). // Content-Type header
	         body(req.toJSONString()).       // Convert JSONObject to a string and set as the request body
	     when().
	         put("users/2").                 // Send the POST request
	     then().
	         statusCode(200).  
	         log().all(). // logs all the data
	         body("name", equalTo("Pooja")). // Validate the response body
	         body("job", equalTo("QA - Intern"));
}
	@Test
	public void testPatch() {
	     // Create a JSONObject and add data to it
	     JSONObject req = new JSONObject();
	     req.put("name", "Pooja");
	     req.put("job", "QA - Intern");

	   
	     System.out.println(req);

	    
	     baseURI = "https://reqres.in";

	     // Send a POST request with the JSON data
	     given().
	         contentType("application/json"). // Content-Type header
	         body(req.toJSONString()).       // Convert JSONObject to a string and set as the request body
	     when().
	         patch("/api/users/2").                 // Send the POST request
	     then().
	         statusCode(200).  
	         log().all(). // logs all the data
	         body("name", equalTo("Pooja")). // Validate the response body
	         body("job", equalTo("QA - Intern"));
}
	@Test
	public void testDelete() {
	  
	     baseURI = "https://reqres.in";
	     when().
	         delete("/api/users/2").                 // Send the POST request
	     then().
	         statusCode(204).  
	         log().all();// logs all the data
	        }
}
