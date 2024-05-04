package APITestAutomation;



import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class schemaValidation {
	
	@Test
	public void testJsonSchema() {
	    String baseUri = "https://reqres.in";

	    Response response = RestAssured.given().baseUri(baseUri)
	            .when().get("/api/users/2")
	            .then().assertThat().statusCode(200)
	            .extract().response();

	    MatcherAssert.assertThat(
	            "Validate json schema",
	            response.getBody().asString(),
	            JsonSchemaValidator.matchesJsonSchemaInClasspath("C:\\Users\\akilan-18633\\eclipse-workspace\\APITestAutomation\\src\\test\\resources\\SchemaValidation\\val.json"));
	}	

}

