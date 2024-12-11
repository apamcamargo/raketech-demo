package com.raketech.demo.api.requests;

import java.util.List;

import com.raketech.demo.core.BaseTest;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;

public class PeopleRequest extends BaseTest{

	@Step("Send GET request to People api and retrieve body response")
	public Response getPeople() {
		return api.restAssured()
				.contentType("application/json")
				.get(yamlReader.getValue("api_base_uri").toString());
	}

	@Step("Check if status code is {expectedStatus} as expected")
	public void validateStatusCode(Response response, int expectedStatus) {
		int currentStatus = response.statusCode();
		Assertions.assertEquals(expectedStatus, currentStatus);
	}

	@Step("Validating that the {key} of the character {characterName} matches the expected value: {expectedValue}")
	public void validateCharacterInformation(Response response, String characterName, String key, String expectedValue) {
	    List<Object> names = api.getJsonpathList(response, "results.name");

	    for (int i = 0; i < names.size(); i++) {
	        if (names.get(i).equals(characterName)) {
				Assertions.assertEquals(expectedValue, api.getJsonpathString(response, "results[" + i + "]." + key));
	            break;
	        }
	    }
	}

	@Step("Return the value of {key} related to the character {characterName}")
	public String returnCharacterInformation(Response response, String characterName, String key) {
		List<Object> names = api.getJsonpathList(response, "results.name");
		String characteristic = "";

		for (int i = 0; i < names.size(); i++) {
			if (names.get(i).equals(characterName)) {
				characteristic = api.getJsonpathString(response, "results[" + i + "]." + key);
			}
		}
		return characteristic;
	}


	@Step("Send GET request to the People API to get content of page number: {page}")
	public Response getPeopleByPage(int page) {
		return api.restAssured()
			.contentType("application/json")
			.get(yamlReader.getValue("api_base_uri").toString() + "?page=" + page);
	}

	@Step("Validate that results array contains {expectedSize} items")
	public void validateResultsSize(Response response, int expectedSize) {
		List<Object> results = api.getJsonpathList(response, "results");
		Assertions.assertEquals(expectedSize, results.size(),
			"Expected results size: " + expectedSize + ", but got: " + results.size());
	}

	@Step("Validate items from two pages are different")
	public void validatePagesAreDifferent(Response page1Response, Response page2Response) {
		List<Object> page1Results = api.getJsonpathList(page1Response, "results");
		List<Object> page2Results = api.getJsonpathList(page2Response, "results");
		Assertions.assertNotEquals(page1Results, page2Results, "Items on page 1 and page 2 are identical!");
	}

	@Step("Send GET request to URL {uri}")
	public Response getRequest(String uri) {
		return api.restAssured()
			.contentType("application/json")
			.get(uri);
	}

	@Step("Validate homeworld name is {expectedPlanetName}")
	public void validateHomeworldName(Response response, String expectedPlanetName) {
		String actualPlanetName = api.getJsonpathString(response, "name");
		Assertions.assertEquals(expectedPlanetName, actualPlanetName,
			"Expected planet name: " + expectedPlanetName + ", but got: " + actualPlanetName);
	}
}
