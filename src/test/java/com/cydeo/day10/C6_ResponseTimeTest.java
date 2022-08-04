package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class C6_ResponseTimeTest extends SpartanAuthTestBase {

    @Test
    public void test1(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .when().get("/api/spartans")
                .then()
                .time(Matchers.both(Matchers.greaterThan(1000L)).and(Matchers.lessThanOrEqualTo(2000L)))
                .extract().response();


        System.out.println("response.getTime() = " + response.getTime());

    }

}
