package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpartanGetRequests {

    String baseUrl = "http://52.200.34.122:8000";

//    Given Accept type application/json
//    When user send GET request to api/spartans end point
//    Then status code must 200
//    And response Content Type must be application/json
//    And response body should include spartan result

    @Test
    public void test1(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(baseUrl + "/api/spartans");

        //printing status code from response object
        System.out.println("response.statusCode() = " + response.statusCode());

        //printing response content type from response object
        System.out.println("response.contentType() = " + response.contentType());

        //print whole result body
        response.prettyPrint();

        //how to test API
        //verify status code
        Assertions.assertEquals(200,response.statusCode());

        //verify content type
        Assertions.assertEquals("application/json",response.contentType());
    }

    @Test
    public void test2(){

        //Given accept header is application/json
        Response response = RestAssured.given().accept(ContentType.JSON)
                //When users sends a get request to /api/spartans/3
                .when().get(baseUrl + "/api/spartans/3");
        //Then status code should be 200
        Assertions.assertEquals(200,response.statusCode());
        //And content type should be application/json
        Assertions.assertEquals("application/json", response.contentType());
        //and json body should contain Fidole
        Assertions.assertTrue(response.body().asString().contains("Fidole"));


    }

    @Test
    public void test3(){

        //Given no headers provided
        //When Users sends GET request to /api/hello
        Response response = RestAssured.when().get(baseUrl + "/api/hello");

        //Then response status code should be 200
        Assertions.assertEquals(200,response.statusCode());

        //And Content type header should be “text/plain;charset=UTF-8”
        Assertions.assertEquals("text/plain;charset=UTF-8", response.contentType());

        //And header should contain date
        //we use hasHeaderWithName method to check if the name of the header exist, it returns boolean
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        //And Content-Length should be 17
        //we use header method to get any header value
        Assertions.assertEquals(17 + "",response.header("Content-Length"));
        Assertions.assertEquals("keep-alive",response.header("Connection"));
        System.out.println(response.header("Connection"));

        //And body should be “Hello from Sparta"
        Assertions.assertEquals("Hello from Sparta", response.body().asString());


    }




}
