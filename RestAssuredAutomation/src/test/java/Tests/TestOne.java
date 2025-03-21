package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestOne {
	@Test
	public void test_1() {	
		Response response = RestAssured.get("https://reqres.in/api/users?page=2");
		System.out.println(response.getStatusCode());
		System.out.println(response.getTime());
		System.out.println(response.getBody());
		System.out.println(response.getStatusLine());
		System.out.println(response.getHeader("content-type"));
		int StatusCode = response.getStatusCode();
		
		Assert.assertEquals(StatusCode, 200);
		
	}

}
