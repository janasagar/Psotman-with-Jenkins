package api_testsuite;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
public class Reqres_api {
	
	
	@Test
	public void list_users() {
		given().get("https://reqres.in/api/users?page=2").then().statusCode(200).log().all();
				
	}
	@Test
	public void single_user() {
		given().
			get("https://reqres.in/api/users/2").
		then().
			statusCode(200).log().all();
	}
	@Test
	public void single_user_not_found() {
		given().
			get("https://reqres.in/api/users/23").
		then().
			statusCode(404).log().all();
	}
	@Test
	public void list_resources() {
		given().
			get("https://reqres.in/api/unknown").
		then().
			statusCode(200).log().all();
		
	}
	@Test
	public void lsingle_resources() {
		given().
			get("https://reqres.in/api/unknown/2").
		then().
			statusCode(200).log().all();
		
	}
	@Test
	public void lsingle_resources_not_found() {
		given().
			get("https://reqres.in/api/unknown/23").
		then().
			statusCode(404).log().all();
		
	}
	@Test
	public void create() {
		JSONObject req_body = new JSONObject();
		req_body.put("name", "morpheus");
		req_body.put("job", "leader");
		given().body(req_body.toJSONString()).when().post("https://reqres.in/api/users").then().statusCode(201).log().all();
	}
	
	@Test
	public void update() {
		JSONObject req_body = new JSONObject();
		req_body.put("name", "morpheus");
		req_body.put("job", "zion resident");
		given().body(req_body.toJSONString()).when().put("https://reqres.in/api/users/2").then().statusCode(200).log().all();
	}
	@Test
	public void update1() {
		JSONObject req_body = new JSONObject();
		req_body.put("name", "morpheus");
		req_body.put("job", "zion resident");
		given().body(req_body.toJSONString()).when().patch("https://reqres.in/api/users/2").then().statusCode(200).log().all();
	}
	@Test
	public void delete() {
		given().
			delete("https://reqres.in/api/users/2").
		then().
			statusCode(204).log().all();
	}
	@Test
	public void reg_successful() {
		JSONObject req_body = new JSONObject();
		req_body.put("email", "eve.holt@reqres.in");
		req_body.put("password", "pistol");
		given().header("Content-Type", "application/json").body(req_body.toJSONString()).when().post("https://reqres.in/api/register").then().statusCode(200).log().all();
	}
	@Test
	public void reg_not_successful() {
		JSONObject req_body = new JSONObject();
//		req_body.put("email", "eve.holt@reqres.in");
		req_body.put("password", "pistol");
		given().body(req_body.toJSONString()).when().post("https://reqres.in/api/register").then().statusCode(400).log().all();
	}
	@Test
	public void login() {
		JSONObject req_body = new JSONObject();
		req_body.put("email", "eve.holt@reqres.in");
		req_body.put("password", "cityslicka");
		given().header("Content-Type", "application/json").body(req_body.toJSONString()).when().post("https://reqres.in/api/login").then().statusCode(200).log().all();
	}
	@Test
	public void login_not_successful() {
		JSONObject req_body = new JSONObject();
		req_body.put("email", "peter@klaven");
//		req_body.put("password", "pistol");
		given().body(req_body.toJSONString()).when().post("https://reqres.in/api/login").then().statusCode(400).log().all();
	}
	@Test
	public void delay() {
		given().
			get("https://reqres.in/api/users?delay=3").
		then().statusCode(200).log().all();	
	}

}
