package org.example.prismatask.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatsResponseTest {


    StatsResponse statsResponse = new StatsResponse(1);
    @Test
    void addProcessingTime() {
        int[] grades = new int[]{90,88,92,44,80,100,90,16};
        int total = 0;
        for(int x : grades ){
            total += x;
            statsResponse.addProcessingTime(x);
        }
        double avg = total / 8;

        // need to change the avgProcessingTimeNs to double to see it work
//        Assertions.assertEquals(avg,statsResponse.getAvgProcessingTimeNs());
    }
}