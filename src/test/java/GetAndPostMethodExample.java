import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class GetAndPostMethodExample {
	
	//@Test
	public void testGet(){
		baseURI="https://reqres.in/api/";
		given().get("/users?page=2").then().statusCode(200).
		body("data[1].first_name",equalTo("Lindsay")).
		body("data.first_name",hasItems("Rachel","Tobias"));
	}
	@Test
	public void testPost(){
		baseURI="https://reqres.in/api/";
		
		JSONObject request=new JSONObject();
		request.put("language","java");
		request.put("job","teacher");
		
		given().
			header("Content-Type","application/json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			post("/users").
		then().
			statusCode(201).log().all();
	}
	
	
	
}
