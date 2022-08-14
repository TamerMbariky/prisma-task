package org.example.prismatask.controller;

import org.example.prismatask.dto.SimilarResponse;
import org.example.prismatask.dto.StatsResponse;
import org.example.prismatask.service.PrismaTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/")
public class PrismaTaskController {

    private PrismaTaskService prismaTaskService;

    private StatsResponse statsResponse;

    @Autowired
    public PrismaTaskController(PrismaTaskService prismaTaskService) {
        this.prismaTaskService = prismaTaskService;
    }

    @PostConstruct
    private void init(){
        statsResponse = new StatsResponse(prismaTaskService.getDictionaryWordsNumber());
    }



    @GetMapping(value="/similar",produces = MediaType.APPLICATION_JSON_VALUE)
    public SimilarResponse getSimilar(@RequestParam("word")String word) throws Exception {
        int start = (int) System.nanoTime();
        List<String> similar = prismaTaskService.getPermutations(word);
        similar.remove(word);
        SimilarResponse similarResponse = new SimilarResponse(similar);
        int end = (int) System.nanoTime();
        statsResponse.addProcessingTime(end-start);
        return similarResponse;
    }


    @GetMapping(value="/stats",produces = MediaType.APPLICATION_JSON_VALUE)
    public StatsResponse stats() {
        return statsResponse;
    }
}
