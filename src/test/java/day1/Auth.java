package day1;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
public class Auth {
  @Test(enabled=false)
  public void f()
  {
	  given()
	  .proxy("192.168.100.1",8081)
	  .auth()
	  .oauth2("421b55eccdfe56d635166206a1324ca3db9d07fa")
	  .when()
	  .post("http://coop.apps.symfonycasts.com/api/426/chickens-feed")
	  .then()
	  .statusCode(200);
	  
  }
  @Test(enabled=true)
  public void f2() {
	  //Generating access token with server
	  Response R=given()
	  .proxy("192.168.100.1",8081)
	  .formParam("client_id", "DemoApp")
	  .formParam("client_secret", "382e6145fa4e37860ee5b3c6297c0a8d" )
	  .formParam("grant_type", "client_credentials")
	  .when()
	  .post("http://coop.apps.symfonycasts.com/token");
	  
	  
	  System.out.println(R.jsonPath().prettify());
	  String token=R.jsonPath().get("access_token");
	  System.out.println("Token is"+token);
	  
	  //using generated token
	  given()
	  .proxy("192.168.100.1",8081)
	  .auth()
	  .oauth2(token)
	  .when()
	  .post("http://coop.apps.symfonycasts.com/api/426/chickens-feed")
	  
	  .then()
	  .statusCode(200);
	  
  }
  
}
