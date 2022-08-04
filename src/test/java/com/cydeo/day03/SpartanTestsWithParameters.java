package com.cydeo.day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestsWithParameters {

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://52.200.34.122:8000";
    }

    @Test
    public void test1(){

        //Given accept type is Json
        //And Id parameter value is 5
        //When user sends GET request to /api/spartans/{id}
        Response response = given().accept(ContentType.JSON)
                .and().pathParams("id",5).when().get("/api/spartans/{id}");

        //Then response status code should be 200
        assertEquals(200, response.statusCode());

        //And response content-type: application/json
        assertEquals("application/json",response.contentType());

        //And "Blythe" should be in response payload
        assertTrue(response.body().asString().contains("Blythe"));

    }

    @Test
    public void test2(){

        // Given accept type is Json
        //And Id parameter value is 500
        //When user sends GET request to /api/spartans/{id}
        Response response = given().accept(ContentType.JSON)
                .and().pathParams("id",500).when().get("/api/spartans/{id}");

        //Then response status code should be 404
        assertEquals(404,response.statusCode());

        //And response content-type: application/json
        assertEquals("application/json", response.contentType());

        //And "Not Found" message should be in response payload
        assertTrue(response.body().asString().contains("Not Found"));

    }

    @Test
    public void test3(){

        //Given accept type is Json
        //And query parameter values are:
        //gender|Female
        //nameContains|e
        //When user sends GET request to /api/spartans/search
        Response response = given().accept(ContentType.JSON).queryParam("nameContains", "e")
                .queryParam("gender", "Female").when().get("/api/spartans/search");

        //Then response status code should be 200
        assertEquals(200, response.statusCode());

        //And response content-type: application/json
        assertEquals("application/json", response.contentType());

        //And "Female" should be in response payload
        //And "Janette" should be in response payload
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));
    }

    @Test
    public void test4(){

        Map<String, Object> queryParamsMap = new HashMap<>();
        queryParamsMap.put("containsName","e");
        queryParamsMap.put("gender","Female");

        Response response = given().log().all().accept(ContentType.JSON).queryParams(queryParamsMap).when().get("/api/spartans/search");

        //Then response status code should be 200
        assertEquals(200, response.statusCode());

        //And response content-type: application/json
        assertEquals("application/json", response.contentType());

        //And "Female" should be in response payload
        //And "Janette" should be in response payload
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));
    }



}
