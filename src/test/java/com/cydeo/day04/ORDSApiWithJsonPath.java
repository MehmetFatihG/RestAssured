package com.cydeo.day04;
import com.cydeo.utilities.HRTestBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiWithJsonPath extends HRTestBase {

    @Test
    public void test1() {

        Response response = get("/countries");

        //get the second country name with json path
        JsonPath jsonPath = response.jsonPath();

        String countryName = jsonPath.get("items[1].country_name");
        System.out.println("countryName = " + countryName);

        //get all country Id's
        List<String> countries = jsonPath.getList("items.country_id");
        for (String eachId : countries) {
            System.out.println("eachCountry = " + eachId);
        }

        //get all country names where their region id is equal to 2
        List<String> countryNames = jsonPath
                .getList("items.findAll {it.region_id == 2}.country_name");

        System.out.println(countryNames);


    }

    @Test
    public void test2(){

        Response response = given().queryParam("limit", 107).when().get("/employees");

        //get me all email employees who is working as IT_PROG
        List<String> emails = response.jsonPath().getList("items.findAll {it.job_id == \"IT_PROG\"}.email");
        System.out.println(emails);

        //get me first name of employees who is making more than 10000
        List<String> firstNames = response.jsonPath().getList("items.findAll {it.salary>10000" +
                "}.first_name");
        System.out.println(firstNames);

        //get the max salary first_name
        String maxSalaryName = response.jsonPath().get("items.max {it.salary}.first_name");
        System.out.println(maxSalaryName);

    }




}






