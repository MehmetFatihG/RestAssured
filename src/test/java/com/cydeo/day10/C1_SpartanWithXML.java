package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.List;


public class C1_SpartanWithXML extends SpartanAuthTestBase {

    @Test
    public void getSpartanWithXML(){
        //we will ask for xml response
        //assert status code 200
        //assert content type is xml (we got xml response)
        //verify first spartan name is Meade
        Response response = RestAssured.given().accept(ContentType.XML)
                .contentType(ContentType.XML).log().all()
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans")
                .then().contentType(ContentType.XML)
                .body("List.item[0].name",Matchers.is("Meade"))
                .body("List.item[0].gender",Matchers.is("Male"))
                .body("List.item[0].phone",Matchers.is("9994128232"))
                .statusCode(200).extract().response();

        //second way of asserting other than body method below
        MatcherAssert.assertThat(response.path("List.item[0].name"), Matchers.is("Meade"));

    }

    @Test
    public void XMLPath(){

        Response response = RestAssured.given().accept(ContentType.XML)
                .contentType(ContentType.XML).log().all()
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans");

        System.out.println("response.xmlPath().get(\"List.item[2].id\") = " + response.xmlPath().get("List.item[2].id"));
        System.out.println("response.xmlPath().get(\"List.item[0].name\") = " + response.xmlPath().get("List.item[0].name"));

        //how to get all names and save them into a list
        List<String> list = response.xmlPath().getList("List.item.name");
        for (String each : list) {
            System.out.println(each);
        }

    }

}
