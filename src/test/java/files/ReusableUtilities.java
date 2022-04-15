package files;

import io.restassured.path.json.JsonPath;

public class ReusableUtilities {

	public static JsonPath rawToJson(String rawData) {
		
		return new JsonPath(rawData); 
	}
}
