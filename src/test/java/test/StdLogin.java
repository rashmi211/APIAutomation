package test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StdLogin {
	public static String value="";
	
	@Test(priority=1)
	public void loginstory() throws IOException, ParseException {
		FileReader fr=new FileReader("C:\\Users\\HP\\eclipse-workspace1\\APIAutomation\\src\\main\\java\\files\\Data.json");
		JSONParser fp=new JSONParser();
		String data=fp.parse(fr).toString();
		System.out.println(data);
		Response response=RestAssured.given().baseUri("http://localhost:9009").body(data).contentType(ContentType.JSON)
		.when().post("/rest/auth/1/session")
		.then().log().body().extract().response();
		System.out.println(response.asString());
		System.out.println(response.getStatusCode());
		
		JSONObject js=new JSONObject(response.asString());
		value="JSESSIONID="+js.getJSONObject("session").get("value").toString();
		
	}
	
	@Test(priority=2)
	public void createuserstory() throws IOException, ParseException {
		FileReader fr=new FileReader("C:\\Users\\HP\\eclipse-workspace1\\APIAutomation\\src\\main\\java\\files\\CreateUserstory.json");
		JSONParser jp=new JSONParser();
		String data=jp.parse(fr).toString();
		System.out.println(data);
		
		Response response=RestAssured.given().baseUri("http://localhost:9009").body(data).contentType(ContentType.JSON).header("Cookie",value)
		.when().post("/rest/api/2/issue")
		.then().log().body().extract().response();
		System.out.println(response.asString());
		System.out.println(response.getStatusCode());
		
	//	JSONObject js=new JSONObject(response.asString());
	//	value="JSESSIONID"+js.getJSONObject("session").get("key").toString();	
		}
	}

