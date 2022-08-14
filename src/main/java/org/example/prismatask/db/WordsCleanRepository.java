package org.example.prismatask.db;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.example.prismatask.helpers.StringSortHelper.countSort;

@Repository
public class WordsCleanRepository {

    protected Logger LOG = LoggerFactory.getLogger(getClass());

    //Map holding sorted string of one permutation and a list of all permutations of the string
    HashMap<String, List<String>> permutationsToWordListMap = new HashMap<>();
    int wordsNumber = 0;


    /***
     * Goes Over db provided words_clean.txt (line by line)
     * if line is empty we skip it
     * 1)sort the line by letters
     * 2)check if permutationsToWordListMap contains the sorted presentation we add the new word to its list
     * 3) if not , we add a new list including the word
     *
     * This happens only on startup to reduce cpu usage when an endpoint is executed
     * @throws Exception
     */
    public WordsCleanRepository() throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("words_clean.txt");
        LOG.info("Started reading file");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line == null || line.isEmpty()) {
                    continue;
                }
                String sortedLettersWord = countSort(line);
                if (permutationsToWordListMap.containsKey(sortedLettersWord)) {
                    permutationsToWordListMap.get(sortedLettersWord).add(line);
                } else {
                    permutationsToWordListMap.put(sortedLettersWord, new ArrayList<>(List.of(line)));
                }
                wordsNumber++;
            }
            LOG.info("Finished reading file");
            LOG.info("Words count is " + wordsNumber);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            throw e;
        }

    }


    public List<String> getPermutations(String sortedWord) throws Exception {
        LOG.info("Check if \"{}\" exists in map", sortedWord);
        if(permutationsToWordListMap.containsKey(sortedWord)) {
            LOG.info("\"{}\" exists in map", sortedWord);
            return permutationsToWordListMap.get(sortedWord);
        }else {
            LOG.info("\"{}\" does not exist in map", sortedWord);
            //if no permutations found , empty list is returned
            // an error can be thrown if we needed
            return new ArrayList<>();

            //System.err.println(NO_PERMUTATIONS_FOUND_ERR);
            //throw new Exception(NO_PERMUTATIONS_FOUND_ERR);
        }
    }


    public int getWordsNumber(){
        return wordsNumber;
    }

}

