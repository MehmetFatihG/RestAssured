package com.cydeo.day04;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanWithJsonPath extends SpartanTestBase {

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

        JsonPath jsonPath = response.jsonPath();

        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long phone = jsonPath.getLong("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

    }

}
