package com.cydeo.day12;

import com.cydeo.utilities.SpartanNewBase;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class C2_SpartanSpecTest extends SpartanNewBase {

    //all tests in this class will use admin credentials
    //all test in this class will expect json result from response

    //all test in this class response status code is 200
    //all test in this class response content type header is json

    @Test
    public void test1(){

        RestAssured.given()
                            .spec(adminSpec)
                   .when()
                             .get("/spartans")
                   .then()
                             .spec(responseSpec);
    }

    @Test
    public void test2(){

        RestAssured.given().spec(adminSpec).pathParam("id", 10)
                .when().get("/spartans/{id}")
                .then().spec(responseSpec);
    }

    @Test
    public void test3(){

        RestAssured.given().spec(userSpec)
                .queryParam("nameContains","j")
                .queryParam("gender","Female")
                .when().get("/spartans/search")
                .then().spec(responseSpec)
                .body("totalElements", Matchers.is(20));

    }

}
