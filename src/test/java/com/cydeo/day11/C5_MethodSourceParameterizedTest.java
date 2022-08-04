package com.cydeo.day11;

import com.cydeo.utilities.ExcelUtil;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class C5_MethodSourceParameterizedTest {

    public static List<String> getNames(){
        //you can get value from anywhere almost anytype and return to your test
        //DB
        //Excel
        //other APIs

        List<String> strings = Arrays.asList("Parvin", "Nasim", "mohamad", "Nadir", "Saim", "Gurhan", "Murodil");
        return strings;
    }

    @ParameterizedTest
    @MethodSource("getNames")
    public void testPrintNames(String name){

        System.out.println("name = " + name);
    }


    public static List<Map<String,String>> getExcelData(){
        //get your file object
        ExcelUtil vytrackFile = new ExcelUtil("src/test/resources/Vytracktestdata.xlsx","QA3-all");
        //return sheet as a alist of map
        return vytrackFile.getDataList();

    }

    @ParameterizedTest
    @MethodSource("getExcelData")
    public void excelParamTest(Map<String,String> userInfo){

        System.out.println("FirstName: "+userInfo.get("firstname"));
        System.out.println("LastName: "+userInfo.get("lastname"));

    }


}
