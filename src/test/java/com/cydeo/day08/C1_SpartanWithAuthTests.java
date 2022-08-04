package com.cydeo.day08;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

public class C1_SpartanWithAuthTests extends SpartanAuthTestBase {

    @Test
    public void test(){

        RestAssured.get("/api/spartan")
                .then()
                .statusCode(401)
                .log().all();

    }

    @Test
    public void testAdmin(){

        //how to pass admin admin as a username and password
        RestAssured.given()
                .accept(ContentType.JSON).log().body()
                .auth()
                .basic("admin","admin")
                .when()
                .get("api/spartans")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void testEditor(){

        RestAssured.given().auth().basic("editor","editor")
                .pathParam("id",15)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(403);

    }

}
