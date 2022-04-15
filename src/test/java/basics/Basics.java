package basics;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*; // static import for static packages for given, when, then etc


public class Basics {

	// given - all input parameters 
	// when - submit api request 
	// then - validate the response
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI =  "https://rahulshettyacademy.com";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body("{\r\n"
				+ "  \"location\": {\r\n"
				+ "    \"lat\": -38.383494,\r\n"
				+ "    \"lng\": 33.427362\r\n"
				+ "  },\r\n"
				+ "  \"accuracy\": 50,\r\n"
				+ "  \"name\": \"Sharma house\",\r\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\r\n"
				+ "  \"types\": [\r\n"
				+ "    \"shoe park\",\r\n"
				+ "    \"showroom\"\r\n"
				+ "  ],\r\n"
				+ "  \"website\": \"http://shouse.com\",\r\n"
				+ "  \"language\": \"French-IN\"\r\n"
				+ "}").when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200);
		
				
	}

}
