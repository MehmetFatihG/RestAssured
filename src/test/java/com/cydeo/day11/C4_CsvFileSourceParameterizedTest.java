package com.cydeo.day11;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class C4_CsvFileSourceParameterizedTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/zipCode.csv",numLinesToSkip = 1)
    public void zipcodeTestWithFile(String state, String city, int zipcode){

        System.out.println("state = " + state);
        System.out.println("city = " + city);
        System.out.println("zipcode = " + zipcode);

        JsonPath jsonPath = RestAssured.given().accept(ContentType.JSON)
                .pathParam("state", state)
                .pathParam("city", city)
                .when().get("https://api.zippopotam.us/us/{state}/{city}")
                .then().statusCode(200)
                .extract().jsonPath();

        int count = jsonPath.getList("places").size();
        System.out.println(count);

        MatcherAssert.assertThat(count, Matchers.is(zipcode));
    }

}
