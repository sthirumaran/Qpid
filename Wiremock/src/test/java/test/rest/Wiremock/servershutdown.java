package test.rest.Wiremock;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpMethod.GET;

import java.io.IOException;

import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static io.restassured.RestAssured.given;


public class servershutdown {


	public void Hostshutdown() throws InterruptedException
	{
		 given()
		   .when().post("http://localhost:8050/__admin/shutdown");
        Thread.sleep(5000);
	}
	
	
	
	@Test
	public void run2() throws IOException, InterruptedException {
		
		String[] command = {"cmd.exe", "/C", "Start", "C:\\Users\\hi\\Downloads\\subash\\wiremock\\Final.bat"};
        Process p =  Runtime.getRuntime().exec(command);    
        Thread.sleep(10000);
        System.out.println(" Queue producer2 java Started Successfully");
		}
	}
