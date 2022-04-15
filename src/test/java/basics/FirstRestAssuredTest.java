package basics;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Header;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class FirstRestAssuredTest {

	@Test
	public void GetBooksDetails() {
		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books";
		// Get the RequestSpecification of the request to be sent to the server.
		RequestSpecification httpRequest = RestAssured.given();
		// specify the method type (GET) and the parameters if any.
		// In this case the request does not take any parameters
		Response response = httpRequest.request(Method.GET, "");
		// Print the status and message body of the response received from the server
		System.out.println("Status received => " + response.getStatusLine());
		System.out.println("Response=>" + response.prettyPrint());

	}

	@Test
	public void GetWeatherDetailsCondensed() {
		// Specify the base URL to the RESTful web service
		RestAssured.baseURI = "https://demoqa.com/swagger/#/Account/AccountV1UserByUserIdGet";

		// Get the RequestSpecification of the request that is to be sent
		// to the server.
		RequestSpecification httpRequest = RestAssured.given();

		// Call RequestSpecification.get() method to get the response.
		// Make sure you specify the resource name.
		Response response = httpRequest.get("");

		// Response.asString method will directly return the content of the body
		// as String.
		System.out.println("Response Body is =>  " + response.asString());
	}

	@Test(enabled = false)
	public void GetWeatherDetails() {
		RestAssured.baseURI = "https://restapi.demoqa.com/utilities/weather/city";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("/Hyderabad");

		// Get the status code from the Response. In case of
		// a successfull interaction with the web service, we
		// should get a status code of 200.
		int statusCode = response.getStatusCode();

		// Assert that correct status code is returned.
		Assert.assertEquals(statusCode /* actual value */, 200 /* expected value */, "Correct status code returned");
	}

	@Test
	public void IteratingHeaders() {
		RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("");
		// Get all the headers and then iterate over allHeaders to print each header
		Headers allHeaders = response.headers();
		// Iterate over all the Headers
		for (Header header : allHeaders) {
			System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
		}
	}

	@Test
	public void GetBookHeaders() {
		RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("");
		// Access header with a given name.
		String contentType = response.header("Content-Type");
		System.out.println("Content-Type value: " + contentType);
		// Access header with a given name.
		String serverType = response.header("Server");
		System.out.println("Server value: " + serverType);
		// Access header with a given name. Header = Content-Encoding
		String acceptLanguage = response.header("Content-Encoding");
		System.out.println("Content-Encoding: " + acceptLanguage);
	}

	@Test
	public void ValidateBookHeaders() {
		RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books";
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get("");
		// Access header with a given name. Header = Content-Type
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType /* actual value */, "application/json; charset=utf-8" /* expected value */);
		// Access header with a given name. Header = Server
		String serverType = response.header("Server");
		Assert.assertEquals(serverType /* actual value */, "nginx/1.17.10 (Ubuntu)" /* expected value */);
	}
}