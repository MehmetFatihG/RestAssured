package com.cydeo.day08;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.swing.text.View;

import static io.restassured.RestAssured.baseURI;

public class C2_BookItTests {

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "https://cybertek-reservation-api-qa2.herokuapp.com";
    }

    @Test
    public void testAuth1(){

        String accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjAyIiwiYXVkIjoic3R1ZGVudC10ZWFtLW1lbWJlciJ9.UQnmL58LBoFW-Opm5OPIv7AgFvupRq4cANOIBQdOlpI";

        //how to pass bearer token for bookit => use header method to give as key value header
        RestAssured.given().header("Authorization",accessToken)
                .accept(ContentType.JSON)
                .get("api/campuses")
                .then()
                .statusCode(200)
                .log().all();


    }

}
