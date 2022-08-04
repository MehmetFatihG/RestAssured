package com.cydeo.day04;

import com.cydeo.utilities.HRTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestsWithPath extends HRTestBase {

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON).
                queryParam("q", "{\"region_id\":2}")
                .when()
                .get("countries");

        assertEquals(200, response.statusCode());
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        //print first country id
        System.out.println("response.path(\"item[0].country_id\") = " + response.path("items[0].country_id"));

        //print second cauntry name
        System.out.println("response.path(\"items[1].country_name\") = " + response.path("items[1].country_name"));


        System.out.println("response.path(\"items[2].links[0].href\") = " + response.path("items[2].links[0].href"));

        //assert that all regions id is 2
        List<Integer> regionsId = response.path("items.region_id");
        for (Integer eachId : regionsId) {
            assertEquals(2, eachId);
        }

    }

    @Test
    public void test2() {

        //Send a GET request to employees and get only employees who works as a IT_PROG
        Response response = given().accept(ContentType.JSON).log().all()
                .queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when().get("/employees");

        //Then response status code should be 200
        assertEquals(200, response.statusCode());

        //And response content-type: application/json
        assertEquals("application/json", response.contentType());

        List<String> jobId = response.path("items.job_id");
        for (String each : jobId) {
            System.out.println(each);
            assertEquals("IT_PROG",each);

        }




    }
}