Feature: API Automation using Cucumber with Rest Assured


  Scenario: GET API - TEST
  Given a base url "https://reqres.in"
  And path is "/api/users?page=2"
  When user sends a get request
  Then status code should be 200
 	And print the response
  

  Scenario: POST API TEST
  Given a base url "https://reqres.in"
  And path is "/api/users?page=2"
  When user sends a post request with body "<name>" and "<job>"
  Then status code should be 201
 	And print the response
 	
 
 	
 	Examples:
	|name|job|
	|Ollie|Developer|
	|Andrew|Analyst|
	|Tonks|Technical Architect|
	
	
	
	
	
	
	
	
	
	