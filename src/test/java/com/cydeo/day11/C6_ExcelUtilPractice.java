package com.cydeo.day11;

import com.cydeo.utilities.ExcelUtil;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class C6_ExcelUtilPractice {

    @Test
    public void test1() {
        //How to use excelUtil file ?
        //it accepts two arguments
        //Argument 1: location of the file(path)
        //Argument 2: sheet that we want to open
        ExcelUtil vytrackFile = new ExcelUtil("src/test/resources/Vytracktestdata.xlsx", "QA3-short");

        List<Map<String, String>> dataList = vytrackFile.getDataList();

        for (Map<String, String> eachRow : dataList) {
            System.out.println(eachRow);
        }

    }

}
