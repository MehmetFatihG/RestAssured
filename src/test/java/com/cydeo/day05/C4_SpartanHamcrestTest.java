package com.cydeo.day05;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.List;

public class C4_SpartanHamcrestTest extends SpartanTestBase {

    @Test
    public void test1(){

        List<String> names = RestAssured.given().accept(ContentType.JSON)
                .and()
                .queryParams("nameContains","j",
                        "gender","Male")
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .and()
                .body("totalElement", Matchers.greaterThanOrEqualTo(3))
                .extract().response().jsonPath().getList("content.name");

        System.out.println(names);

    }


    @Test
    public void test2(){

        int statusCodes = RestAssured.given().accept(ContentType.JSON)
                .and()
                .queryParams("nameContains","j",
                        "gender","Male")
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .and()
                .body("totalElement", Matchers.greaterThanOrEqualTo(3))
                .extract().response().statusCode();

        System.out.println(statusCodes);

    }
}
