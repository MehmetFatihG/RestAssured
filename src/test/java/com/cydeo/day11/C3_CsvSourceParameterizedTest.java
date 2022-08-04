package com.cydeo.day11;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import oracle.ucp.proxy.annotation.ProxyAccess;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class C3_CsvSourceParameterizedTest {

    // Test first number + second number = third number
//            1, 3 , 4
//            2, 3 , 5
//            8, 7 , 15
//            10, 9 , 19
    @ParameterizedTest
    @CsvSource({"1, 3 , 4", "2, 3 , 5", "8, 7 , 15", "10, 9 , 19"})
    public void testAddition(int num1, int num2, int sum){

        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
        System.out.println("sum = " + sum);

        MatcherAssert.assertThat(num1 + num2, Matchers.is(sum));


    }

    @ParameterizedTest
    @CsvSource({"NY, New York", "CO, Denver", "VA, Fairfax", "VA, Arlington", "MA, Boston",
            "NY, New York", "MD, Annapolis"})
    public void zipCodeMultiInputTest(String state,String city){
        // Write a parameterized test for this request
        // GET https://api.zippopotam.us/us/{state}/{city}
    /*
        "NY, New York",
        "CO, Denver",
        "VA, Fairfax",
        "VA, Arlington",
        "MA, Boston",
        "NY, New York",
        "MD, Annapolis"
     */
        JsonPath jsonPath = RestAssured.given().accept(ContentType.JSON)
                .pathParam("state", state)
                .pathParam("city", city)
                .when().get("https://api.zippopotam.us/us/{state}/{city}")
                .then().statusCode(200)
                .extract().jsonPath();

        //verify place name contains your city name
        MatcherAssert.assertThat(jsonPath.getList("places.'place name'"),
                Matchers.everyItem(Matchers.containsStringIgnoringCase(city)));

        //print number of places for each request
        int countOfPlaces = jsonPath.getList("places").size();
        System.out.println("countOfPlaces = " + countOfPlaces);


    }

}
