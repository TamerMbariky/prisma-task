package org.example.prismatask.dto;

public class StatsResponse {
    private int totalWords;
    private int totalRequests;
    private int avgProcessingTimeNs;

    public StatsResponse() {

    }

    public StatsResponse(int totalWords) {
        this.totalWords = totalWords;
    }


    public int getTotalWords() {
        return totalWords;
    }

    public int getTotalRequests() {
        return totalRequests;
    }

    public int getAvgProcessingTimeNs() {
        return avgProcessingTimeNs;
    }

    public void addProcessingTime(int requestProcessingTimeNs){
        //synchronized so multitasking will not corrupt the calculations
        synchronized (this){
            int averageMultiTotalReqPlusCurrentPros=((totalRequests*avgProcessingTimeNs) + requestProcessingTimeNs);
            totalRequests++;
            avgProcessingTimeNs = averageMultiTotalReqPlusCurrentPros/totalRequests;
        }
    }
}
