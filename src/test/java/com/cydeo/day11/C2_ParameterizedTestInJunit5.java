package com.cydeo.day11;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class C2_ParameterizedTestInJunit5 {

    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,58,3,45,98})
    public void test1(int number){

        System.out.println("number = " + number);
        Assertions.assertTrue(number > 5);

    }

    @ParameterizedTest
    @ValueSource(strings = {"john","abbas","ali","TJ"})
    public void testMultipleNames(String name){
        System.out.println("name = " + name);

    }


    @ParameterizedTest
    @ValueSource(ints = {22030,22031, 22032, 22033 , 22034, 22035, 22036})
    public void zipCodeTest(int number){

        // SEND GET REQUEST TO https://api.zippopotam.us/us/{zipcode}
        // with these zipcodes 22030,22031, 22032, 22033 , 22034, 22035, 22036
        // check status code 200
        RestAssured.given().accept(ContentType.JSON)
                .pathParam("zipcode", number)
                .when().get("https://api.zippopotam.us/us/{zipcode}")
                .then().statusCode(200);

    }

}
