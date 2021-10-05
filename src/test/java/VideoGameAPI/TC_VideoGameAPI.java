package VideoGameAPI;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;


public class TC_VideoGameAPI {
	
	
	@Test(priority=1)
	public void test_getAllVideoGames(){
		
		given()
		.when()
			.get("http://localhost:8080/app/videogames")
		.then()
			.statusCode(200);
		
	}
	
	@Test(priority=2)
	public void test_addNewVideoGame(){
		
		HashMap data=new HashMap();
		data.put("id","100");
		data.put("name","awesomemaster");
		data.put("releaseDate","2021-10-05T14:10:22.547Z");
		data.put("reviewScore", "5");
		data.put("category", "Adventure");
		data.put("rating","Universal");
		Response res=
			given()
				.contentType("application/json")
				.body(data)
			.when()
				.post("http://localhost:8080/app/videogames")
			.then()
				.statusCode(200)
				.log().body()
				.extract().response();
		
		String jsonString=res.asString();
		Assert.assertEquals(jsonString.contains("Record Added Successfully"),true);
		
		
	}
	
	@Test(priority=3)
	public void test_getSingleGameId(){
		given()
		.when()
			.get("http://localhost:8080/app/videogames/100")
		.then()
			.statusCode(200)
			.log().body()
			.body("videoGame.id",equalTo("100"))
			.body("videoGame.name", equalTo("awesomemaster"));
				
	}
	
	@Test(priority=4)
	public void test_updateRecord(){
		HashMap data=new HashMap();
		data.put("id","100");
		data.put("name","chandelier");
		data.put("releaseDate","2021-10-05T14:10:22.547Z");
		data.put("reviewScore", "7");
		data.put("category", "Adventure");
		data.put("rating","Universal");
		
		
		given()
			.contentType("application/json")
			.body(data)		
		.when()
			.put("http://localhost:8080/app/videogames/100")
		.then()
			.statusCode(200)
			.log().body()
			.body("videoGame.id",equalTo("100"))
			.body("videoGame.name",equalTo("chandelier"))
			.body("videoGame.reviewScore", equalTo("7"));
	}
	
	@Test(priority=5)
	public void test_deleteSingleVideoGame() throws InterruptedException{
		Response res=
		
		given()
		.when()
			.delete("http://localhost:8080/app/videogames/100")
		.then()	
			.statusCode(200)
			.log().body()
			.extract().response();
	Thread.sleep(3000);
	String jsonString=res.asString();
	Assert.assertEquals(jsonString.contains("Record Deleted Successfully"), true);
			
	}
	
}
