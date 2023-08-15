package advance;

import java.io.IOException;

import org.json.JSONObject;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import test.ReadPropertiesfile;
import utility.ReadInputs;
import utility.ReadPropertis;

public class E2E_UserStory {
	
	public static String keyvalue;
	public static String jsessionid;
	public static String issueID;
	
	
	@Test(priority=1)
	public void logintoJira() throws IOException, ParseException {
		String responsebody=ReadInputs.readJsonData("C:\\Users\\HP\\eclipse-workspace1\\APIAutomation\\src\\main\\java\\afiles\\logintojira.json");
		
		Response response=RestAssured.given().baseUri(ReadPropertis.readProperties("URL")).body(responsebody).contentType(ContentType.JSON)
		.when().post("/rest/auth/1/session")
		.then().log().body().extract().response();

		JSONObject jb=new JSONObject(response.asString());
		jsessionid="JSESSIONID="+jb.getJSONObject("session").get("value").toString();
		
		System.out.println(response.asString());
		System.out.println(response.getStatusCode());
		
	}
	
	@Test(priority=2)
	public void Createuserstory() throws IOException, ParseException {
	    String responsebody=ReadInputs.readJsonData("C:\\Users\\HP\\eclipse-workspace1\\APIAutomation\\src\\main\\java\\afiles\\CreateUserstory.json");
		
		JSONObject updatebody=new JSONObject(responsebody);
		updatebody.getJSONObject("fields").getJSONObject("issuetype").put("name","Story");
		
		Response response=RestAssured.given().baseUri(ReadPropertis.readProperties("URL")).body(updatebody.toString()).contentType(ContentType.JSON).header("Cookie",jsessionid)
				.when().post("/rest/api/2/issue")
				.then().log().body().extract().response();
				System.out.println(response.asString());
				System.out.println(response.getStatusCode());
					
		JSONObject jb=new JSONObject(response.asString());
		issueID=jb.get("key").toString();
		System.out.println(issueID);				
}
}