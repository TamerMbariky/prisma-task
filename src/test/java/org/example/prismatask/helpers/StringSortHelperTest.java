package org.example.prismatask.helpers;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringSortHelperTest {

    @Test
    void countSort() throws Exception {
        for(int i=0 ; i<= 1000 ; i++){
            String testString = RandomStringUtils.randomAlphabetic(i+1);
            List<String> stringList = new ArrayList<>();
            for(char letter : testString.toCharArray()){
                stringList.add(String.valueOf(letter));
            }
            Collections.sort(stringList, String.CASE_INSENSITIVE_ORDER);
            String sorted = String.join("",stringList);
            String countSortedString = StringSortHelper.countSort(testString);
            Assertions.assertEquals(sorted.toLowerCase(),countSortedString.toLowerCase());
        }
    }
}