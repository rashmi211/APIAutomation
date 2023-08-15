package test;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Login {
	
	public static String value="";
	public static String issueid="";
	
	@Test(priority=1)
	public void login() {
	Response response=	RestAssured.given().baseUri("http://localhost:9009").body("{\r\n"
				+ "     \"username\": \"rashmi\",\r\n"
				+ "      \"password\": \"rashmi\"\r\n"
				+ " }").contentType(ContentType.JSON)
		.when().post("/rest/auth/1/session")
		.then().log().body().extract().response();
	
	System.out.println(response.asString());
	System.out.println(response.getStatusCode());
	
	
	JSONObject js= new JSONObject(response.asString());
	 value="JSESSIONID="+js.getJSONObject("session").get("value").toString();
	System.out.println(value);
	}
	
	@Test (priority=2)
	public void createUserstory() {
		 Response response = RestAssured.given().baseUri("http://localhost:9009").body(" \r\n"
		 		+ " {\r\n"
		 		+ "   \"fields\": {\r\n"
		 		+ "       \"project\": {\r\n"
		 		+ "           \"key\": \"AM\"\r\n"
		 		+ "       },\r\n"
		 		+ "       \"summary\": \"Creating new Userstory for API project\",\r\n"
		 		+ "       \"issuetype\": {\r\n"
		 		+ "           \"name\": \"Story\"\r\n"
		 		+ "       }\r\n"
		 		+ " }\r\n"
		 		+ " }").contentType(ContentType.JSON).header("Cookie",value)
		.when().post("/rest/api/2/issue")
		.then().log().body().extract().response();
		 
		 System.out.println(response.getStatusCode());
		 
		 JSONObject js= new JSONObject(response.asString());
		 issueid=js.get("key").toString();
		System.out.println(issueid);
	
	}
	@Test(priority=3)
	public void getuserstory() {
		Response response=RestAssured.given().baseUri("http://localhost:9009").contentType(ContentType.JSON)
		.header("cookie",value)
		.when().get("/rest/api/2/issue/"+issueid)
		.then().log().body().extract().response();
		System.out.println(response.getStatusCode());
		
	}
	@Test (priority=4)
	public void updateUserstory() {
		 Response response=RestAssured.given().baseUri("http://localhost:9009").body(" {\r\n"
		 		+ "   \"fields\": {\r\n"
		 		+ "       \"project\": {\r\n"
		 		+ "           \"key\": \"AM\"\r\n"
		 		+ "       },\r\n"
		 		+ "       \"summary\": \"Updating this userstory for API projet\",\r\n"
		 		+ "       \"issuetype\": {\r\n"
		 		+ "           \"name\": \"Story\"\r\n"
		 		+ "       }\r\n"
		 		+ " }\r\n"
		 		+ " }").contentType(ContentType.JSON).header("cookie",value)
		 .when().put("/rest/api/2/issue/"+issueid)
		 .then().log().body().extract().response();
		 System.out.println(response);
		 System.out.println(response.statusCode());
	}
	
	@Test(priority=5)
	public void deleteuserstory() {
		Response response=RestAssured.given().baseUri("http://localhost:9009").contentType(ContentType.JSON).header("cookie",value)
		.when().delete("/rest/api/2/issue/"+issueid)
		.then().log().body().extract().response();
		System.out.println(response.getStatusCode());
	}

}
