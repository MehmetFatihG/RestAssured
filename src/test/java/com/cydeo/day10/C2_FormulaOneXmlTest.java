package com.cydeo.day10;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.RestAssured.*;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;

public class C2_FormulaOneXmlTest {



    @BeforeAll
    public static void setUp(){
        //http://ergast.com/api/f1/drivers/alonso
        baseURI = "https://ergast.com";
        basePath = "/api/f1";
    }

    @Test
    public void test1(){

        Response response = RestAssured.given()
                .pathParam("driver", "alonso")
                .when()
                .get("/drivers/{driver}")
                .then().statusCode(200).log().all()
                .extract().response();

        //get given name
        String givenName = response.xmlPath().getString("MRData.DriverTable.Driver.GivenName");
        System.out.println("givenName = " + givenName);

        //get family name
        String familyName = response.xmlPath().getString("MRData.DriverTable.Driver.FamilyName");
        System.out.println("familyName = " + familyName);

        //if you are trying to get attribute, you can use @ sign
        //get driver id
        String driverId = response.xmlPath().getString("MRData.DriverTable.Driver.@driverId");
        System.out.println("driverId = " + driverId);
    }
}
