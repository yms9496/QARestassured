package rsa;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.Payload;
import static files.ReusableUtilities.*;


public class E2ETest1 {

	// Test - Add Place > Update place with new address > Get Place to validate update
	
	
	public static void main(String rgs[]) {
		
		JsonPath js;
		
		////////////////////////////////////////////////////////////Add Place///////////////////////////////////////
		
		// Adding the place and storing response as String
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(Payload.addPlace())
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200)
		.body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		// Parsing reponse String as a JSOn
		js = rawToJson(response);
		
		// Get Place IS as a string rom json response
		String placeID = js.getString("place_id");
		
		System.out.println(placeID);
		////////////////////////////Update place with new address//////////////////////////////////////////
		
		String newAddress = "Hamster Stret, New York, Indore";
		
		// Update the place wit new address 
		response = given().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(Payload.putPlace(placeID, newAddress))
		.when().put("maps/api/place/update/json")
		.then().assertThat().statusCode(200)
		.body("msg", equalTo("Address successfully updated"))
		.header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();

		System.out.println(response);
		
		////////////////////////// validate successful update////////////////////////////////////////
		response = given().queryParam("key", "qaclick123").queryParam("place_id", placeID)
		.when().log().all().get("maps/api/place/get/json")
		.then().assertThat().statusCode(200)
		.body("address", equalTo(newAddress))
		.header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();

		// parse response as json
		js = rawToJson(response);
		
		// Get actual address
		String actualAddress = js.getString("address");
		System.out.println(actualAddress+"          -           "+newAddress);
		
		Assert.assertEquals(actualAddress, newAddress, "Address not updated");
		
	}
}
