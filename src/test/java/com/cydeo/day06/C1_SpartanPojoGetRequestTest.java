package com.cydeo.day06;

import com.cydeo.pojo.Search;
import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class C1_SpartanPojoGetRequestTest extends SpartanTestBase {

    @Test
    public void oneSpartanPojo(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200).extract().response();

        //De serialize --> JSON to POJO (java custom class)
        //different ways to do this
        //1.using as() method
        //we convert json response to spartan object with the help of jackson
        //as() method uses jackson to de serialize(converting JSON to Java class)
        Spartan spartan15 = response.as(Spartan.class);

        System.out.println(spartan15);
        System.out.println("spartan15.getGender() = " + spartan15.getGender());
        System.out.println("spartan15.getName() = " + spartan15.getName());
        System.out.println("spartan15.getId() = " + spartan15.getId());
        System.out.println("spartan15.getPhone() = " + spartan15.getPhone());

        //second way of deserialize json to java
        //2.using JsonPath to deserialize to custom class
        JsonPath jsonPath = response.jsonPath();

        Spartan s15 = jsonPath.getObject("", Spartan.class);

        System.out.println("s15.getName() = " + s15.getName());
        System.out.println("s15.getGender() = " + s15.getGender());
        System.out.println("s15.getId() = " + s15.getId());
        System.out.println("s15.getPhone() = " + s15.getPhone());

    }

    @Test
    public void SpartanSearchWithPojo(){

        ///spartans/search?nameContains=a&gender=Male
        // send get request to above endpoint and save first object with type Spartan POJO
        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("nameContains", "a")
                .queryParam("gender", "Male")
                .when()
                .get("/api/spartans/search");

        Spartan spartanFirst = response.jsonPath().getObject("content[0]", Spartan.class);
        System.out.println("spartanFirst = " + spartanFirst);


    }

    @Test
    public void test3(){

        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("nameContains", "a")
                .queryParam("gender", "Male")
                .when()
                .get("/api/spartans/search");

        Search search = response.as(Search.class);
        System.out.println(search.getContent().get(0).getName());

    }

    @Test
    public void test4(){

        Response response = given()
                .accept(ContentType.JSON)
                .queryParam("nameContains", "a")
                .queryParam("gender", "Male")
                .when()
                .get("/api/spartans/search")
                .then()
                .extract().response();

        List<Spartan> spartanList = response.jsonPath().getList("content", Spartan.class);

        for (Spartan spartan : spartanList) {
            System.out.println(spartan);
        }

    }

    @Test
    public void test5(){

        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans")
                .then()
                .extract().response();

        List<Spartan> spartanList = response.jsonPath().getList("", Spartan.class);

        for (Spartan spartan : spartanList) {
            System.out.println(spartan);
        }

    }
}
