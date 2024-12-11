package com.raketech.demo.api.tests;

import com.raketech.demo.api.requests.PeopleRequest;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PeopleTest {

    PeopleRequest peopleRequest = new PeopleRequest();

    @ParameterizedTest
    @CsvSource({
        "R2-D2, 200, skin_color, 'white, blue'",
        "Luke Skywalker, 200, hair_color, blond",
        "Darth Vader, 200, eye_color, yellow"
    })
    @DisplayName("Validate characteristics of multiple characters")
    @Description("Send a GET request to the People API endpoint and validate the specified characteristics of multiple characters")
    public void validateSkinColor(String characterName, int statusCode, String characterCharacteristic, String expectedSkinColor) {
        Response response = peopleRequest.getPeople();
        peopleRequest.validateStatusCode(response, statusCode);
        peopleRequest.validateCharacterInformation(response, characterName, characterCharacteristic, expectedSkinColor);
    }

    @ParameterizedTest
    @CsvSource({
        "1, 200, 2, 10"
    })
    @DisplayName("Validate results size and distinct data per page")
    @Description("Validate pagination logic, check if each page returns 10 results and distinct data per page")
    public void validatePagination(int firstPage, int statusCode, int secondPage, int pageSize) {
        Response firstPageResponse = peopleRequest.getPeopleByPage(firstPage);
        peopleRequest.validateStatusCode(firstPageResponse, statusCode);
        peopleRequest.validateResultsSize(firstPageResponse, pageSize);

        Response secondPageResponse = peopleRequest.getPeopleByPage(secondPage);
        peopleRequest.validateStatusCode(secondPageResponse, statusCode);
        peopleRequest.validateResultsSize(secondPageResponse, pageSize);
        peopleRequest.validatePagesAreDifferent(firstPageResponse, secondPageResponse);
    }

	@ParameterizedTest
	@CsvSource({
		"R2-D2, 200, homeworld, Naboo",
		"Leia Organa, 200, homeworld, Alderaan",
		"Beru Whitesun lars, 200, homeworld, Tatooine"
	})
    @DisplayName("Validate character planet")
    @Description("Retrieve People API results, retrieve homeworld value with planet api endpoint, then send a GET to validate if the character plant returns properly")
    public void validateHomeworld(String characterName, int statusCode, String characterCharacteristic, String planet) {
		Response response = peopleRequest.getPeople();
		peopleRequest.validateStatusCode(response, statusCode);

        Response homeworldResponse = peopleRequest.getRequest(peopleRequest.returnCharacterInformation(response, characterName, characterCharacteristic));
        peopleRequest.validateStatusCode(homeworldResponse, 200);
        peopleRequest.validateHomeworldName(homeworldResponse, planet);
    }

}
