package com.cydeo.day12;

import com.cydeo.utilities.SpartanNewBase;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class C1_OldRestAssuredTests extends SpartanNewBase {

    @Test
    public void getAllSpartan(){

        RestAssured.given()
                               .accept(ContentType.JSON)
                .auth().basic("admin","admin")
                .when()
                               .get("/spartans")
                .then()
                               .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id[0]", Matchers.is(1));//if one body fails it doesn't execute the others

    }

    @Test
    public void test2(){

        RestAssured.given()
                .accept(ContentType.JSON)
                .auth().basic("admin","admin")
                .expect()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id[0]",Matchers.is(12))//even if former body fails, it executes the
                .body("id[5]", Matchers.is(6))//the other bodies
                .logDetail(LogDetail.ALL)
                .when().get("/spartans");


    }


}
