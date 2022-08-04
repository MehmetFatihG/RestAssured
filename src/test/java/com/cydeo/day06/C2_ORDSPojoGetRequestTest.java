package com.cydeo.day06;

import com.cydeo.pojo.Employee;
import com.cydeo.pojo.Link;
import com.cydeo.pojo.Region;
import com.cydeo.pojo.Regions;
import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class C2_ORDSPojoGetRequestTest extends HRTestBase {

    @Test
    public void regionTest(){


        JsonPath jsonPath = given()
                .when()

                .get("/regions")
                .then().statusCode(200)
                .extract().jsonPath();

        Region region = jsonPath.getObject("items[0]", Region.class);
        System.out.println("region = " + region);
        System.out.println(region.getRegion_name());
        System.out.println("region.getLinks().get(0).getHref() = " +
                region.getLinks().get(0).getHref());
        System.out.println("region.getRegionId() = " + region.getRegionId());

        Link link = region.getLinks().get(0);
        System.out.println("link.getHref() = " + link.getHref());
    }

    @Test
    public void employeeGet(){

        Employee employee = given().accept(ContentType.JSON)
                .when()
                .get("/employees")
                .then().statusCode(200)
                .extract().jsonPath().getObject("items[0]", Employee.class);

        System.out.println("employee = " + employee);

    }

    @Test
    public void regionPojoTest(){

        //send a get request to regions
        Regions regions1 = given()
                .accept(ContentType.JSON)
                .when()
                .get("/regions")
                .then().statusCode(200)
                .extract().response().as(Regions.class);

        for (Region eachRegion : regions1.getItems()) {
            System.out.println(eachRegion);
        }

        //verify that regions names "Europe" ,"Americas" , "Asia", "Middle East and Africa"
        //verify that region ids are 1,2,3,4
        List<Integer> regionsId = new ArrayList<>();
        List<String> regionsNames = new ArrayList<>();

        for (Region eachRegion : regions1.getItems()) {
            regionsId.add(eachRegion.getRegionId());
            regionsNames.add(eachRegion.getRegion_name());
        }

        MatcherAssert.assertThat(regionsNames,Matchers.containsInAnyOrder("Europe" ,"Americas" , "Asia", "Middle East and Africa"));
        MatcherAssert.assertThat(regionsId,Matchers.containsInAnyOrder(1,2,3,4));

        //verify that count is 4
        MatcherAssert.assertThat(regions1.getCount(),Matchers.is(4));

        //try to use pojo as much as possible
        //ignore non used fields

    }

}
