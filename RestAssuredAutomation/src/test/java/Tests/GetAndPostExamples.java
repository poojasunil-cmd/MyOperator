package Tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class GetAndPostExamples {
	@Test
	public void testGet() {
		
		baseURI = "https://reqres.in/api";
	
		given().
		  get("/users?page=2").
		then().
		  statusCode(200).
		  body("data[4].first_name",equalTo("George")).
		  body("data.first_name",hasItems("George","Rachel"));
	}
	
	 @SuppressWarnings("unchecked")
	@Test
	 public void testPost() {
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
	         post("/users").                 // Send the POST request
	     then().
	         statusCode(201).  
	         log().all(). // logs all the data
	         body("name", equalTo("Pooja")). // Validate the response body
	         body("job", equalTo("QA - Intern"));
	 }
			
	}

