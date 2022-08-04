package com.cydeo.day12;

import com.cydeo.utilities.BookItTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class C3_BookItSpecTest extends BookItTestBase {

    @Test
    public void test1(){
        //send a get request to /api/users/me endpoint as a teacher
        //verify status code and content type
        RestAssured.given().spec(userRequestSpec("teacher"))
                .when().get("/api/users/me")
                .then().spec(getDynamicResponseSpec(200,ContentType.JSON))
                .spec(userCheck("Sam","Nickel"));
    }

    @Test
    public void test2(){
        //send a get request to /api/users/me endpoint as a student member
        //verify status code and content type
        RestAssured.given().spec(userRequestSpec("student member"))
                .when().get("/api/users/me")
                .then().spec(responseSpec)
                .spec(userCheck("Lorette","Bradnum"));
    }

}
