package com.cydeo.day07;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class C1_SpartanPostRequestDemo extends SpartanTestBase {

    @Test
    public void postMethod1(){

        //    Given accept type and Content type is JSON
        //    And request json body is:
        //    {
        //      "gender":"Male",
        //      "name":"Severus",
        //      "phone":8877445596
        //   }
        //    When user sends POST request to '/api/spartans'
        //    Then status code 201
        //    And content type should be application/json
        //    And json payload/response/body should contain:
        //    "A Spartan is Born!" message
        //    and same data what is posted

        String requestJsonBody = "{\"gender\":\"Male\",\n" +
                "\"name\":\"Severus\",\n" +
                "\"phone\":8877445596}";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(requestJsonBody)
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .contentType("application/json")
                .extract()
                .response();
        MatcherAssert.assertThat(response.path("success"), Matchers.is("A Spartan is Born!"));
        MatcherAssert.assertThat(response.path("data.name"),Matchers.is("Severus"));
        MatcherAssert.assertThat(response.path("data.gender"),Matchers.is("Male"));
        MatcherAssert.assertThat(response.path("data.phone"),Matchers.is(8877445596L));


    }

    @Test
    public void postMethod2(){

        //    Given accept type and Content type is JSON
        //    And request json body is:
        //    {
        //      "gender":"Male",
        //      "name":"Severus",
        //      "phone":8877445596
        //   }
        //    When user sends POST request to '/api/spartans'
        //    Then status code 201
        //    And content type should be application/json
        //    And json payload/response/body should contain:
        //    "A Spartan is Born!" message
        //    and same data what is posted

        //create a map to keep request json body information
        Map<String, Object> requestJsonMap = new LinkedHashMap<>();
        requestJsonMap.put("name","Lazarus");
        requestJsonMap.put("gender","Male");
        requestJsonMap.put("phone",8877445596L);

        Response response = RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON).log().all()
                .body(requestJsonMap)
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201).log().all()
                .contentType("application/json")
                .extract()
                .response();

        MatcherAssert.assertThat(response.path("success"),Matchers.is("A Spartan is Born!"));
        MatcherAssert.assertThat(response.path("data.name"),Matchers.is("Lazarus"));
        MatcherAssert.assertThat(response.path("data.gender"),Matchers.is("Male"));
        MatcherAssert.assertThat(response.path("data.phone"),Matchers.is(8877445596L));


    }

    @Test
    public void postMethod3(){

        //    Given accept type and Content type is JSON
        //    And request json body is:
        //    {
        //      "gender":"Male",
        //      "name":"Severus",
        //      "phone":8877445596
        //   }
        //    When user sends POST request to '/api/spartans'
        //    Then status code 201
        //    And content type should be application/json
        //    And json payload/response/body should contain:
        //    "A Spartan is Born!" message
        //    and same data what is posted

        Spartan spartan = new Spartan();
        spartan.setName("Milanus");
        spartan.setGender("Male");
        spartan.setPhone(8877445596L);

        System.out.println(spartan);

        Response response = RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON).log().all()
                .body(spartan)
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201).log().all()
                .contentType("application/json")
                .extract()
                .response();

        MatcherAssert.assertThat(response.path("success"),Matchers.is("A Spartan is Born!"));
        MatcherAssert.assertThat(response.path("data.name"),Matchers.is("Milanus"));
        MatcherAssert.assertThat(response.path("data.gender"),Matchers.is("Male"));
        MatcherAssert.assertThat(response.path("data.phone"),Matchers.is(8877445596L));



    }

    @Test
    public void postMethod4(){

        //    Given accept type and Content type is JSON
        //    And request json body is:
        //    {
        //      "gender":"Male",
        //      "name":"Severus",
        //      "phone":8877445596
        //   }
        //    When user sends POST request to '/api/spartans'
        //    Then status code 201
        //    And content type should be application/json
        //    And json payload/response/body should contain:
        //    "A Spartan is Born!" message
        //    and same data what is posted

        //this example we implement serialization with creatin spartan object
        // sending as a request body
        //also implemented deserialization getting the id,sending get request and
        // saving that body as a response
        Spartan spartan = new Spartan();
        spartan.setName("Yelenus");
        spartan.setGender("Male");
        spartan.setPhone(1234569870);

        System.out.println(spartan);

        int idFromPost = RestAssured.given().accept(ContentType.JSON)
                .contentType(ContentType.JSON).log().all()
                .body(spartan)
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201).log().all()
                .contentType("application/json")
                .extract()
                .response().path("data.id");

        //send a request to id
        Spartan spartanPostedYelenus = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", idFromPost)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).extract().response().as(Spartan.class);

        System.out.println("spartanPostedYelenus.getName() = " + spartanPostedYelenus.getName());
        System.out.println("spartan.getName() = " + spartan.getName());

        MatcherAssert.assertThat(spartanPostedYelenus.getName(),Matchers.is(spartan.getName()));


    }
}
