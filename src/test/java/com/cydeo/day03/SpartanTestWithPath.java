package com.cydeo.day03;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.baseURI;

public class SpartanTestWithPath extends SpartanTestBase {


    @Test
    public void test1(){

        // Given accept type is json
        //     And path param id is 10
        //     When user sends a get request to "api/spartans/{id}"
        Response response = given().accept(ContentType.JSON)
                .and().pathParams("id",10).when().get("/api/spartans/{id}");

        //Then response status code should be 200
        assertEquals(200, response.statusCode());

        //And response content-type: application/json
        assertEquals("application/json",response.contentType());

        //     And response payload values match the following:
        //          id is 10,
        //          name is "Lorenza",
        //          gender is "Female",
        //          phone is 3312820936

        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        System.out.println(id);
        System.out.println(name);
        System.out.println(gender);
        System.out.println(phone);

        assertEquals(10,id);
        assertEquals("Lorenza",name);
        assertEquals("Female",gender);
        assertEquals(3312820936l,phone);


    }

    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON).when().get("/api/spartans");

        //response.prettyPrint();

        int firstId = response.path("id[0]");
        System.out.println("firstId = " + firstId);

        List<String> names = response.path("name");

        for (String eachName : names) {
            System.out.println(eachName);
        }

    }

}
