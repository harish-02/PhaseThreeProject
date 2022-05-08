package com.steps;

import java.io.File;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import io.cucumber.core.gherkin.messages.internal.gherkin.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIAutomationSteps {
	
	String path = "";
	RequestSpecification request;
	Response response;
	Logger logger = LogManager.getLogger(APIAutomationSteps.class.getName());

	@Given("a base url {string}")
	public void a_base_ur(String baseURL) {
		PropertyConfigurator.configure("log4j.properties");
		logger.info("Base URL is " + baseURL);

		RestAssured.baseURI = baseURL;

		// INITIALIZE REQUEST
		request = RestAssured.given();

	}

	@Given("path is {string}")
	public void path_is(String _path) {
		logger.info("API Path is " + _path);

		path = _path;
	}

	@When("user sends a get request")
	public void user_sends_a_get_request() {
		logger.info("Sending GET API Request >>>> " + RestAssured.baseURI + path);
		response = request.get(path);
	}

	@Then("status code should be {int}")
	public void status_code_should_be(Integer status) {
		logger.info("Getting response");
		logger.info("Status Code: " + response.statusCode());

		try {
			response.then().assertThat().statusCode(Integer.valueOf(status));
		} catch (AssertionError ex) {
			logger.error(ex.getMessage());
		}
	}

//	@Given("payload file is {string}")
//	public void payload_file_is(String payload) {
//		String projectPath = System.getProperty("user.dir");
//		String filePath = projectPath + "/" + payload;
//		File pf = new File(filePath);
//		
//		if (pf.exists() == true) {
//			request.header("Content-Type", "application/json");
//			request.body(pf);
//		} else {
//			logger.error("Request body not found");
//		}
//	}

	@When("user sends a post request with body {string} and {string}")
	public void user_sends_a_post_request_with_body_and(String name, String job) {
		
		JsonObject reqParams = new JsonObject();
		reqParams.add("name", name);
		reqParams.add("job", job);
		
		request.header("Content-Type", "application/json");
		request.body(reqParams.toString());

		logger.info("Sending POST API Request >>>> " + RestAssured.baseURI + path);
		response = request.post(path);
	}

	@Then("print the response")
	public void print_the_response() {
		String responseBody = response.getBody().asPrettyString();

		logger.info("Printing API Response: ");
		logger.info(responseBody);
	}
}
