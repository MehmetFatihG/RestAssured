package com.cydeo.day03;

import com.cydeo.utilities.HRTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestsWithParameters extends HRTestBase {


    @Test
    public void test1(){

        //        Given accept type is Json
        //        And parameters: q = {"region_id":2}
        //        When users sends a GET request to "/countries"
        Response response = given().accept(ContentType.JSON).log().all()
                .queryParam("q", "{\"region_id\":2}")
                .when().get("/countries");

        //Then response status code should be 200
        assertEquals(200, response.statusCode());

        //And response content-type: application/json
        assertEquals("application/json", response.contentType());

        //        And Payload should contain "United States of America"
        assertTrue(response.body().asString().contains("United States of America"));

    }

    @Test
    public void test2(){

        //Send a GET request to employees and get only employees who works as a IT_PROG
        Response response = given().accept(ContentType.JSON).log().all()
                .queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when().get("/employees");

        //Then response status code should be 200
        assertEquals(200, response.statusCode());

        //And response content-type: application/json
        assertEquals("application/json", response.contentType());

        assertTrue(response.body().asString().contains("IT_PROG"));


    }


}
