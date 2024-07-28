import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

import static io.restassured.RestAssured.*;

import java.io.File;

public class JiraTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI="http://localhost:8080";
		SessionFilter session = new SessionFilter();
		String response = given().header("Content-Type","application/json").body("{ \"username\": \"s.dajah\", \"password\": \"1986713\" }").log().all()
		.log().all().filter(session).when().post("/rest/auth/1/session").then().log().all().extract().response().asString();
		
		given().pathParam("id", "10004").log().all().header("Content-Type","application/json").body("{\r\n"
				+ "    \"body\": \"This is my first comment.\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session).when().post("/rest/api/2/issue/{id}/comment").then().log().all().assertThat().statusCode(201);
		
		given().header("Atlassian-Token","no-check").filter(session).pathParam("id", "10004")
		.header("Content-Type", "multipart/form-data")
		.multiPart("file", new File("JiraFile.txt")).when().post("/rest/api/2/issue/{id}/attachments").then().log().all();
	}

}
