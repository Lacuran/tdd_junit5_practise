package pl.qaaacademy.todo.item.stringgenerator;

import java.util.Random;

public class RandomGeneratedString {
    public static String randomGeneratedString(int length) {

        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = length;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }
}
