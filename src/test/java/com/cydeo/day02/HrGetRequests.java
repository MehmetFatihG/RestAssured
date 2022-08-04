package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HrGetRequests {

    @BeforeAll
    public static void init(){
        //save base url inside the this variable so that we don't need to type in every test cases
        RestAssured.baseURI = "http://52.200.34.122:1000/ords/hr";
    }

    @Test
    public void test1(){

        Response response = RestAssured.get("/regions");

        System.out.println("response.statusCode() = " + response.statusCode());


    }

    @Test
    public void test2(){
        //Given accept type is application/json
        //When user sends get request to /regions/2
        Response response = given().accept(ContentType.JSON).when()
                .get("/regions/2");

        //Then response status code must be 200
        assertEquals(200,response.statusCode());

        //and content type equals to application/json
        assertEquals("application/json", response.contentType());

        //and response body contains   Americas
        assertTrue(response.body().asString().contains("Americas"));
    }

}
