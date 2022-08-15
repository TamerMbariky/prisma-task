package org.example.prismatask.service;

import org.example.prismatask.db.WordsCleanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.example.prismatask.dto.PrismaTaskConstants.INPUT_NULL_OR_EMPTY;
import static org.example.prismatask.helpers.StringSortHelper.countSort;

@Service
public class PrismaTaskService {
    protected Logger LOG = LoggerFactory.getLogger(getClass());

    private WordsCleanRepository wordsCleanRepository;

    @Autowired
    public PrismaTaskService(WordsCleanRepository wordsCleanRepository) {
        this.wordsCleanRepository = wordsCleanRepository;
    }


    /**
     *
     * @param inputWord The parameter to work on
     * 1) sort the word letters using count sort
     * 2) find if our permutation map has any key matching the permutation
     * 3) return the list of permutations if found
     * @return List of permutations
     * @throws Exception if input word is not alphabetic
     */
    public List<String> getPermutations(String inputWord) throws Exception {

        if (inputWord == null || inputWord.isEmpty()) {
            LOG.error(INPUT_NULL_OR_EMPTY);
            //System.err.println(INPUT_NULL_OR_EMPTY);
            throw new Exception(INPUT_NULL_OR_EMPTY);
        }
        LOG.info("get permutations of \"{}\"", inputWord);
        String sortedInputWord = countSort(inputWord);
        LOG.info("\"{}\" sorted is \"{}\"", inputWord, sortedInputWord);
        List<String> permutationsList = wordsCleanRepository.getPermutations(sortedInputWord);
        int permutationsNumber = permutationsList.size() - 1 > 0 ? permutationsList.size() - 1 : 0;
        LOG.info("{} permutations of \"{}\"",permutationsNumber , inputWord);
        //BUG FIX ,The remove was done on the list inside the map ,
        // so the change was deleting the inputWord from the map
        List<String> toReturn = new ArrayList<>(permutationsList);
        toReturn.remove(inputWord);
        return toReturn;
    }


    public int getDictionaryWordsNumber(){
        return wordsCleanRepository.getWordsNumber();
    }
}
