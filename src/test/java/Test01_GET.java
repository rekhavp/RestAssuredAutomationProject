import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Test01_GET {
	
	@Test
	void test_01(){
		Response response=get("https://reqres.in/api/users?page=2");
		System.out.println(response.getStatusCode());
		System.out.println(response.getTime());
		System.out.println(response.getBody().asString());
		System.out.println(response.getStatusLine());
		System.out.println(response.getHeader("content-type"));
		int expectStatusCode=200;
		Assert.assertEquals(response.getStatusCode(),expectStatusCode );
		
	}
	
	@Test
	public void test_02(){
		baseURI="https://reqres.in/api";
		given().get("/users?page=2").
		then().statusCode(200).
		body("data[2].id",equalTo(9)).log().all();
	}
	

}
