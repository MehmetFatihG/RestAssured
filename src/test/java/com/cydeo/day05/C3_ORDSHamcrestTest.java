package com.cydeo.day05;

import com.cydeo.utilities.HRTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class C3_ORDSHamcrestTest extends HRTestBase {

    @Test
    public void employeesTest() {

        //send a get request to emplyoees endpoint with query parameter job_id IT_PROG
        //verify each job_id is IT_PROG
        //verify first names are .... (find proper method to check list against list)
        //verify emails without checking order (provide emails in different order,just
        // make sure it has same emails)
        //expected names

        List<String> names = Arrays.asList("Alexander", "Bruce", "David", "Valli", "Diana");

        RestAssured.given().accept(ContentType.JSON)
                .queryParam("q", "{\"job_id\": \"IT_PROG\"}").when().get("employees")
                .then()
                .body("items.job_id", Matchers.everyItem(Matchers.is("IT_PROG")))
                .body("items.first_name", Matchers.containsInRelativeOrder("Alexander", "Bruce", "David", "Valli", "Diana"))
                .body("items.email", Matchers.containsInAnyOrder("VPATABAL", "DAUSTIN", "BERNST", "AHUNOLD", "DLORENTZ")) //contains without order
                .body("items.first_name", Matchers.equalTo(names)); // equality of lists assertion)

    }


    @Test
    public void employeesTest2() {
        //we want to chain and also get response object


        JsonPath jsonPath = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .body("items.job_id", Matchers.everyItem(Matchers.equalTo("IT_PROG")))
                .extract().jsonPath();
        //extract() --> method that allow us to get response object after we use then() method.
        //assert that we have only 5 firstnames
        MatcherAssert.assertThat(jsonPath.getList("items.first_name"), Matchers.hasSize(5));

        //assert firstnames order
        MatcherAssert.assertThat(jsonPath.getList("items.first_name"), Matchers.containsInRelativeOrder("Alexander", "Bruce", "David", "Valli", "Diana"));
    }


}


