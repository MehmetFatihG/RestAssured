package com.cydeo.day10;

import com.cydeo.utilities.SpartanAuthTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Test;

import java.io.File;

public class C4_JsonSchemaValidationTest extends SpartanAuthTestBase {

    @Test
    public void schemaValidation(){

        RestAssured.given().accept(ContentType.JSON)
                .pathParam("id",10)
                .auth().basic("admin","admin")
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .log().all()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath
                        ("SingleSpartanSchema.json"));

    }

    @Test
    public void allSpartanSchemaTest(){

        RestAssured.given().accept(ContentType.JSON)
                .auth().basic("admin","admin")
                .when().get("/api/spartans")
                .then().statusCode(200)
                //what if your json file not under the resource file, you can use this way
                .body(JsonSchemaValidator
                .matchesJsonSchema(new File("src/test/resources/AllSpartansSchema.json")));

    }

}
