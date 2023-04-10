package BetikaTests;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestGetUserApi {
	  private static final String API_BASE_URL = "https://fakerestapi.azurewebsites.net/api/v1";
	  private static final String API_ENDPOINT = "/Users";

	    @BeforeClass
	   public void setup() {
	        RestAssured.baseURI = API_BASE_URL;
	    }
	    @Test
	   public void testGetUserApi() {
	        Response response = RestAssured.get(API_ENDPOINT);
	        int statusCode = response.getStatusCode();
	        System.out.println("Status Code: " + statusCode);
	        String responseBody = response.getBody().asString();
	        System.out.println("Response Body: " + responseBody);

	        // Assert that the response code is 200
	        Assert.assertEquals(statusCode, 200, "Expected status code: 200, but found: " + statusCode);

	        // Assert that 'User 6' is present in the response
	        Assert.assertTrue(responseBody.contains("User 6"), "Expected user 'User 6' not found in the response");
	        JsonPath jsonPath = response.jsonPath();

	        // Count the number of objects in the response
	        int objectCount = jsonPath.getList("").size();

	        // Print the object count
	        System.out.println("Number of objects in the response: " + objectCount);
	    }
}
