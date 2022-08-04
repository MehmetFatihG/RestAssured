package com.cydeo.day05;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


public class C1_HamcrestMatchersIntro {

    @Test
    public void simpleTest(){

        MatcherAssert.assertThat(5+5, Matchers.is(10));
        MatcherAssert.assertThat(5+5, Matchers.equalTo(10));
        MatcherAssert.assertThat(5+5,Matchers.is(Matchers.equalTo(10)));

        MatcherAssert.assertThat(5+5,Matchers.not(9));
        MatcherAssert.assertThat(5+5,Matchers.is(Matchers.not(9)));
        MatcherAssert.assertThat(5+5, Matchers.is(Matchers.not(Matchers.equalTo(9))));

        MatcherAssert.assertThat(5+5,Matchers.greaterThan(9));
        MatcherAssert.assertThat(5+5,Matchers.greaterThanOrEqualTo(9));

    }

    @Test
    public void stringHamcrest(){

        String text = "he is learning hamcrest";
        MatcherAssert.assertThat(text,Matchers.is("he is learning hamcrest"));
        MatcherAssert.assertThat(text, Matchers.startsWith("he"));
        MatcherAssert.assertThat(text, Matchers.endsWith("crest"));
        MatcherAssert.assertThat(text,Matchers.containsString("is learning"));

    }

    @Test
    public void testCollection(){

        List<Integer> listOfNumbers = Arrays.asList(1,4,5,6,32,54,66,77,45,23);

        //check size of the list
        MatcherAssert.assertThat(listOfNumbers,Matchers.hasSize(10));
        MatcherAssert.assertThat(listOfNumbers,Matchers.hasItem(77));
        MatcherAssert.assertThat(listOfNumbers,Matchers.hasItems(77,54,23));
        MatcherAssert.assertThat(listOfNumbers,Matchers.everyItem(Matchers.greaterThan(0)));




    }

}
