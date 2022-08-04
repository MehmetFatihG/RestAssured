package com.cydeo.day05;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;

public class C2_HamcrestMatchersAPITest {

    @Test
    public void test1(){

        //       given accept type is Json
        //       And path param id is 15
        //       When user sends a get request to spartans/{id}
        //       Then status code is 200
        //       And content type is Json
        //       And json data has following
        //           "id": 15,
        //           "name": "Meta",
        //           "gender": "Female",
        //           "phone": 1938695106

        given()
                            .accept(ContentType.JSON).pathParam("id", 15)
                   .when()
                            .get("http://52.200.34.122:8000/api/spartans/{id}")
                   .then()
                           .statusCode(200)
                .contentType("application/json").and()
                .body("id", Matchers.equalTo(15),
                        "name",Matchers.is("Meta"),
                        "gender",Matchers.is("Female"),
                        "phone",Matchers.equalTo(1938695106))
                .log().all();

    }


    @Test
    public void teacherData(){
        given().accept(ContentType.JSON).pathParam("id",20450)
                .when().log().all()
                .get("http://api.cybertektraining.com/teacher/{id}")
                .then()
                .statusCode(200)
                .contentType("application/json;charset=UTF-8")
                .header("Vary",Matchers.is("Accept-Encoding"))
                .header("Date",Matchers.notNullValue())
                //.header("Content-Encoding",Matchers.is("gzip"))
                .body("teachers[0].firstName", Matchers.is("Alexander"))
                .body("teachers[0].lastName",Matchers.is("Syrup"))
                .body("teachers[0].gender",Matchers.is("male"))
                .log().all();

    }

    @Test
    public void teachersTest(){

        given().accept(ContentType.JSON)
                .when()
                .get("http://api.cybertektraining.com/teacher/all")
                .then()
                .body("teachers.firstName",Matchers.hasItems("Alexander","Leonel"));

    }

}
