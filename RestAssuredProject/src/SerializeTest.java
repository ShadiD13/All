import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.AddPlace;
import pojo.Location;
public class SerializeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://rahulshettyacademy.com");
		p.setName("Shadi Al Daajah");
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		p.setTypes(myList);
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		p.setLocation(location);
		
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		Response response = given().queryParam("key","qaclick123")
		.body(p)
		.when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response();
		String responseString = response.asString();
		System.out.println(responseString);
		

	}

}
