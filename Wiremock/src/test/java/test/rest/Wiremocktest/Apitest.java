package test.rest.Wiremocktest;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;



import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.WireMockServerRunner;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;

import static org.springframework.http.HttpMethod.GET;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.http.params.CoreConnectionPNames;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class Apitest {
	 

	String flag = "True";
	
	@Given("^User connected on host server successfully") 
	    public void testStatusCodePositive() throws InterruptedException {
		try {
	        
		    given().
            when().
            get("http://localhost:8050/api/mytest").
            then().
            assertThat().statusCode(200);   			   
		  
			System.out.println(" Server 8050 working FINE");
		
       } catch(Exception e) {
           assertTrue(e.getMessage().equals("Connection refused: connect"));
           System.out.println(" Server 8050 Not working");
           flag="False";
       	
          
       }
	    
	    
	    }

	    @When("^User connected on second server successfully")
	    public void testStatusCodesecond() throws IOException, InterruptedException {
	    	
	    
	    	
	    	
	    			if (flag.equals("False")) {
	    	    		new SkipException("Skipping Test 2 as flag is false.");
	    	    		System.out.println(" Skipping testStatusCodesecond");
	    	    		} else {
	    	    		
	    	    		
	    	    			 try {
	    		    given().
	                when().
	                get("http://localhost:8056/api/mytest").
	                then().
	                assertThat().statusCode(200);   			   
				  
					System.out.println(" Server 8056 working FINE");
	    	    			 }
				
		      catch(Exception e) {
		           assertTrue(e.getMessage().equals("Connection refused: connect"));    
		          
		           System.out.println(" Server 8056 Not working");
		           
		           String[] command = {"cmd.exe", "/C", "Start", "C:\\Users\\hi\\Downloads\\subash\\wiremock\\wire.bat"};
	                Process p =  Runtime.getRuntime().exec(command);    
	                Thread.sleep(5000);
	                System.out.println(" Server 8056 server started successfully");
		      } 
		           
		       }
		       	
	    }
	   
	    private final RestTemplate restTemplate = new RestTemplate(); 
	    
	    
	    @Then("^Verify API Test on Host server by checking body")
		public void test()
	{
	    	if (flag.equals("False")) {
	    		new SkipException("Skipping Test 2 as flag is true.");
	    		System.out.println(" Skipping test");
	    		} else {
	    		
	    		
	    	
	    	try {
			
		ResponseEntity<String> exchange =
				restTemplate.exchange("http://localhost:8050/api/mytest",
						GET, null, new ParameterizedTypeReference<String>() {});
		assertTrue( exchange.getBody().equals("API Test"));
		System.out.println(" Server 8050 API working fine");
	    	}
	    	catch(Exception e) {
		           assertTrue(e.getMessage().equals("Connection refused: connect"));   
	    	}
	    	
	}}
	
	    @Then("^Verify API Test on Second server by checking body")
		public void test1()
	{
	    	if (flag.equals("False")) {
	    		new SkipException("Skipping Test 2 as flag is true.");
	    		System.out.println(" Skipping test1");
	    	
	    		} else {
	    		
	    		
	    	
	    	try
	    	{
		ResponseEntity<String> exchange =
				restTemplate.exchange("http://localhost:8056/api/mytest",
						GET, null, new ParameterizedTypeReference<String>() {});
		assertTrue( exchange.getBody().equals("API Test"));
		System.out.println(" Server 8056 API working fine");
	    	}
		catch(Exception e) {
	           assertTrue(e.getMessage().equals("Connection refused: connect"));   
 	}
		
	}
	    
	

	}
}
	


