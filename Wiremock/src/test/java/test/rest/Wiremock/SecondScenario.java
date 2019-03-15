package test.rest.Wiremock;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;



import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
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

public class SecondScenario {
	
	
	@Given("^Check Second Server disconnected")
	public void Hostshutdown() throws InterruptedException
	{
		System.out.println("---------Second Scenario started--------------");
		try {
		 given()
		   .when().post("http://localhost:8056/__admin/shutdown");
        Thread.sleep(5000);
	
	} catch(Exception e) {
		e.getMessage();
		System.out.println(e);
	}
	}
	      
	

	@Given("^Verify host server connected successfully") 
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
       System.out.println(" ---Server 8050 Not Connected---");

   }
    
    
    }
	
	
    @When("^Verify second server started when its is not connected.")
    public void testStatusCodesecond() throws IOException, InterruptedException {
    	 try {
		        
    		    given().
                when().
                get("http://localhost:8056/api/mytest").
                then().
                assertThat().statusCode(200);   			   
			  
				System.out.println(" Server 8056 working FINE");
			
	       } catch(Exception e) {
	           assertTrue(e.getMessage().equals("Connection refused: connect"));    
	          
	          
	           
	           String[] command = {"cmd.exe", "/C", "Start", "C:\\Users\\hi\\Downloads\\subash\\wiremock\\wire.bat"};
                Process p =  Runtime.getRuntime().exec(command);    
                Thread.sleep(10000);
                System.out.println(" Server 8056 Started Successfully");
	       }
	       	
    }
   
    private final RestTemplate restTemplate = new RestTemplate(); 
    

    @Then("^Verify API Test on Host server.")
	public void test()
{
    	
    	try {
		
	ResponseEntity<String> exchange =
			restTemplate.exchange("http://localhost:8050/api/mytest",
					GET, null, new ParameterizedTypeReference<String>() {});
	assertTrue( exchange.getBody().equals("API Test"));
	System.out.println(" Server 8050 API working fine");
    	}
    	catch(Exception e) {
	           assertTrue(e.getMessage().equals("Connection refused: connect")); 
	           System.out.println(" ---Server 8050 Not Connected---");
    	}
    	
}
   
    @Then("^Verify API Test on Second server.")
	public void test1()
{
    	
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
           System.out.println(" ---Server 8056 Not Connected---");
	}
	
}

}
