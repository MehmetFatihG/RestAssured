package com.cydeo.day10;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

public class C5_SSLTest {

    @Test
    public void test1(){

        RestAssured.given().
                relaxedHTTPSValidation()//even if it doesnt have valid certificate I want to send request
                .when().get("https://untrusted-root.badssl.com/")
                .prettyPrint();

    }

    @Test
    public void keyStore(){

        RestAssured.given()
                .keyStore("pathtofile","password")
                .when().get("apiurl");


    }

}
