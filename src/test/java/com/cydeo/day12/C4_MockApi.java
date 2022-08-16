package com.cydeo.day12;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class C4_MockApi {

    //https://337ce3aa-8ffa-4101-8fcf-16402c4dc0c5.mock.pstmn.io

    @Test
    public void test1(){

        RestAssured.given().accept(ContentType.JSON)
                .baseUri("https://337ce3aa-8ffa-4101-8fcf-16402c4dc0c5.mock.pstmn.io")
                .when().get("/customer")
                .prettyPrint();
    }

    @Test
    public void test2(){

        RestAssured.given().accept(ContentType.JSON)
                .baseUri("https://337ce3aa-8ffa-4101-8fcf-16402c4dc0c5.mock.pstmn.io")
                .when().get("/employees")
                .prettyPrint();
    }

}
