Feature: To check whether two server connected successfully

@First
Scenario: To check whether two server connected successfully Test Scenario
Given User connected on host server successfully
When User connected on second server successfully
Then Verify API Test on Host server by checking body
Then Verify API Test on Second server by checking body