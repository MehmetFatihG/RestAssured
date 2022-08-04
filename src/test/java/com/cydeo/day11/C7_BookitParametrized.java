package com.cydeo.day11;

import com.cydeo.utilities.ExcelUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;

public class C7_BookitParametrized {

    public static List<Map<String,String>> userInfo(){

        ExcelUtil bookIt = new ExcelUtil("src/test/resources/BookItQa3.xlsx","QA3");

       return bookIt.getDataList();
    }

    @ParameterizedTest
    @MethodSource("userInfo")
    public void bookItTest(Map<String,String> user){

        System.out.println("user.get(\"email\") = " + user.get("email"));
        System.out.println("user.get(\"password\") = " + user.get("password"));

        RestAssured.given().accept(ContentType.JSON)
                .queryParams(user)
                .when().get("https://cybertek-reservation-api-qa2.herokuapp.com/sign")
                .then().statusCode(200)
                .log().body();



    }



}
