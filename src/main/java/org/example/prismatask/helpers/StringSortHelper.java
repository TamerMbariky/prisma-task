package org.example.prismatask.helpers;

import static org.example.prismatask.dto.PrismaTaskConstants.INPUT_NOT_ALPHA_ERR;

public class StringSortHelper {


    // TIME COMPLEXITY O(N)
    // SPACE COMPLEXITY O(N + K) -- O(N) as k is a know = 26
    public static String countSort(String word) throws Exception {
        int[] alphabet = new int[26]; // create array with the number of english alphabet letters


        // TIME COMPLEXITY O(N)
        for (char letter : word.toCharArray()) {
            //for each letter in the word, increase its index in alphabet array by 1 for each time
            //its read in the word
            Integer letterNumber = getLetterNumber(letter);
            alphabet[letterNumber]++;
        }



        //add margins between every alphabet letter as the number of times each letter appear
        //e.g if the letter b appears 5 times , and letter c appears two times,
        // the index of the letter b will have the value 5 , on the other hand c will have 2+5
        // 5 indicating there will be 5 before it,
        // if the letter d appears 4 times then the value of its index will be 4 + 7
        // indicating there is 7 letters appearing before it
        // the letters that have 0 appearances will have the total of the last letter appearing before it
        // TIME COMPLEXITY O(N)
        addMargins(alphabet);


        // create an array for new sorted word
        // same length as the original word
        char[] sortedString = new char[word.length()];


        // for each letter in the original word,
        // get the value from its index in alphabet array ,
        // treat the value as the index for the new sorted array and place the letter in that index
        // decrease the value by 1 , as one letter is already sorted
        // TIME COMPLEXITY O(N)
        for (char letter : word.toCharArray()) {
            Integer letterNumber = getLetterNumber(letter);
            int newLetterIndex = alphabet[letterNumber]-1;
            alphabet[letterNumber]--;
            sortedString[newLetterIndex] = letter;
        }


        return String.valueOf(sortedString);
    }

    private static Integer getLetterNumber(char letter) throws Exception {
        Integer letterIndex = null;
        if (letter >= 'A' && letter <= 'Z') {
            letterIndex = letter - 'A';
        }else
        if (letter >= 'a' && letter <= 'z') {
            letterIndex = (int) letter - 'a';
        }else {
            System.err.println(INPUT_NOT_ALPHA_ERR);
            throw new Exception(INPUT_NOT_ALPHA_ERR);
        }
        return letterIndex;
    }

    private static void addMargins(int[] alphabet) {
        int addition = 0;
        for(int i =0 ; i<26 ; i++){
            //add the margin between the two unique sorted members
            alphabet[i] += addition;
            addition = alphabet[i];
        }
    }
}
