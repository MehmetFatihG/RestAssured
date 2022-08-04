package com.cydeo.utilities;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.reset;

public class BookItTestBase {

    public static RequestSpecification teacherRequestSpec;
    public static RequestSpecification studentMemberRequestSpec;
    public static RequestSpecification studentLeaderRequestSpec;
    public static ResponseSpecification responseSpec;

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = ConfigurationReader.getProperty("base_url");
        teacherRequestSpec = RestAssured.given().accept(ContentType.JSON)
                .header("Authorization", getTokenByRole("teacher"))
                .log().all();

        studentMemberRequestSpec = RestAssured.given().accept(ContentType.JSON)
                .header("Authorization", getTokenByRole("student member"))
                .log().all();

        studentLeaderRequestSpec = RestAssured.given().accept(ContentType.JSON)
                .header("Authorization", getTokenByRole("student leader"))
                .log().all();

        responseSpec = expect().statusCode(200)
                .contentType(ContentType.JSON)
                .logDetail(LogDetail.BODY);

    }

    @AfterAll
    public static void close(){
        //it resets the info we set above
        reset();
    }

    public static ResponseSpecification getDynamicResponseSpec(Integer expectedStatusCode, ContentType contentType){
                return expect().statusCode(expectedStatusCode)
                        .contentType(contentType)
                        .logDetail(LogDetail.ALL);

    }

    public static ResponseSpecification userCheck(String firstName, String lastName){
        return expect().body("firstName", Matchers.is(firstName))
                .body("lastName",Matchers.is(lastName))
                .logDetail(LogDetail.ALL);

    }

    public static RequestSpecification userRequestSpec(String role){

        return RestAssured.given().accept(ContentType.JSON)
                .header("Authorization", getTokenByRole(role))
                .log().all();
    }

    public static String getTokenByRole(String role){

        if(role.equalsIgnoreCase("teacher")){
            String teacherToken = given().accept(ContentType.JSON)
                    .queryParam("email", ConfigurationReader.getProperty("teacher_email"))
                    .queryParam("password", ConfigurationReader.getProperty("teacher_password"))
                    .when().get("/sign")
                    .then().statusCode(200).extract().jsonPath().getString("accessToken");
            return "Bearer " + teacherToken;
        }else if(role.equalsIgnoreCase("student member")){
            String studentMemberToken = given().accept(ContentType.JSON)
                    .queryParam("email", ConfigurationReader.getProperty("team_member_email"))
                    .queryParam("password", ConfigurationReader.getProperty("team_member_password"))
                    .when().get("/sign")
                    .then().statusCode(200).extract().jsonPath().getString("accessToken");
            return "Bearer " + studentMemberToken;
        }else if(role.equalsIgnoreCase("student leader")){
            String studentLeaderToken = given().accept(ContentType.JSON)
                    .queryParam("email", ConfigurationReader.getProperty("team_leader_email"))
                    .queryParam("password", ConfigurationReader.getProperty("team_leader_password"))
                    .when().get("/sign")
                    .then().statusCode(200).extract().jsonPath().getString("accessToken");
            return "Bearer " + studentLeaderToken;
        }else{
            throw new IllegalStateException();
        }

    }

}
