package com.cydeo.day01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class SimpleGetRequest {

    String url = "http://52.200.34.122:8000/api/spartans";

    @Test
    public void test1(){

        //send a get request and save it inside the Response object
        Response response = RestAssured.get(url);

        //to print response status code
        System.out.println(response.statusCode());

        //to print response body
        response.prettyPrint();

    }

}
