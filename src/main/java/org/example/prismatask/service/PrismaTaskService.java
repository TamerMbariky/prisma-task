package org.example.prismatask.service;

import org.example.prismatask.db.WordsCleanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.prismatask.helpers.StringSortHelper.countSort;

@Service
public class PrismaTaskService {

    private WordsCleanRepository wordsCleanRepository;

    @Autowired
    public PrismaTaskService(WordsCleanRepository wordsCleanRepository) {
        this.wordsCleanRepository = wordsCleanRepository;
    }


    public List<String> getPermutations(String inputWord) throws Exception {
        String sortedInputWord = countSort(inputWord);
        return wordsCleanRepository.getPermutations(sortedInputWord);
    }


    public int getDictionaryWordsNumber(){
        return wordsCleanRepository.getWordsNumber();
    }
}
