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
public class ThirdScenario {
	
	
	@Given("^Check Host Server disconnected")
	public void Hostshutdown() throws InterruptedException
	{
		
		System.out.println("--------------Third Scenario started--------------------");
		 given()
		   .when().post("http://localhost:8050/__admin/shutdown");
        Thread.sleep(5000);
        System.out.println("Server 8050 disconnected successfully");
	}
	
	
	
	@When("^Verify host server connection not available and stopped second server") 
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
           
           given()
		   .when().post("http://localhost:8056/__admin/shutdown");
          Thread.sleep(5000);
          System.out.println("Server 8056 disconnected successfully");

       }
	    
	    
	    }
	
		
	    @Then("^Verify second server is not available")
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
		          
		           System.out.println("----Server 8056 is not connected-------");
		       }
		       	
	    }
	   
	    
	    
	    @Then("^Verify API Test should not work on Host server when its not available")
		public void test()
	{
	    	try
	    	{
	    		
	        given().
            when().
            get("http://localhost:8050/api/mytest").
            then().
            assertThat().statusCode(200);   			   
		  
			System.out.println(" Server 8050 working FINE");
		
       } catch(Exception e) {
           assertTrue(e.getMessage().equals("Connection refused: connect"));    
           System.out.println("----Server 8050 is not connected-------");
 
       }
	    	
	}

	    
	
	
}

