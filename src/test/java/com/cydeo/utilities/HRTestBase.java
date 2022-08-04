package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class HRTestBase {

    @BeforeAll
    public static void init(){
        //save base url inside the this variable so that we don't need to type in every test cases
        RestAssured.baseURI = "http://52.200.34.122:1000/ords/hr";
    }

}
