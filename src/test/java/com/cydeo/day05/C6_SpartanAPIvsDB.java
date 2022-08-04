package com.cydeo.day05;

import com.cydeo.utilities.DB_Utils;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class C6_SpartanAPIvsDB extends SpartanTestBase {

    @Test
    public void test1(){

        //get information from database
        String query = "select spartan_id,name ,gender,phone from spartans\n" +
                "where spartan_id = 15";

        Map<String, Object> dbMap = DB_Utils.getRowMap(query);
        System.out.println(dbMap);

        //get information from API
        Response response = RestAssured.given().accept(ContentType.JSON).pathParam("id", 15).when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().response();

        Map<String,Object> apiMap = response.body().as(Map.class);
        System.out.println(apiMap);

        //compare
        MatcherAssert.assertThat(apiMap.get("id").toString(), Matchers.is(dbMap.get("SPARTAN_ID").toString()));
        MatcherAssert.assertThat(apiMap.get("name"), Matchers.is(dbMap.get("NAME")));
        MatcherAssert.assertThat(apiMap.get("gender"), Matchers.is(dbMap.get("GENDER")));
        MatcherAssert.assertThat(apiMap.get("phone").toString(), Matchers.is(dbMap.get("PHONE").toString()));


    }

}
