package com.cydeo.day04;

import static io.restassured.RestAssured.baseURI;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class CBTrainingAPIWithJsonPath {

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://api.cybertektraining.com/";

    }

    @Test
    public void test1(){

//send a get request to student id 32881 as a path parameter and accept header
// application/json
        Response response = given().accept(ContentType.JSON).pathParam("id", 32881)
                .when()
                .get("/student/{id}");

//verify status code=200 /content type=application/json;charset=UTF-8 /Content-Encoding = gzip
        assertEquals(200, response.statusCode() );
        assertEquals("application/json;charset=UTF-8",response.contentType());
        assertEquals("Accept-Encoding",response.header("Vary"));
        //assertEquals("334",response.header("Content-Length"));
        assertEquals("gzip", response.header("Content-Encoding"));

//verify Date header exists
        assertTrue(response.headers().hasHeaderWithName("Date"));

//assert that
            /*
               firstName Vera
                batch 14
                section 12
                emailAddress aaa@gmail.com
                companyName Cybertek
                state IL
                zipCode 60606
                using JsonPath
             */
        assertEquals("Vera",response.jsonPath().getString("students[0].firstName"));
        assertEquals(14,response.jsonPath().getInt("students[0].batch"));
        assertEquals(12,response.jsonPath().getInt("students[0].section"));
        assertEquals("aaa@gmail.com", response.jsonPath().getString("students[0].contact.emailAddress"));
        assertEquals("Cybertek",response.jsonPath().getString("students[0].company.companyName"));
        assertEquals("IL",response.jsonPath().getString("students[0].company.address.state"));
        assertEquals(60606, response.jsonPath().getInt("students[0].company.address.zipCode"));

    }

    @Test
    public void test2(){

//send a get request to student id 32881 as a path parameter and accept header application/json
        //verify status code=200 /content type=application/json;charset=UTF-8 /Content-Encoding = gzip
        //verify Date header exists
        //assert that
            /*
                firstName Vera
                batch 14
                section 12
                emailAddress aaa@gmail.com
                companyName Cybertek
                state IL
                zipCode 60606
                using JsonPath
             */
        Response response = given().accept(ContentType.JSON).and().pathParam("id",32881).when().get("/students/{id}");
        //Response response = given().accept(ContentType.JSON).when().get("/students/32881");
        assertEquals(200,response.statusCode());
        assertEquals("application/json;charset=UTF-8",response.contentType());
        assertEquals("gzip",response.header("Content-Encoding"));
        assertTrue(response.headers().toString().contains("Date"));
        assertEquals("Vera",response.path("firstName"));
        assertEquals(14, (Integer) response.path("batch"));
        assertEquals("12",response.path("section"));
        assertEquals("aaa@gmail.com",response.jsonPath().getString("contact.emailAddress"));
        assertEquals("Cybertek",response.jsonPath().getString("company.companyName"));
        assertEquals("IL",response.jsonPath().getString("company.address.state"));
        assertEquals(60606,(Integer)  response.path("company.address.zipCode"));

    }
}
