package com.cydeo.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanTestBase {

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we dont need to type each http method.
        baseURI = "http://52.200.34.122:8000";

        String dbUrl = "jdbc:oracle:thin:@52.200.34.122:1521:XE";
        String dbUsername = "SP";
        String dbPassword = "SP";

        //DB_Utils.createConnection(dbUrl,dbUsername,dbPassword);
    }

    @AfterAll
    public static void tearDown(){

        //DB_Utils.destroy();
    }

}
