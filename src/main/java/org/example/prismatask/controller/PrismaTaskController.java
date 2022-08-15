package org.example.prismatask.controller;

import org.example.prismatask.dto.InputError;
import org.example.prismatask.dto.SimilarResponse;
import org.example.prismatask.dto.StatsResponse;
import org.example.prismatask.service.PrismaTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        SimilarResponse similarResponse = new SimilarResponse(similar);
        int end = (int) System.nanoTime();
        // adds the processing time to the average
        statsResponse.addProcessingTime(end-start);
        return similarResponse;
    }



    @GetMapping(value="/stats",produces = MediaType.APPLICATION_JSON_VALUE)
    public StatsResponse stats() {
        synchronized (statsResponse) {
            return statsResponse;
        }
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(Exception ex,
                                         HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (ex instanceof NullPointerException) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.badRequest().body(new InputError(ex.getMessage()));
    }
}
