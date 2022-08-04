package com.cydeo.day05;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class C5_JsonToJavaTest extends SpartanTestBase {

    @Test
    public void oneSpartanToMap(){

        Response response = RestAssured.given().pathParam("id", 15).when()
                .get("/api/spartans/{id}")
                .then().statusCode(200).extract().response();

        Map<String,Object> jsonMap = response.body().as(Map.class);
        System.out.println(jsonMap);

        //after we got the map, we can use hamcrest or junit assertions to do assertion
        String actualName = (String) jsonMap.get("name");
        MatcherAssert.assertThat(actualName, Matchers.is("Meta"));

    }

    @Test
    public void getAllSpartanToJava(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/api/spartans")
                .then().statusCode(200).extract().response();

        List<Map<String,Object>> allSpartan = response.body().as(List.class);
        System.out.println(allSpartan);
        System.out.println(allSpartan.get(1).get("name"));
    }



}
