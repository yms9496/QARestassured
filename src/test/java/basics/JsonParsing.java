package basics;

import org.testng.Assert;

import files.Payload;
import files.ReusableUtilities;
import io.restassured.path.json.JsonPath;

// https://jsoneditoronline.org/ 

public class JsonParsing {

	public static void main(String ar[]) {

		JsonPath js = ReusableUtilities.rawToJson(Payload.jsonDataToParse());

		// Print nuber of courses returned by API
		int c_count = js.getInt("courses.size()");
		System.out.println(c_count);

		// Print purchase amount
		int p_amount = js.getInt("dashboard.purchaseAmount");
		System.out.println(p_amount);

		// Get first course title
		String title1 = js.getString("courses[0].title");
		System.out.println(title1);

		// print all course titles and their respective title
		for (int i = 0; i < c_count; i++) {

			// Get course title
			String title = js.getString("courses[" + i + "].title");
			System.out.println(title);

			// Get course price
			String price = js.getString("courses[" + i + "].price");
			System.out.println(price);
		}
		
		// print number of copies sold by RPA course 
		for (int i = 0; i < c_count; i++) {

			// Get course title
			String title = js.getString("courses[" + i + "].title");
			
			if(title.equalsIgnoreCase("RPA")) {
				
				String copies = js.getString("courses[" + i + "].copies");
				System.out.println("RPA sold no of copies = " + copies);
			}
			
		}
		
		
		// verify sum of all courses matches purchase amount
		int totalAmt = 0;
		for (int i = 0; i < c_count; i++) {
			
			totalAmt += (js.getInt("courses[" + i + "].price") * js.getInt("courses[" + i + "].copies"));			
		}

		System.out.println(totalAmt);
		Assert.assertEquals(totalAmt, p_amount, "Amount mismatch");

			
	}
}
