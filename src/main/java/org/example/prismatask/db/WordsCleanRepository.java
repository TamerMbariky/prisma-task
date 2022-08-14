package org.example.prismatask.db;

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

import static org.example.prismatask.dto.PrismaTaskConstants.NO_PERMUTATIONS_FOUND_ERR;
import static org.example.prismatask.helpers.StringSortHelper.countSort;

@Repository
public class WordsCleanRepository {

    HashMap<String, List<String>> permutationsToWordListMap = new HashMap<>();
    int wordsNumber = 0;

    public WordsCleanRepository() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("words_clean.txt");

        try (InputStreamReader streamReader =
                     new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            String line;
            while ((line = reader.readLine()) != null) {
                if(line == null || line.isEmpty() ){
                    continue;
                }
                String sortedLettersWord = countSort(line);
                if (permutationsToWordListMap.containsKey(sortedLettersWord)){
                    permutationsToWordListMap.get(sortedLettersWord).add(line);
                } else {
                    permutationsToWordListMap.put(sortedLettersWord,new ArrayList<>(Arrays.asList(line)));
                }
                wordsNumber ++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public List<String> getPermutations(String sortedWord) throws Exception {
        if(sortedWord.contains(sortedWord)) {
            return permutationsToWordListMap.get(sortedWord);
        }else {
            System.err.println(NO_PERMUTATIONS_FOUND_ERR);
            throw new Exception(NO_PERMUTATIONS_FOUND_ERR);
        }
    }


    public int getWordsNumber(){
        return wordsNumber;
    }

}

