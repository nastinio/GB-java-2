package ru.nastinio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainExtraTask {

    public static void main(String[] args) {

        //Дополнительное задание по подсчету количества гласных
        String[] words = {"apple", "avocado", "grapes", "lemon", "mango", "AAA", "bbb", null, ""};
        getNumberVowelsListWords(words);

    }

    //Проходит по массиву и подсчитывает количество гласных в каждой строке
    public static int[] getNumberVowelsListWords(String[] words) {
        int[] result = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            result[i] = getNumberVowels(words[i]);
            System.out.println("Гласных в '" + words[i] + "': " + result[i]);
        }
        return result;
    }

    //Подсчитывает количество гласных в одном слове
    public static int getNumberVowels(String input) {
        int count = 0;
        if (input == null) {
            return count;
        }

        Pattern pattern = Pattern.compile("[aeiouyAEIOUY]");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            count++;
        }

        return count;
    }
}
