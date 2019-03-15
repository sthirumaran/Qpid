Feature: To check whether two server connected successfully

@First
Scenario: To check whether two server connected successfully Test Scenario
Given User connected on host server successfully
Given User connected on second server successfully
Then Check an API Test in Host server by checking body
Then Check an API Test on Second server by checking body


@Second
Scenario: To check whether second server started when its not available Test Scenario
Given Check Second Server disconnected
Given Verify host server connected successfully
When Verify second server started when its is not connected.
Then Verify API Test on Host server.
Then Verify API Test on Second server.


@Third
Scenario: To check whether second server stopped when host server not available Test Scenario
Given Check Host Server disconnected
When Verify host server connection not available and stopped second server
Then Verify second server is not available
Then Verify API Test should not work on Host server when its not available



 


 