package com.raketech.demo.core;

import java.util.List;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredHelper {

    /**
     * Retrieves a specific value from the API response using the provided key.
     *
     * @param response    The API response object.
     * @param responseKey The key to retrieve the desired value from the response.
     * @return The value associated with the provided key as a String.
     */
    public String getResponseValue(Response response, String responseKey) {
        return response.path(responseKey).toString();
    }

    /**
     * Extracts a list of objects from the API response using the specified JSON path.
     *
     * @param response The API response object.
     * @param path     The JSON path to retrieve the list.
     * @return A list of objects extracted from the response.
     */
    public List<Object> getJsonpathList(Response response, String path) {
        return response.jsonPath().getList(path);
    }

    /**
     * Retrieves a string value from the API response using the specified JSON path.
     *
     * @param response The API response object.
     * @param path     The JSON path to retrieve the string value.
     * @return The string value extracted from the response.
     */
    public String getJsonpathString(Response response, String path) {
        return response.jsonPath().getString(path);
    }

    /**
     * Configures and returns a RestAssured RequestSpecification object.
     * The request includes relaxed HTTPS validation and Allure integration for logging.
     *
     * @return A configured RequestSpecification instance.
     */
    public RequestSpecification restAssured() {
        return RestAssured.given().relaxedHTTPSValidation()
            .filter(new AllureRestAssured()).log().all();
    }
}
