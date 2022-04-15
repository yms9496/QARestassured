package basics;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JsonAsFile {

	// given - all input parameters 
	// when - submit api request 
	// then - validate the response
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		RestAssured.baseURI =  "https://rahulshettyacademy.com";
		
		Response res = given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(new String(Files.readAllBytes(Paths.get("./src/test/java/basics/JsonAsFile.java")))).when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response();
		
		System.out.println(res.statusCode());
		System.out.println(res.statusLine());
				
	}
}
