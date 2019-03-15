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

public class FirstScenario {
	
	
	
	@Given("^User connected on host server successfully") 
    public void testStatusCodePositive() throws InterruptedException {
	try {
		System.out.println("---------First Scenario started--------------");
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
	
    @Given("^User connected on second server successfully")
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
	           System.out.println(" ---Server 8056 Not Connected---");
	      
	           
	       }
	       	
    }
   
    private final RestTemplate restTemplate = new RestTemplate(); 
    
	
    @Then("^Check an API Test in Host server by checking body")
    public void test()
{
    	
    	try {
		
	ResponseEntity<String> exchange =
			restTemplate.exchange("http://localhost:8050/api/mytest",
					GET, null, new ParameterizedTypeReference<String>() {});
	assertTrue( exchange.getBody().equals("API Test"));
	String A = exchange.getBody();
	System.out.println(" Server 8050 API working fine");
    	}
    	catch(Exception e) {
	           assertTrue(e.getMessage().equals("Connection refused: connect")); 
	           System.out.println(" ---Server 8050 Not Connected---");
    	}
    	
}
	
    @Then("^Check an API Test on Second server by checking body")
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
