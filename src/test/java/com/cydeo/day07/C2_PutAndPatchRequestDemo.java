package com.cydeo.day07;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class C2_PutAndPatchRequestDemo extends SpartanTestBase {

    @Test
    public void PUTRequest() {

        Map<String, Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("name", "Milanus");
        putRequestMap.put("gender", "Male");
        putRequestMap.put("phone", 8111112222L);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON).pathParam("id", 120)
                .body(putRequestMap)
                .when()
                .put("/api/spartans/{id}")
                .then()
                .statusCode(204).extract().response();

        //to get the putted spartan with id
        Response response1 = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 120)
                .when()
                .get("api/spartans/{id}")
                .then()
                .statusCode(200).log().body()
                .extract().response();

        MatcherAssert.assertThat(response1.path("phone"), Matchers.is(8111112222L));

    }

    @Test
    public void PATCHRequest() {

        Map<String, Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("phone", 7777772222L);

        Response response = RestAssured.given()
                .contentType(ContentType.JSON).pathParam("id", 120)
                .body(putRequestMap).log().body()
                .when()
                .patch("/api/spartans/{id}")
                .then()
                .statusCode(204).extract().response();

        //to get the putted spartan with id
        Response response1 = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 120)
                .when()
                .get("api/spartans/{id}")
                .then()
                .statusCode(200).log().body()
                .extract().response();

        MatcherAssert.assertThat(response1.path("phone"), Matchers.is(7777772222L));


    }

    @Test
    public void DELETESpartan(){

        RestAssured.given().pathParam("id",120)
                .when().and()
                .delete("/api/spartans/{id}")
                .then().statusCode(204);

        //send a request after deleting and verify that status code is 404
        RestAssured.given().contentType(ContentType.JSON)
                .pathParam("id",120)
                .when()
                .get("/api/spartans/{id}")
                .then().statusCode(404);

    }

}